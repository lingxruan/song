import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
            
public class SanayAkoNaLang {
            
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
                        {"May nagmamahal na ba sayo?", "120"},
                        {"Kung wala'y ako nalang", "120"},
                        {"Lahat ibibigay sayo", "120"},
                        {"Na walang alinlangan", "120"},
                        {"Sana'y bigyan naman ng pansin", "120"},
                        {"Ang pusong kung ito", "120"},
                        {"Kaya tanong ko lang", "120"},
                        {"Kung may nagmamahal naba?", "120"},
                        {"Sana'y ako nalang", "120"},   
                    };
                    
                    long[] delays = {300, 3900, 7000, 10500, 15000, 18500, 21500, 24900, 28500};
            
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
            