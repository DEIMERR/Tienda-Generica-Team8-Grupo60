package com.planck.DAO;

import java.sql.*;

public class Connection {
    String database = "store";
    String user = "root";
    String password = "admin";
    String url = "jdbc:mysql://localhost/" + database;

    java.sql.Connection connection = null;

    public java.sql.Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos");
            }
        }

        catch (SQLException | ClassNotFoundException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return connection;
    }
}
