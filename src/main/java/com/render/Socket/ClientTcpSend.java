package com.render.socket;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.Socket;

/**
 * Created by yg on 2017/3/23.
 */
public class ClientTcpSend {

    public static void main(String[] args) throws IOException {


//        System.out.println(this.getClass().getResource("").getPath());
//        System.out.println(this.getClass().getResource("/").getPath());
        System.out.println(System.getProperty("user.dir"));

        Resource res = new ClassPathResource("resource/ApplicationContext.xml");
        InputStream input = res.getInputStream();
        File file = res.getFile();

        int length = 0;
        byte[] sendByte = null;
        Socket socket = null;
        DataOutputStream dout = null;
        FileInputStream fin = null;
        try {
            try {
//                socket = new socket();
//                socket.connect(new InetSocketAddress("127.0.0.1", 33456),10 * 1000);
//                dout = new DataOutputStream(socket.getOutputStream());
//
////                1、资源文件路径(默认在resources文件夹里面)
//                file = ResourceUtils.getFile("classpath:config.inc.php");
//                System.out.print("file path is :" + file.getPath());
//
////                2、文件路径，绝对路径
////                File file = new File("/Users/yg/Desktop/config.inc.php");
//                fin = new FileInputStream(file);
//                sendByte = new byte[1024];
//                dout.writeUTF(file.getName());
//                while((length = fin.read(sendByte, 0, sendByte.length))>0){
//                    dout.write(sendByte,0,length);
//                    dout.flush();
//                }
            } catch (Exception e) {

            } finally{
                if (dout != null)
                    dout.close();
                if (fin != null)
                    fin.close();
                if (socket != null)
                    socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
