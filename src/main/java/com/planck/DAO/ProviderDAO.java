package com.planck.DAO;
import com.planck.Model.Provider;

import java.sql.*;
import java.util.ArrayList;

public class ProviderDAO {

    Connection connection = new Connection();

    public void createProvider(long providerNit, String providerCity, String providerAddress, String providerName, String providerPhone) {
        String query = "INSERT INTO providers (providerNit, providerCity, providerAddress, providerName, providerPhone) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setLong(1, providerNit);
            statement.setString(2, providerCity);
            statement.setString(3, providerAddress);
            statement.setString(4, providerName);
            statement.setString(5, providerPhone);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos añadiendo al proveedor");
            e.printStackTrace();
        }
    }

    public void createProvider(Provider provider) {
        String query = "INSERT INTO providers (providerNit, providerCity, providerAddress, providerName, providerPhone) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setLong(1, provider.getProviderNit());
            statement.setString(2, provider.getProviderCity());
            statement.setString(3, provider.getProviderAddress());
            statement.setString(4, provider.getProviderName());
            statement.setString(5, provider.getProviderPhone());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos añadiendo al proveedor");
            e.printStackTrace();
        }
    }

    public ArrayList<Provider> listProviders() {

        ArrayList<Provider> providers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement("SELECT * FROM providers");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                long providerNit = result.getLong("providerNit");
                String providerCity = result.getString("providerCity");
                String providerAddress = result.getString("providerAddress");
                String providerName = result.getString("providerName");
                String providerPhone = result.getString("providerPhone");
                providers.add(new Provider(providerNit, providerCity, providerAddress, providerName, providerPhone));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error leyendo la base de datos de proveedores");
            e.printStackTrace();

        }
        return providers;
    }

    public Provider searchProvider(String parameterName, String parameter) {
        String query = "SELECT * FROM providers WHERE " + parameterName + "=" + "'" + parameter + "'";
        Provider response = null;
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                long providerNit = result.getLong("providerNit");
                String providerCity = result.getString("providerCity");
                String providerAddress = result.getString("providerAddress");
                String providerName = result.getString("providerName");
                String providerPhone = result.getString("providerPhone");
                response = new Provider(providerNit, providerCity, providerAddress, providerName, providerPhone);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos buscando al proveedor");
            e.printStackTrace();

        }
        return response;

    }

    public void updateProvider(Provider provider) {
        String query = "UPDATE providers SET providerCity=?, providerAddress=?, providerName=?, providerPhone=? WHERE providerNit=?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setString(1, provider.getProviderCity());
            statement.setString(2, provider.getProviderAddress());
            statement.setString(3, provider.getProviderName());
            statement.setString(4, provider.getProviderPhone());
            statement.setLong(5, provider.getProviderNit());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos actualizando al proveedor");
            e.printStackTrace();
        }
    }

    public void deleteProvider(Provider provider) {
        String query = "DELETE FROM providers WHERE providerNit=?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setLong(1, provider.getProviderNit());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos eliminando al proveedor");
            e.printStackTrace();
        }
    }

    public void deleteProvider(long providerNit) {
        String query = "DELETE FROM providers WHERE providerNit=?";
        try {
            PreparedStatement statement = connection.getConnection().prepareStatement(query);
            statement.setLong(1, providerNit);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error con la base de datos eliminando al proveedor");
            e.printStackTrace();
        }
    }
}
