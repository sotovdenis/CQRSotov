package gpb.query.repository;

import gpb.common.exception.OrderNotFoundException;
import gpb.query.model.OrderView;

import java.util.*;
import java.util.stream.Collectors;

public class OrderViewRepository {
    private final Map<String, OrderView> orders = new HashMap<>();

    public void save(OrderView orderView) {
        orders.put(orderView.getOrderId(), orderView);
    }

    public OrderView findById(String orderId) {
        return Optional.ofNullable(orders.get(orderId))
                .orElseThrow(() -> new OrderNotFoundException("Заказ не найден: " + orderId));
    }

    public List<OrderView> findByCustomerName(String customerName) {
        return orders.values().stream()
                .filter(order -> order.getCustomerName().equals(customerName))
                .collect(Collectors.toList());
    }

    public List<OrderView> findAll() {
        return new ArrayList<>(orders.values());
    }
}