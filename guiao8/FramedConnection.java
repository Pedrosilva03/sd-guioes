package guiao8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

public class FramedConnection implements AutoCloseable{
    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ReentrantLock read;
    private ReentrantLock write;
    public FramedConnection(Socket s) throws IOException{
        this.s = s;
        this.dis = new DataInputStream(s.getInputStream());
        this.dos = new DataOutputStream(s.getOutputStream());
        this.read = new ReentrantLock();
        this.write = new ReentrantLock();
    }

    public void send(byte[] data) throws IOException {
        try{
            this.write.lock();
            this.dos.writeInt(data.length);
            this.dos.flush();
            this.dos.write(data);
            this.dos.flush();
        }
        finally{
            this.write.unlock();
        }
    }

    public byte[] receive() throws IOException {
        try{
            this.read.lock();
            int size = this.dis.readInt();
            byte[] b = new byte[size];
            this.dis.readFully(b);
            return b;
        }
        finally{
            this.read.unlock();
        }
    }

    public void close() throws IOException {
        this.s.shutdownInput();
        this.s.shutdownOutput();
        this.s.close();
    }
}
