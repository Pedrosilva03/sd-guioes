import java.util.*;
import java.util.concurrent.locks.*;

public class ControladorImp implements Controlador{
    public static final int MAX_KARTS = 10;
    public static final int MAX_VOLTAS = 10;
    private int[] voltas = new int[MAX_KARTS];
    private Map<Integer, Boolean> karts = new HashMap<Integer, Boolean>(); // false -> livre, true -> ocupado
    private Map<Integer, Boolean> preparados = new HashMap<Integer, Boolean>();

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Condition condition2 = lock.newCondition();

    void init(){
        for(int i = 0; i < MAX_KARTS; i++){
            voltas[i] = 0;
            karts.put(i, false);
            preparados.put(i, false);
        }
    }

    @Override
    public int reserva() {
        lock.lock();
        try{
            for(int i = 0; i < MAX_KARTS; i++){
                if(!karts.get(i)){
                    karts.put(i, true);
                    return i;
                }
            }
            return -1;
        }finally{
            lock.unlock();
        }
    }

    @Override
    public void preparado(int kart) {
        preparados.put(kart, true);

        if (!preparados.containsValue(false)) {
            condition.signalAll();
        }

        while (preparados.containsValue(false)) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void completaVolta(int kart) {
        lock.lock();
        try{
            voltas[kart]++;

            if (voltas[kart] == MAX_VOLTAS) {
                condition2.signalAll();
            }
        }finally{
            lock.unlock();
        }
    }

    @Override
    public int[] voltasCompletas() {
        lock.lock();
        try{
            return voltas;
        }finally{
            lock.unlock();
        }
    }

    @Override
    public int vencedor() {
        lock.lock();
        try{
            while (Arrays.stream(voltas).anyMatch(volta -> volta < MAX_VOLTAS)) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int vencedor = 0;
            for (int i = 1; i < MAX_KARTS; i++) {
                if (voltas[i] > voltas[vencedor]) {
                    vencedor = i;
                }
            }

            return vencedor;
        }finally{
            lock.unlock();
        }
    }
}
