import java.util.List;

interface Raid {
    List <String> players();
    void waitStart() throws InterruptedException;
    void leave();
}
