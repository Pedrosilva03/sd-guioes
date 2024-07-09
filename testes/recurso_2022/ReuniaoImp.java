package testes.recurso_2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReuniaoImp implements Reuniao {
    private int numero_listas;
    private Map<Integer, Integer> elementos_espera; // lista -> quantidade
    private ArrayList<Integer> fila_espera; // orndem de chegada das listas
    private int lista_atual;
    private int elementos_sala;

    private ReentrantLock lock = new ReentrantLock();
    private Condition salaLivre = lock.newCondition();

    public ReuniaoImp(int numero_listas) {
        this.numero_listas = numero_listas;
        this.elementos_espera = new HashMap<>();
        for (int i = 1; i < numero_listas; i++) {
            this.elementos_espera.put(i, 0);
        }
        this.fila_espera = new ArrayList<>();
        this.lista_atual = 0;
        this.elementos_sala = 0;
    }

    public void participa(int lista) {
        lock.lock();
        try {
            if (lista == this.lista_atual) {
                this.elementos_sala++;
            }
            
            else {
                if (!this.fila_espera.contains(lista)) {
                    this.fila_espera.add(lista);
                }

                this.elementos_espera.put(lista, this.elementos_espera.get(lista) + 1);

                while (this.elementos_sala != 0) {
                    try {
                        salaLivre.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                this.lista_atual = lista;
                this.elementos_sala = this.elementos_espera.get(lista);
                this.elementos_espera.put(lista, 0);
                this.fila_espera.remove(this.fila_espera.indexOf(lista));
            }
        } finally {
            lock.unlock();
        }
    }

    public void abandona(int lista) {
        lock.lock();
        try {
            if (lista == this.lista_atual) {
                this.elementos_sala--;

                if (this.elementos_sala == 0) {
                    salaLivre.signalAll();
                }
            }
            
            else {
                this.elementos_espera.put(lista, this.elementos_espera.get(lista) - 1);
            }
        } finally {
            lock.unlock();
        }
    }

    public int naSala() {
        return this.elementos_sala;
    }

    public int aEspera() {
        int total = 0;
        for (int i = 1; i < this.numero_listas; i++) {
            total += this.elementos_espera.get(i);
        }
        return total;
    }
}