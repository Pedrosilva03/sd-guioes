import java.util.*;
import java.util.concurrent.locks.*;

public class ManagerImp implements Manager {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public static final int R = 10;
    private int num_raids = 0;
    private Queue<RaidImp> waitingQueue = new ArrayDeque<>();
    private RaidImp currentRaid = new RaidImp();
    private int maxMin = 0;

    @Override
    public Raid join(String name, int minPlayers) throws InterruptedException {
        lock.lock();
        try {
            RaidImp raid = currentRaid;
            raid.playersList.add(name);
            maxMin = Math.max(maxMin, minPlayers);
            if (raid.playersList.size() == maxMin) {
                raid.init();
                tryStart(raid);
                maxMin = 0;
                currentRaid = new RaidImp();
                condition.signalAll();
            } else {
                while (currentRaid == raid)
                    condition.await();
            }
            return raid;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void tryStart(RaidImp raid) {
        if (num_raids < R) {
            num_raids += 1;
            raid.start();
        } else {
            waitingQueue.add(raid);
        }
    }

    @Override
    public void finished() {
        lock.lock();
        num_raids -= 1;
        RaidImp raid = waitingQueue.poll();
        if (raid != null)
            tryStart(raid);
        lock.unlock();
    }
}
