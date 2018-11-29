package tasks.second.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tasks.second.model.Employee;
import tasks.second.service.EmployeeService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class WebController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/")
    public String greetings() {
        return "Oh Hi";
    }

    @RequestMapping(value = "add/employee/{firstName}/{lastName}/{birthday}/{password}", method = RequestMethod.GET)
    @ResponseBody
    public String addEmployee(@PathVariable String firstName,
                              @PathVariable String lastName,
                              @PathVariable String birthday,
                              @PathVariable String email,
                              @PathVariable String password) {
        employeeService.addEmployee(firstName, lastName, LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd.MM.uuuu")), email, password);
        return "Employee added: " + firstName + " " + lastName;
    }

    @RequestMapping(value = "find/Employee/{email}" , method = RequestMethod.GET)
    public String findEmployee(@PathVariable  String email) {
        Employee employee = employeeService.findEmployee(email);
        return employee.getFirstName() + " " + employee.getLastName();
    }

    @RequestMapping(value = "delete/employee/{email}")
    public String deleteEmployee(@PathVariable String email) {
        employeeService.deleteEmployee(email);
        return "Done";
    }
}
