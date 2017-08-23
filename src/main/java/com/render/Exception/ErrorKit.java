package com.render.exception;

import org.apache.commons.mail.HtmlEmail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by zukon on 2016/1/7.
 */
public class ErrorKit {

    public static void sendErrorEmail(Exception e) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        String[] lines = new String(baos.toByteArray()).split("\r\n");
        StringBuilder exceptionTrace = new StringBuilder();
        for (String line : lines) {
            exceptionTrace.append(line).append("<br/>");
        }

        EmailKit.sendHtmlEmail("smtp.163.com",
                "ebeicn@163.com", "ebeicn@163.com",
                "111qqq", "1094962443@qq.com",
                "【阿里云测试服务器】发生异常！",
                "<html><body>" + e.getMessage() + "<hr/><br/>" + exceptionTrace.toString() + "</body></html>");
    }

    public static void main(String[] args) throws Exception{

        HtmlEmail email = new HtmlEmail();
        email.setAuthentication("yanggao199005@163.com", "123456ww");
        email.setHostName("smtp.163.com");
        email.addTo("1094962443@qq.com", "yanggao199005@163.com");
        email.setFrom("yanggao199005@163.com");
        email.setSubject("【阿里云测试服务器】发生异常！");
        email.setCharset("GB2312");
        email.setHtmlMsg("<html><body>" + "aaaa" + "<hr/><br/>" + "</body></html>"); /* 邮件内容 */
        email.setTextMsg("据说是不支持html时显示这个。。。");
        email.send();
    }
}


