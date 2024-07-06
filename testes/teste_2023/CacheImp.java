package testes.teste_2023;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CacheImp implements Cache {
    private int MAX_KEYS;
    private Map<Integer, byte[]> cache = new HashMap<Integer, byte[]>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public CacheImp(int MAX_KEYS) {
        this.MAX_KEYS = MAX_KEYS;
    }

    @Override
    public void put(int key, byte[] value) {
        lock.lock();
        try {
            while (cache.size() >= MAX_KEYS) {
                condition.await();
            }
            cache.put(key, value);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public byte[] get(int key) {
        lock.lock();
        try {
            byte[] value = cache.get(key);
            return value;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void evict(int key) {
        lock.lock();
        try {
            cache.remove(key);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
