package testes.EE_2021;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ControloVacinasImp implements ControloVacinas {
    private int NUM;
    private int nfrascos;
    private int contadorFrasco;
    private int pessoasEspera;

    private ReentrantLock lock = new ReentrantLock();
    private Condition podeVacinar = lock.newCondition();
    private Condition temFrascos = lock.newCondition();

    public ControloVacinasImp(int NUM) {
        this.NUM = NUM;
        this.nfrascos = 0;
        this.contadorFrasco = 0;
        this.pessoasEspera = 0;
    }

    public void pedirParaVacinar() {
        lock.lock();
        try {
            this.pessoasEspera++;

            if (this.pessoasEspera == this.NUM) {
                this.podeVacinar.signalAll();
            }

            while (this.nfrascos == 0 || this.pessoasEspera < this.NUM) {
                
                if (this.nfrascos == 0) this.temFrascos.await();
                
                else this.podeVacinar.await();
            }

            this.pessoasEspera--;

            if (this.contadorFrasco == this.NUM) {
                this.nfrascos--;
                this.contadorFrasco = 0;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void fornecerFrascos(int frascos) {
        lock.lock();
        try {
            this.nfrascos += frascos;
            this.temFrascos.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
