package Database;

import java.sql.*;

public class DatabaseConnection {
    public Connection connection;
    Statement statement;
    ResultSet resultSet;
    int val;
    public DatabaseConnection(){
        try {

            String username = "root";
            String password = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dsa",username,password);
            if (connection !=null){
                System.out.println("Connected to Database");
            }else {
                System.out.println("Error Connecting Database");
            }
            statement = connection.createStatement();
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    //prepared statement syntax
    //manipulate
    public int manipulate(PreparedStatement ps){
        try {
            val =ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return val;
    }

    // for retrieving
    public ResultSet retrieve(PreparedStatement ps){
        try {
            resultSet = ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet retrieve(String query){
        try {
            resultSet=statement.executeQuery(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public int manipulate(String query){
        try {
            val = statement.executeUpdate(query);
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return val;
    }


    public static void main(String[] args) {
        new DatabaseConnection();
    }
}
