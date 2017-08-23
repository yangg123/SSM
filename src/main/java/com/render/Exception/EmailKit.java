package com.render.exception;

import com.render.exception.queue.QueueUtil;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Created by zukon on 2015/12/19.
 */
public class EmailKit {

    public static void sendHtmlEmail(String host, String from, String username, String password, String to, String subject, String htmlMsg) {
        try {
            /* new一个HtmlEmail发送对象 */
            HtmlEmail email = new HtmlEmail();
            email.setAuthentication(username, password);
            email.setHostName(host);
            email.addTo(to, from);
            email.setFrom(from);
            email.setSubject(subject);
            // 注意，发送内容时，后面这段会让中文正常显示，否则乱码
            email.setCharset("GB2312");
            email.setHtmlMsg(htmlMsg); /* 邮件内容 */
            email.setTextMsg("据说是不支持html时显示这个。。。");
            // 放入消息队列
            QueueUtil.getQueue().add(email);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }


}
