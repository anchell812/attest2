package test;

import API.EmployeeService;
import DB.CompanyRepository;
import DB.EmployeeRepository;
import Model.API.EmployeeEntity;
import Model.API.EmployeeFromAPI;
import Model.DB.EmployeeFromDB;
import ext.CompanyRepositoryProvider;
import ext.EmployeeRepositoryProvider;
import ext.EmployeeServiceResolver;
import ext.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* Тесты

Получение списка сотрудников компании
Добавление нового сотрудника к компании
Получение сотрудника по id
Изменение информации о сотруднике
Получение сотрудника по несуществующему id
Изменение сотрудника по несуществующему id
Добавление нового сотрудника не админом
Добавление нового сотрудника к отсутствующей компании
Изменение информации о сотруднике не админом
Получение списка сотрудников несуществующей компании
Получение списка сотрудников компании без сотрудников
Добавление сотрудника без обязательного поля

*/

@ExtendWith({EmployeeRepositoryProvider.class, CompanyRepositoryProvider.class, EmployeeServiceResolver.class,})
public class EmployeeTests {

    @Test
    @DisplayName("1. Get a list of employees")
    public void getListOfEmployees(CompanyRepository companyRepository, EmployeeRepository employeeRepository,
                                   EmployeeService service) throws SQLException, IOException {

        int companyId = companyRepository.create();
        EmployeeFromDB createdEmployee = new EmployeeFromDB();
        employeeRepository.addEmployee(createdEmployee);

        List<EmployeeFromDB> list1 = employeeRepository.getAll(companyId);
        List<EmployeeFromAPI> list2 = service.getEmployees(companyId);
        assertEquals(list1.size(), list2.size());
    }

    @Test
    @DisplayName("2. Add employee by admin")
    public void adminCreateEmployee(CompanyRepository companyRepository, EmployeeRepository employeeRepository,
                                    @AdminAuthorized EmployeeService service) throws SQLException, IOException {

        int companyId = companyRepository.create();

        EmployeeEntity createdEmployee = new EmployeeEntity(1, true, "Test", "Tests", "MidName", "79991111111",
                "email@ya.ru", companyId);
        service.createEmployeeEnt(createdEmployee);
        List<EmployeeFromDB> list = employeeRepository.getAll(companyId);
        assertEquals(1, list.size());
        EmployeeFromDB employeeBack = employeeRepository.getById(list.get(0).getId());
        assertEquals(createdEmployee.getFirstName(), employeeBack.getFirst_name());
        assertEquals(createdEmployee.getLastName(), employeeBack.getLast_name());
        assertEquals(createdEmployee.getMiddleName(), employeeBack.getMiddle_name());
        assertEquals(createdEmployee.getPhone(), employeeBack.getPhone());
        assertEquals(createdEmployee.getEmail(), employeeBack.getEmail());
        assertEquals(createdEmployee.getCompanyId(), employeeBack.getCompany_id());
    }

    @Test
    @DisplayName("2.2 Add employee")
    public void clientShouldNotCreateEmployee(CompanyRepository companyRepository,
                                              EmployeeRepository employeeRepository,
                                              @Authorized EmployeeService service) throws IOException, SQLException {

        int companyId = companyRepository.create();
        List<EmployeeFromDB> list1 = employeeRepository.getAll(companyId);
        assertEquals(0, list1.size());

        EmployeeEntity createdEmployee = new EmployeeEntity(2, true, "Test123", "Tests123", "MidName", "79991111111",
                "email123@ya.ru", companyId);
        service.createEmployeeEnt(createdEmployee);
        List<EmployeeFromDB> list2 = employeeRepository.getAll(companyId);
        assertEquals(1, list2.size());
    }

    @Test
    @DisplayName("3. Get employee by Id")
    public void employeeById(CompanyRepository companyRepository, EmployeeRepository employeeRepository,
                             EmployeeService service) throws IOException, SQLException {

        int companyId = companyRepository.create();

        EmployeeEntity employee = new EmployeeEntity(5, false, "Alex", "Alexeev",
                "Ivanovich", "79111234567", "aa@ya.ru", companyId);
        employeeRepository.addEmployeeEnt(employee);
        List<EmployeeFromDB> list = employeeRepository.getAll(companyId);
        assertEquals(1, list.size());

        EmployeeFromAPI employeeFromApi = service.getEmployeeById(list.get(0).getId());
        assertEquals(employee.getFirstName(), employeeFromApi.getFirstName());
        assertEquals(employee.getLastName(), employeeFromApi.getLastName());
        assertEquals(employee.getMiddleName(), employeeFromApi.getMiddleName());
        assertEquals(employee.getPhone(), employeeFromApi.getPhone());
        assertEquals(employee.getEmail(), employeeFromApi.getEmail());

    }

    @Test
    @DisplayName("4. Update last name")
    public void updateEmployee(CompanyRepository companyRepository, EmployeeRepository employeeRepository,
                               @AdminAuthorized EmployeeService service) throws SQLException, IOException {

        int companyId = companyRepository.create();
        EmployeeEntity employee = new EmployeeEntity(8, false, "Mark", "Alexeev",
                "S", "79111234568", "al@ya.ru", companyId);
        employeeRepository.addEmployeeEnt(employee);
        EmployeeEntity employeeUpd = new EmployeeEntity(8, false, "Mark", "Ivanov",
                "S", "79111234568", "al@ya.ru", companyId);
        List<EmployeeFromDB> list = employeeRepository.getAll(companyId);
        assertEquals(1, list.size());
        service.updateEmployeeEnt(employee.getId(), employeeUpd);
        EmployeeFromDB employeeFromDB = employeeRepository.getById(list.get(0).getId());
        assertEquals(employeeUpd.getLastName(), employeeFromDB.getLast_name());


    }


    @Test
    @Tag("Negative")
    @DisplayName("User can not create an employee")
    public void userCreateEmployee(CompanyRepository companyRepository,
                                   EmployeeRepository employeeRepository,
                                   EmployeeService service) throws IOException, SQLException {

        int companyId = companyRepository.create();
        List<EmployeeFromDB> listBefore = employeeRepository.getAll(companyId);
        assertEquals(0, listBefore.size());

        EmployeeEntity employee = new EmployeeEntity(10, false, "Markus", "Alexeevus",
                "S", "79111234511", "11@ya.ru", companyId);
        ;
        service.createEmployeeUnauthorizedEnt(employee);
        List<EmployeeFromDB> listAfter = employeeRepository.getAll(companyId);
        assertEquals(0, listAfter.size());
    }


}
