package ui;

import javax.swing.*;
import ui.components.RoundedButtonUI;
import ui.components.StyledPanel;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WelcomeScreen {

  private JFrame frame;

  public WelcomeScreen() {
    Font openRundeFont = null;

    try {
      Path fontPath = Paths.get("assets", "OpenRunde-Semibold.otf");
      openRundeFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath.toString()))

          .deriveFont(20f);
    } catch (FontFormatException | IOException e) {
      e.printStackTrace();
     
    }

    if (openRundeFont != null) {
      UIManager.put("Label.font", openRundeFont);
    }

    frame = new JFrame("Marked");
    frame.setSize(900, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    JPanel contentPanel = StyledPanel.createWelcomePanel();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

    Path logoPath = Paths.get("assets", "logo.png");
    ImageIcon logoIcon = new ImageIcon(logoPath.toString());
    Image logoImage = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
    JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
    logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel welcomeLabel = new JLabel("Welcome to");
    welcomeLabel.setFont(openRundeFont.deriveFont(16f));
    welcomeLabel.setForeground(Color.decode("#ABB3BA"));
    welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));

    JLabel appNameLabel = new JLabel("Marked");
    appNameLabel.setFont(openRundeFont.deriveFont(32f));
    appNameLabel.setForeground(Color.decode("#E6E6EA"));
    appNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    appNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

    JButton getStartedButton = new JButton("Get started →");
    getStartedButton.setFont(openRundeFont.deriveFont(18f));
    getStartedButton.setForeground(Color.WHITE);
    getStartedButton.setBackground(Color.decode("#636B75"));
    getStartedButton.setFocusPainted(false);
    getStartedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    getStartedButton.setPreferredSize(new Dimension(160, 40));
    getStartedButton.setMaximumSize(new Dimension(160, 40));
    getStartedButton.setUI(new RoundedButtonUI());
    getStartedButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    getStartedButton.addActionListener(e -> {
      new MainMenu(frame);
    });

    contentPanel.add(Box.createVerticalGlue());
    contentPanel.add(logoLabel);
    contentPanel.add(welcomeLabel);
    contentPanel.add(appNameLabel);
    contentPanel.add(getStartedButton);
    contentPanel.add(Box.createVerticalGlue());

    frame.add(contentPanel, BorderLayout.CENTER);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(WelcomeScreen::new);
  }
}
