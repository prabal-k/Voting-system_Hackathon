import RoundedPanels.RoundedButton;
import RoundedPanels.RoundedPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserMenu {
    PartiesClass partiesClass;
     static JFrame frame = new JFrame();
    RoundedButton voteButton = new RoundedButton("Vote",20);
    // JButton button2 = new JButton("Vote Eligibility");
    RoundedButton partiesButton = new RoundedButton("Parties",20);

    RoundedButton countButton = new RoundedButton("Stats",20);

    //Panels
    RoundedPanel headerPanel = new RoundedPanel(0);
    RoundedPanel userMenuPanel = new RoundedPanel(30);


    RoundedButton backButton = new RoundedButton("<",60);

    JLabel titleLabel = new JLabel("Nepal Election Commission");

    JLabel partiesLabel = new JLabel();
    JLabel voteLabel = new JLabel("");

    JLabel countLabel = new JLabel("");

    JLabel logoLabel = new JLabel("");

    Font labelFont = new Font("Arial", Font.BOLD, 20);
    Font textBox = new Font("Arial",Font.BOLD,16);
    Font customFont = new Font("Arial", Font.BOLD, 46);

    Color btnColor = new Color(230,57,70);
    Color fontColor = new Color(241,250,238);
    Color greenColor = new Color(87, 204, 153);

//    JPanel panel1 = new JPanel();



    //Constructor
    UserMenu()
    {

        userMenuPanel.setBounds(200,175,600,400);
        userMenuPanel.setBackground(new Color(255,255,255,100));
        userMenuPanel.setLayout(null);

        headerPanel.setBounds(0,0,1000,100);
        headerPanel.setBackground(new Color(230, 57, 70));
        headerPanel.setLayout(null);


        backButton.setBounds(10,20,60,60);
        backButton.setFocusPainted(false);
        backButton.setBackground(greenColor);
        backButton.setBorderPainted(false);
        backButton.setFont(labelFont);
        backButton.setForeground(fontColor);

        titleLabel.setBounds(195, 14, 800, 80);
//        Font labelFont = titleLabel.getFont();
        titleLabel.setFont(customFont);
        titleLabel.setForeground(fontColor);


        headerPanel.add(backButton);
        headerPanel.add(titleLabel);
        headerPanel.add(logoLabel);

        //Buttons and Labels
        voteButton.setBounds(83,150,100,50);
        voteButton.setFocusPainted(false);
        voteButton.setBorderPainted(false);
        voteButton.setBackground(btnColor);
        voteButton.setForeground(fontColor);
        voteButton.setFont(labelFont);
        //voteButton.setOpaque(false);


        partiesButton.setBounds(383,150,  100,50);
        partiesButton.setFocusPainted(false);
        partiesButton.setBackground(new Color(230, 57, 70));
        partiesButton.setBorderPainted(false);
        partiesButton.setForeground(fontColor);
        partiesButton.setFont(labelFont);
       // partiesButton.setOpaque(false);
        partiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partiesClass = new PartiesClass();
                partiesClass.partieslogos();
                frame.setVisible(false);
            }
        });

        countButton.setBounds(240, 300, 100, 50);
        countButton.setFocusPainted(false);
        countButton.setBorderPainted(false);
        countButton.setBackground(new Color(230, 57, 70));
        countButton.setForeground(fontColor);
        countButton.setFont(labelFont);
        //countButton.setOpaque(false);


//        backButton.setOpaque(false);


        partiesLabel.setBounds(400,10,200,200);

        voteLabel.setBounds(100,10,200,200);

        countLabel.setBounds(250, 170, 200, 200);

        logoLabel.setBounds(830,5,100,94);


        //Adding Image to a Button
        try {
            Image voteImg = ImageIO.read(getClass().getResource("Images\\vote.png"));
            voteLabel.setIcon(new ImageIcon(voteImg));

            Image partiesImg = ImageIO.read(getClass().getResource("Images\\parties.png"));
            partiesLabel.setIcon(new ImageIcon(partiesImg));

//            Image backImg = ImageIO.read(getClass().getResource("Images\\back3.png"));
//            backButton.setIcon(new ImageIcon(backImg));

            Image logoImage = ImageIO.read(getClass().getResource("Images\\nepallogo.png"));
            logoLabel.setIcon(new ImageIcon(logoImage));


            Image countImg = ImageIO.read(getClass().getResource("Images\\count.png"));
            countLabel.setIcon(new ImageIcon(countImg));

        } catch (Exception ex) {
            System.out.println(ex);
        }


        userMenuPanel.add(voteButton);
        userMenuPanel.add(partiesButton);
        userMenuPanel.add(countButton);
        userMenuPanel.add(partiesLabel);
        userMenuPanel.add(voteLabel);
//        frame.add(backButton);
        userMenuPanel.add(countLabel);


        //adding elements to Frame

        //frame.add(titleLabel );

        frame.add(userMenuPanel);
        frame.add(headerPanel);




        //Frame setting
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(false);
        frame.getContentPane().setBackground(new Color(72,202,228));
        frame.setLocationRelativeTo(null);
    }
}
