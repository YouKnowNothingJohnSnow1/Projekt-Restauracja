package pl.wsb.programowaniejava.ProjektRestauracja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wsb.programowaniejava.ProjektRestauracja.entity.Order;
import pl.wsb.programowaniejava.ProjektRestauracja.repository.OrderCrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderCrudRepository orderCrudRepository;
    @Autowired
    private final List<Order> orders = new ArrayList<>();

    public Order addOrder(Long orderId,String menuCategory, String manuName, String menuDescription, double price){
        Order order = Order.builder()
                .orderId(orderId)
                .menuCategory(menuCategory)
                .menuName(manuName)
                .menuDescription(menuDescription)
                .price(price)
                .build();
                return orderCrudRepository.save(order);
    }
    public Optional<Order> getOrder(Order employeeId) {
        return orderCrudRepository.findById(employeeId.getOrderId());
    }

    public List<Order> getOrders(String sortBy) {
        if ("orderId".equals(sortBy)) {
            return orderCrudRepository.findAllByOrderId();
        } else if ("menuCategory".equals(sortBy)) {
            return orderCrudRepository.findAllByMenuCategory();
        } else if ("menuName".equals(sortBy)) {
            return orderCrudRepository.findAllByMenuName();
        } else if ("menuDescription".equals(sortBy)) {
            return orderCrudRepository.findAllByMenuDescription();
        } else if ("price".equals(sortBy)) {
            return orderCrudRepository.findAllByPrice();
        } else {
            return orderCrudRepository.findAll();
        }
    }
    public Optional<Order> updateOrder(Long orderId,String menuCategory, String manuName,
                                       String menuDescription, double price){
        Order order = Order.builder()
                .orderId(orderId)
                .menuCategory(menuCategory)
                .menuName(manuName)
                .menuDescription(menuDescription)
                .price(price)
                .build();
        return orderCrudRepository
                .findById(orderId)
                .map(saveOrder -> orderCrudRepository.save(order));}
    public void deleteOrder(long id){
        orders.removeIf(order -> order.getOrderId() == id);
    }}
