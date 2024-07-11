package guiao4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Agreement {
    private int N;
    private int max;
    private ReentrantLock lock;
    private Barrier b1;
    private Barrier b2;
    public Agreement(int N){
        this.N = N;
        this.max = 0;
        this.lock = new ReentrantLock();
        this.b1 = new Barrier(this.N);
        this.b2 = new Barrier(this.N);
    }

    public int propose(int choice) throws InterruptedException{
        b1.await();

        this.lock.lock();

        if(choice > this.max){
            this.max = choice;
        }

        this.lock.unlock();

        b2.await();

        return this.max;
    }
}
