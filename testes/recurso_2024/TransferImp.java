import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class TransferImp implements Transfer {
    private static final int transfer_capacity = 16 * 1024; // 16K bytes
    private byte[] transfer_buffer;
    private int current_size;
    private Boolean completed;

    private ReentrantLock lock = new ReentrantLock();
    private Condition freeBufferCondition = lock.newCondition();
    private Condition fullBufferCondition = lock.newCondition();

    public TransferImp() {
        this.transfer_buffer = new byte[transfer_capacity];
        this.completed = false;
        this.current_size = 0;
    }

    public void enqueue(byte[] b) throws InterruptedException {
        try {
            if (b == null) {
                completed = true;
                fullBufferCondition.signalAll();
                return;
            }

            while (current_size + b.length > transfer_capacity) {
                freeBufferCondition.await();
            }

            for (int i = 0; i < b.length; i++) {
                transfer_buffer[current_size++] = b[i];
            }

            fullBufferCondition.signalAll();
        } finally {}
    }
    
    public byte[] dequeue() throws InterruptedException {
        try {
            while (!completed) {
                while (current_size == 0) {
                    fullBufferCondition.await();
                }

                byte[] b = new byte[current_size];

                for (int i = 0; i < current_size; i++) {
                    b[i] = transfer_buffer[i];
                    transfer_buffer[i] = '0';
                    current_size--;
                }

                freeBufferCondition.signalAll();

                return b;
            }

            return null;
        } finally {}
    }
}
