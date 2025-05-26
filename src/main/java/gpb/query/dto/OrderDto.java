package gpb.query.dto;

import gpb.command.model.OrderStatus;
import gpb.query.model.OrderItemView;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    private String orderId;
    private String customerName;
    private OrderStatus status;
    private double price;
    private LocalDateTime createdAt;
    private List<OrderItemView> items;

    public OrderDto(String orderId,
                    String customerName,
                    OrderStatus status,
                    double price,
                    LocalDateTime createdAt,
                    List<OrderItemView> items) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.status = status;
        this.price = price;
        this.createdAt = createdAt;
        this.items = items;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public List<OrderItemView> getItems() {
        return items;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void addItem(OrderItemView item) {
        this.items.add(item);
    }
}

