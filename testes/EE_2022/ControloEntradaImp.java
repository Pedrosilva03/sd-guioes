package testes.EE_2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ControloEntradaImp implements ControloEntrada {
    private int maxPassageiros;
    private int nPassageiros;
    private boolean entradaAberta;
    private List<String> bilhetesUsados;

    private ReentrantLock lock = new ReentrantLock();
    private Condition podeAbrir = lock.newCondition();
    private Condition podeFechar = lock.newCondition();

    public ControloEntradaImp(int maxPassageiros, int nPortas) {
        this.maxPassageiros = maxPassageiros;
        this.nPassageiros = 0;
        this.entradaAberta = false;
        this.bilhetesUsados = new ArrayList<>();
    }

    public void podeAbrirEntrada() {
        lock.lock();
        try {
            while (this.nPassageiros > 0 || !this.entradaAberta) {
                this.podeAbrir.await();
            }
            this.entradaAberta = true;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void saiuPassageiro() {
        lock.lock();
        try {
            if (this.nPassageiros > 0) {
                this.nPassageiros--;
                if (this.nPassageiros == 0) {
                    this.podeAbrir.signalAll();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void podeFecharEntrada() {
        lock.lock();
        try {
            while (this.nPassageiros < this.maxPassageiros) {
                this.podeFechar.await();
            }
            this.entradaAberta = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void entrouPassageiro(String bilhete) {
        lock.lock();
        try {
            if (this.entradaAberta && !this.bilhetesUsados.contains(bilhete)) {
                this.nPassageiros++;
                this.bilhetesUsados.add(bilhete);

                if (this.nPassageiros == this.maxPassageiros) {
                    this.podeFechar.signalAll();
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
