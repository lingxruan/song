import java.util.concurrent.TimeUnit;

public class SheKnows {

    public static void main(String[] args) {
        String[] lines = {
            "Girl I'm tryna loosin you",
            "Half and half of that goose and juice",
            "Baby tell me what it's gon be",
            "Look at the sparks comin' right now",
            "Lookin' like the 4th of july",
            "You're officially coming with me",
            "she knows she knows she knows",
            "oh she knows she knows she knows",
            "she knows she knows",
            "she knows she knows she knows she knows she knows",
            "she knows she knows she knows"
        };

        double[] charDelays = {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
        double[] lineDelays = {2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0};

        for (int i = 0; i < lines.length; i++) {
            printWithDelay(lines[i], charDelays[i]);
            try {
                TimeUnit.SECONDS.sleep((long) lineDelays[i]);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println();
        }
    }

    private static void printWithDelay(String line, double charDelay) {
        for (char c : line.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            try {
                TimeUnit.MILLISECONDS.sleep((long) (charDelay * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}