package practise.janeiro2424;

import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {
    public static void main(String[] args) throws IOException{
        ServerSocket s = new ServerSocket();

        Manager m = new ManagerImpl();
    }
}
