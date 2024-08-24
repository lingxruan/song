import java.util.ArrayList;
import java.util.List;

public class YouDaOne {

    private static final Object lock = new Object();

    public static void main(String[] args) {
        singSong();
    }

    private static void printWithDelay(String text, long delay) {
        synchronized (lock) {
            for (char character : text.toCharArray()) {
                System.out.print(character);
                System.out.flush();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println();
        }
    }

    private static void performLyric(String lyric, long startDelay, long charDelay) {
        try {
            Thread.sleep(startDelay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        printWithDelay(lyric, charDelay);
    }

    private static void singSong() {
        String[][] lyricsWithTimings = {
                {"\n'Cause you know how to give me that", "70"},
                {"Tryna get away from loving ya", "70"},
                {"You know how to pull me back when I go running, running", "70"},
                {"Yep, I'm falling for ya, but there's nothing wrong with that", "70"},
                {"I won't lie, I'm falling hard", "70"},
                {"You know how to love me hard", "70"},
                {"You're the one that I think about always", "70"},
                {"You're the one that I dream about all day", "70"}
                
        };
        long[] lyricStartDelays = {300, 1700, 3000, 4500, 6000, 9500, 12900, 13900, 15500, 17000, 18500, 22000};

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < lyricsWithTimings.length; i++) {
            String lyric = lyricsWithTimings[i][0];
            long charDelay = Long.parseLong(lyricsWithTimings[i][1]);
            long startDelay = lyricStartDelays[i];
            Thread thread = new Thread(() -> performLyric(lyric, startDelay, charDelay));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
