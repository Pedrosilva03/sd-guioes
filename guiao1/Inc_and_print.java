package guiao1;

import java.util.ArrayList;
import java.util.List;

class Inc_and_print{
    private static final int N = 10;
    public static void main(String[] args) throws InterruptedException{
        List<Thread> t = new ArrayList<>();
        for(int i = 0; i < N; i++){
            Thread newt = new Thread(new Increment());
            t.add(newt);
            newt.start();
        }
        for(Thread tt: t)
            tt.join();

        System.out.println("FIM");
    }
}

class Increment implements Runnable {
    public void run(){
        final long I=100;
        
        for (long i = 0; i < I; i++)
            System.out.println(i);
    }
}