package com.ssm.controler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.ssm.pojo.Page;
import com.ssm.pojo.Shop;
import com.github.pagehelper.PageInfo;
import com.render.core.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ssm.model.User;
import com.ssm.service.IUserService;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private static List<String> datas = new ArrayList<String>();
	{
		datas.add("ajax1");
		datas.add("ajax2");
		datas.add("ajax3");
		datas.add("ajax4");
		datas.add("james1");
		datas.add("james2");
		datas.add("kobe");
	}

	private static List<User> accounts = new ArrayList<User>();
	{
		accounts.add(new User());
		accounts.add(new User());

		User ac1 = accounts.get(0);
		User ac2 = accounts.get(1);

		ac1.setUserName("Robin");
		ac1.setPassword("123123");

		ac2.setUserName("Lucy");
		ac2.setPassword("123456");
	}

	@Resource //@Autowired
	private IUserService userService; // <==>UserService service = (UserService)SpringContextUtil.getBean("userServiceImpl");

//	被@ModelAttribute注释的方法会在此controller每个方法执行前被执行，因此对于一个controller映射多个URL的用法来说，要谨慎使用。
//	@ModelAttribute("accounts")
//	public List<User> getAccounts() {
//		System.out.println("getAccounts" + accounts);
//		return accounts;
//	}

	@RequestMapping("/showUser") // 第一种，返回类型为String
	public String toIndex(HttpServletRequest request,Model model) {

		String cdn = this.propKit.getProperty("cdn");
		logger.info("\n----cdn is {}",cdn);

		HashMap<String, Object> parameters = getRequestMapSingle(request);
		PageInfo<User> page = this.userService.queryByPage("干干", 1, 20);

		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		if (user == null) {
			throw new RuntimeException("用户不存在////");
		}

		model.addAttribute("user", user);
		System.out.print("\n-------进入controller---");
		return "showUser";
	}

	@RequestMapping("getList")  // 第二种，返回类型为ModelAndView
	public ModelAndView getList(HttpServletRequest request){

		Map<String, Object> map = new HashMap<String, Object>();
		String pageNow = request.getParameter("pageNow");

		Page page = null;
		List<User> userlist = new ArrayList<User>();
		int totalCount = userService.getAllUsersCount();
		if(pageNow != null){
			page = new Page(totalCount, Integer.parseInt(pageNow));
			userlist = userService.getAllUsersList(page);
		} else {
			page = new Page(totalCount,1);
			userlist = userService.getAllUsersList(page);
		}
		map.put("page", page);
		map.put("userlist", userlist);
		return new ModelAndView("userlist",map);
	}

	@RequestMapping(value = "addUser")
	public ModelAndView addUsers(HttpServletRequest request,User user){
		userService.addUsers(user);
		return getList(request);
	}

	@RequestMapping(value = "createUser", method = RequestMethod.GET)
	public ModelAndView createUser() {
		User user = this.userService.getUserById(1);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("createSuccess");
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		System.out.println("index");
		return "modelAttribute";
	}

	@RequestMapping(value = "/handle11")
	public ModelAndView handle11(
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam("age") int age) {
		Map<String,Object> map = new HashMap<>();
		map.put("userName",userName);
		map.put("age", age);
		return new ModelAndView("requestParam",map);
	}

	// 直接输出 json 字符串
	@RequestMapping(value = "{shopName}",method = RequestMethod.GET)
	public @ResponseBody Shop getShopInJSON(@PathVariable String shopName)
	{
		System.out.print("...... 跑这里了......");
		Shop sp = new Shop();
		sp.setShopName(shopName);
		sp.setStaffName(new String[]{"yanggao", "yangyi"});
		return sp;
	}

	// json上传下载测试
	@RequestMapping(value="/jsonTest")
	public String showUploadPage(){
		return "json";
	}


	@RequestMapping(value = "/saveJson", method = {RequestMethod.POST})
	public @ResponseBody String saveUser(@RequestBody List<User> users) {

		logger.info(">>>>>>>接受的Json是 {}",JSON.toJSONString(users));
		return JSON.toJSONString(users);
	}



	// 构造下拉框的数据
	public List<String> getData(String keyword){
		List <String> list = new ArrayList<>();
		for (String data : datas) {
			if (data.contains(keyword))
			list.add(data);
		}
		return list;
	}

	@RequestMapping(value = "/search", method = {RequestMethod.GET})
	public @ResponseBody String searchsss(HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		List<String> listData = getData(keyword);
		return JSON.toJSONString(listData);
	}
}