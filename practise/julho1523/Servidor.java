package practise.julho1523;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static ServerSocket ss;
    public static void main(String[] args) throws IOException{
        ss = new ServerSocket();

        HPC hpc = new HPCimp(10);

        while(true){
            Socket client = ss.accept();
        }
    }
}
