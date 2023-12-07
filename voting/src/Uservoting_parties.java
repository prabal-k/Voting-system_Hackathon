import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Uservoting_parties {
    JFrame voterFrame = new JFrame("Voter Login Form");
    ButtonGroup buttonGroup = new ButtonGroup();
    JButton confirmbutton =new JButton("confirm");

    Uservoting_parties() {
        // Set up the JFrame
        voterFrame.setResizable(false);
        voterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        voterFrame.setSize(1000, 700);
        voterFrame.setLocationRelativeTo(null);

        //confirm button
        confirmbutton.setBounds(500,630,80,30);


        // Load the background image using ImageIO
        //Image backgroundImage = loadImage("Images\\photo.jpeg");
        try {
            Image backgroundImage = ImageIO.read(getClass().getResource("Images\\untitled.png"));
            BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
            // Create a custom panel for the background image
            backgroundPanel.setLayout(null);

            // Define x and y positions for radio buttons
            int[] xPositions = {150, 390, 630, 870, 150, 390, 630, 870};
            int[] yPositions = {300, 300, 300, 300, 550, 550, 550, 550};

            // Add 8 radio buttons
            addRadioButtons(backgroundPanel, xPositions, yPositions);

            // Set the custom panel as the content pane of the JFrame
            voterFrame.setContentPane(backgroundPanel);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Make the JFrame visible
        voterFrame.add(confirmbutton);
        voterFrame.setVisible(true);
    }

    private Image loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void addRadioButtons(JPanel panel, int[] xPositions, int[] yPositions) {
        for (int i = 0; i < xPositions.length; i++) {
            JRadioButton radioButton = new JRadioButton();
            radioButton.setBounds(xPositions[i], yPositions[i], 20, 20);
            buttonGroup.add(radioButton);
            // Add radio buttons to the custom panel
            panel.add(radioButton);
        }
    }
    // Custom panel for background image
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, this);
            }
        }
    }
}
