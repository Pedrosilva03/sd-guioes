package testes.EE_2023;

import java.util.Map;

interface Hpc {
    int inicio(int ncores);
    void fim(int tarefa, long tempo);
    Map<Integer, Long> historia();
}
