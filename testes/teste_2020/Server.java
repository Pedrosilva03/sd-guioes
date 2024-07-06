package testes.teste_2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            ControloTrafegoAereo cta = new ControloTrafegoAereoImp(3);

            while (true) {
                Socket client = serverSocket.accept();

                new Thread(() -> {
                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        PrintWriter output = new PrintWriter(client.getOutputStream(), true);

                        String value;
                        while((value = input.readLine()) != null) {
                            String[] parts = value.split(" ");
                            String command = parts[0];

                            if (command.equals("PEDIR_DECOLAGEM")) {
                                int pista = cta.pedirParaDescolar();
                                output.println(pista);
                            } 
                            
                            else if (command.equals("PEDIR_ATERRAGEM")) {
                                int pista = cta.pedirParaAterrar();
                                output.println(pista);
                            } 
                            
                            else if (command.equals("DECOLAGEM")) {
                                int pista = Integer.parseInt(parts[1]);
                                cta.descolou(pista);
                            } 
                            
                            else if (command.equals("ATERRAGEM")) {
                                int pista = Integer.parseInt(parts[1]);
                                cta.aterrou(pista);
                            }

                            else {
                                output.println("Comando inválido");
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
