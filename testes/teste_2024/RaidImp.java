import java.util.*;
import java.util.concurrent.locks.*;

class RaidImp implements Raid {
    int players = 0;
    List<String> playersList = new ArrayList<>();
    boolean started = false;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void init() {
        playersList = Collections.unmodifiableList(playersList);
        players = playersList.size();
    }

    public void start() {
        started = true;
        condition.signalAll();
    }

    @Override
    public List<String> players() {
        return playersList;
    }

    @Override
    public void waitStart() throws InterruptedException {
        lock.lock();
        try {
            while (!started) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void leave() {
        lock.lock();
        try {
            players -= 1;
            if (players == 0) {
                // finished();
            }
        } finally {
            lock.unlock();
        }
    }
}
