public class shewillbeloved {

    public static void main(String[] args) {
        singSong();
    }

    public static void animateText(String text, long speed) {
        try {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                System.out.flush();
                Thread.sleep(speed);
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void singSong() {
        String[][] lyrics = {
                { "\nI don't mind spending everyday", "100" },
                { "Out on your corner in the pouring rain", "90" },
                { "Look for the girl with the broken smile", "90" },
                { "Ask her if she wants to stay a while?", "90" },
                { "And she will be loved", "100" },
                { "And she will be loved", "100" }
        };

        for (String[] line : lyrics) {
            animateText(line[0], Long.parseLong(line[1]));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
