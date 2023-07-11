package bigWork.Server;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class ServerThread implements Runnable{
    private Properties userinfo;
    private Socket accept;


    public ServerThread (Properties userinfo,Socket accept) {
        this.accept = accept;
        this.userinfo = userinfo;
    }

    @Override
    public void run() {
        try {
            //读取用户输入的信息
            BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));

            while (true) {
                //得到用户输入的选择
                String choose = br.readLine();



                if (choose.equals("1")) {
                    String userInfo = br.readLine();

                    String[] split = userInfo.split("&");
                    String name = split[0].split("=")[1];
                    String password = split[1].split("=")[1];

                    if (userinfo.containsKey(name) && userinfo.containsValue(password)) {
                        System.out.println("开始聊天");
                        determine ("5");
                        Server.arr.add(accept);
                        pritln(br,name);
                    }else if (userinfo.containsKey(name)) {
                        determine ("3");

                    }else {
                        determine ("4");
                    }
                }else if (choose.equals("2")) {
                    System.out.println("注册");

                    String userInfo = br.readLine();

                    String[] split = userInfo.split("&");
                    String name = split[0].split("=")[1];
                    String password = split[1].split("=")[1];

                    if (userinfo.containsKey(name)) {
                        determine("a");

                    }else {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("..\\Pracitce\\src\\userInfo.txt",true));

                        bw.write("\r\n" +name+"="+password);
                        bw.newLine();
                        bw.flush();
                        System.out.println("成功注册");
                    }




                }

            }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }






    public void pritln(BufferedReader bw,String name) throws IOException {
        while (true) {
            String message = bw.readLine();
            System.out.println(name + "发来了信息：" + message);
            for (Socket socket : Server.arr) {

                BufferedWriter outW = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                outW.write(name + "说:" + message);
                outW.newLine();
                outW.flush();
            }




        }

    }


    public void determine (String feedback) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        bw.write(feedback);
        bw.newLine();
        bw.flush();
    }



}
