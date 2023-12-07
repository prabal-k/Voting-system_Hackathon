import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class adminLogin implements ActionListener
{
    JFrame adminframe =new JFrame();
    JLabel username = new JLabel();
    JLabel password = new JLabel();

    JTextField usernametextfield = new JTextField();
    JTextField passwordtextfield = new JTextField();

    JButton submitButton =new JButton();

    void framesetup()
    {
        //frame setup
        adminframe.setResizable(false);
        adminframe.setLayout(null);
        adminframe.setVisible(true);
        adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminframe.setSize(800,800);
        adminframe.setLocation(650,280);
        adminframe.setLocationRelativeTo(null);

        //labels setup for user name and password
        username.setBounds(200,200,80,30);
        username.setText("Username: ");
        adminframe.add(username);

        password.setBounds(200,300,80,30);
        password.setText("Password: ");
        adminframe.add(password);


        //textfield setup for username and password input

        usernametextfield.setBounds(300,200,80,30);
        adminframe.add(usernametextfield);

        passwordtextfield.setBounds(300,300,80,30);
        adminframe.add(passwordtextfield);

        //submit button
        submitButton.setBounds(300,400,80,30);
        submitButton.setText("Submit");
        adminframe.add(submitButton);

        submitButton.addActionListener((ActionListener) this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton)
        {
            String username = usernametextfield.getText();
            String pasword = passwordtextfield.getText();

            sql_con _sql_con= new sql_con(username,pasword);
            try {
                _sql_con.databaseconnection();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
