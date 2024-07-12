package testes.EE_2020;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(3000);
            SalaDeEspera sala = new SalaEsperaImp();

            while (true) {
                Socket client = server.accept();

                new Thread(() -> {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                        String line;
                        while ((line = in.readLine()) != null) {
                            String[] parts = line.split(" ");
                            String command = parts[0];

                            if (command.equals("espera")) {
                                String nome = parts[1];
                                boolean result = sala.espera(nome);
                                out.write(result ? "true\n" : "false\n");
                                out.flush();
                            } 
                            
                            else if (command.equals("desiste")) {
                                String nome = parts[1];
                                sala.desiste(nome);
                            } 
                            
                            else if (command.equals("atende")) {
                                String nome = sala.atende();
                                out.write(nome + "\n");
                                out.flush();
                            }

                            else {
                                out.write("Comando inválido\n");
                                out.flush();
                            }
                        }

                        client.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }

            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
