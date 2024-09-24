import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Billionaire extends JFrame {
    private JLabel lyricsLabel;
    private String[] lyrics = {
        "I wanna be a billionaire so fucking bad",
        "Buy all of the things I never had",
        "I wanna be on the cover of Forbes magazine",
        "Smiling next to Oprah and the Queen",
        "Oh, every time I close my eyes",
        "I see my name in shiny lights, yeah",
        "A different city every night, oh, I, I swear",
        "The world better prepare for when I'm a billionaire"
    };
    private int index = 0;

    public Billionaire() {
        setTitle("Billionaire Lyrics");
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
            Billionaire frame = new Billionaire();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setLocation(frame.getX(), Toolkit.getDefaultToolkit().getScreenSize().height - frame.getHeight()); // Position at bottom
        });
    }
}
