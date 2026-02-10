package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;



public class ClientCloud {

    public static void main(String[] args) {

        final String ipServer = "192.168.100.18";




        try {
            InetAddress ip = InetAddress.getByName(ipServer);
            Socket socket = new Socket(ip,2555);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            Thread manda = new Thread(new Manda(dos));
            manda.start();

            Thread recibe = new Thread(new Recibe(dis));
            recibe.start();

        } catch (IOException e) {
            System.out.println("Client Error: " + e);
        }
    }
}
