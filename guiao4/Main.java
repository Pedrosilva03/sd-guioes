package guiao4;

import java.util.Random;

public class Main {
    public static void main(String[] args){
        int N = 10;

        Agreement a = new Agreement(N);
        Barrier b = new Barrier(N);

        Random r = new Random();

        for(int i = 0; i < N; i++){
            Thread t = new Thread(() -> {
                try{
                    //b.await();
                    System.out.println(a.propose(r.nextInt()));
                }
                catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }
            });
            t.start();
        }
    }
}
