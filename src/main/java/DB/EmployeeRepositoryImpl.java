package DB;

import Model.API.EmployeeEntity;
import Model.DB.EmployeeFromDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private Connection connection;
    private static final String SELECT_ALL_BY_COMPANY_ID = "select*from employee where \"company_id\"=?";
    private static final String INSERT_EMPLOYEE = "insert into employee(first_name,last_name,middle_name,phone,email," +
            "avatar_url,company_id,birthdate) values(?,?,?,?,?,?,?,?)";
    private static final String SELECT_BY_ID = "select*from employee where \"id\"=?";

    public EmployeeRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<EmployeeFromDB> getAll(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_COMPANY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<EmployeeFromDB> list = new ArrayList<>();
        while (resultSet.next()) {
            EmployeeFromDB employee = new EmployeeFromDB();
            employee.setId(resultSet.getInt("id"));
            employee.setIs_active(resultSet.getBoolean("is_active"));
            employee.setCreate_timestamp(resultSet.getTimestamp("create_timestamp"));
            employee.setChange_timestamp(resultSet.getTimestamp("change_timestamp"));
            employee.setFirst_name(resultSet.getString("first_name"));
            employee.setLast_name(resultSet.getString("last_name"));
            employee.setMiddle_name(resultSet.getString("middle_name"));
            employee.setPhone(resultSet.getString("phone"));
            employee.setEmail(resultSet.getString("email"));
            employee.setAvatar_url(resultSet.getString("avatar_url"));
            employee.setCompany_id(resultSet.getInt("company_id"));
            employee.setBirthdate(resultSet.getTimestamp("birthdate"));
            list.add(employee);
        }
        return list;
    }

    @Override
    public void addEmployee(EmployeeFromDB employee) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE);
        preparedStatement.setString(1, employee.getFirst_name());
        preparedStatement.setString(2, employee.getLast_name());
        preparedStatement.setString(3, employee.getMiddle_name());
        preparedStatement.setString(4, employee.getPhone());
        preparedStatement.setString(5, employee.getEmail());
        preparedStatement.setString(6, employee.getAvatar_url());
        preparedStatement.setInt(7, employee.getCompany_id());
        preparedStatement.setTimestamp(8, employee.getBirthdate());
        preparedStatement.executeUpdate();
    }

    @Override
    public void addEmployeeEnt(EmployeeEntity employee) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setString(3, employee.getMiddleName());
        preparedStatement.setString(4, employee.getPhone());
        preparedStatement.setString(5, employee.getEmail());
        preparedStatement.setString(6, employee.getAvatar());
        preparedStatement.setInt(7, employee.getCompanyId());
        preparedStatement.setDate(8, employee.getBirthdate());
        preparedStatement.executeUpdate();
    }

    @Override
    public EmployeeFromDB getById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        EmployeeFromDB employee = new EmployeeFromDB();
        resultSet.next();
        employee.setId(resultSet.getInt("id"));
        employee.setIs_active(resultSet.getBoolean("is_active"));
        employee.setCreate_timestamp(resultSet.getTimestamp("create_timestamp"));
        employee.setChange_timestamp(resultSet.getTimestamp("change_timestamp"));
        employee.setFirst_name(resultSet.getString("first_name"));
        employee.setLast_name(resultSet.getString("last_name"));
        employee.setMiddle_name(resultSet.getString("middle_name"));
        employee.setPhone(resultSet.getString("phone"));
        employee.setEmail(resultSet.getString("email"));
        employee.setAvatar_url(resultSet.getString("avatar_url"));
        employee.setCompany_id(resultSet.getInt("company_id"));
        employee.setBirthdate(resultSet.getTimestamp("birthdate"));
        return employee;
    }


}
