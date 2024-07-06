package testes.teste_2023;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server_socket = new ServerSocket(12345);
            Cache cache = new CacheImp(10);

            while (true) {
                Socket socket = server_socket.accept();

                new Thread(() -> {
                    try {
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                        while (input.readObject() != null) {
                            String inputString = (String) input.readObject();
                            String[] parts = inputString.split(" ");

                            if (parts[0].equals("put")) {
                                cache.put(Integer.parseInt(parts[1]), parts[2].getBytes());
                                output.writeObject("OK");
                            } 
                            
                            else if (parts[0].equals("get")) {
                                byte[] value = cache.get(Integer.parseInt(parts[1]));
                                output.writeObject(value);
                            } 
                            
                            else if (parts[0].equals("exit")) {
                                server_socket.close();
                                break;
                            } 
                            
                            else {
                                output.writeObject("Invalid command");
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
