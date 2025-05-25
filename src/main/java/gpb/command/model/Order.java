package gpb.command.model;

import gpb.common.event.*;
import gpb.common.event.base.EventBus;
import gpb.common.exception.InvalidStatusTransitionException;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String orderId;
    private final String customerName;
    private OrderStatus status;
    private double price;
    private List<OrderItem> items;

    public Order(String customerName) {
        this.orderId = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.status = OrderStatus.CREATED;
        this.items = new ArrayList<>();

        EventBus.getInstance().publish(
                new OrderCreatedEvent(orderId, customerName)
        );
    }

    public Order(String orderId, String customerName, OrderStatus status, List<OrderItem> items) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.status = status;
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

    public List<OrderItem> getItems() {
        return items;
    }

    public double getPrice() {
        return price;
    }

    public void calculatePrice() {
        this.price = this.items.stream()
                .mapToDouble(OrderItem::getPrice).sum();

    }

    public void addItem(Menu menuDish, int quantity) {
        if (menuDish == null) {
            throw new IllegalArgumentException("Блюдо не может быть null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным");
        }

        OrderItem newItem = new OrderItem(menuDish.getName(), menuDish.getPrice(), quantity);

        this.items.add(newItem);

        EventBus.getInstance().publish(
                new DishAddedToOrderEvent(orderId, menuDish, quantity)
        );
    }

    public void updateItem(String dishName, int newQuantity) {
        if (newQuantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным");
        }

        OrderItem itemToUpdate = items.stream()
                .filter(item -> item.getDishName().equals(dishName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Блюдо не найдено в заказе"));

        itemToUpdate.setQuantity(newQuantity);

        EventBus.getInstance().publish(
                new DishUpdatedInOrderEvent(orderId, dishName, newQuantity)
        );
    }

    public void removeItem(String dishName) {
        boolean removed = items.removeIf(item -> item.getDishName().equals(dishName));

        if (!removed) {
            throw new IllegalArgumentException("Блюдо не найдено в заказе");
        }

        EventBus.getInstance().publish(
                new DishRemovedFromOrderEvent(orderId, dishName)
        );
    }

    public void replaceItem(int point, OrderItem newDish) {
        if (newDish == null) {
            throw new IllegalArgumentException("Новое блюдо не может быть null");
        }
        if (point < 0 || point >= items.size()) {
            throw new IllegalArgumentException("Недопустимая позиция для замены блюда");
        }

        items.remove(point);

        items.add(point, newDish);

        this.status = OrderStatus.IN_PROGRESS;

        EventBus.getInstance().publish(new OrderUpdatedEvent(orderId, point, newDish));
    }

    public void updateStatus(OrderStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Статус не может быть null");
        }

        if (!isValidStatusTransition(status, newStatus)) {
            throw new InvalidStatusTransitionException(
                    "Недопустимый переход из статуса " + status + " в статус " + newStatus
            );
        }

        this.status = newStatus;

        EventBus.getInstance().publish(new OrderStatusUpdatedEvent(orderId, newStatus));
    }

    public void closeOrder() {
        if (status != OrderStatus.COMPLETED) {
            throw new InvalidStatusTransitionException(
                    "Недопустимый переход из статуса " + status + " в статус " + OrderStatus.CLOSED
            );
        }

        this.status = OrderStatus.CLOSED;

        EventBus.getInstance().publish(new OrderClosedEvent(orderId));
    }

    private boolean isValidStatusTransition(OrderStatus currentStatus, OrderStatus newStatus) {
        if (newStatus == OrderStatus.CANCELLED) {
            return true;
        }

        if (newStatus == OrderStatus.CLOSED) {
            return false;
        }

        return newStatus.ordinal() == currentStatus.ordinal() + 1;
    }
}