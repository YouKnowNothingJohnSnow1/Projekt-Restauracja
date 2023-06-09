package pl.wsb.programowaniejava.ProjektRestauracja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsb.programowaniejava.ProjektRestauracja.entity.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeCrudRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeId(Long employeeId);
    List<Employee> findByEmployeeId();
}
