package testes.EE_2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SalaEsperaImp implements SalaDeEspera {
    private List<String> fila;
    private int count;
    private Map<Integer, String> senhas;
    private int senhaAtual;

    private ReentrantLock lock = new ReentrantLock();
    private Condition atender = lock.newCondition();
    private Condition chegou = lock.newCondition();

    public SalaEsperaImp() {
        this.fila = new ArrayList<>();
        this.count = 1;
        this.senhas = new HashMap<>();
        this.senhaAtual = 1;
    }

    public boolean espera(String nome) {
        lock.lock();
        try {
            this.fila.add(nome);
            int senha = this.count;
            this.senhas.put(senha, nome);
            this.count++;
            chegou.signalAll();

            while (this.senhaAtual != senha) {
                atender.await();
            }

            if (this.fila.contains(nome)) {
                this.fila.remove(nome);
                return true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public void desiste(String nome) {
        lock.lock();
        try {
            this.fila.remove(nome);
        } finally {
            lock.unlock();
        }
    }

    public String atende() {
        lock.lock();
        try {
            if (this.fila.isEmpty()) {
                return null;
            }
            String nome = this.senhas.get(this.senhaAtual);
            atender.signalAll();
            return nome;
            this.senhaAtual++;
        } finally {
            lock.unlock();
        }
    }

    public List<String> atende(int n) {
        lock.lock();
        try {
            List<String> nomes = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                this.chegou.await();
                nomes.add(this.senhas.get(this.senhaAtual));
                this.fila.remove(nomes.get(i));
                atender.signalAll();
                this.senhaAtual++;
            }

            return nomes;
        } finally {
            lock.unlock();
        }
    }
}
