package pl.wsb.programowaniejava.ProjektRestauracja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wsb.programowaniejava.ProjektRestauracja.entity.Employee;
import pl.wsb.programowaniejava.ProjektRestauracja.entity.EmployeeDto;
import pl.wsb.programowaniejava.ProjektRestauracja.exception.ResourceNotFoundException;
import pl.wsb.programowaniejava.ProjektRestauracja.repository.EmployeeCrudRepository;
import pl.wsb.programowaniejava.ProjektRestauracja.service.EmployeeApiService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeCrudRepository employeeCrudRepository;
    @Autowired
    private EmployeeApiService employeeApiService;

    @GetMapping("/employee/{id}")
    public List<EmployeeDto> getEmployee(@PathVariable("id") Long employeeId){
        return employeeCrudRepository.findByEmployeeId().stream().map(this::asDto).collect(Collectors.toList());
    }

    @PostMapping("/employee")
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto ){
        return asDto(
                employeeApiService.addEmployee(employeeDto.getEmployeeId()));
    }
    @PutMapping("/employees/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto employeeDto){
        return employeeApiService
                .updateEmployee
                        (employeeDto.getEmployeeId())
                .map(this::asDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployeeId(@PathVariable("id") Long employeeId) {
        employeeApiService.deleteEmployeeId(employeeId);
    }

    public EmployeeDto asDto(Employee employee){
        return EmployeeDto.builder()
                .employeeId(employee.getEmployeeId())
                .build();
    }
}
