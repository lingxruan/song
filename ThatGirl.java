public class ThatGirl {

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
        }
    }

    public static void processLine(String line, long speed) {
        String[] words = line.split(" ");
        for (String word : words) {
            animateText(word + " ", speed);
        }
        System.out.println();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void singSong() throws InterruptedException {
        String[] lyrics = {
            "You don't wanna lose at love",
            "Its only gonna hurt too much",
            "Im telling you",
            "You dont wanna lose at love",
            "Its only gonna hurt too much",
            "You dont wanna lose at love",
            "Cause theres no hope for the broken heart",
            "About that girl",
            "The one I let get away"
        };

        for (String lyric : lyrics) {
            long speed = 90;
            processLine(lyric, speed);
        }
    }
}
