package Dao;

import ConnectionManager.ConnectionManager;
import ConnectionManager.ConnectionManagerJdbcImpl;
import Manufacturer.ManufacturerDao;
import Manufacturer.ManufacturerDaoJdbcImps;
import Pojo.Manufacturer;
import Pojo.Mobile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MobileDaoJdbcImpl implements MobileDao {
    private static ConnectionManager connectionManager =
            ConnectionManagerJdbcImpl.getInstance();
    private static ManufacturerDao manufacturerDao = new ManufacturerDaoJdbcImps();

    @Override
    public boolean addMobile(Mobile mobile) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO mobile values (DEFAULT, ?, ?, ?)")) {

            preparedStatement.setString(1, mobile.getModel());
            preparedStatement.setFloat(2, mobile.getPrice());
            preparedStatement.setInt(3, mobile.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Mobile getMobileById(Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM mobile INNER JOIN manufacturer ON manufacturer_id = manufacturer.id WHERE mobile.id = ?")) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("in");
                Manufacturer manufacturer = new Manufacturer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("country"));

                Mobile mobile = new Mobile(
                        resultSet.getInt("id"),
                        resultSet.getString("model"),
                        resultSet.getFloat("price"),
                        manufacturer);
                return mobile;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateMobileById(Mobile mobile) {

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE mobile SET model=?, price=?, manufacturer_id=? " +
                             "WHERE id=?")) {

            preparedStatement.setString(1, mobile.getModel());
            preparedStatement.setFloat(2, mobile.getPrice());
            System.out.println(preparedStatement);
            preparedStatement.setInt(3, mobile.getManufacturer().getId());
            preparedStatement.setInt(4, mobile.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteMobileById(Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM mobile WHERE id=?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
