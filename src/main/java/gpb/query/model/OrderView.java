package gpb.query.model;

import gpb.command.model.OrderStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderView {
    private String orderId;
    private String customerName;
    private OrderStatus status;
    private double price;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    private List<OrderItemView> items;

    public OrderView(String orderId, String customerName, OrderStatus status, double price, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.status = status;
        this.price = price;
        this.createdAt = createdAt;
        this.lastUpdatedAt = createdAt;
        this.items = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public List<OrderItemView> getItems() {
        return items;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public void addItem(OrderItemView item) {
        this.items.add(item);
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public void updateItem(String dishName, int newQuantity) {
        this.items.stream()
                .filter(item -> item.getDishName().equals(dishName))
                .findFirst()
                .ifPresent(item -> {
                    item.setQuantity(newQuantity);
                    this.lastUpdatedAt = LocalDateTime.now();
                });
    }

    public void removeItem(String dishName) {
        this.items.removeIf(item -> item.getDishName().equals(dishName));
        this.lastUpdatedAt = LocalDateTime.now();
    }
}
