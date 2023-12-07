import RoundedPanels.RoundedButton;
import RoundedPanels.RoundedPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class mainmenu implements ActionListener{
     static JFrame frame = new JFrame();
    JLabel label = new JLabel();
    RoundedButton userBtn = new RoundedButton("User",30);
    RoundedButton adminBtn = new RoundedButton("Dashboard",30);
    public UserMenu _menu;

    //Panel
    private RoundedPanel mainPanel;

    //Class
    voterForm _voterForm = new voterForm();

    mainmenu(){
        frame.getContentPane().setBackground(new Color(135, 206, 250)); // RGB values for light blue

        JLabel label = new JLabel();
        label.setBounds(20,80,393,500);
        frame.add(label);
        label.setHorizontalAlignment(JLabel.LEFT);
        try {
            Image img = ImageIO.read(getClass().getResource("Images\\electionCommission.png"));
            label.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Add the label to the content pane of the JFrame
//        frame.getContentPane().add(label, BorderLayout.WEST);
//        userBtn.setBounds(500,200,200,50);
//        adminBtn.setBounds(500,300,200,50);

//        userBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frame.setVisible(false);
//                _voterForm =  new voterForm();
////                _voterForm.voterFrame.setVisible(true);
//            }
//        });

//        frame.add(userBtn);
//        frame.add(adminBtn);
        userBtn.setBorderPainted(false);
        userBtn.setFocusPainted(false);
        adminBtn.setFocusPainted(false);



        mainPanel = new RoundedPanel(30);
        mainPanel.setBounds(500,175,400,300);
//        mainPanel.setBackground(Color.lightGray);
        mainPanel.setBackground(new Color(255, 255, 255, 100));
        mainPanel.setLayout(null);

        userBtn.setBounds(80,80,250,60);
        adminBtn.setBounds(80,160,250,60);

        mainPanel.add(userBtn);
        mainPanel.add(adminBtn);

        frame.add(mainPanel);

//        frame.add(mainPanel, BorderLayout.CENTER);
//        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));


        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        frame.setLocation(650,280);

        userBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
//                _menu = new UserMenu();
//                _menu.frame.setVisible(true);

                voterForm _voteform =new voterForm();
                _voterForm.voterFrame.setVisible(true);
                //_voteform.uservalidate();
            }
        });
        adminBtn.addActionListener((ActionListener) this);
        frame.setLocationRelativeTo(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==adminBtn)
        {
            frame.dispose();
            adminLogin login= new adminLogin();
            login.framesetup();

        }
    }
}
