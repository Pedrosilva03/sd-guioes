package practise.julho1523;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static ServerSocket ss;
    public static void main(String[] args) throws IOException{
        ss = new ServerSocket();

        HPC hpc = new HPCimp(10);

        while(true){
            Socket client = ss.accept();

            new Thread(() -> {
                try {
                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

                    while(true){
                        String pedido = (String)ois.readObject();
                        String[] pedidoFormat = pedido.split(" ");

                        if(pedidoFormat[0].equals("INICIO")){
                            int coresClient = Integer.parseInt(pedidoFormat[1]);
                            int res = hpc.inicio(coresClient);
                            if(res == -1){
                                oos.writeObject("ERRO");
                            }
                            else{
                                oos.writeObject(new StringBuilder("RESERVADO ").append(res).toString());
                            }
                        }
                        else if(pedidoFormat[0].equals("FIM")){
                            hpc.fim(Integer.parseInt(pedidoFormat[1]), Integer.parseInt(pedidoFormat[2]));
                        }
                        else if(pedidoFormat[0].equals("HISTORIA")){
                            oos.writeObject(hpc.historia());
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }).start();
        }
    }
}
