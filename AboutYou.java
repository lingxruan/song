import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AboutYou {
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
            {"\nDo you think I have forgotten?", "0.1"},
            {"Do you think I have forgotten?", "0.1"},
            {"Do you think I have forgotten?", "0.1"},
            {"About you?", "0.2"},
            {"\nThere was something bout you that now I cant remember", "0.1"},
            {"Its the same damn thing that made my heart surrender", "0.1"},
            {"And I miss you on a train, I miss you in the morning", "0.1"},
            {"I never know what to think about", "0.1"},
            {"I think about youuuuuuuuuuuuuuuuuuuuu", "0.1"}
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