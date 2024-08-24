import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ToTheBone {

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
            {"\nTake me home, I'm fallin'", "100"},
            {"Love me long, I'm rollin'", "90"},
            {"Losing control, body and soul", "90"},
            {"Mind too for sure, I'm already yours", "100"},
            {"Walk you down, I'm all in", "100"},
            {"Hold you tight, you call and", "90"},
            {"I'll take control, your body and soul", "70"},
            {"Mind too for sure, I'm already yours", "90"},
        };
        
        long[] delays = {300, 3900, 7000, 10500, 15000, 18500, 21500, 24900};

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
