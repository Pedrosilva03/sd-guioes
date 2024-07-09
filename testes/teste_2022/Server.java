package testes.teste_2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            Votacao votacao = new VotacaoImp(10, 5);

            while (true) {
                Socket socket = serverSocket.accept();

                new Thread(() -> {
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        int identidade;

                        String command;
                        while ((command = in.readLine()) != null) {
                            String[] parts = command.split(" ");

                            if (parts[0].equals("verifica")) {
                                identidade = Integer.parseInt(parts[1]);
                                out.println(votacao.verifica(identidade));
                            } 
                            
                            else if (parts[0].equals("esperaPorCabine")) {
                                out.println(votacao.esperaPorCabine());
                            } 
                            
                            else if (parts[0].equals("vota")) {
                                int escolha = Integer.parseInt(parts[1]);
                                votacao.vota(escolha);
                            }
                            
                            else if (parts[0].equals("desocupaCabine")) {
                                int i = Integer.parseInt(parts[1]);
                                votacao.desocupaCabine(i);
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
