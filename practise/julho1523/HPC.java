package practise.julho1523;

import java.util.Map;

public interface HPC {
    int inicio(int ncores);
    void fim(int tarefa, long tempo);
    Map<Integer, Long> historia();
}
