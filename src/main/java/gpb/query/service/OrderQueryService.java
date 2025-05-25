package gpb.query.service;

import gpb.query.dto.OrderDto;
import gpb.query.model.OrderItemView;
import gpb.query.model.OrderView;
import gpb.query.repository.OrderViewRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OrderQueryService {
    private final OrderViewRepository orderRepository;

    public OrderQueryService(OrderViewRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto getOrderById(String orderId) {
        OrderView order = orderRepository.findById(orderId);

        return new OrderDto(
                order.getOrderId(),
                order.getCustomerName(),
                order.getStatus(),
                calculateTotalPrice(order.getItems()),
                order.getCreatedAt(),
                order.getItems()
        );
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderDto(
                        order.getOrderId(),
                        order.getCustomerName(),
                        order.getStatus(),
                        calculateTotalPrice(order.getItems()),
                        order.getCreatedAt(),
                        order.getItems()
                ))
                .collect(Collectors.toList());
    }

    public List<OrderDto> getOrdersByCustomer(String customerName) {
        return orderRepository.findByCustomerName(customerName).stream()
                .map(order -> new OrderDto(
                        order.getOrderId(),
                        order.getCustomerName(),
                        order.getStatus(),
                        calculateTotalPrice(order.getItems()),
                        order.getCreatedAt(),
                        order.getItems()
                ))
                .collect(Collectors.toList());
    }


    private double calculateTotalPrice(List<OrderItemView> items) {
        return items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}
