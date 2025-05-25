package gpb.command.repository;


import gpb.command.model.Order;
import gpb.common.exception.OrderNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderRepository {
    private final Map<String, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public Order findById(String id) {
        return Optional.ofNullable(orders.get(id))
                .orElseThrow(() -> new OrderNotFoundException("Счет не найден: " + id));
    }

    public Optional<Order> findByCustomerName(String customer) {
        return orders.values().stream()
                .filter(a -> a.getCustomerName().equals(customer))
                .findFirst();
    }
}

