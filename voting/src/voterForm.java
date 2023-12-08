import RoundedPanels.RoundedButton;
import RoundedPanels.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class voterForm implements ActionListe`ner{
    JFrame voterFrame = new JFrame("Votee Login Form");
    JPanel voterFromPanel = new JPanel();

    JLabel citizion_No = new JLabel("Citizenship No :");
    JLabel age = new JLabel("Birth Year");
    JLabel headerTitle = new JLabel("Voter Login");
    JTextField citizion_noText = new JTextField();
    JTextField ageText = new JTextField();
    RoundedButton submitBtn = new RoundedButton("login",30);
    RoundedButton backBtn = new RoundedButton("<",50);
    Font customFont = new Font("Arial", Font.BOLD, 56);
    UserMenu _menu;

    Font labelFont = new Font("Arial", Font.BOLD, 20);
    Font textBox = new Font("Arial",Font.BOLD,16);

    Color btnColor = new Color(230,57,70);
    Color fontColor = new Color(241,250,238);
    Color greenColor = new Color(87, 204, 153);


    voterForm(){
        voterFromPanel.setBounds(200,175,600,400);
        voterFromPanel.setBackground(new Color(255,255,255,100));
        voterFromPanel.setLayout(null);

        headerPanel.setBounds(0,0,1000,100);
        headerPanel.setBackground(new Color(230, 57, 70));
        headerPanel.setLayout(null);

        headerTitle.setBounds(375,0,400,100);
        headerTitle.setFont(customFont);
        headerTitle.setForeground(Color.white);

        backBtn.setBounds(10,25,50,50);
        backBtn.setBackground(Color.green);
        backBtn.setBorderPainted(false);
        backBtn.setFont(labelFont);
        backBtn.setForeground(fontColor);

        headerPanel.add(headerTitle);
        headerPanel.add(backBtn);

        citizion_No.setBounds(130,70,200,50);
        citizion_No.setForeground(Color.white);
        citizion_No.setFont(labelFont);

        citizion_noText.setBounds(130,120,330,50);
        citizion_noText.setFont(textBox);

        age.setBounds(130,180,200,30);
        age.setForeground(Color.white);
        age.setFont(labelFont);

        ageText.setBounds(130,220,330,50);
        ageText.setFont(textBox);

        submitBtn.setBounds(200,300,200,50);
        submitBtn.setBackground(new Color(230, 57, 70));
        submitBtn.setBorderPainted(false);
        submitBtn.setFont(labelFont);
        submitBtn.setForeground(new Color(241, 250, 238));

        voterFromPanel.add(citizion_No);
        voterFromPanel.add(citizion_noText);
        voterFromPanel.add(age);
        voterFromPanel.add(ageText);
        voterFromPanel.add(submitBtn);



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

        voterFrame.add(headerPanel);
        voterFrame.add(voterFromPanel);

//        voterFrame.add(backBtn);
//        voterFrame.add(headerTitle);
//        voterFrame.add(citizion_No);
//        voterFrame.add(age);
//        voterFrame.add(citizion_noText);
//        voterFrame.add(ageText);
//        voterFrame.add(submitBtn);

        voterFrame.getContentPane().setBackground(new Color(72, 202, 228));
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
