package testes.teste_2020;

import java.util.concurrent.locks.Condition; // Add this import statement
import java.util.concurrent.locks.ReentrantLock;

public class ControloTrafegoAereoImp implements ControloTrafegoAereo {
    private int NUM_PISTAS;
    private int idAterrar;
    private int idDescolar;
    private int aterragens;
    private int descolagens;
    private boolean[] pistas;

    private ReentrantLock lock = new ReentrantLock();
    private Condition pista_libertada = lock.newCondition();

    public ControloTrafegoAereoImp(int numPistas) {
        this.NUM_PISTAS = numPistas;
        this.pistas = new boolean[NUM_PISTAS];
        for (int i = 0; i < NUM_PISTAS; i++) {
            pistas[i] = true;
        }
    }

    public int pistaLivre() {
        for (int i = 0; i < NUM_PISTAS; i++) {
            if (pistas[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int pedirParaDescolar() {
        int num_pista;
        int id = this.idDescolar;
        this.idDescolar++;
        lock.lock();
        try {
            while ((num_pista = this.pistaLivre()) == -1
                    || id != this.descolagens) {
                pista_libertada.await();
            }
            pistas[num_pista] = false;
            return num_pista;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int pedirParaAterrar() {
        int num_pista;
        int id = this.idAterrar;
        this.idAterrar++;
        lock.lock();
        try {
            while ((num_pista = this.pistaLivre()) == -1
                    || id != this.aterragens) {
                pista_libertada.await();
            }
            pistas[num_pista] = false;
            return num_pista;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void descolou(int pista) {
        pistas[pista] = true;
        descolagens++;
        pista_libertada.signalAll();
    }

    @Override
    public void aterrou(int pista) {
        pistas[pista] = true;
        aterragens++;
        pista_libertada.signalAll();
    }
}
