import javax.swing.*;
import java.awt.*;

public class voterForm {
    JFrame voterFrame = new JFrame("Voteer Login Form");
    JPanel voterFromPanel = new JPanel();
    JLabel citizion_No = new JLabel("Citizenship No :");
    JLabel age = new JLabel("Birth Year");

    JTextField citizion_noText = new JTextField();
    JTextField ageText = new JTextField();
    JButton submitBtn = new JButton();

    voterForm(){
//        voterFromPanel.setBounds(50,70,700,500);
//        voterFromPanel.setBackground(Color.red);

        citizion_No.setBounds(325,70,200,30);
        citizion_noText.setBounds(325,100,200,50);
        age.setBounds(325,150,200,30);
        ageText.setBounds(325,200,200,50);

//        submitBtn.setBounds(325,);

//        voterFromPanel.add(citizion_noText);
//        voterFromPanel.add(ageText);
//        voterFrame.add(voterFromPanel);
        voterFrame.add(citizion_No);
        voterFrame.add(age);
        voterFrame.add(citizion_noText);
        voterFrame.add(ageText);
        voterFrame.setResizable(false);
        voterFrame.setLayout(null);
        voterFrame.setVisible(false);
        voterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        voterFrame.setSize(800,600);
        voterFrame.setLocation(650,280);
    }

}
