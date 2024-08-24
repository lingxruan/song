public class CruelSummer {

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
            {"I'm drunk in the back of the car", "45"},
            {"And I cried like a baby coming home from the bar (oh)", "30"},
            {"Said, \"I'm fine,\" but it wasn't true", "40"},
            {"I don't wanna keep secrets just to keep you", "40"},
            {"And I snuck in through the garden gate", "40"},
            {"Every night that summer just to seal my fate (oh)", "40"},
            {"And I screamed for whatever it's worth", "40"},
            {"\"I love you,\" ain't that the worst thing you ever heard?", "30"},
            {"He looks up grinning like a devil", "40"},
            {"It's new, the shape of your body", "40"},
            {"It's blue, the feeling I've got", "40"},
            {"And it's ooh, whoa, oh", "50"},
            {"It's a cruel summer", "40"}
        };

        long delayBetweenLines = 500;

        for (int i = 0; i < lyrics.length; i++) {
            String lyric = lyrics[i][0];
            long speed = Long.parseLong(lyrics[i][1]);
            long delay = i == 0 ? 0 : delayBetweenLines;
            singLyric(lyric, delay, speed);
        }
    }
}
