package pl.wsb.programowaniejava.ProjektRestauracja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wsb.programowaniejava.ProjektRestauracja.entity.Employee;
import pl.wsb.programowaniejava.ProjektRestauracja.repository.EmployeeCrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeApiService {




    @Autowired
    private EmployeeCrudRepository employeeCrudRepository;
    @Autowired
    private EmployeeApiService employeeApiService;

    @Autowired
    private final List<Employee> employees = new ArrayList<>();


    public List<EmployeeApiService> getEmployees() {
        return employeeApiService.getEmployees().stream().collect(Collectors.toList());
    }

    public List<EmployeeApiService> getDefaultEmployeeId() {
        return employeeApiService.getDefaultEmployeeId().stream().collect(Collectors.toList());
    }

    public List<Employee> getEmployees(String sortBy) {
        if ("employeeId".equals(sortBy)) {
            return employeeCrudRepository.findAllByEmployeeId();
        } else {
            return employeeCrudRepository.findAll();
        }
    }

    public Employee addEmployee(Long employeeId){
        Employee employee = Employee.builder()
                .employeeId(employeeId)
                .build();
        return employeeCrudRepository.save(employee);
    };

    public void deleteEmployeeId(Long employeeId) {
        employees.removeIf(employee -> employee.getEmployeeId() == employeeId);
    }

    public Optional<Employee> updateEmployee(Long employeeId) {
        Employee employee = Employee.builder()
                .employeeId(employeeId)
                .build();
        return employeeCrudRepository
                .findByEmployeeId()
                .map(saveEmployee -> employeeCrudRepository.save(employee));
    }
}

