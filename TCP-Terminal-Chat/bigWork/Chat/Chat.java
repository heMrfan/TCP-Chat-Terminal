package bigWork.Chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Chat {
    public static void main(String[] args) throws IOException {





        Scanner sc = new Scanner(System.in);

        //创建Socket对象，和服务器进行连接
        Socket connect = new Socket("127.0.0.1",10000);
        System.out.println("连接成功");


        while (true) {
            System.out.println("聊天室 version= 2.0");
            System.out.println("========欢迎来到范先生的聊天室么么哒========");
            System.out.println("1:登录");
            System.out.println("2:注册");
            String choose = sc.next();

            if (choose.equals("1")) {

                //把选择提交给服务器进行判断
                BufferedWriter wChoose = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                wChoose.write(choose);
                wChoose.newLine();
                wChoose.flush();

                System.out.println("请输入用户名");
                String username = sc.next();
                System.out.println("请输入密码");
                String password = sc.next();

                StringBuilder sb  = new StringBuilder();
                sb.append("username=" + username+"&password="+password);
                //把用户输入的信息写出到服务器
                wChoose.write(sb.toString());
                wChoose.newLine();
                wChoose.flush();




                BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                String feedback = br.readLine();

                if (feedback.equals("5")) {
                    System.out.println("开始聊天");
                    new Thread(new ChatThread(connect)).start();
                    talk(wChoose);
                }else if (feedback.equals("4")) {
                    System.out.println("用户不存在");
                }else  if (feedback.equals("3")) {
                    System.out.println("密码错误");
                }



            }else if (choose.equals("2")) {
                BufferedWriter wChoose1 = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                wChoose1.write(choose);
                wChoose1.newLine();
                wChoose1.flush();

                System.out.println("请输入用户名");
                String username = sc.next();
                System.out.println("请输入密码");
                String password = sc.next();

                StringBuilder sb = new StringBuilder();
                if (!username.matches("(?!\\d*+$)[a-zA-Z]{6,18}")) {
                    System.out.println("用户名格式有误：6~18位，纯字母，不能有数字或其他符号");
                    continue;
                }else if (!password.matches("[A-Za-z](?![a-zA-Z]*+$)[\\d]{2,7}")) {
                    System.out.println("密码格式有误：密码长度3~8位。第一位必须是小写或者大小的字母，后面必须是纯数字");
                    continue;
                }



                sb.append("username=" + username+"&password="+password);
                wChoose1.write(sb.toString());
                wChoose1.newLine();
                wChoose1.flush();



                BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                String feedback = br.readLine();
                if (feedback.equals("a")) {
                    System.out.println("当前用户已经存在");
                }


                


            }else {
                System.out.println("输入的选项无效");
            }
        }

    }

    public static void talk (BufferedWriter bw) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("开始聊天");
        while (true) {
            String text = sc.next();
            bw.write(text);
            bw.newLine();
            bw.flush();

        }

    }





}
