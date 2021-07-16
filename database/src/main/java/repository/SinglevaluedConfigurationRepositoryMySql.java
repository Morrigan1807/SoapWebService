package repository;

import lombok.extern.log4j.Log4j2;
import model.SinglevaluedConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class SinglevaluedConfigurationRepositoryMySql implements SinglevaluedConfigurationRepository {

    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/service";
    private static final String USER = "niagara";
    private static final String PASSWORD = "123456Qz!";

    private static final String DELETE = "DELETE FROM general_singlevalued_configuration WHERE attribute_name=?";
    private static final String SELECT_ALL = "SELECT * FROM general_singlevalued_configuration";
    private static final String SELECT_BY_ATTRIBUTE_NAME = "SELECT * FROM general_singlevalued_configuration WHERE attribute_name=?";
    private static final String INSERT = "INSERT INTO general_singlevalued_configuration(attribute_name, attribute_value, attribute_desc) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE general_singlevalued_configuration SET attribute_name=?, attribute_value=?, attribute_desc=? WHERE attribute_name=?";

    public void delete(String attributeName) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setString(1, attributeName);

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException exception) {
            log.error(exception.getMessage());
        }
    }

    public List<SinglevaluedConfiguration> selectAll() {
        List<SinglevaluedConfiguration> result = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                result.add(SinglevaluedConfiguration.builder()
                        .attributeName(resultSet.getString("attribute_name"))
                        .attributeValue(resultSet.getString("attribute_value"))
                        .attributeDesc(resultSet.getString("attribute_desc"))
                        .build());
            }
        } catch (SQLException | ClassNotFoundException exception) {
            log.error(exception.getMessage());
        }

        return result;
    }

    public SinglevaluedConfiguration selectByAttributeName(String attributeName) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ATTRIBUTE_NAME)) {
            statement.setString(1, attributeName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return SinglevaluedConfiguration.builder()
                        .attributeName(resultSet.getString("attribute_name"))
                        .attributeValue(resultSet.getString("attribute_value"))
                        .attributeDesc(resultSet.getString("attribute_desc"))
                        .build();
            }
        } catch (SQLException | ClassNotFoundException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }

    public void insert(SinglevaluedConfiguration singlevaluedConfiguration) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, singlevaluedConfiguration.getAttributeName());
            statement.setString(2, singlevaluedConfiguration.getAttributeValue());
            statement.setString(3, singlevaluedConfiguration.getAttributeDesc());

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException exception) {
            log.error(exception.getMessage());
        }
    }

    public void update(SinglevaluedConfiguration singlevaluedConfiguration) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, singlevaluedConfiguration.getAttributeName());
            statement.setString(2, singlevaluedConfiguration.getAttributeValue());
            statement.setString(3, singlevaluedConfiguration.getAttributeDesc());
            statement.setString(4, singlevaluedConfiguration.getAttributeName());

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException exception) {
            log.error(exception.getMessage());
        }
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER_NAME);
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
