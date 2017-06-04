package com.render.Socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yg on 2017/3/14.
 * 服务器程序
 * Socket 的基本概念
 *　　1．建立连接
 *　　当需要建立网络连接时，必须有一台机器运行一个程序，随时等候连接，而另一端的程序这对其发出连接请求。这一点同电话系统类似——必须有一方拨打电话，而另一方必须等候电话连通。建立连接的过程为：
 *　　（1）现在服务器端生成一个 ServerSocket 实例对象，随时监听客户端的连接请求。
 *　　（2）当客户端需要连接时，相应地要生成一个 Socket 实例对象，并发出连接请求，其中 host参数指明该主机名，port#参数指明该主机端口号。
 *　　（3）服务器端通过 accept()方法接收到客户端的请求后，开辟一个接口与之进行连接，并生成所需的 I/O 数据流。
 *　　（4）客户端和服务器端的通信都是通过一对 InputStream 和 OutputStream 进行的，通信结束后，两端分别关闭对应的 Socket 接口。
 */
public class TestServer {

    public static void main(String[] args){
        try {
            ServerSocket ss=new ServerSocket(8488);
            while(true){
                Socket s=ss.accept();
                OutputStream os=s.getOutputStream();
                DataOutputStream dos=new DataOutputStream(os);
//                dos.writeUTF("服务器向客户端发送信息 "+s.getInetAddress()+":"+s.getPort());
                dos.writeUTF("发送第二条信息:" + ss.getLocalPort());
                dos.flush();
                dos.close();
                s.close();
            }
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }
}
