import RoundedPanels.RoundedButton;
import RoundedPanels.RoundedPanel;

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
    JLabel headerTitle = new JLabel("Admin Login");

    public JTable dataTable;
    private DefaultTableModel tableModel;
    JTextField usernametextfield = new JTextField();
    JTextField passwordtextfield = new JTextField();

    RoundedButton submitButton =new RoundedButton("Submit",10);
    RoundedButton backbutton =new RoundedButton("",40);

    RoundedPanel adminLoginPanel = new RoundedPanel(20);
    RoundedPanel headerPanel = new RoundedPanel(0);


    //Fonts
    Font customFont = new Font("Arial", Font.BOLD, 56);
    Font labelFont = new Font("Arial", Font.BOLD, 20);
    Font textBox = new Font("Arial",Font.BOLD,16);

    //Colors
    Color btnColor = new Color(230,57,70);
    Color fontColor = new Color(241,250,238);
    Color greenColor = new Color(87, 204, 153);
    JFrame insertFrame = new JFrame("Insert Panel");
    JFrame validationFrame = new JFrame("Validation Panel");
    JFrame deleteFrame = new JFrame("Delete Panel");
    JFrame resultsFrame = new JFrame("Results Panel");


    void framesetup()
    {
        adminframe.getContentPane().setBackground(new Color(72, 202, 228));

        //Panels
        adminLoginPanel.setBounds(200,175,600,400);
        adminLoginPanel.setBackground(new Color(255,255,255,100));
        adminLoginPanel.setLayout(null);

        headerPanel.setBounds(0,0,1000,100);
        headerPanel.setBackground(new Color(230, 57, 70));
        headerPanel.setLayout(null);

        headerTitle.setBounds(375,0,400,100);
        headerTitle.setFont(customFont);
        headerTitle.setForeground(Color.white);

        backbutton.setBounds(10,25,40,40);
        backbutton.setText("<");
        backbutton.setFont(labelFont);
        backbutton.setForeground(Color.white);
        backbutton.setBackground(greenColor);
//        // Load the image icon
//        ImageIcon backIcon = new ImageIcon("Images\\back.png"); // Replace with the actual path to your image
//        // Set the image icon for the button
//        backbutton.setIcon(backIcon);
        backbutton.setBorderPainted(false);
        backbutton.setFocusPainted(false);

        headerPanel.add(headerTitle);
        headerPanel.add(backbutton);

        username.setBounds(100,100,150,30);
        username.setText("Username: ");
        username.setForeground(new Color(241, 250, 238));
        username.setFont(labelFont);


        password.setBounds(100,200,150,30);
        password.setText("Password: ");
        password.setForeground(new Color(241, 250, 238));
        password.setFont(labelFont);


        usernametextfield.setBounds(260,100,200,30);
        usernametextfield.setFont(textBox);


        passwordtextfield.setBounds(260,200,200,30);
        passwordtextfield.setFont(textBox);

        submitButton.setBounds(250,280,150,50);
        submitButton.setBorderPainted(false);
        submitButton.setBackground(new Color(230,57,70));
        submitButton.setForeground(new Color(241,250,238));
        submitButton.setFont(labelFont);


        adminLoginPanel.add(username);
        adminLoginPanel.add(password);
        adminLoginPanel.add(usernametextfield);
        adminLoginPanel.add(passwordtextfield);
        adminLoginPanel.add(submitButton);



//        adminframe.add(backbutton);

        submitButton.addActionListener((ActionListener) this);
        backbutton.addActionListener((ActionListener) this);


        adminframe.add(adminLoginPanel);
        adminframe.add(headerPanel);


        //Frames
        adminframe.setResizable(false);
        adminframe.setLayout(null);
        adminframe.setVisible(true);
        adminframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminframe.setSize(1000,700);
        adminframe.setLocation(650,280);
        adminframe.setLocationRelativeTo(null);


    }
    void adminPanel() {
        JFrame adminPanelFrame = new JFrame("Admin Panel");

//        Color btnColor = new Color(230,57,70);
//        Color fontColor = new Color(241,250,238);

        adminPanelFrame.getContentPane().setBackground(new Color(72, 202, 228));

        RoundedPanel buttonPanel = new RoundedPanel(0);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        buttonPanel.setLayout(layout);
        buttonPanel.setBackground(new Color(255,255,255,100));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);


        Dimension buttonSize = new Dimension(250, 50);
        RoundedButton insertButton = new RoundedButton("Insert",15);
        insertButton.setPreferredSize(buttonSize);
        insertButton.setBorderPainted(false);
        insertButton.setBackground(btnColor);
        insertButton.setForeground(fontColor);
        insertButton.setFont(labelFont);

        RoundedButton deleteButton = new RoundedButton("Delete",15);
        deleteButton.setPreferredSize(buttonSize);
        deleteButton.setBorderPainted(false);
        deleteButton.setBackground(btnColor);
        deleteButton.setForeground(fontColor);
        deleteButton.setFont(labelFont);


        RoundedButton updateButton = new RoundedButton("Update",15);
        updateButton.setPreferredSize(buttonSize);
        updateButton.setBorderPainted(false);
        updateButton.setBackground(btnColor);
        updateButton.setForeground(fontColor);
        updateButton.setFont(labelFont);


        RoundedButton resultsButton = new RoundedButton("Results",15);
        resultsButton.setPreferredSize(buttonSize);
        resultsButton.setBorderPainted(false);
        resultsButton.setBackground(btnColor);
        resultsButton.setForeground(fontColor);
        resultsButton.setFont(labelFont);


        RoundedButton refreshButton = new RoundedButton("Refresh",15);
        refreshButton.setPreferredSize(buttonSize);
        refreshButton.setBorderPainted(false);
        refreshButton.setBackground(greenColor);
        refreshButton.setForeground(fontColor);
        refreshButton.setFont(labelFont);

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
        adminPanelFrame.setSize(1000, 700);
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
        sql_con _sql_con = new sql_con("root", "@qwe@123", this);

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "root", "@qwe@123");
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
        validationFrame.setVisible(false);
//        insertFrame.setVisible(false);
        resultsFrame.setVisible(false);
        deleteFrame.setVisible(false);

        // Panel to hold the form components
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10)); // 6 rows, 2 columns

        // Labels and TextFields for each field
        JLabel citizenshipLabel = new JLabel("Citizenship No:");
        citizenshipLabel.setFont(labelFont);
//        citizenshipLabel.setForeground(fontColor);

        JTextField citizenshipTextField = new JTextField();
        citizenshipTextField.setFont(labelFont);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);

        JTextField nameTextField = new JTextField();
        nameTextField.setFont(textBox);

        JLabel birthYearLabel = new JLabel("Birth Year:");
        birthYearLabel.setFont(labelFont);

        JTextField birthYearTextField = new JTextField();
        birthYearTextField.setFont(textBox);

        JLabel fatherNameLabel = new JLabel("Father's Name:");
        fatherNameLabel.setFont(labelFont);

        JTextField fatherNameTextField = new JTextField();
        fatherNameTextField.setFont(textBox);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(labelFont);

        JTextField addressTextField = new JTextField();
        addressTextField.setFont(textBox);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(labelFont);

        JTextField cityTextField = new JTextField();
        cityTextField.setFont(textBox);

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
        RoundedButton insertButton = new RoundedButton("Insert",15);
        insertButton.setBackground(btnColor);
        insertButton.setForeground(fontColor);
        insertButton.setBorderPainted(false);
        insertButton.setFont(labelFont);

        insertButton.addActionListener(e -> {
            // Get data from text fields
            int citizenshipNo = Integer.parseInt(citizenshipTextField.getText());
            String name = nameTextField.getText();
            int birthYear = Integer.parseInt(birthYearTextField.getText());
            String fatherName = fatherNameTextField.getText();
            String address = addressTextField.getText();
            String city = cityTextField.getText();

            sql_con sqlCon = new sql_con("root", "@qwe@123", this);
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
//        JFrame validationFrame = new JFrame("Validation Panel");

        insertFrame.setVisible(false);
        deleteFrame.setVisible(false);
        resultsFrame.setVisible(false);
        JPanel validationPanel = new JPanel();
        validationPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel validationLabel = new JLabel("Citizenship No:");
        JTextField validationTextField = new JTextField();

        RoundedButton verifyButton = new RoundedButton("Verify",15);
        verifyButton.setFont(labelFont);
        verifyButton.setBackground(btnColor);
        verifyButton.setBorderPainted(false);
        verifyButton.setForeground(fontColor);



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
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "root", "@qwe@123");

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

            sql_con sqlCon = new sql_con("root", "@qwe@123", this);
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
        insertFrame.setVisible(false);
        validationFrame.setVisible(false);
        resultsFrame.setVisible(false);

        JPanel validationPanel = new JPanel();
        validationPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel validationLabel = new JLabel("Citizenship No:");
        JTextField validationTextField = new JTextField();

        RoundedButton verifyButton = new RoundedButton("Verify",15);
        verifyButton.setFont(labelFont);
        verifyButton.setBackground(btnColor);
        verifyButton.setBorderPainted(false);
        verifyButton.setForeground(fontColor);


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
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(1, 2, 10, 10));

        JLabel citizenshipLabel = new JLabel("Citizenship No:");
        JTextField citizenshipTextField = new JTextField(Integer.toString(citizenshipNo));
        citizenshipTextField.setEditable(false);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int citizenshipNoToDelete = Integer.parseInt(citizenshipTextField.getText());

            sql_con sqlCon = new sql_con("root", "@qwe@123", this);
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
//        JFrame resultsFrame = new JFrame("Results Panel");
        // Add your results panel components and setup here

        validationFrame.setVisible(false);
//        insertFrame.setVisible(false);
        insertFrame.setVisible(false);
        deleteFrame.setVisible(false);

        resultsFrame.setSize(600, 600);
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
