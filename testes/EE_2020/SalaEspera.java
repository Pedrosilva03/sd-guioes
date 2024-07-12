package testes.EE_2020;

import java.util.List;

interface SalaDeEspera {
    boolean espera(String nome);
    void desiste(String nome);
    String atende();
    List<String> atende(int n);
}