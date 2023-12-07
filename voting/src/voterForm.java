import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class voterForm implements ActionListener{
    JFrame voterFrame = new JFrame("Votee Login Form");
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
    boolean matchFound = false;


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

        submitBtn.addActionListener((ActionListener) this);

    }

    void uservalidate()
    {

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            String cno = citizion_noText.getText();
            String year = ageText.getText();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "root", "kist@123");

                // Validate user against personal_info
                String validationQuery = "SELECT * FROM personal_info WHERE Citizenship_No = ? AND Birth_Year = ?";
                try (PreparedStatement validationStatement = con.prepareStatement(validationQuery)) {
                    validationStatement.setString(1, cno);
                    validationStatement.setString(2, year);
                    ResultSet validationResultSet = validationStatement.executeQuery();

                    if (validationResultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Successful Validation!!");
                        //voterFrame.setVisible(false);
                        // User is valid, now check vote_info
                        String checkQuery = "SELECT * FROM vote_info WHERE Citizenship_No = ? AND Birth_Year = ?";
                        try (PreparedStatement checkStatement = con.prepareStatement(checkQuery)) {
                            checkStatement.setString(1, cno);
                            checkStatement.setString(2, year);
                            ResultSet checkResult = checkStatement.executeQuery();

                            if (!checkResult.next()) {
                                // User does not exist in vote_info, insert data
                                String insertQuery = "INSERT INTO vote_info (Citizenship_No, Birth_Year, Vote_Status) VALUES (?, ?, 'Not Done')";
                                try (PreparedStatement insertStatement = con.prepareStatement(insertQuery)) {
                                    insertStatement.setString(1, cno);
                                    insertStatement.setString(2, year);
                                    insertStatement.executeUpdate();
                                }
                            }

                            // Proceed to Uservoting_parties
                            Uservoting_parties _uservotingparties = new Uservoting_parties(cno);
                            //voterFrame.setVisible(false);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Your Details Doesn't Match !! Please Enter Correct Info");
                        citizion_noText.setText("");
                        ageText.setText("");
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error occurred.");
            }
        }
    }

}
