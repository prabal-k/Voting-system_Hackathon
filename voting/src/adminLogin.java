import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class adminLogin implements ActionListener
{
    JFrame adminframe =new JFrame();
    JLabel username = new JLabel();
    JLabel password = new JLabel();
    public JTable dataTable;
    private DefaultTableModel tableModel;
    JTextField usernametextfield = new JTextField();
    JTextField passwordtextfield = new JTextField();

    JButton submitButton =new JButton();
    JButton backbutton =new JButton();


    void framesetup()
    {
        //frame setup
        adminframe.setResizable(false);
        adminframe.setLayout(null);
        adminframe.setVisible(true);
        adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminframe.setSize(500,500);
        adminframe.setLocation(650,280);
        adminframe.setLocationRelativeTo(null);

        adminframe.getContentPane().setBackground(new Color(135,206,250));

        username.setBounds(200,200,80,30);
        username.setText("Username: ");
        adminframe.add(username);

        password.setBounds(200,300,80,30);
        password.setText("Password: ");
        adminframe.add(password);

        usernametextfield.setBounds(300,200,80,30);
        adminframe.add(usernametextfield);

        passwordtextfield.setBounds(300,300,80,30);
        adminframe.add(passwordtextfield);

        submitButton.setBounds(300,400,80,30);
        submitButton.setText("Submit");
        adminframe.add(submitButton);

        //back button
        backbutton.setBounds(10,10,40,40);
//      backbutton.setText("");
        // Load the image icon
        ImageIcon backIcon = new ImageIcon("Images\\back.png"); // Replace with the actual path to your image

        // Set the image icon for the button
        backbutton.setIcon(backIcon);
        backbutton.setFocusPainted(false);
        adminframe.add(backbutton);

        submitButton.addActionListener((ActionListener) this);
        backbutton.addActionListener((ActionListener) this);


    }
    void adminPanel() {
        JFrame adminPanelFrame = new JFrame("Admin Panel");

        JPanel buttonPanel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        buttonPanel.setLayout(layout);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);


        Dimension buttonSize = new Dimension(150, 50);
        JButton insertButton = new JButton("Insert");
        insertButton.setPreferredSize(buttonSize);
        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(buttonSize);
        JButton updateButton = new JButton("Update");
        updateButton.setPreferredSize(buttonSize);
        JButton resultsButton = new JButton("Results");
        resultsButton.setPreferredSize(buttonSize);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setPreferredSize(buttonSize);

        refreshButton.addActionListener(e -> {
            // Call method to refresh data in the table
            try {
                retrieveAndDisplayData();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(adminPanelFrame, "Error retrieving data from the database");
            }
        });

        insertButton.addActionListener(e -> openInsertPanel());
        deleteButton.addActionListener(e -> openDeletePanel());
        updateButton.addActionListener(e -> openUpdatePanel());
        resultsButton.addActionListener(e -> openResultsPanel());

        buttonPanel.add(insertButton, gbc);
        buttonPanel.add(deleteButton, gbc);
        buttonPanel.add(updateButton, gbc);
        buttonPanel.add(resultsButton, gbc);
        buttonPanel.add(refreshButton, gbc);

        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);

        // Set up table model with column names
        tableModel.setColumnIdentifiers(new String[]{"Citizenship_No", "Name", "Birth_Year", "Father_Name", "Address", "City"});

        // Centering the button panel in the frame
        adminPanelFrame.setLayout(new GridBagLayout());
        GridBagConstraints frameGbc = new GridBagConstraints();
        frameGbc.gridx = 0;
        frameGbc.gridy = 0;
        frameGbc.weightx = 1;
        frameGbc.weighty = 1;
        frameGbc.fill = GridBagConstraints.NONE;

        adminPanelFrame.add(buttonPanel, frameGbc);

        frameGbc.gridx = 1;
        frameGbc.fill = GridBagConstraints.BOTH;
        adminPanelFrame.add(scrollPane, frameGbc);

        // Frame setup
        adminPanelFrame.setSize(500, 500);
        adminPanelFrame.setLocationRelativeTo(null);
        adminPanelFrame.setVisible(true);
        try {
            retrieveAndDisplayData();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(adminPanelFrame, "Error retrieving data from the database");
        }
    }
    public void retrieveAndDisplayData() throws ClassNotFoundException, SQLException {
        tableModel.setRowCount(0);
        sql_con _sql_con = new sql_con("root", "kist@123", this);

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "root", "kist@123");
        String query = "Select * from personal_info";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            // Retrieve data from the result set
            int citizenshipNo = rs.getInt("Citizenship_No");
            String name = rs.getString("Name");
            int birthYear = rs.getInt("Birth_Year");
            String fatherName = rs.getString("Father_Name");
            String address = rs.getString("Address");
            String city = rs.getString("City");

            // Add the data to the table model
            tableModel.addRow(new Object[]{citizenshipNo, name, birthYear, fatherName, address, city});
        }

        // Close resources
        rs.close();
        st.close();
        con.close();
    }
    void openInsertPanel() {
        JFrame insertFrame = new JFrame("Insert Panel");

        // Panel to hold the form components
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10)); // 6 rows, 2 columns

        // Labels and TextFields for each field
        JLabel citizenshipLabel = new JLabel("Citizenship No:");
        JTextField citizenshipTextField = new JTextField();

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameTextField = new JTextField();

        JLabel birthYearLabel = new JLabel("Birth Year:");
        JTextField birthYearTextField = new JTextField();

        JLabel fatherNameLabel = new JLabel("Father's Name:");
        JTextField fatherNameTextField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressTextField = new JTextField();

        JLabel cityLabel = new JLabel("City:");
        JTextField cityTextField = new JTextField();

        // Add labels and text fields to the form panel
        formPanel.add(citizenshipLabel);
        formPanel.add(citizenshipTextField);

        formPanel.add(nameLabel);
        formPanel.add(nameTextField);

        formPanel.add(birthYearLabel);
        formPanel.add(birthYearTextField);

        formPanel.add(fatherNameLabel);
        formPanel.add(fatherNameTextField);

        formPanel.add(addressLabel);
        formPanel.add(addressTextField);

        formPanel.add(cityLabel);
        formPanel.add(cityTextField);

        // Button to perform the insert operation
        JButton insertButton = new JButton("Insert");
        insertButton.addActionListener(e -> {
            // Get data from text fields
            int citizenshipNo = Integer.parseInt(citizenshipTextField.getText());
            String name = nameTextField.getText();
            int birthYear = Integer.parseInt(birthYearTextField.getText());
            String fatherName = fatherNameTextField.getText();
            String address = addressTextField.getText();
            String city = cityTextField.getText();

            sql_con sqlCon = new sql_con("root", "kist@123", this);
            sqlCon.insertDataIntoDatabase(citizenshipNo, name, birthYear, fatherName, address, city);

            // Optionally, close the insert frame or show a success message
            insertFrame.dispose();
        });

        // Add form panel and insert button to the insert frame
        insertFrame.setLayout(new BorderLayout());
        insertFrame.add(formPanel, BorderLayout.CENTER);
        insertFrame.add(insertButton, BorderLayout.SOUTH);

        // Set frame properties
        insertFrame.setSize(500, 500);
        insertFrame.setLocationRelativeTo(null);
        insertFrame.setVisible(true);
    }


    void openUpdatePanel() {
        JFrame validationFrame = new JFrame("Validation Panel");
        JPanel validationPanel = new JPanel();
        validationPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel validationLabel = new JLabel("Citizenship No:");
        JTextField validationTextField = new JTextField();

        JButton verifyButton = new JButton("Verify");
        verifyButton.addActionListener(e -> {
            int enteredCitizenshipNo = Integer.parseInt(validationTextField.getText());

            if (isCitizenshipNoValid(enteredCitizenshipNo)) {
                JOptionPane.showMessageDialog(validationFrame, "Successfully Verified");
                validationFrame.dispose();
                showUpdatePanel(enteredCitizenshipNo);
            } else {
                JOptionPane.showMessageDialog(validationFrame, "The details do not match. Please enter correct data.");
            }
        });

        validationPanel.add(validationLabel);
        validationPanel.add(validationTextField);
        validationPanel.add(new JLabel()); // Empty label for spacing
        validationPanel.add(verifyButton);

        validationFrame.setLayout(new BorderLayout());
        validationFrame.add(validationPanel, BorderLayout.CENTER);
        validationFrame.setSize(300, 150);
        validationFrame.setLocationRelativeTo(null);
        validationFrame.setVisible(true);
    }
    private boolean isCitizenshipNoValid(int enteredCitizenshipNo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "root", "kist@123");

            String query = "SELECT * FROM personal_info WHERE Citizenship_No=?";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, enteredCitizenshipNo);
                ResultSet resultSet = preparedStatement.executeQuery();

                return resultSet.next();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error validating citizenship no");
            return false;
        }
    }
    private void showUpdatePanel(int citizenshipNo) {
        JFrame updateFrame = new JFrame("Update Panel");
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 10, 10));

        JLabel citizenshipLabel = new JLabel("Citizenship No:");
        JTextField citizenshipTextField = new JTextField(Integer.toString(citizenshipNo));
        citizenshipTextField.setEditable(false);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameTextField = new JTextField();

        JLabel birthYearLabel = new JLabel("Birth Year:");
        JTextField birthYearTextField = new JTextField();

        JLabel fatherNameLabel = new JLabel("Father's Name:");
        JTextField fatherNameTextField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressTextField = new JTextField();

        JLabel cityLabel = new JLabel("City:");
        JTextField cityTextField = new JTextField();

        formPanel.add(citizenshipLabel);
        formPanel.add(citizenshipTextField);

        formPanel.add(nameLabel);
        formPanel.add(nameTextField);

        formPanel.add(birthYearLabel);
        formPanel.add(birthYearTextField);

        formPanel.add(fatherNameLabel);
        formPanel.add(fatherNameTextField);

        formPanel.add(addressLabel);
        formPanel.add(addressTextField);

        formPanel.add(cityLabel);
        formPanel.add(cityTextField);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            int citizenshipNoToUpdate = Integer.parseInt(citizenshipTextField.getText());
            String name = nameTextField.getText();
            int birthYear = Integer.parseInt(birthYearTextField.getText());
            String fatherName = fatherNameTextField.getText();
            String address = addressTextField.getText();
            String city = cityTextField.getText();

            sql_con sqlCon = new sql_con("root", "kist@123", this);
            sqlCon.updateDataInDatabase(citizenshipNoToUpdate, name, birthYear, fatherName, address, city);

            updateFrame.dispose();
        });

        updateFrame.setLayout(new BorderLayout());
        updateFrame.add(formPanel, BorderLayout.CENTER);
        updateFrame.add(updateButton, BorderLayout.SOUTH);

        updateFrame.setSize(500, 500);
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
    }
    void openDeletePanel() {
        JFrame validationFrame = new JFrame("Validation Panel");
        JPanel validationPanel = new JPanel();
        validationPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel validationLabel = new JLabel("Citizenship No:");
        JTextField validationTextField = new JTextField();

        JButton verifyButton = new JButton("Verify");
        verifyButton.addActionListener(e -> {
            int enteredCitizenshipNo = Integer.parseInt(validationTextField.getText());

            if (isCitizenshipNoValid(enteredCitizenshipNo)) {
                JOptionPane.showMessageDialog(validationFrame, "Successfully Verified");
                validationFrame.dispose();
                showDeletePanel(enteredCitizenshipNo);
            } else {
                JOptionPane.showMessageDialog(validationFrame, "The details do not match. Please enter correct data.");
            }
        });

        validationPanel.add(validationLabel);
        validationPanel.add(validationTextField);
        validationPanel.add(new JLabel()); // Empty label for spacing
        validationPanel.add(verifyButton);

        validationFrame.setLayout(new BorderLayout());
        validationFrame.add(validationPanel, BorderLayout.CENTER);
        validationFrame.setSize(300, 150);
        validationFrame.setLocationRelativeTo(null);
        validationFrame.setVisible(true);
    }

    private void showDeletePanel(int citizenshipNo) {
        JFrame deleteFrame = new JFrame("Delete Panel");
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(1, 2, 10, 10));

        JLabel citizenshipLabel = new JLabel("Citizenship No:");
        JTextField citizenshipTextField = new JTextField(Integer.toString(citizenshipNo));
        citizenshipTextField.setEditable(false);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int citizenshipNoToDelete = Integer.parseInt(citizenshipTextField.getText());

            sql_con sqlCon = new sql_con("root", "kist@123", this);
            sqlCon.deleteDataFromDatabase(citizenshipNoToDelete);

            deleteFrame.dispose();
        });

        formPanel.add(citizenshipLabel);
        formPanel.add(citizenshipTextField);

        deleteFrame.setLayout(new BorderLayout());
        deleteFrame.add(formPanel, BorderLayout.CENTER);
        deleteFrame.add(deleteButton, BorderLayout.SOUTH);

        deleteFrame.setSize(500, 200);
        deleteFrame.setLocationRelativeTo(null);
        deleteFrame.setVisible(true);
    }

    void openResultsPanel() {
        JFrame resultsFrame = new JFrame("Results Panel");
        resultsFrame.setLayout(new BorderLayout()); // Use BorderLayout for better positioning

        // Panel for labels
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        JLabel totalVotesLabel = new JLabel();
        JLabel leadingPartiesLabel = new JLabel("<html>Leading Parties:<br>");

        // Table for displaying party info
        DefaultTableModel partyInfoModel = new DefaultTableModel();
        JTable partyInfoTable = new JTable(partyInfoModel);
        JScrollPane scrollPane = new JScrollPane(partyInfoTable);
        partyInfoModel.setColumnIdentifiers(new String[]{"Party Name", "Vote Count"});

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "root", "kist@123");

            // Query to get the total number of votes
            String totalVotesQuery = "SELECT SUM(Vote_Count) AS TotalVotes FROM party_info";

            // Query to find the leading parties
            String leadingPartiesQuery = "SELECT Party_Name FROM party_info WHERE Vote_Count = (SELECT MAX(Vote_Count) FROM party_info)";

            // Execute queries and populate labels and table
            try (Statement stmt = con.createStatement()) {
                ResultSet totalVotesRs = stmt.executeQuery(totalVotesQuery);
                if (totalVotesRs.next()) {
                    int totalVotes = totalVotesRs.getInt("TotalVotes");
                    totalVotesLabel.setText("Total Votes: " + totalVotes);
                }

                ResultSet leadingPartiesRs = stmt.executeQuery(leadingPartiesQuery);
                while (leadingPartiesRs.next()) {
                    String leadingParty = leadingPartiesRs.getString("Party_Name");
                    leadingPartiesLabel.setText(leadingPartiesLabel.getText() + leadingParty + "<br>");
                }
                leadingPartiesLabel.setText(leadingPartiesLabel.getText() + "</html>"); // Close HTML tag

                ResultSet partyInfoRs = stmt.executeQuery("SELECT * FROM party_info");
                while (partyInfoRs.next()) {
                    partyInfoModel.addRow(new Object[]{
                            partyInfoRs.getString("Party_Name"),
                            partyInfoRs.getInt("Vote_Count")
                    });
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(resultsFrame, "Database error occurred.");
        }

        // Add labels to the label panel
        labelPanel.add(totalVotesLabel);
        labelPanel.add(leadingPartiesLabel);

        // Add components to the frame
        resultsFrame.add(labelPanel, BorderLayout.CENTER);
        resultsFrame.add(scrollPane, BorderLayout.EAST); // Table on the right side

        // Frame setup
        resultsFrame.setSize(800, 600); // Adjusted for better visibility
        resultsFrame.setLocationRelativeTo(null);
        resultsFrame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton)
        {
            String username = usernametextfield.getText();
            String password = passwordtextfield.getText();

            sql_con _sql_con = new sql_con(username, password, this);
            try {
                _sql_con.databaseconnection();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==backbutton)
        {
            adminframe.setVisible(false);
            mainmenu.frame.setVisible(true);
        }
    }
}
