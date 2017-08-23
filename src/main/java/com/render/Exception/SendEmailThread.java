package com.render.exception;

import com.render.exception.queue.QueueUtil;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Created by zukon on 2015/12/19.
 */
public class SendEmailThread implements Runnable {

    @Override
    public void run() {
        String msgId = null;
        for (;;) {
            if (Thread.currentThread().isInterrupted()) break;
            HtmlEmail email = QueueUtil.getQueue().remove();
            if (email == null) {
                try {
                    Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
                continue;
            }
            try {
                msgId = email.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }
            System.out.println("发送邮件：msgId=" + msgId);
        }
    }
}
