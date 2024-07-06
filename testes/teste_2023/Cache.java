package testes.teste_2023;

interface Cache {
    void put(int key, byte[] value);
    byte[] get(int key);
    void evict(int key);
    }
