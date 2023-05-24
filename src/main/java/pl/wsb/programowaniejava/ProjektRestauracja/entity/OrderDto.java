package pl.wsb.programowaniejava.ProjektRestauracja.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDto {

    private Long orderId;
    private String menuCategory;
    private String menuName;
    private String menuDescription;
    private double price;

}
