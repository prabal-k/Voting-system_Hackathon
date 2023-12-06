import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainmenu implements ActionListener{
    JFrame frame = new JFrame();
    JLabel logoLebel = new JLabel();
    JButton userBtn = new JButton("User");
    JButton adminBtn = new JButton("Dashboard");
    public Menu _menu;

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
        frame.setSize(800,800);
        frame.setLocation(650,280);

        userBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                _menu = new Menu();
                _menu.frame.setVisible(true);
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
