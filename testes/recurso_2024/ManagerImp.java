package testes.recurso_2024;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ManagerImp implements Managerr {
    private static final int server_capacity = 16 * 1024 * 1024; // 16M bytes
    private Map<String, TransferImp> transfers;

    private ReentrantLock lock = new ReentrantLock();
    private Condition freeServerCondition = lock.newCondition();

    public ManagerImp() {
        this.transfers = new HashMap<>();
    }

    public String newTransfer() throws InterruptedException {
        lock.lock();
        try {
            while ((transfers.size()*16*1024) + (16*1024) >= server_capacity) {
                freeServerCondition.await();
            }
            String id = String.valueOf(transfers.size());
            transfers.put(id, new TransferImp());
            return id;
        } finally {
            lock.unlock();
        }
    }

    public Transfer getTransfer(String identifier) {
        return transfers.get(identifier);
    }
}
