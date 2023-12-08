import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Enumeration;

public class Uservoting_parties {
    JFrame voterFrame = new JFrame("Voter Login Form");
    ButtonGroup buttonGroup = new ButtonGroup();
    JButton confirmbutton = new JButton("VOTE");
    private String currentUserCitizenshipNo;

    Uservoting_parties(String citizenshipNo) {
        this.currentUserCitizenshipNo = citizenshipNo;
        // Set up the JFrame
        voterFrame.setResizable(false);
        voterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        voterFrame.setSize(1000, 700);
        voterFrame.setLocationRelativeTo(null);

        // Confirm button
        confirmbutton.setBounds(500, 630, 80, 30);
        confirmbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if any radio button is selected
                if (buttonGroup.getSelection() == null) {
                    JOptionPane.showMessageDialog(voterFrame, "Please Select A Party To Vote");
                } else {
                    // Handle the case when a radio button is selected
                    handleVoteConfirmation();
                }
            }
        });

        // Load the background image using ImageIO
        try {
            Image backgroundImage = ImageIO.read(getClass().getResource("Images/untitled.png"));
            BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
            backgroundPanel.setLayout(null);

            // Define x and y positions for radio buttons
            int[] xPositions = {150, 390, 630, 870, 150, 390, 630, 870};
            int[] yPositions = {300, 300, 300, 300, 550, 550, 550, 550};

            // Add 8 radio buttons
            addRadioButtons(backgroundPanel, xPositions, yPositions);

            // Set the custom panel as the content pane of the JFrame
            voterFrame.setContentPane(backgroundPanel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Make the JFrame visible
        voterFrame.add(confirmbutton);
        voterFrame.setVisible(true);
    }

    private void handleVoteConfirmation() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "root", "kist@123");

            // Assuming you have a way to get the current user's Citizenship_No
            String currentUserCitizenshipNo = getCurrentUserCitizenshipNo(); // Implement this method

            // Check if the user has already voted
            String checkVoteStatusQuery = "SELECT Vote_Status FROM vote_info WHERE Citizenship_No = ?";
            try (PreparedStatement checkVoteStatusStatement = con.prepareStatement(checkVoteStatusQuery)) {
                checkVoteStatusStatement.setString(1, currentUserCitizenshipNo);
                ResultSet voteStatusResult = checkVoteStatusStatement.executeQuery();

                if (voteStatusResult.next() && "Done".equals(voteStatusResult.getString("Vote_Status"))) {
                    JOptionPane.showMessageDialog(voterFrame, "Sorry!! You have already voted");
                    return;
                }
            }

            // Get the selected party name
            String selectedParty = getSelectedPartyName();

            // Update the vote count for the selected party
            String updatePartyVoteQuery = "UPDATE party_info SET Vote_Count = Vote_Count + 1 WHERE Party_Name = ?";
            try (PreparedStatement updatePartyVoteStatement = con.prepareStatement(updatePartyVoteQuery)) {
                updatePartyVoteStatement.setString(1, selectedParty);
                updatePartyVoteStatement.executeUpdate();
            }

            // Update the voting status in vote_info table
            String updateVoteStatusQuery = "UPDATE vote_info SET Vote_Status = 'Done' WHERE Citizenship_No = ?";
            try (PreparedStatement updateVoteStatusStatement = con.prepareStatement(updateVoteStatusQuery)) {
                updateVoteStatusStatement.setString(1, currentUserCitizenshipNo);
                updateVoteStatusStatement.executeUpdate();
            }

            JOptionPane.showMessageDialog(voterFrame, "Vote Confirmed for " + selectedParty + "!");

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(voterFrame, "Database error occurred.");
        }
    }
    private String getCurrentUserCitizenshipNo() {
        return this.currentUserCitizenshipNo;
    }
    // Method to get the selected party from the radio buttons
    private String getSelectedPartyName() {
        if (buttonGroup.getSelection() != null) {
            return buttonGroup.getSelection().getActionCommand(); // Get the selected party name
        }
        return null;
    }

    // Method to update the vote count for the selected party
    private void updateVoteCount(Connection con, String selectedParty) throws SQLException {
        // SQL query to update the vote count
        String updateQuery = "UPDATE party_info SET Vote_Count = Vote_Count + 1 WHERE Party_Name = ?";

        // Prepare the statement to avoid SQL injection
        try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, selectedParty);
            preparedStatement.executeUpdate();
        }
    }

    private Image loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void addRadioButtons(JPanel panel, int[] xPositions, int[] yPositions) {
        String[] partyNames = {
                "National Socialist Party", "Communist Party Of Nepal(MC)",
                "Peoples Socialist Party", "National Independent Party",
                "National Democratic Party", "Nepali Congress",
                "Public Opinion Party", "Communist Party Of Nepal(UML)"
        };

        for (int i = 0; i < xPositions.length; i++) {
            JRadioButton radioButton = new JRadioButton();
            radioButton.setBounds(xPositions[i], yPositions[i], 20, 20);
            radioButton.setActionCommand(partyNames[i]); // Set the party name as action command
            buttonGroup.add(radioButton);
            panel.add(radioButton);
        }
    }
    // Custom panel for background image
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, this);
            }
        }
    }
}
