package pl.wsb.programowaniejava.ProjektRestauracja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsb.programowaniejava.ProjektRestauracja.entity.Order;

import java.util.List;

@Repository
public interface OrderCrudRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByOrderId();
    List<Order> findAllByMenuCategory();
    List<Order> findAllByMenuName();
    List<Order> findAllByMenuDescription();
    List<Order> findAllByPrice();
}
