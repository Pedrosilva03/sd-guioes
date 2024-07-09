package testes.recurso_2022;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            Reuniao reuniao = new ReuniaoImp(10);

            while (true) {
                Socket socket = serverSocket.accept();

                new Thread(() -> {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                        String value;
                        while ((value = in.readLine()) != null) {
                            String[] parts = value.split(" ");
                            String command = parts[0];

                            if (command.equals("PARTICIPA")) {
                                int lista = Integer.parseInt(parts[1]);
                                reuniao.participa(lista);
                            } 
                            
                            else if (command.equals("ABANDONEI")) {
                                int lista = Integer.parseInt(parts[1]);
                                reuniao.abandona(lista);
                            }
                            
                            else {
                                out.write("Comando inválido\n");
                                out.flush();
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
