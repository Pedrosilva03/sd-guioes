package guiao5;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Warehouse {
    private Map<String, Product> map =  new HashMap<String, Product>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private class Product { int quantity = 0; }

    private Product get(String item) {
        Product p = map.get(item);
        if (p != null) return p;
        p = new Product();
        map.put(item, p);
        return p;
    }

    public void supply(String item, int quantity) {
        this.lock.lock();
        Product p = get(item);
        p.quantity += quantity;
        cond.signalAll();
        this.lock.unlock();
    }

    // Errado se faltar algum produto...
    public void consume(Set<String> items){
        try{
            for (String s : items){
                while(!map.containsKey(s)) cond.await();
                get(s).quantity--;
            }
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

}
