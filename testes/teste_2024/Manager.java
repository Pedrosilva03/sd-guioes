interface Manager {
    Raid join(String nome, int minPlayers) throws InterruptedException;
    void finished();
    void tryStart(RaidImp raid);
}