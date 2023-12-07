import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class voterForm {
    JFrame voterFrame = new JFrame("Voteer Login Form");
    JPanel voterFromPanel = new JPanel();
    JLabel citizion_No = new JLabel("Citizenship No :");
    JLabel age = new JLabel("Birth Year");
    JLabel headerTitle = new JLabel("Vote");
    JTextField citizion_noText = new JTextField();
    JTextField ageText = new JTextField();
    JButton submitBtn = new JButton("login");
    JButton backBtn = new JButton("Back");
    Font customFont = new Font("Arial", Font.BOLD, 56);
    UserMenu _menu;

    voterForm(){
//        voterFromPanel.setBounds(50,70,700,500);
//        voterFromPanel.setBackground(Color.red);

        headerTitle.setBounds(365,10,200,100);
        headerTitle.setFont(customFont);
        citizion_No.setBounds(325,100,200,30);
        citizion_noText.setBounds(325,130,200,50);
        age.setBounds(325,180,200,30);
        ageText.setBounds(325,230,200,50);

        submitBtn.setBounds(325,330,200,50);
        backBtn.setBounds(10,10,100,50);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voterFrame.setVisible(false);
                _menu = new UserMenu();
                _menu.frame.setVisible(true);
            }
        });
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

//        voterFromPanel.add(citizion_noText);
//        voterFromPanel.add(ageText);
//        voterFrame.add(voterFromPanel);
        voterFrame.add(backBtn);
        voterFrame.add(headerTitle);
        voterFrame.add(citizion_No);
        voterFrame.add(age);
        voterFrame.add(citizion_noText);
        voterFrame.add(ageText);
        voterFrame.add(submitBtn);
        voterFrame.setResizable(false);
        voterFrame.setLayout(null);
        voterFrame.setVisible(false);
        voterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        voterFrame.setSize(800,500);
        voterFrame.setLocation(650,280);
    }

}
