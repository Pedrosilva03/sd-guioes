package testes.teste_2022;

interface Votacao {
    boolean verifica(int identidade);
    int esperaPorCabine();
    void vota(int escolha);
    void desocupaCabine(int i);
}
