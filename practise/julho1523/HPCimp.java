package practise.julho1523;

import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;

public class HPCimp implements HPC{
    private int maxCores;
    private int usedCores;
    private Map<Integer, Long> timestamps;
    private Map<Integer, Tarefa> queue;
    private ReentrantLock lock;
    private Condition cond;
    private int taskCounter;
    
    public HPCimp(int cores){
        this.timestamps = new HashMap<Integer, Long>();
        this.queue = new HashMap<Integer, Tarefa>();
        this.maxCores = cores;
        this.usedCores = 0;
        this.lock = new ReentrantLock();
        this.cond = lock.newCondition();
        this.taskCounter = 0;
    }

    @Override
    public int inicio(int ncores){
        this.lock.lock();
        try{
            while(!(this.usedCores + ncores <= this.maxCores)){
                cond.await();
            }
            Tarefa t = new Tarefa(this.taskCounter++, ncores);
            queue.put(t.getID(), t);
            this.usedCores += t.getCores();
            return t.getID();
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
            return -1;
        }
        finally{
            this.lock.unlock();
        }
    }

    @Override
    public void fim(int tarefa, long tempo){
        this.lock.lock();
        this.timestamps.put(tarefa, tempo);
        this.usedCores -= this.queue.remove(tarefa).getCores();
        this.lock.unlock();
    }

    @Override
    public Map<Integer, Long> historia(){
        return new HashMap<Integer, Long>(timestamps);
    }
}

class Tarefa{
    private int id;
    private int cores;

    public Tarefa(int id, int cores){
        this.id = id;
        this.cores = cores;
    }

    public int getID(){
        return this.id;
    }

    public int getCores(){
        return this.cores;
    }
}
