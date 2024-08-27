import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
            
public class BirdsOfAFeather {
            
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
                        {"And I don't know what I'm crying for", "90"},
                        {"I don't think I could love you more", "90"},
                        {"It might not be long, but baby, I...", "90"},
                        {"I'll love you `till the day that I die", "90"},
                        {"`Till the day that I die..", "90"},
                        {"Till the lights leaves my eyes", "90"},
                        {"`Till the day that I die..", "90"},
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
            