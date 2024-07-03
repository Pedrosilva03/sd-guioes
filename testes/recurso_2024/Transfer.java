interface Transfer {
    void enqueue(byte[] b) throws InterruptedException;
    byte[] dequeue() throws InterruptedException;
}
