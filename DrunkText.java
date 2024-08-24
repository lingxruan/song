import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DrunkText {
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
            {"I wish I was who you drunk texted at midnight", "0.1"},
            {"Wish I was the reason you stay up 'til 3", "0.1"},
            {"And you can't fall asleep", "0.1"},
            {"Waiting for me to reply", "0.1"},
            {"I wish I was more than just someone you walk by", "0.1"},
            {"Wish I wasn't scared to be honest and open", "0.1"},
            {"Instead of just hoping", "0.1"},
            {"You'd feel what I'm feeling inside", "0.1"},
        };

        double[] delays = {0.3, 5.0, 10.0, 15.0, 20.3, 25.0, 27.0, 30.2, 35.0};

        for (int i = 0; i < lyrics.length; i++) {
            String lyric = lyrics[i][0];
            double speed = Double.parseDouble(lyrics[i][1]);
            double delay = delays[i];

            new Thread(() -> singLyric(lyric, delay, speed)).start();
        }
    }

    public static void main(String[] args) {
        singSong();
    }
}