package teste_2023;

interface Controlador {
    int reserva();
    void preparado(int kart);
    void completaVolta(int kart);
    int[] voltasCompletas();
    int vencedor();
}