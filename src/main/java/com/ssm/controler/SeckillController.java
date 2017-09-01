package com.ssm.controler;


import com.ssm.pojo.Exposer;
import com.ssm.model.Seckill;
import com.ssm.pojo.SeckillExecution;
import com.ssm.pojo.Result;
import com.render.enums.SeckillStatEnum;
import com.ssm.service.SeckillService;
import com.render.exception.RepeatKillException;
import com.render.exception.SeckillCloseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by codingBoy on 16/11/28.
 */
@Component
@RequestMapping("/seckill")//url:模块/资源/{}/细分
public class SeckillController
{
    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model)
    {
        //获取列表页
        List<Seckill> list=seckillService.getSeckillList();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model)
    {
        if (seckillId == null)
        {
            return "redirect:/seckill/list";
        }

        Seckill seckill=seckillService.getById(seckillId);
        if (seckill==null)
        {
            return "forward:/seckill/list";
        }

        model.addAttribute("seckill",seckill);

        return "detail";
    }

    //ajax,json暴露秒杀接口的方法
    @RequestMapping(value = "/{seckillId}/exposer",
                    method = RequestMethod.GET,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result<Exposer> exposer(@PathVariable("seckillId") Long seckillId)
    {
        Result<Exposer> result;
        try {

            System.out.print("----------秒杀id 是:" + seckillId);
            Exposer exposer=seckillService.exportSeckillUrl(seckillId);
            result=new Result<Exposer>(true,exposer);
        } catch (Exception e) {
            e.printStackTrace();
            result=new Result<Exposer>(false,e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                            @PathVariable("md5") String md5,
                                            @CookieValue(value = "userPhone",required = false) Long phone)
    {

        System.out.print("\n------ 开始秒杀啦-----------" + seckillId + "md5:" + md5 + "phone" +phone);
        if (phone==null)
        {
            return new Result<SeckillExecution>(false,"未注册");
        }

        Result<SeckillExecution> result;
        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);
            return new Result<SeckillExecution>(true, execution);
        }catch (RepeatKillException e1)
        {
            System.out.print("\n------ 开始秒杀啦1-----------");
            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new Result<SeckillExecution>(true,execution);
        }catch (SeckillCloseException e2)
        {
            System.out.print("\n------ 开始秒杀啦2-----------");
            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new Result<SeckillExecution>(true,execution);
        }
        catch (Exception e)
        {
            System.out.print("\n------ 开始秒杀啦3-----------");
            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new Result<SeckillExecution>(true,execution);
        }
    }

    //获取系统时间
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public Result<Long> time()
    {
        Date now=new Date();
        return new Result<Long>(true,now.getTime());
    }
}























