package pl.wsb.programowaniejava.ProjektRestauracja.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "menu_category")
    private String menuCategory;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "description")
    private String menuDescription;

    @Column(name = "price")
    private double price;
}
