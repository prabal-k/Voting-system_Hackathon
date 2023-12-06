import javax.swing.*;
import java.sql.*;

public class sql_con

{
    Connection con;
    String dbusername;
    String dbpass;
    boolean validate =false;


    public sql_con(String username, String pasword) {
        dbusername=username;
        dbpass=pasword;

    }

    void databaseconnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/voting","root","@prabal9869");

        String query = "Select * from admin_login";
        //open the statement
        Statement st=con.createStatement();

        //handel the results
        ResultSet rs=st.executeQuery(query);

        //display the results
        while(rs.next())
        {
            String username= rs.getString(("Username"));
            String pass=rs.getString("Password");
            System.out.println(username+" "+pass);
            System.out.println(dbusername+" "+dbpass);
            if(username.equals(dbusername) && pass.equals(dbpass))
            {
                JOptionPane.showMessageDialog(null,"Welcome");
            }
        else
            {

                JOptionPane.showMessageDialog(null,"invalid username or password");
            }


        }

    }
}
