import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WideAwake {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        String[][] lyrics = {
            {"Falling from cloud nine", "0.12"},
            {"Crashing from the high", "0.11"},
            {"I'm letting go tonight", "0.11"},
            {"I'm falling from cloud nine", "0.12"},
            {"Thunder rumbling", "0.10"},
            {"Castles crumbling", "0.10"},
            {"I am trying to hold on", "0.11"}
        };
        double[] delays = {0.3, 5.7, 11.0, 16.2, 22.5, 25.2, 27.8};

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
