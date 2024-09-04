public class ChristmasInOurHearts {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        displayChristmas();
        drawChristmasTree();
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

    public static void displayChristmas() throws InterruptedException {
        String christmasText = "Merry Christmas";
        animateText(christmasText, 100);
        System.out.println(); 
        Thread.sleep(1000);
    }

    public static void drawChristmasTree() throws InterruptedException {
        String[] treeLines = {
            "        *        ",
            "       ***       ",
            "      *****      ",
            "     *******     ",
            "    *********    ",
            "   ***********   ",
            "  *************  ",
            "       |||       ",
            "       |||       "
        };

        long treeDrawingSpeed = 30;

        for (String line : treeLines) {
            animateText(line, treeDrawingSpeed);
            System.out.println();
        }

        Thread.sleep(1000); 
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
            "Whenever I see girls and boys",
            "Selling lanterns on the streets,",
            "I remember the Child",
            "In the manger as He sleeps.",
            "",
            "Wherever there are people",
            "Giving gifts, exchanging cards,",
            "I believe that Christmas",
            "Is truly in their hearts.",
            "",
            "Let's light our Christmas trees",
            "For a bright tomorrow",
            "Where nations are at peace",
            "And all are one in God",
            "",
            "Let's sing Merry Christmas",
            "And a happy holiday,",
            "This season may we never forget",
            "The love we have for Jesus",
            "Let Him be the One to guide us",
            "As another new year starts",
            "And may the spirit of Christmas",
            "Be always in our hearts."
        };

        for (String lyric : lyrics) {
            if (lyric.isEmpty()) {
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                long speed = 90;
                processLine(lyric, speed);
            }
        }
    }
}
