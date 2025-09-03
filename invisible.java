import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class invisible {

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
            {"\ntrust the one", "100"},
            {"who's been where you are", "90"},
            {"wishing all it was sticks and stones", "90"},
            {"yeah, those words cut deep", "100"},
            {"but they don't mean your're all alone", "100"},
            {"and you're not invisible", "90"},
            {"hear me out there's so much more", "90"},
            {"of this life than what", "100"},
            {"you're feeling now", "100"},
            {"and someday you'll look back", "90"},
            {"on all this days and all this pain is gonna be", "90"},
            {"invisible", "100"}
        };
        
        long[] delays = {300, 3900, 7000, 10500, 15500, 21000, 26000, 32400, 37400, 44500, 51000, 57300};

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