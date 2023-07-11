package bigWork.Server;

import javax.annotation.processing.Filer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class Server {


    static ArrayList<Socket> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        //读取本地文件的用户信息
        InputStreamReader isr = new InputStreamReader(new FileInputStream("..\\Pracitce\\src\\userInfo.txt"));
        Properties userinfo = new Properties();
        userinfo.load(isr);

        //创建服务的对象，让客户端连接
        ServerSocket startServer = new ServerSocket(10000);

        while (true) {
            //开启连接，等待客户端
            Socket accept = startServer.accept();

            //开启一条线程，判断用户账号信息
            new Thread(new ServerThread(userinfo,accept)).start();
        }







    }

}
