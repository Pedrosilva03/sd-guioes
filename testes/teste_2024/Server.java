import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            Manager manager = new ManagerImp();
            ServerSocket server = new ServerSocket(12345);

            while (true) {
                Socket socket = server.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                new Thread(() -> {
                    try {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(" ");
                            String command = parts[0];
                            String response = "";
                
                            if (command.equals("join")) {
                                String name = parts[1];
                                int minPlayers = Integer.parseInt(parts[2]);
                                Raid raid = manager.join(name, minPlayers);
                                response = raid.toString();
                            }
                            
                            else if (command.equals("tryStart")) {
                                RaidImp raid = new RaidImp();
                                manager.tryStart(raid);
                                response = "OK";
                            }
                            
                            else if (command.equals("finished")) {
                                manager.finished();
                                response = "OK";
                            }

                            else if (command.equals("exit")) {
                                server.close();
                                break;
                            }
                
                            else {
                                response = "Invalid command";
                            }
                
                            writer.write(response + "\n");
                            writer.flush();
                        }
                
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } 
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}