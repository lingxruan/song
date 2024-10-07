import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThatsTheWayILoveYou extends JFrame {
    private JLabel lyricsLabel;
    private String[] lyrics = {
        "Screaming and Fighting",
        "And kissing in the rain",
        "and it's 2 a.m",
        "and I'm cursing your name",
        "so in love that you act insane",
        "and that's the way i love you",
        "breaking down and coming undone",
        "it's a roller coaster kind of rush",
        "and i never knew  i could feel that much",
        "and that's the way i love you"
    };
    private int index = 0;

    public ThatsTheWayILoveYou() {
        setTitle("ThatsTheWayILoveYou Lyrics");
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
            ThatsTheWayILoveYou frame = new ThatsTheWayILoveYou();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setLocation(frame.getX(), Toolkit.getDefaultToolkit().getScreenSize().height - frame.getHeight());
        });
    }
}
