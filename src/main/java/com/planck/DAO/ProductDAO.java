package com.planck.DAO;

import com.planck.Model.Product;
import com.planck.Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {

    Connection connection = new Connection();

    public void createProduct(long productId, double purchaseVAT, long providerNit, String productName, double purchasePrice, double salePrice) {
        String query = "INSERT INTO productos (codigo_producto, ivaventa, nitproveedor, nombre_producto, precio_compra, precio_venta) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setLong(1, productId);
            statement.setString(2, (purchaseVAT+""));
            statement.setString(3, (providerNit+""));
            statement.setString(4, productName);
            statement.setString(5, (purchasePrice+""));
            statement.setString(6, (salePrice+""));
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos añadiendo productos");
            e.printStackTrace();
        }
    }

    public void createProduct(Product product) {
        String query = "INSERT INTO productos (codigo_producto, ivaventa, nitproveedor, nombre_producto, precio_compra, precio_venta) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setLong(1, product.getProductId());
            statement.setString(2, (product.getPurchaseVAT()+""));
            statement.setString(3, (product.getProviderNit()+""));
            statement.setString(4, product.getProductName());
            statement.setString(5, (product.getPurchasePrice()+""));
            statement.setString(6, (product.getSalePrice()+""));
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos añadiendo productos");
            e.printStackTrace();
        }
    }

    public ArrayList<Product> listProducts() {

        ArrayList<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM productos");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                long productId = result.getLong("codigo_producto");
                double purchaseVAT = result.getDouble("ivaventa");
                long providerNit = result.getLong("nitproveedor");
                String productName = result.getString("nombre_producto");
                double purchasePrice = result.getDouble("precio_compra");
                double salePrice = result.getDouble("precio_venta");
                products.add(new Product(productId, purchaseVAT, providerNit, productName, purchasePrice, salePrice));
            }
            result.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Ocurrió un error leyendo la base de datos de productos");
            e.printStackTrace();

        }
        return products;
    }

    public Product searchUser(String parameterName, String parameter) {
        String query = "SELECT * FROM productos WHERE " + parameterName + "=" + "'" + parameter + "'";
        Product response = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                long productId = result.getLong("codigo_producto");
                double purchaseVAT = result.getDouble("ivaventa");
                long providerNit = result.getLong("nitproveedor");
                String productName = result.getString("nombre_producto");
                double purchasePrice = result.getDouble("precio_compra");
                double salePrice = result.getDouble("precio_venta");
                response = new Product (productId, purchaseVAT, providerNit, productName, purchasePrice, salePrice);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos buscando el producto");
            e.printStackTrace();

        }
        return response;

    }
    //Seguir desde aquí

    public void updateUser(User user) {
        String query = "UPDATE usuarios SET email_usuario=? , nombre_usuario=? , password=? , usuario=? WHERE cedula_usuario=?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, user.getUserEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUser());
            statement.setLong(5, user.getUserIdCard());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos actualizando al usuario");
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        String query = "DELETE FROM usuarios WHERE cedula_usuario=?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setLong(1, user.getUserIdCard());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos eliminando al usuario");
            e.printStackTrace();
        }
    }

    public void deleteUser(long userIdCard) {
        String query = "DELETE FROM usuarios WHERE cedula_usuario=?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setLong(1, userIdCard);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos eliminando al usuario");
            e.printStackTrace();
        }
    }
}
