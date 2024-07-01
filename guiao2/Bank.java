package guiao2;

import java.util.concurrent.locks.ReentrantLock; // Exercício 1

public class Bank {

    //private static final ReentrantLock lock = new ReentrantLock(); // Lock ao nivel do banco (menos eficiente)

    private static class Account {
        private static final ReentrantLock lock = new ReentrantLock(); // Lock ao nivel das contas (mais eficiente)
        private int balance;
        Account(int balance) { this.balance = balance; }
        int balance() { return balance; }
        boolean deposit(int value) {
            lock.lock();
            balance += value;
            try{
                return true;
            }
            finally{
                lock.unlock();
            }
        }
        boolean withdraw(int value) {
            lock.lock();
            try{
                if (value > balance)
                    return false;
                balance -= value;
                return true;
            }
            finally{
                lock.unlock();
            }
        }
    }

    // Bank slots and vector of accounts
    private int slots;
    private Account[] av; 

    public Bank(int n) {
        slots=n;
        av=new Account[slots];
        for (int i=0; i<slots; i++) av[i]=new Account(0);
    }

    // Account balance
    public int balance(int id) {
        if (id < 0 || id >= slots)
            return 0;
        return av[id].balance();
    }

    // Deposit
    public boolean deposit(int id, int value) {
        //lock.lock();
        try{
            if (id < 0 || id >= slots)
                return false;
            return av[id].deposit(value);
        }
        finally{
            //lock.unlock();
        }
    }

    // Withdraw; fails if no such account or insufficient balance
    public boolean withdraw(int id, int value) {
        //lock.lock();
        try{
            if (id < 0 || id >= slots)
                return false;
            return av[id].withdraw(value);
        }
        finally{
            //lock.unlock();
        }
    }

    public boolean transfer(int from, int to, int value){
        //lock.lock();
        try{
            if(!withdraw(from, value)){
                return false;
            }
            if(!deposit(to, value)){
                deposit(from, value); // Caso de dar erro ao depositar, devolve o dinheiro à conta "from"
                return false;
            }
            return true;
        }
        finally{
            //lock.unlock();
        }
    }

    public int totalBalance(){
        int total = 0;
        for(Account a: av){
            total += a.balance();
        }
        return total;
    }
}
