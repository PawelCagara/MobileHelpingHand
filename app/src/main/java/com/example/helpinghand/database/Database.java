package com.example.helpinghand.database;

import android.database.Cursor;
import android.widget.EditText;

import com.example.helpinghand.model.User;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    User user = null;
    Connection conn = null;
    Statement stmt = null;
    String login = "cagarap";
    String password = "yestErla7";

    String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+login;

    public Database(){}

    private void openConnection(){

        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch(Exception e) { System.out.println("Failed"); }

        try{

            conn = DriverManager.getConnection(url, login, password);
            stmt = conn.createStatement();
        } catch(SQLException se) { System.out.println("failed to connect"); System.out.println(se); }
    }
    private void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private User getNextUser(ResultSet rs){
        User thisUser = null;
        try {
            thisUser = new User(
                    rs.getInt("id"),
                    rs.getString("userName"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("firstname"),
                    rs.getString("postcode"),
                    rs.getInt("group"),
                    rs.getInt("admin"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return thisUser;
    }

    public void addUser (String userName, String email, String password, String firstname, String postcode, int group, int admin) throws SQLException {
        openConnection();
        ResultSet rs = stmt.executeQuery("select max(id) from users");
        rs.next();
        // searching through DB for max ID then adding 1 to create next ID
        int id = rs.getInt(1) + 1;
        String query = "insert into users values ('"+id+"', '"+userName+"', '"+email+"', '"+password+"', '"+firstname+"', '"+postcode+"', '"+group+"', '"+admin+"')";
        stmt.executeUpdate(query);
        stmt.close();
        closeConnection();
    }

    public void updateUserProfile(String username,String email, String firstname, String postcode) throws SQLException {
        openConnection();
        String query = "Update users set firstname = '"+firstname+"' ,postcode = '"+postcode+"' , email = '"+email+"'  where username = '"+username+"'";
        stmt.executeUpdate(query);
        stmt.close();
        closeConnection();
    }

    public void updatePassword( String username, String password) throws SQLException {
        openConnection();
        String query = "Update users set password = '"+password+"' where username = '"+username+"'";
        stmt.executeUpdate(query);
        stmt.close();
        closeConnection();
    }

    public int checkIfUserExist(String username) throws SQLException {
        openConnection();
        ResultSet query =stmt.executeQuery ("SELECT Count(*) FROM users where username like '"+username+"'");
        query.next();
        int c = query.getInt(1);
        stmt.close();
        closeConnection();
        return c;
    }

    public int checkUsernameAndPassword (String username, String password) throws SQLException {
        openConnection();
        ResultSet query =stmt.executeQuery ("SELECT Count(*) FROM users where username like '"+username+"' && password like '"+password+"'");
        query.next();
        int c = query.getInt(1);
        stmt.close();
        closeConnection();
        return c;
    }

    public String readEmail(String username) throws SQLException {
        openConnection();
        ResultSet query =stmt.executeQuery ("SELECT email FROM users where username like '"+username+"' ");
        query.next();
        String email = query.getString(1);
        closeConnection();
        return email;
    }

    public String readFirstname(String username) throws SQLException {
        openConnection();
        ResultSet query =stmt.executeQuery ("SELECT firstname FROM users where username like '"+username+"' ");
        query.next();
        String firstname = query.getString(1);
        closeConnection();
        return firstname;
    }

    public String readPostcode(String username) throws SQLException {
        openConnection();
        ResultSet query =stmt.executeQuery ("SELECT postcode FROM users where username like '"+username+"' ");
        query.next();
        String postcode = query.getString(1);
        closeConnection();
        return postcode;
    }

    public void setAsGiverOrReciver(String username, int group) throws SQLException {
        openConnection();
        String query = "Update users set setGroup = '"+group+"' where username = '"+username+"'";
        stmt.executeUpdate(query);
        stmt.close();
        closeConnection();
    }

    public int checkUserGroup(String username) throws SQLException {
        openConnection();
        ResultSet query =stmt.executeQuery ("SELECT setGroup FROM users where username = '"+username+"' ");
        query.next();
        int groupNumber = query.getInt(1);
        closeConnection();
        return groupNumber;
    }




    public ArrayList<User> getUserByUsername (String username){
       ArrayList<User> userByUsername = new ArrayList<User>();
       openConnection();

       try{
           String selectSQL = "select * from users where username like '"+username+"'";
           ResultSet rs = stmt.executeQuery(selectSQL);
           while(rs.next()){
               user = getNextUser(rs);
               userByUsername.add(user);
           }
           stmt.close();
           closeConnection();

       } catch(SQLException se)
            {System.out.println(se);}
       return userByUsername;
    }



}
