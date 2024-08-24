import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Strong   {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        String[][] lyrics = {
            {"I'm sorry if I say, I need you", "0.08"},
            {"But I don't care, I'm not scared of love", "0.08"},
            {"Cause when I'm not with you, I'm weaker", "0.06"},
            {"Is that so wrong? Is it so wrong?", "0.08"},
            {"That you make me strong?", "0.06"}
        };
        double[] delays = {0.3, 3.7, 7.4, 10.2, 13.7};

        Thread[] threads = new Thread[lyrics.length];

        for (int i = 0; i < lyrics.length; i++) {
            String[] lyric = lyrics[i];
            double delay = delays[i];
            threads[i] = new Thread(new SingLyric(lyric[0], delay, Double.parseDouble(lyric[1])));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread was interrupted: " + e.getMessage());
            }
        }
    }

    static class SingLyric implements Runnable {
        private final String lyric;
        private final double delay;
        private final double speed;

        SingLyric(String lyric, double delay, double speed) {
            this.lyric = lyric;
            this.delay = delay;
            this.speed = speed;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((long) (delay * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread was interrupted: " + e.getMessage());
            }
            animateText(lyric, speed);
        }
    }

    private static void animateText(String text, double delay) {
        lock.lock();
        try {
            for (char ch : text.toCharArray()) {
                System.out.print(ch);
                System.out.flush();
                try {
                    Thread.sleep((long) (delay * 1000));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread was interrupted: " + e.getMessage());
                }
            }
            System.out.println();
        } finally {
            lock.unlock();
        }
    }
}
