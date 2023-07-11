package bigWork.Chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatThread implements Runnable{


    private Socket connect;


    public ChatThread (Socket connect) {
        this.connect = connect;
    }



    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            while (true) {
                System.out.println(br.readLine());
            }




        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
