package testes.recurso_2024;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TransferServer {
    public void main() {
        ServerSocket server_socket = new ServerSocket(12345);
        Managerr manager = new ManagerImp();

        while (true) {
            Socket client_socket = server_socket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client_socket.getOutputStream()));

            new Thread(() -> {
                String command;
                while ((command = reader.readLine()) != null) {
                    String[] parts = command.split(" ");
                    
                    if (parts[0].equals("newTransfer")) {
                        try {
                            String id = manager.newTransfer();
                            writer.write(id + "\n");
                            writer.flush();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    else if (parts[0].equals("enqueue")) {
                        Transfer current_transfer = manager.getTransfer(parts[1]);
                        byte[] data = parts[2].getBytes();
                        current_transfer.enqueue(data);
                    }

                    else if (parts[0].equals("dequeue")) {
                        Transfer current_transfer = manager.getTransfer(parts[1]);
                        byte[] data = current_transfer.dequeue();
                        writer.write(new String(data) + "\n");
                        writer.flush();
                    }

                    else {
                        writer.write("Invalid command\n");
                        writer.flush();
                    }
                }
            }).start();
        }
    }
}
