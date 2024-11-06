import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageInTheBottle extends JFrame {
    private JLabel lyricsLabel;
    private String[] lyrics = {
        "Cause you could be the one that I love",
        "I could be the one that you dream of",
        "Message in a bottle is all I can do",
        "Standin' here, hopin' it gets to you"
    };  
    private int index = 0;

    public MessageInTheBottle() {
        setTitle("MessageInTheBottle Lyrics");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE); 

        lyricsLabel = new JLabel("", SwingConstants.CENTER);
        lyricsLabel.setFont(new Font("Serif", Font.BOLD, 24));
        lyricsLabel.setForeground(Color.BLACK);
        panel.add(lyricsLabel, BorderLayout.CENTER);
        add(panel);

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
    }

    private void fadeOut(JLabel label, Runnable onComplete) {
        Timer fadeOutTimer = new Timer(20, new ActionListener() { 
            int alpha = 255;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (alpha > 0) {
                    label.setForeground(new Color(0, 0, 0, alpha));
                    alpha -= 5; 
                } else {
                    ((Timer) e.getSource()).stop();
                    onComplete.run();
                }
            }
        });
        fadeOutTimer.start();
    }

    private void fadeIn(JLabel label) {
        Timer fadeInTimer = new Timer(20, new ActionListener() { 
            int alpha = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (alpha < 255) {
                    label.setForeground(new Color(0, 0, 0, alpha));
                    alpha += 5; 
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        fadeInTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MessageInTheBottle frame = new MessageInTheBottle();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        });
    }
}
