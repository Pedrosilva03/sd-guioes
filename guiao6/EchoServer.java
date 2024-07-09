package guiao6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

public class EchoServer {

    static int soma = 0;
    static int counter = 0;
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(12345);

            while (true) {
                Socket socket = ss.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());

                Thread t = new Thread(() -> {
                    try{
                        String line;
                        while ((line = in.readLine()) != null) {
                            if(line.equals("")){
                                out.println(soma / counter);
                                out.flush();
                            }
                            else{
                                soma(Integer.parseInt(line));
                                out.println(soma);
                                out.flush();
                            }
                        }

                        socket.shutdownOutput();
                        socket.shutdownInput();
                        socket.close();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                });
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void soma(int line){
        lock.lock();
        soma += line;
        counter++;
        lock.unlock();
    }
}
