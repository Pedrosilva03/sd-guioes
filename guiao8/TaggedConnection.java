package guiao8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

public class TaggedConnection implements AutoCloseable{
    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;

    private ReentrantLock read;
    private ReentrantLock write1;
    private ReentrantLock write2;

    public static class Frame {
        public final int tag;
        public final byte[] data;
        public Frame(int tag, byte[] data) {
            this.tag = tag;
            this.data = data;
        }
    }

    public TaggedConnection(Socket socket) throws IOException {
        this.s = socket;
        this.dis = new DataInputStream(s.getInputStream());
        this.dos = new DataOutputStream(s.getOutputStream());

        this.read = new ReentrantLock();
        this.write1 = new ReentrantLock();
        this.write2 = new ReentrantLock();
    }

    public void send(Frame frame) throws IOException {
        this.write1.lock();
        try{
            this.dos.writeInt(frame.tag);
            this.dos.flush();
            this.dos.writeInt(frame.data.length);
            this.dos.flush();
            this.dos.write(frame.data);
        }
        finally{
            this.write1.unlock();
        }
    }

    public void send(int tag, byte[] data) throws IOException {
        this.write2.lock();
        try{
            this.dos.writeInt(tag);
            this.dos.flush();
            this.dos.writeInt(data.length);
            this.dos.flush();
            this.dos.write(data);
        }
        finally{
            this.write2.unlock();
        }
    }

    public Frame receive() throws IOException {
        this.read.lock();
        try{
            int tag = this.dis.readInt();
            int length = this.dis.readInt();
            byte[] b = new byte[length];
            this.dis.readFully(b);
            return new Frame(tag, b);
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
