package testes.EE_2021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String args[]) {
        try {
            ServerSocket server = new ServerSocket(7777);
            int NUM = Integer.parseInt(args[0]);
            ControloVacinas cv = new ControloVacinasImp(NUM);

            while (true) {
                Socket socket = server.accept();
                
                new Thread(() -> {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                        String value;
                        while ((value = in.readLine()) != null) {
                            String[] parts = value.split(" ");
                            String command = parts[0];

                            if (command.equals("PEDIR")) {
                                cv.pedirParaVacinar();
                            } 
                            
                            else if (command.equals("FORNECER")) {
                                int frascos = Integer.parseInt(parts[1]);
                                cv.fornecerFrascos(frascos);
                            } 
                            
                            else {
                                out.write("Comando inválido\n");
                                out.flush();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}       
