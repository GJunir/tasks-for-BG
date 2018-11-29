package tasks.second.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tasks.second.model.Employee;
import tasks.second.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public UUID addEmployee(String firstName, String lastName, LocalDate birthday, String email, String password) {
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID());
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setBirthday(birthday);
        employee.setEmail(email);
        employee.setPassword(password);
        employeeRepository.save(employee);
        return employee.getId();
    }

    @Transactional
    public Employee findEmployee(String email) {
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        return employee;
    }

    @Transactional
    public String deleteEmployee(String email) {
        Employee employee = employeeRepository.deleteEmployeeByEmail(email);
        return "Employee " + employee.getFirstName() + " " + employee.getLastName() + " has been deleted";
    }
}
