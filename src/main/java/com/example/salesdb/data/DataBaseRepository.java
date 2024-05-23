package com.example.salesdb.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class DataBaseRepository implements Repository{
    private DataBaseConnector dataBaseConnector;
    public DataBaseRepository(
            DataBaseConnector dataBaseConnector) {
        this.dataBaseConnector = dataBaseConnector;
        try (Connection conn = dataBaseConnector.getConnection()) {
            String tableCreateStr =
                    "CREATE TABLE IF NOT EXISTS Employee (\n" +
                            "    id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                            "    department VARCHAR(255),\n" +
                            "    surnameAndInitials VARCHAR(255),\n" +
                            "    position VARCHAR(255),\n" +
                            "    qualification VARCHAR(255),\n" +
                            "    hoursWorkedNumber DOUBLE,\n" +
                            "    hourPay DOUBLE,\n" +
                            "    PRIMARY KEY (id)\n" +
                            ");";
            Statement createTable = conn.createStatement();
            createTable.execute(tableCreateStr);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean addEmployee(Employee sale) {
        int updCount = 0;
        try (Connection conn = dataBaseConnector.getConnection()) {
            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO Employee " +
                            "(department, surnameAndInitials, position, qualification, hoursWorkedNumber, hourPay) " +
                            "VALUES (?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, sale.getDepartment());
            preparedStatement.setString(2, sale.getSurnameAndInitials());
            preparedStatement.setString(3, sale.getPosition());
            preparedStatement.setString(4, sale.getQualification());
            preparedStatement.setDouble(5, sale.getHoursWorkedNumber());
            preparedStatement.setDouble(6, sale.getHourPay());
            updCount = preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    sale.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to retrieve auto-generated ID.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updCount > 0;
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        try(Connection connection = dataBaseConnector.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Employee;");
            while(rs.next()){
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("department"),
                        rs.getString("surnameAndInitials"),
                        rs.getString("position"),
                        rs.getString("qualification"),
                        rs.getDouble("hoursWorkedNumber"),
                        rs.getDouble("hourPay")
                ));
            }
            rs.close();
        } catch (SQLException exception) {
            System.out.println("Не відбулося підключення до БД");
            exception.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<String> getSalesByBuyerCode(String position) {
        List<String> list = new ArrayList<>();
        try (Connection connection = dataBaseConnector.getConnection()) {
            String query = "SELECT id, department, surnameAndInitials, position, qualification, hoursWorkedNumber, hourPay FROM Employee WHERE position = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, position);
                try (ResultSet rs = statement.executeQuery()) {
                    int id = 1;
                    while (rs.next()) {
                        String item = rs.getInt("id") + ") " +
                                rs.getString("department") + ", " +
                                rs.getString("surnameAndInitials") + ", " +
                                rs.getString("position") + ", " +
                                rs.getString("qualification") + ", " +
                                rs.getDouble("hoursWorkedNumber") + ", " +
                                rs.getDouble("hourPay");
                        list.add(item);
                    }
                }
            }
        } catch (SQLException exception) {
            System.out.println("Не відбулося підключення до БД");
            exception.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> getSalesByProductName(String qualification) {
        List<String> list = new ArrayList<>();
        try (Connection connection = dataBaseConnector.getConnection()) {
            String query = "SELECT id, department, surnameAndInitials, position, qualification, hoursWorkedNumber, hourPay FROM Employee WHERE qualification = ?;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, qualification);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        String code = rs.getInt("id") + ") " +
                                rs.getString("department") + ", " +
                                rs.getString("surnameAndInitials") + ", " +
                                rs.getString("position") + ", " +
                                rs.getString("qualification") + ", " +
                                rs.getDouble("hoursWorkedNumber") + ", " +
                                rs.getDouble("hourPay");
                        int num = rs.getInt("hoursWorkedNumber");
                        list.add(code);
                    }
                }
            }
        } catch (SQLException exception) {
            System.out.println("Не відбулося підключення до БД");
            exception.printStackTrace();
        }
        return list;
    }

    @Override
    public List<String> getPayroll() {
        List<String> list = new ArrayList<>();
        try (Connection connection = dataBaseConnector.getConnection()) {
            String query = "SELECT id, department, surnameAndInitials, position, qualification, hoursWorkedNumber, hourPay FROM Employee;";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet rs = statement.executeQuery()) {
                    int id = 1;
                    while (rs.next()) {
                        String item = rs.getInt("id") + ") " +
                                rs.getString("surnameAndInitials") + ", earned " +
                                (rs.getDouble("hoursWorkedNumber") * rs.getDouble("hourPay"));
                        list.add(item);
                    }
                }
            }
        } catch (SQLException exception) {
            System.out.println("Не відбулося підключення до БД");
            exception.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateSale(int id, Employee sale) {
        int updCount = 0;
        try (Connection conn = dataBaseConnector.getConnection()) {
            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE Employee " +
                            "SET department = ?, surnameAndInitials = ?, position = ?, qualification = ?, hoursWorkedNumber = ?, " +
                            "hourPay = ? " +
                            "WHERE id = ?;");
            preparedStatement.setString(1, sale.getDepartment());
            preparedStatement.setString(2, sale.getSurnameAndInitials());
            preparedStatement.setString(3, sale.getPosition());
            preparedStatement.setString(4, sale.getQualification());
            preparedStatement.setDouble(5, sale.getHoursWorkedNumber());
            preparedStatement.setDouble(6, sale.getHourPay());
            preparedStatement.setInt(7, id);
            updCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updCount > 0;
    }

    @Override
    public boolean deleteEmployee(int id) {
        int updCount = 0;
        try (Connection conn = dataBaseConnector.getConnection()) {
            PreparedStatement preparedStatement =
                    conn.prepareStatement("DELETE FROM Employee WHERE id = ?;");
            preparedStatement.setInt(1, id);
            updCount = preparedStatement.executeUpdate();

            try (PreparedStatement updateStatement =
                         conn.prepareStatement("UPDATE Employee SET id = id - 1 WHERE id > ?")) {
                updateStatement.setInt(1, id);
                updateStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updCount > 0;
    }

    @Override
    public Employee getSaleById(int id) {
        Employee employee = null;
        try(Connection connection = dataBaseConnector.getConnection()){
            PreparedStatement statement =
                    connection.prepareStatement("select * from Employee where id = ?;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("department"),
                        rs.getString("surnameAndInitials"),
                        rs.getString("position"),
                        rs.getString("qualification"),
                        rs.getDouble("hoursWorkedNumber"),
                        rs.getDouble("hourPay")
                );
            }
            rs.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }finally {
            return employee;
        }
    }
}