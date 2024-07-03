package guiao4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Barrier {
    private int N;
    private int counter;
    private ReentrantLock lock;
    private Condition cond;
    public Barrier(int N){
        this.N = N;
        this.counter = 0;
        this.lock = new ReentrantLock();
        this.cond = lock.newCondition();
    }

    public void await() throws InterruptedException{
        this.lock.lock();
        this.counter++;
        cond.signalAll();

        while(this.counter < this.N){
            cond.await();
        }

        try{
            return;
        }
        finally{
            this.lock.unlock();
        }
    }
}
