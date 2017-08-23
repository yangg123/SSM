package com.render.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author gacl
 * 定义SEI(WebService EndPoint Interface(终端))
 */
//使用@WebService注解标注WebServiceI接口
@WebService
public interface WebServiceI {

    //使用@WebMethod注解标注WebServiceI接口中的方法
    @WebMethod
    String sayHello(String name);

    @WebMethod
    String save(String name,String pwd);
}