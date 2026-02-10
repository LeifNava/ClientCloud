package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.Gson;

public class Manda implements Runnable {
    final private DataOutputStream dos;
    private boolean otro = true;
    final private Scanner scanner = new Scanner(System.in);
    final private Gson gson = new Gson();


    public Manda(DataOutputStream dos){
        this.dos = dos;
    }

    @Override
    public void run() {
        String mensaje;
        while (otro) {
            System.out.print("Mensaje: ");
            mensaje = scanner.nextLine();
            System.out.print("otro ? (true/false): ");
            otro = Boolean.parseBoolean(scanner.nextLine());
            User user = new User("Fer", 30, mensaje);
            String json = gson.toJson(user);

            try {
                dos.writeUTF(json);
                dos.flush();
            }
            catch (IOException e) {
                System.out.println("Conexión cerrada");
                break;
            }
        }
    }


}
