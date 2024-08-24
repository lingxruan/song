import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RightNow {
    private static final Lock lock = new ReentrantLock();

    public static void animateText(String text, double delay) {
        lock.lock();
        try {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                System.out.flush();
                Thread.sleep((long) (delay * 1000));
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public static void singLyric(String lyric, double delay, double speed) {
        try {
            Thread.sleep((long) (delay * 1000));
            animateText(lyric, speed);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void singSong() {
        String[][] lyrics = {
            {"Right now", "0.11"},
            {"I wish you were here with me", "0.07"},
            {"Cause right now", "0.09"},
            {"Everything is new to me", "0.09"},
            {"You know I can't fight the feeling", "0.10"},
            {"And every night I feel it", "0.09"},
            {"Right now", "0.11"},
            {"I wish you were here with me", "0.07"},
        };

        double[] delays = {0.3, 2.2, 7.5, 10.0, 16.2, 20.5, 24.0, 26.0};

        Thread[] threads = new Thread[lyrics.length];

        for (int i = 0; i < lyrics.length; i++) {
            String lyric = lyrics[i][0];
            double speed = Double.parseDouble(lyrics[i][1]);
            double delay = delays[i];

            threads[i] = new Thread(() -> singLyric(lyric, delay, speed));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        singSong();
    }
}
