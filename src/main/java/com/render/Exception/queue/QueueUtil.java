package com.render.exception.queue;


import org.apache.commons.mail.HtmlEmail;

/**
 * @author linzukon
 * 
 * 作用：一条消息队列的管理
 * 
 * */
public class QueueUtil {
	
	private static LinkQueue<HtmlEmail> queue;
	
	private QueueUtil() {}

	public static LinkQueue<HtmlEmail> getQueue() {
		if (queue == null) queue = new LinkQueue<HtmlEmail>();
		return queue;
	}
	
}
