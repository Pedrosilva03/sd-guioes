package testes.EE_2023;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server_socket = new ServerSocket(12345);
        Hpc hpc = new HpcIm(10);

        while (true) {
            Socket client_socket = server_socket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client_socket.getOutputStream()));

            new Thread(() -> {
                String command;
                try {
                    while ((command = reader.readLine()) != null) {
                        String[] parts = command.split(" ");

                        if (parts[0].equals("inicio")) {
                            int ncores = Integer.parseInt(parts[1]);
                            int task = hpc.inicio(ncores);
                            writer.write("RESERVADO\nID: " + task + "\n");
                            writer.flush();
                        } 
                        
                        else if (parts[0].equals("fim")) {
                            int task = Integer.parseInt(parts[1]);
                            long time = Long.parseLong(parts[2]);
                            hpc.fim(task, time);
                        } 
                        
                        else if (parts[0].equals("historia")) {
                            hpc.historia().forEach((task, time) -> {
                                try {
                                    writer.write(task + " " + time + "\n");
                                    writer.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
