package com.render.Socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by yg on 2017/3/14.
 * 客户端程序
 */
public class TestClient {
    public static void main(String[] args){
        try {
            Socket s = new Socket("127.0.0.1",8488);
            //从服务器端收到信息
            InputStream is=s.getInputStream();
            DataInputStream dis=new DataInputStream(is);
            System.out.println(dis.readUTF());
//            dis.close();
//            s.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
