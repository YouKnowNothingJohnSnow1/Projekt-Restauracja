package pl.wsb.programowaniejava.ProjektRestauracja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wsb.programowaniejava.ProjektRestauracja.entity.Order;
import pl.wsb.programowaniejava.ProjektRestauracja.entity.OrderDto;
import pl.wsb.programowaniejava.ProjektRestauracja.exception.ResourceNotFoundException;
import pl.wsb.programowaniejava.ProjektRestauracja.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders(@RequestParam(value = "sortBy", required = false) String sortBy){
        return orderService.getOrders(sortBy).stream().map(this::asDto).collect(Collectors.toList());
    }

    @GetMapping("/orders/{id}")
    public OrderDto getOrder(@PathVariable("id") Order id) {
        return orderService.getOrder(id).map(this::asDto).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping("/orders")
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        return asDto(
                orderService.addOrder(
                        orderDto.getOrderId(), orderDto.getMenuCategory(),
                        orderDto.getMenuName(), orderDto.getMenuDescription(),
                        orderDto.getPrice()));
    }

    @PutMapping("/orders/{id}")
    public OrderDto updateOrder(@PathVariable("id") Long id, @RequestBody OrderDto orderDto){
        return orderService
                .updateOrder(
                        orderDto.getOrderId(), orderDto.getMenuCategory(), orderDto.getMenuName(),
                        orderDto.getMenuDescription(), orderDto.getPrice())
                .map(this::asDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @DeleteMapping("/orderss/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
    }

    public OrderDto asDto(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .menuCategory(order.getMenuCategory())
                .menuDescription(order.getMenuDescription())
                .menuName(order.getMenuName())
                .price(order.getPrice())
                .build();
    }

}
