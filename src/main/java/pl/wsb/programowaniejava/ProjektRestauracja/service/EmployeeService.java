package pl.wsb.programowaniejava.ProjektRestauracja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeApiService employeeApiService;

    public Optional<List<EmployeeApiService>> getDefaultEmployeeId(Long employeeId){
        return employeeApiService.getEmployees()
                .stream()
                .filter(employee -> employee.getEmployees() != null && employee.getEmployees().equals(employeeId))
                .findFirst()
                .map(EmployeeApiService::getDefaultEmployeeId);
    }


}
