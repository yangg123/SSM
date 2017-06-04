package com.ssm.controler;

import com.render.kit.PathKit;
import com.render.kit.QRCodeUtil;
import com.render.kit.QiniuKit;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yg on 17/2/15.
 */

@Controller
@RequestMapping("/")
public class IndexController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    // 七牛token
    @RequestMapping(value="/qiniuToken")
    public String qiniuToken() {
        String token = QiniuKit.getToken();
        Map<String, Object> map = new HashMap<>();
        map.put("uptoken", token);
        return null;
    }

    // 统一文件上传接口
    @RequestMapping(value="doUpload",method=RequestMethod.POST)
    public String doUploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {

//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String basePath = request.getContextPath();
        basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+basePath+"/";

        String servletPath = request.getServletPath();
        String realPath = request.getServletContext().getRealPath("/");

        log.info("\n---- request base path is: {} ", basePath);
        log.info("\n---- 请求接口servlet路径: {} ", servletPath);
        log.info("\n---- 在服务器上的路径是: {} " , realPath);

        if (!file.isEmpty()) {
            log.info("Process file: {} ",file.getOriginalFilename());
        }
        // 1: mac 本机路径
        //FileUtils.copyInputStreamToFile(file.getInputStream(), new File("/Users/yg/Desktop/",file.getOriginalFilename()));
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath,file.getOriginalFilename()));
        log.info("\n/*---------- 文件上传成功-------------*/");

//        String url = "http://"+ PropKit.getProperty("cdn")+"" + destFileName;
//        Map<String, Object> map = new HashMap<>();
//        map.put("uptoken", token);

        return "uploadSuccess";
    }


//    // 上传文件测试页面
//    @RequestMapping("upload")  // 第二种，返回类型为ModelAndView
//    public ModelAndView showUploadPage(HttpServletRequest request){
//        return new ModelAndView("upload_file_test_page");
//    }

    //上传文件测试页面
    @RequestMapping(value="/upload")
    public String showUploadPage(){
        return "upload_file_test_page";
    }

    //生成二维码
    @RequestMapping(value="/createQrcode")
    public String createQrcode() {

        System.out.println("---- webroot is :" + PathKit.getWebRootPath());
        String qrcodeString = "wwww.baidu.com";
        int logoPx = 200;
        File logoFile = new File(PathKit.getWebRootPath() + "/public/404/img/aboutLogo@2x.png");
        File qrcodeFile = new File(PathKit.getWebRootPath() + "/public/404/img/below.png");
        boolean success = QRCodeUtil.getQrCodeImgByContents(qrcodeString, 675, logoPx, "jpg", logoFile, qrcodeFile);
        if (!success) {
            System.out.println("生成二维码失败---------------");
        }

        return "hello";
    }
}
