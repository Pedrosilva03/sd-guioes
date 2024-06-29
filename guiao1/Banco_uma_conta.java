package guiao1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Banco_uma_conta {
    private static final int N = 10;
    private static final int I = 1000;
    private static final int V = 100;
    public static void main(String[] args) throws InterruptedException{
        Bank b = new Bank();
        List<Thread> t = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Thread newt = new Thread(() -> {
                for (int j = 0; j < I; j++) {
                    b.deposit(V);
                }
            });
            t.add(newt);
            newt.start();
        }
        for (Thread tt: t) tt.join();
        System.out.println(b.balance());
    }
}

class Bank {
    private static class Account {
        private int balance;
        Account(int balance) { this.balance = balance; }
        int balance() { return balance; }
        boolean deposit(int value) {
            balance += value;
            return true;
        }
    }

    private ReentrantLock lock = new ReentrantLock(); // Lock para o ex 3. Fix ao valor final do balance
  
    // Our single account, for now
    private Account savings = new Account(0);
  
    // Account balance
    public int balance() {
        return savings.balance();
    }
  
    // Deposit
    boolean deposit(int value) {
        try{
            lock.lock();
            return savings.deposit(value);
        }
        finally{
            lock.unlock();
        }
    }
}
