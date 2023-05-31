package pl.wsb.programowaniejava.ProjektRestauracja.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.wsb.programowaniejava.ProjektRestauracja.entity.Employee;
import pl.wsb.programowaniejava.ProjektRestauracja.entity.EmployeeApiDto;
import pl.wsb.programowaniejava.ProjektRestauracja.repository.EmployeeCrudRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class EmployeeApiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeApiService.class);
    @Value("${employeeApi.endpoint}")
    private String employeeApiEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    public List<EmployeeApiDto> getOrder(){
        try {
            EmployeeApiDto[] orders = restTemplate.getForObject(employeeApiEndpoint, EmployeeApiDto[].class);
            return ofNullable(orders).map(Arrays::asList).orElseGet(Collections::emptyList);
        } catch (Exception ex) {
            LOGGER.warn("Failed to load data from Employee API: {}",  ex.getMessage(), ex);
            return List.of();
        }
    }

    @Autowired
    private EmployeeCrudRepository employeeCrudRepository;

    @Autowired
    private final List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployee() {
        return employeeCrudRepository.findByEmployeeId().stream().collect(Collectors.toList());
    }

    public Employee addEmployee(Long employeeId){
        Employee employee = Employee.builder()
                .employeeId(employeeId)
                .build();
        return employeeCrudRepository.save(employee);
    }

    public Optional<Employee> updateEmployee(Long employeeId) {
        Employee employee = Employee.builder()
                .employeeId(employeeId)
                .build();
        return employeeCrudRepository
                .findByEmployeeId(employeeId)
                .map(saveEmployee->employeeCrudRepository.save(employee));
    }

    public void deleteEmployeeId(Long employeeId) {
        employees.removeIf(employee -> employee.getEmployeeId() == employeeId);
    }
}










    /*

     public List<Employee> getEmployees(String sortBy) {
        if ("employeeId".equals(sortBy)) {
            return employeeCrudRepository.findAllByEmployeeId();
        } else {
            return employeeCrudRepository.findAll();
        }
    }



    public List<EmployeeApiService> getEmployees() {
        return employeeApiService.getEmployees().stream().collect(Collectors.toList());
    }

  public List<EmployeeApiService> getDefaultEmployeeId() {
        return employeeApiService.getDefaultEmployeeId().stream().collect(Collectors.toList());
    }

    @Autowired
    private EmployeeApiService employeeApiService;

   */
