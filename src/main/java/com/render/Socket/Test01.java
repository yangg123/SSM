package com.render.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * InetAddress��
 */
public class Test01 {

	public static void main(String[] args) throws UnknownHostException {

		InetAddress address = InetAddress.getLocalHost();
		System.out.println("计算机名" + address.getHostName());
		System.out.println("IP地址" + address.getHostAddress());
	}

}
