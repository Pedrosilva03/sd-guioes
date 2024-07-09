package guiao8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TaggedConnection implements AutoCloseable{
    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;

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
    }

    public void send(Frame frame) throws IOException {

    }

    public void send(int tag, byte[] data) throws IOException {

    }

    public Frame receive() throws IOException {
        
    }

    public void close() throws IOException {

    }
}
