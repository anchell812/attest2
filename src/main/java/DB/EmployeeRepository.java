package DB;

import Model.API.EmployeeEntity;
import Model.DB.EmployeeFromDB;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeRepository {
    List<EmployeeFromDB> getAll(int id) throws SQLException;

    void addEmployee(EmployeeFromDB employee) throws SQLException;

    void addEmployeeEnt(EmployeeEntity employee) throws SQLException;

    EmployeeFromDB getById(int id) throws SQLException;
}
