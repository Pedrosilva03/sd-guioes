package testes.recurso_2024;

interface Managerr {
    String newTransfer() throws InterruptedException;
    Transfer getTransfer(String identifier);
}