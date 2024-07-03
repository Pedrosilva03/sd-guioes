interface Managerr {
    String newTransfer() throws InterruptedException;
    Transfer getTransfer(String identifier);
}