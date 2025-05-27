package gpb.query.service;

import gpb.query.dto.OrderDto;
import gpb.query.dto.OrderStatisticDto;
import gpb.query.model.OrderItemView;
import gpb.query.model.OrderView;
import gpb.query.repository.OrderViewRepository;

import java.time.LocalDateTime;
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

    public OrderStatisticDto getOrderStatistics() {
        List<OrderView> orders = orderRepository.findAll();

        int totalOrders = orders.size();
        int completedOrders = (int) orders.stream()
                .filter(order -> order.getStatus() == gpb.command.model.OrderStatus.COMPLETED)
                .count();
        int inProgressOrders = (int) orders.stream()
                .filter(order -> order.getStatus() == gpb.command.model.OrderStatus.IN_PROGRESS)
                .count();
        int cancelledOrders = (int) orders.stream()
                .filter(order -> order.getStatus() == gpb.command.model.OrderStatus.CANCELLED)
                .count();

        double averageItemsPerOrder = orders.isEmpty() ? 0 :
                orders.stream().mapToDouble(order -> order.getItems().size()).average().orElse(0);

        double averagePrice = 0d;

        for (int i = 0; i < orders.size(); i++) {
            averagePrice += orders.get(i).getPrice();
        }

        return new OrderStatisticDto(
                totalOrders,
                completedOrders,
                inProgressOrders,
                cancelledOrders,
                averagePrice,
                averageItemsPerOrder
        );
    }
}
