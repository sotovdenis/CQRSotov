package gpb.common.event;

import gpb.command.model.OrderStatus;
import gpb.common.event.base.Event;

public class OrderStatusUpdatedEvent extends Event {
    private final String orderId;
    private final OrderStatus newStatus;

    public OrderStatusUpdatedEvent(String orderId, OrderStatus newStatus) {
        super();
        this.orderId = orderId;
        this.newStatus = newStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getNewStatus() {
        return newStatus;
    }

    @Override
    public String toString() {
        return "OrderStatusUpdatedEvent{" +
                "orderId='" + orderId + '\'' +
                ", newStatus=" + newStatus +
                '}';
    }
}