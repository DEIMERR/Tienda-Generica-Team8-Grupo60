package com.planck.DAO;
import com.planck.Model.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO {

    Connection connection = new Connection();

    public void createCustomer(long customerIdCard, String customerAddress, String customerEmail, String customerName, String customerPhone) {
        String query = "INSERT INTO customers (customerIdCard, customerAddress, customerEmail, customerName, customerPhone) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setLong(1, customerIdCard);
            statement.setString(2, customerAddress);
            statement.setString(3, customerEmail);
            statement.setString(4, customerName);
            statement.setString(5, customerPhone);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos añadiendo al cliente");
            e.printStackTrace();
        }
    }

    public void createCustomer(Customer customer) {
        String query = "INSERT INTO customers (customerIdCard, customerAddress, customerEmail, customerName, customerPhone) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setLong(1, customer.getCustomerIdCard());
            statement.setString(2, customer.getCustomerAddress());
            statement.setString(3, customer.getCustomerEmail());
            statement.setString(4, customer.getCustomerName());
            statement.setString(5, customer.getCustomerPhone());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos añadiendo al cliente");
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> listCustomers() {

        ArrayList<Customer> customers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM customers");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                long customerIdCard = result.getLong("customerIdCard");
                String customerAddress = result.getString("customerAddress");
                String customerEmail = result.getString("customerEmail");
                String customerName = result.getString("customerName");
                String customerPhone = result.getString("customerPhone");
                customers.add(new Customer(customerIdCard, customerAddress, customerEmail, customerName, customerPhone));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error leyendo la base de datos de clientes");
            e.printStackTrace();

        }
        return customers;
    }

    public Customer searchCustomer(String parameterName, String parameter) {
        String query = "SELECT * FROM customers WHERE " + parameterName + "=" + "'" + parameter + "'";
        Customer response = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                long customerIdCard = result.getLong("customerIdCard");
                String customerAddress = result.getString("customerAddress");
                String customerEmail = result.getString("customerEmail");
                String customerName = result.getString("customerName");
                String customerPhone = result.getString("customerPhone");
                response = new Customer(customerIdCard, customerAddress, customerEmail, customerName, customerPhone);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos buscando al cliente");
            e.printStackTrace();

        }
        return response;

    }

    public void updateCustomer(Customer customer) {
        String query = "UPDATE customers SET customerAddress=?, customerEmail=?, customerName=?, customerPhone=? WHERE customerIdCard=?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, customer.getCustomerAddress());
            statement.setString(2, customer.getCustomerEmail());
            statement.setString(3, customer.getCustomerName());
            statement.setString(4, customer.getCustomerPhone());
            statement.setLong(5, customer.getCustomerIdCard());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos actualizando al cliente");
            e.printStackTrace();
        }
    }

    public void deleteCustomer(Customer customer) {
        String query = "DELETE FROM customers WHERE customerIdCard=?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setLong(1, customer.getCustomerIdCard());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos eliminando al cliente");
            e.printStackTrace();
        }
    }

    public void deleteCustomer(long customerIdCard) {
        String query = "DELETE FROM customers WHERE customerIdCard=?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setLong(1, customerIdCard);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos eliminando al cliente");
            e.printStackTrace();
        }
    }
}

