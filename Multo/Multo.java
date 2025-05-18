import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class Multo extends JFrame {
    private JTextPane textPane;
    private JProgressBar progressBar;
    private JLabel timeLabel;
    private JLabel songTitle;
    private JLabel artistName;
    private Timer timer;
    private long startTime;

    private final String[] lines = {
        "Hindi na makalaya",
        "Dinadalaw mo 'ko bawat gabi",
        "Wala mang makikita",
        "Haplos mo'y ramdam pa rin",
        "sa dilim",
        "Hindi na na-nanaginip",
        "Hindi na ma-makagising",
        "Pasindi na ng ilaw",
        "Minumulto na 'ko ng damdamin ko",
        "Ng damdamin ko",
        "'Di mo ba ako lilisanin?",
        "Hindi pa ba sapat",
        "pagpapahirap sa 'kin? (Damdamin ko)",
        "Hindi na ba ma-mamamayapa? (Damdamin ko)",
        "Hindi na ba ma-mamamayapa?",
        "Hindi na makalaya"
    };

    public Multo() {
        setTitle("Multo");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(18, 18, 18));
        setLocationRelativeTo(null);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(18, 18, 18));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        songTitle = new JLabel("Multo", SwingConstants.CENTER);
        songTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        songTitle.setFont(new Font("Arial", Font.BOLD, 24));
        songTitle.setForeground(Color.WHITE);

        artistName = new JLabel("Cup of Joe", SwingConstants.CENTER);
        artistName.setFont(new Font("Arial", Font.PLAIN, 14));
        artistName.setForeground(new Color(179, 179, 179));

        headerPanel.add(songTitle, BorderLayout.NORTH);
        headerPanel.add(artistName, BorderLayout.SOUTH);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(18, 18, 18));

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(18, 18, 18));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel imageLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (getIcon() != null) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    int diameter = Math.min(getWidth(), getHeight());
                    int x = (getWidth() - diameter) / 2;
                    int y = (getHeight() - diameter) / 2;
                    Ellipse2D circle = new Ellipse2D.Double(x, y, diameter, diameter);
                    g2.setClip(circle);
                    getIcon().paintIcon(this, g2, x, y);
                    g2.dispose();
                }
            }
        };

        try {
            ImageIcon originalIcon = new ImageIcon("multo.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            imageLabel.setIcon(createPlaceholderIcon(250, 250));
        }

        JPanel imageWrapper = new JPanel(new BorderLayout());
        imageWrapper.setBackground(new Color(18, 18, 18));
        imageWrapper.add(imageLabel, BorderLayout.CENTER);
        imagePanel.add(imageWrapper, BorderLayout.CENTER);

        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBackground(new Color(18, 18, 18));
        textPane.setForeground(Color.WHITE);
        textPane.setFont(new Font("Arial", Font.PLAIN, 18));
        textPane.setMargin(new Insets(20, 30, 20, 30));

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(18, 18, 18));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        contentPanel.add(imagePanel, BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        progressBar = new JProgressBar();
        progressBar.setBackground(new Color(40, 40, 40));
        progressBar.setForeground(new Color(30, 215, 96));
        progressBar.setBorder(BorderFactory.createEmptyBorder());

        timeLabel = new JLabel("0:00 / 3:45", SwingConstants.CENTER);
        timeLabel.setForeground(new Color(179, 179, 179));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel progressPanel = new JPanel(new BorderLayout());
        progressPanel.setBackground(new Color(18, 18, 18));
        progressPanel.add(progressBar, BorderLayout.CENTER);
        progressPanel.add(timeLabel, BorderLayout.SOUTH);
        progressPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));

        JPanel controlPanel = createControlPanel();

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(progressPanel, BorderLayout.SOUTH);
        add(controlPanel, BorderLayout.PAGE_END);

        setVisible(true);
        displayLyrics();
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        panel.setBackground(new Color(18, 18, 18));

        JButton prevButton = createIconButton("prev.png", 30, 30);
        JButton playButton = createIconButton("play.png", 40, 40);
        JButton nextButton = createIconButton("next.png", 30, 30);

        JSlider volumeSlider = new JSlider(0, 100, 70);
        volumeSlider.setPreferredSize(new Dimension(100, 20));
        volumeSlider.setBackground(new Color(18, 18, 18));
        volumeSlider.setForeground(Color.WHITE);

        panel.add(prevButton);
        panel.add(playButton);
        panel.add(nextButton);
        panel.add(volumeSlider);

        return panel;
    }

    private JButton createIconButton(String iconPath, int width, int height) {
        JButton button = new JButton();
        try {
            ImageIcon originalIcon = new ImageIcon(iconPath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            button.setText(getFallbackText(iconPath));
            button.setFont(new Font("Arial", Font.BOLD, 16));
        }
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setBackground(new Color(18, 18, 18));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(40, 40, 40));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(18, 18, 18));
            }
        });
        return button;
    }

    private String getFallbackText(String iconPath) {
        if (iconPath.contains("prev")) return "<<";
        if (iconPath.contains("play")) return ">";
        if (iconPath.contains("next")) return ">>";
        return "?";
    }

    private ImageIcon createPlaceholderIcon(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(new Color(40, 40, 40));
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(new Color(179, 179, 179));
        g2d.setFont(new Font("Arial", Font.PLAIN, 48));
        FontMetrics fm = g2d.getFontMetrics();
        String text = "♫";
        int x = (width - fm.stringWidth(text)) / 2;
        int y = ((height - fm.getHeight()) / 2) + fm.getAscent();
        g2d.drawString(text, x, y);
        g2d.dispose();
        return new ImageIcon(image);
    }

    private void displayLyrics() {
        StyledDocument doc = textPane.getStyledDocument();
        StyleContext context = new StyleContext();
        Style regular = doc.addStyle("regular", context.getStyle(StyleContext.DEFAULT_STYLE));
        Style highlight = doc.addStyle("highlight", regular);
        StyleConstants.setForeground(highlight, new Color(30, 215, 96));

        try {
            StringBuilder fullText = new StringBuilder();
            for (String line : lines) {
                fullText.append(line).append("\n");
                doc.insertString(doc.getLength(), line + "\n", regular);
            }
            textPane.setCaretPosition(0);

            startTime = System.currentTimeMillis();
            timer = new Timer(100, e -> {
                long elapsed = System.currentTimeMillis() - startTime;
                int progress = (int) (elapsed * 100 / (3 * 60 * 1000 + 45 * 1000));
                progressBar.setValue(Math.min(progress, 100));
                updateTimeLabel(elapsed);
            });
            timer.start();

            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    
                    int firstWordStart = 0;
                    int firstWordLength = "Hindi".length();
                    SwingUtilities.invokeLater(() -> {
                        doc.setCharacterAttributes(firstWordStart, firstWordLength, highlight, false);
                        textPane.setCaretPosition(firstWordStart);
                    });
                    
                    Thread.sleep(500);
                    
                    int offset = firstWordStart + firstWordLength + 1;
                    String[] remainingWords = lines[0].substring(firstWordLength + 1).split(" ");
                    
                    for (String word : remainingWords) {
                        final int start = offset;
                        final int length = word.length();
                        SwingUtilities.invokeLater(() -> {
                            doc.setCharacterAttributes(0, doc.getLength(), regular, true);
                            doc.setCharacterAttributes(start, length, highlight, false);
                            textPane.setCaretPosition(start);
                        });
                        Thread.sleep(500);
                        offset += word.length() + 1;
                    }
                    
                    for (int i = 1; i < lines.length; i++) {
                        Thread.sleep(3000);
                        String line = lines[i];
                        offset = fullText.toString().indexOf(line, offset);
                        
                        String[] words = line.split(" ");
                        for (String word : words) {
                            final int start = offset;
                            final int length = word.length();
                            SwingUtilities.invokeLater(() -> {
                                doc.setCharacterAttributes(0, doc.getLength(), regular, true);
                                doc.setCharacterAttributes(start, length, highlight, false);
                                textPane.setCaretPosition(start);
                            });
                            Thread.sleep(500);
                            offset += word.length() + 1;
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    timer.stop();
                }
            }).start();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void updateTimeLabel(long elapsedMillis) {
        long totalMillis = 3 * 60 * 1000 + 45 * 1000;
        String elapsedTime = String.format("%d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(elapsedMillis),
                TimeUnit.MILLISECONDS.toSeconds(elapsedMillis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(elapsedMillis)));

        String totalTime = String.format("%d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(totalMillis),
                TimeUnit.MILLISECONDS.toSeconds(totalMillis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalMillis)));

        timeLabel.setText(elapsedTime + " / " + totalTime);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Multo();
        });
    }
}