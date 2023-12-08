import RoundedPanels.RoundedButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PartiesClass extends JFrame {
    void partieslogos() {
        Color greenColor = new Color(87, 204, 153);
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Color fontColor = new Color(241,250,238);

        JLabel label1 = new JLabel();
        label1.setBounds(0, 0, 1000, 700);

        JButton backButton = new RoundedButton("<", 60);
        backButton.setFont(labelFont);
        backButton.setForeground(fontColor);
        // Adjusted the position and size of the button
        backButton.setBounds(40, 25, 50, 50);
        backButton.setFocusPainted(false);
        backButton.setBackground(greenColor);
        backButton.setBorderPainted(false);
        //backButton.setOpaque(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PartiesClass.this.setVisible(false);
                UserMenu.frame.setVisible(true);


            }
        });

        this.setSize(1000, 700);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(backButton);
        this.add(label1);



        try {
            Image partiesImage = ImageIO.read(getClass().getResource("Images/partiesImage.png"));
            label1.setIcon(new ImageIcon(partiesImage));

        } catch (Exception ex) {
            System.out.println(ex);
        }


    }
}

