package testes.EE_2023;

import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class HpcIm implements Hpc {
    private int free_cores;
    private int num_tasks;
    private Map<Integer, Long> timestamps;

    private ReentrantLock lock = new ReentrantLock();
    private Condition FreeCoresCondition = lock.newCondition();

    public HpcIm(int cores) {
        this.free_cores = cores;
        this.num_tasks = 0;
        this.timestamps = new java.util.HashMap<Integer, Long>();
    }

    @Override
    public int inicio(int ncores) {
        lock.lock();
        try {
            while (ncores > free_cores) {
                FreeCoresCondition.await();
            }
            free_cores -= ncores;
            return num_tasks++;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return -1;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void fim(int tarefa, long tempo) {
        lock.lock();
        timestamps.put(tarefa, tempo);
        free_cores += 1;
        FreeCoresCondition.signalAll();
        lock.unlock();
    }

    @Override
    public Map<Integer, Long> historia() {
        return new java.util.HashMap<Integer, Long>(timestamps);
    }
}
