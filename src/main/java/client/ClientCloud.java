package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import com.google.gson.Gson;

public class ClientCloud
{
    static void main(String[] args)
    {
        final String ipFer="192.168.0.126";
        final String ipServer="192.168.0.137";
        final String ipGio="192.168.0.131";

        Gson gson = new Gson();
        User user = new User("Fer",30);
        String json = gson.toJson(user);
        try{
            Scanner input = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName(ipServer);

            Socket socket = new Socket(ip, 2555);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            while(true)
            {
                String msg = dis.readUTF();
                System.out.println(json);
                String toSend = input.nextLine();
                dos.writeUTF(json);
                if(toSend.equals("Exit")){
                    socket.close();
                    dis.close();
                    dos.close();
                }
            }
        }catch(Exception e){
            System.out.println("Client Error:"+e);
        }
    }
}
