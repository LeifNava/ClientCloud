package client;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.IOException;


public class Recibe implements   Runnable {
    final private DataInputStream dis;
    final private Gson gson = new Gson();

    public Recibe(DataInputStream dis){
        this.dis = dis;
    }

    @Override
    public void run() {
        String mensaje;

        while(true){
            try {
                mensaje = dis.readUTF();
                User user = gson.fromJson(mensaje, User.class);
                System.out.printf("\nUser %s  Mensaje : %s",user.name, user.mensaje);
            }
            catch (IOException e ){
                System.out.println("Error");
                break;
            }
        }

    }

}

