package guiao8;

import java.io.IOException;

import guiao8.TaggedConnection.Frame;

public class Demultiplexer {
    private TaggedConnection t;
    public Demultiplexer(TaggedConnection conn) {
        this.t = conn;
    }

    public void start() {

    }

    public void send(Frame frame) throws IOException {
        
    }

    public void send(int tag, byte[] data) throws IOException {
        
    }

    public byte[] receive(int tag) throws IOException, InterruptedException {
        return null;
    }
    
    public void close() throws IOException {
        
    }
}
