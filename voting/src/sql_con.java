import javax.swing.*;
import java.sql.*;
public class sql_con
{
    Connection con;
    String dbusername;
    String dbpass;
    boolean validate =false;
    static adminLogin adminLoginRef;

    public sql_con(String username, String password, adminLogin ref) {
        dbusername = username;
        dbpass = password;
        this.adminLoginRef = ref;
    }

    void databaseconnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "root", "@qwe@123");

        String query = "Select * from admin_login";
        //open the statement
        Statement st = con.createStatement();

        //handel the results
        ResultSet rs = st.executeQuery(query);

        //display the results
        String username = null;
        String pass = null;
        while (rs.next()) {
            username = rs.getString(("Username"));
            pass = rs.getString("Password");
            System.out.println(username + " " + pass);
            System.out.println(dbusername + " " + dbpass);
            if (username.equals(dbusername) && pass.equals(dbpass)) {
                JOptionPane.showMessageDialog(null, "Welcome !!");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password !!");
            }

        }
        if (username.equals(dbusername) && pass.equals(dbpass)) {
            adminLoginRef.adminPanel();
            return;
        }
    }
    void insertDataIntoDatabase(int citizenshipNo, String name, int birthYear, String fatherName, String address, String city) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "root", "@qwe@123");

            // Prepare the SQL statement
            String insertQuery = "INSERT INTO personal_info (Citizenship_No, Name, Birth_Year, Father_Name, Address, City) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, citizenshipNo);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, birthYear);
                preparedStatement.setString(4, fatherName);
                preparedStatement.setString(5, address);
                preparedStatement.setString(6, city);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Data inserted successfully
                    JOptionPane.showMessageDialog(null, "Data inserted successfully!");
                } else {
                    // Insertion failed
                    JOptionPane.showMessageDialog(null, "Failed to insert data!");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting data into the database");
        }
    }
    void updateDataInDatabase(int citizenshipNo, String name, int birthYear, String fatherName, String address, String city) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "root", "@qwe@123");

            // Prepare the SQL statement
            String updateQuery = "UPDATE personal_info SET Name=?, Birth_Year=?, Father_Name=?, Address=?, City=? WHERE Citizenship_No=?";
            try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
                // Set values for parameters in the prepared statement
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, birthYear);
                preparedStatement.setString(3, fatherName);
                preparedStatement.setString(4, address);
                preparedStatement.setString(5, city);
                preparedStatement.setInt(6, citizenshipNo);

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Data updated successfully
                    JOptionPane.showMessageDialog(null, "Data updated successfully!");
                } else {
                    // Update failed
                    JOptionPane.showMessageDialog(null, "Failed to update data!");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating data in the database");
        }
    }
    public void deleteDataFromDatabase(int citizenshipNo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/voting", "root", "@qwe@123");

            String query = "DELETE FROM personal_info WHERE Citizenship_No=?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, citizenshipNo);
            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Data deleted successfully");
                adminLoginRef.retrieveAndDisplayData(); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(null, "No data found with the given Citizenship No");
            }

            preparedStatement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting data from the database");
        }
    }

}
