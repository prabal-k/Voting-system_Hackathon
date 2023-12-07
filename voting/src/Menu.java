//public class Menu {
//}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Stack;

public class Menu {
    JFrame frame = new JFrame();
    JButton button1 = new JButton("Vote");
    JButton button2 = new JButton("Vote Eligibility");
    JButton button3 = new JButton("Parties");

    JButton button4 = new JButton("Vote Count");

    JLabel label1 = new JLabel("Nepal Election Commission");

    //ImageIcon icon1 = new ImageIcon("Images/vote.png");

   // URL iconURL = getClass().getResource("Images/vote.png");
    // iconURL is null when not found

    voterForm _voterForm;



    Menu()
    {

        button1.setBounds(100,400,130,40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _voterForm =  new voterForm();
                _voterForm.voterFrame.setVisible(true);
                frame.setVisible(false);
            }
        });

        button2.setBounds(350,400,130,40);
        button3.setBounds(600,400,  130,40);
        button4.setBounds(350, 600, 130, 40);
        label1.setBounds(330, 50, 200, 40);

        frame.add(label1);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        //     frame.setIconImage(icon1.getImage());
       // ImageIcon icon = new ImageIcon(iconURL);
        //frame.setIconImage(icon.getImage());

        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(false);

        frame.setLocationRelativeTo(null);
    }


}
