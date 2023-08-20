package API;

import Model.API.EmployeeEntity;
import Model.API.EmployeeFromAPI;

import java.io.IOException;
import java.util.List;

public interface EmployeeService extends Authorizable {
    List<EmployeeFromAPI> getEmployees(int id) throws IOException;
    int createEmployee(EmployeeFromAPI employeeFromAPI) throws IOException;

    int createEmployeeEnt(EmployeeEntity employeeEntity) throws IOException;

    int createEmployeeUnauthorized(EmployeeFromAPI employeeFromAPI) throws IOException;

    int createEmployeeUnauthorizedEnt(EmployeeEntity employeeEntity) throws IOException;

    void updateEmployeeEnt(int id, EmployeeEntity employeeEntity) throws IOException;

    EmployeeFromAPI getEmployeeById(int id) throws IOException;





}
