package com.example.helpinghand.database;

import android.database.Cursor;

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
                    rs.getString("password"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return thisUser;
    }

    public void addUser (String userName, String email, String password) throws SQLException {
        openConnection();
        ResultSet rs = stmt.executeQuery("select max(id) from users");
        rs.next();
        // searching through DB for max ID then adding 1 to create next ID
        int id = rs.getInt(1) + 1;
        String query = "insert into users values ('"+id+"', '"+userName+"', '"+email+"', '"+password+"')";
       // String query = "insert into users values ('"+userName+"', '"+email+"', '"+password+"')";
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




    public ArrayList<User> getUserByUsername (String username){
       ArrayList<User> userByUsername = new ArrayList<User>();
       openConnection();

       try{
           String selectSQL = "select * from users where username like '%"+username+"%'";
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
