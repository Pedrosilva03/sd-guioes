package guiao4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Agreement {
    private int N;
    private int counter1;
    private int counter2;
    private int max;
    private ReentrantLock lock;
    private Condition cond1;
    private Condition cond2;
    public Agreement(int N){
        this.N = N;
        this.counter1 = 0;
        this.counter2 = 0;
        this.max = 0;
        this.lock = new ReentrantLock();
        this.cond1 = lock.newCondition();
        this.cond2 = lock.newCondition();
    }

    public int propose(int choice) throws InterruptedException{
        this.lock.lock();
        this.counter1++;
        this.cond1.signalAll();

        while(this.counter1 < this.N){
            this.cond1.await();
        }

        this.lock.unlock();
        this.lock.lock();

        if(choice > this.max){
            this.max = choice;
        }

        this.counter2++;
        this.cond2.signalAll();

        while(this.counter2 < this.N){
            this.cond2.await();
        }

        try{
            return this.max;
        }
        finally{
            this.lock.unlock();
        }
    }
}
