import java.util.ArrayList;
import java.util.List;

public class ForeverYoung {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        singSong();
    }

    public static void animateText(String text, long delay) {
        synchronized (lock) {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }

    public static void singLyric(String lyric, long delay, long speed) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        animateText(lyric, speed);
    }

    public static void singSong() throws InterruptedException {
        String[][] lyrics = {
                {"\nForever young", "90"},
                {"I want to be forever young", "90"},
                {"Do you really want to live forever?", "80"},
                {"Forever, and ever", "140"},
                {"Forever young", "90"},
                {"I want to be forever young", "100"},
                {"Do you really want to live forever?", "80"},
                {"Forever, and ever", "140"}
        };

        long[] delays = {300, 2800, 7500, 10900, 14500, 16900, 21600, 23000};

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < lyrics.length; i++) {
            String lyric = lyrics[i][0];
            long speed = Long.parseLong(lyrics[i][1]);
            long delay = delays[i];
            Thread t = new Thread(() -> singLyric(lyric, delay, speed));
            threads.add(t);
            t.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
