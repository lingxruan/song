import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class dress {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        singSong();
    }

    public static void animateText(String text, long delay) {
        lock.lock();
        try {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                System.out.flush();
                Thread.sleep(delay);
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public static void singSong(String lyric, long delay, long speed) {
        try {
            Thread.sleep(delay);
            animateText(lyric, speed);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void singSong() {
        String[][] lyrics = {
                { "\nFlashback when you met me", "100" },
                { "Your buzzcut and my hair bleached", "90" },
                { "Even in my worst times", "90" },
                { "You could see the best in me", "90" },
                { "Flashback to my mistakes", "100" },
                { "My rebounds, my earthquakes", "100" },
                { "Even in my worst lies", "90" },
                { "You saw the truth in me", "100" },
                { "And I woke up just in time", "90" },
                { "Now I wake up by your side", "100" },
                { "My one and only, my lifeline", "100" },
                { "I woke up just in time", "90" },
                { "Now I wake up by your side", "100" },
                { "My hands shake, I can’t explain this,", "90" },
                { "ah, ha, ha, ha-ah", "100" },
                { "Say my name and everything just stops", "100" }
        };

        long[] delays = { 300, 3900, 7000, 10500, 11000, 14000, 16500, 19000, 21500, 25000, 31000, 36000, 42000, 47000, 51000, 56000 };

        ExecutorService executor = Executors.newFixedThreadPool(lyrics.length);
        for (int i = 0; i < lyrics.length; i++) {
            final int index = i;
            executor.submit(() -> singSong(lyrics[index][0], delays[index], Long.parseLong(lyrics[index][1])));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }
}