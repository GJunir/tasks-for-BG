package tasks.second.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import tasks.second.model.Employee;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, UUID> {


    //Implementation? Nah! Thx SpringBoot
    Employee findEmployeeByEmail(String email);
    //Same
    Employee deleteEmployeeByEmail(String email);
}
