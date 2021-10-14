package com.planck.DAO;

import java.sql.*;

public class Connection {
    String database = "tiendagrupo60";
    String user = "root";
    String password = "admin123";
    String port = "3306";
    //String url = "localhost";
    String url = "tiendagrupo60.c47knbsonjdi.us-east-2.rds.amazonaws.com";
    String jdbcUrl = "jdbc:mysql://" + url+ ":" + port + "/" + database + "?user=" + user + "&password=" + password;

    java.sql.Connection connection = null;

    public java.sql.Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl);
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
