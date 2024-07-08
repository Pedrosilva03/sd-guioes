package practise.janeiro2424;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ManagerImpl implements Manager {
    private Random r = new Random();

    private static ReentrantLock lock = new ReentrantLock();
    public static Condition servercond = lock.newCondition();

    private HashMap<String, Transfer> transfers = new HashMap<>();
    private final int max_server = 16 * 1024 * 1024;
    private final int max_transfer = 16 * 1024;
    private int current_used = 0;

    @Override
    public String newTransfer() {
        lock.lock();
        while(current_used + max_transfer > max_server){
            try{
                servercond.await();
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        current_used += max_transfer;
        String id = String.valueOf(r.nextInt());
        Transfer t = new TransferImpl();
        transfers.put(id, t);
        return id;
    }

    @Override
    public Transfer getTransfer(String identifier) {
        return this.transfers.get(identifier);
    }
}
