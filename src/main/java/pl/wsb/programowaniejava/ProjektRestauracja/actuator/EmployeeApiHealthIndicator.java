package pl.wsb.programowaniejava.ProjektRestauracja.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import pl.wsb.programowaniejava.ProjektRestauracja.service.EmployeeApiService;

import static java.util.Optional.ofNullable;

@Component
public class EmployeeApiHealthIndicator implements HealthIndicator {
    @Autowired
    private EmployeeApiService employeeApiService;

    @Override
    public Health health() {
        return ofNullable(employeeApiService.getOrder())
                .filter(v -> v.size() > 0)
                .map(v -> Health.up())
                .orElseGet(Health::down)
                .build();
    }
}
