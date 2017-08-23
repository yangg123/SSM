package com.render.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * ʹ��URL��ȡ��ҳ����
 */
public class Test03 {
	public static void main(String[] args) {
		try {

			URL url = new URL("http://www.baidu.com");

			InputStream is = url.openStream();
			//���ֽ�������ת��Ϊ�ַ������
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			//Ϊ�ַ���������ӻ���
			BufferedReader br = new BufferedReader(isr);
			String data = br.readLine();//��ȡ����
			while (data != null) {//ѭ����ȡ����
				System.out.println(data);//�������
				data = br.readLine();
			}
			br.close();
			isr.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
