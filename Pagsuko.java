import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pagsuko extends JFrame {
    private JLabel lyricsLabel;
    private String[] lyrics = {
        "Pwede bang pag-isipan? Huwag ka munang lumiban",
        "Baka sakali na ito ay maisalba pa",
        "Lumalamig ang gabi",
        "Hindi na tulad ng dati",
        "May pag-asa pa ba kung susuko ka na?",
        "Larawan mo ba'y lulukutin ko na?",
        "Sa hirap at ginhawa, tayo ay nagsama",
        "Damdamin mo tila'y napagod na",
        "Ikaw at ako ay alaala na lang kung susuko ka na"
    };
    private int index = 0;

    public Pagsuko() {
        setTitle("Pagsuko Lyrics");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        lyricsLabel = new JLabel("", SwingConstants.CENTER);
        lyricsLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        add(lyricsLabel, BorderLayout.CENTER);

        Timer timer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < lyrics.length) {
                    fadeOut(lyricsLabel, () -> {
                        displayLyrics(lyrics[index]);
                        fadeIn(lyricsLabel);
                        index++;
                    });
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    private void displayLyrics(String lyric) {
        lyricsLabel.setText(lyric);
        lyricsLabel.setForeground(new Color(0, 0, 0, 0));
    }

    private void fadeOut(JLabel label, Runnable onComplete) {
        Timer fadeOutTimer = new Timer(30, new ActionListener() {
            int alpha = 255;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (alpha > 0) {
                    label.setForeground(new Color(0, 0, 0, alpha));
                    alpha -= 15; 
                } else {
                    ((Timer) e.getSource()).stop();
                    onComplete.run();
                }
            }
        });
        fadeOutTimer.start();
    }

    private void fadeIn(JLabel label) {
        Timer fadeInTimer = new Timer(30, new ActionListener() {
            int alpha = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (alpha < 255) {
                    label.setForeground(new Color(0, 0, 0, alpha));
                    alpha += 15;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        fadeInTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Pagsuko frame = new Pagsuko();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setLocation(frame.getX(), Toolkit.getDefaultToolkit().getScreenSize().height - frame.getHeight()); // Position at bottom
        });
    }
}
