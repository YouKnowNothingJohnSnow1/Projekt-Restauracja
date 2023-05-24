package pl.wsb.programowaniejava.ProjektRestauracja.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmployeeApiDto {


    private Long employeeId;
    private List<Order> orders;
}
