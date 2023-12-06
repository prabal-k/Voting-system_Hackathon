import javax.swing.*;

public class mainmenu {
    JFrame frame = new JFrame();
    JLabel logoLebel = new JLabel();
    JButton userBtn = new JButton("User");
    JButton adminBtn = new JButton("Dashboard");

//        frame.setSize(800,700);

    mainmenu(){
        userBtn.setBounds(500,200,200,50);
        adminBtn.setBounds(500,300,200,50);


        frame.add(userBtn);
        frame.add(adminBtn);


        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocation(650,280);
    }


}
