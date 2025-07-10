import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class naiilang {

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
                { "\nAlam ko naman", "100" },
                { "Kaibigan tayo", "90" },
                { "Kasalanan bang mahulog sayo?", "90" },
                { "Tumingin ka sa akin", "90" },
                { "Gusto kung linawin", "100" },
                { "Naiilang ka ba?", "100" },
                { "'pag tayo lang dal'wa?", "90" },
                { "Sinasabi ko nga na ", "100" },
                { "atin ang mundo", "90" },
                { "Walang ibang tulad mo ", "100" }
        };

        long[] delays = { 300, 3900, 7000, 10500, 11000, 14000, 16500, 19000, 21500, 25000 };

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