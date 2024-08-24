public class HanggangSaHuli{

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

    public static void singLyric(String lyric, long speed) {
        animateText(lyric, speed);
    }

    public static void singSong() throws InterruptedException {
        String[][] lyrics = {
            {"\nHanggang sa huli", "130"},
            {"Mga problema'y 'santabi, ipagpaliban ang gabi", "130"},
            {"Sulitin bawat sandali, kahit 'di na 'to madali", "130"},
            {"Hanggang sa huli", "130"},
            {"Mga problema'y 'santabi, ipagpaliban ang gabi", "130"},
            {"Kahit alam kong aalis at 'di ka na babalik", "130"},
            {"\nPa'no na ba 'to, dito na lang ba tayo", "120"},
            {"Maghihintay, kung aabot pa sa dulo", "120"},
            {"Pa'no na ba 'to, dito na lang ba tayo", "120"},
            {"Maghihintay, kung aabot pa sa dulo", "120"},
            {"Pa'no na ba 'to, dito na lang ba tayo", "120"},
            {"Maghihintay, kung aabot pa sa dulo", "120"},
            {"Pa'no na ba 'to, dito na lang ba tayo", "120"},
            {"Guguho", "120"}
        };

        for (int i = 0; i < lyrics.length; i++) {
            String lyric = lyrics[i][0];
            long speed = Long.parseLong(lyrics[i][1]);

            
            singLyric(lyric, speed);

            if (i < lyrics.length - 1) {
                Thread.sleep(1000);
            }
        }
    }
}
