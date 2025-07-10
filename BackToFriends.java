import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class BackToFriends {

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
            {"\nHow can we go back to being friends", "100"},
            {"when we just shared a bed?", "90"},
            {"How can you look at me and pretend", "90"},
            {"i'm someone you've never met?", "100"}
        };
        
        long[] delays = {300, 3900, 7000, 10500};

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