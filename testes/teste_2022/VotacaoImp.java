package testes.teste_2022;

import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class VotacaoImp implements Votacao {
    private int E;
    private int N_CABINES;
    private int[] ja_votou;
    private Map<Integer, Integer> votos;
    private boolean[] cabines; // true se a cabine está livre
    private boolean votacaoTerminada;

    private ReentrantLock lock = new ReentrantLock();
    private Condition cabineLivreCondition = lock.newCondition();

    public VotacaoImp(int E, int N_CABINES) {
        this.E = E;
        this.N_CABINES = N_CABINES;
        this.cabines = new boolean[N_CABINES];
        for (int i = 0; i < N_CABINES; i++) {
            cabines[i] = true;
        }
        this.votacaoTerminada = false;
    }

    @Override
    public boolean verifica(int identidade) {
        lock.lock();
        try {
            if (votacaoTerminada) {
                return false;
            }

            for (int i = 0; i < ja_votou.length; i++) {
                if (ja_votou[i] == identidade) {
                    return false;
                }
            }
            
            return true;
        } finally {
            lock.unlock();
        }
    }

    public int cabineLivre() {
        for (int i = 0; i < N_CABINES; i++) {
            if (!cabines[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int esperaPorCabine() {
        lock.lock();
        int cabine = -1;
        try {
            if (votacaoTerminada) {
                return -1;
            }

            while (!votacaoTerminada && 
                    (cabine = cabineLivre()) == -1) {
                cabineLivreCondition.await();
            }

            return cabine;
        } catch (InterruptedException e) {
            return -1;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void vota(int escolha) {
        lock.lock();
        try {
            if (escolha < this.E) {
                votos.put(escolha, votos.getOrDefault(escolha, 0) + 1);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void desocupaCabine(int i) {
        this.cabines[i] = true;
        cabineLivreCondition.signalAll();
    }
}