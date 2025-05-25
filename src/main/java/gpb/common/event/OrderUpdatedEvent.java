package gpb.common.event;

import gpb.command.model.OrderItem;
import gpb.common.event.base.Event;

public class OrderUpdatedEvent extends Event {
    private final String orderId;
    private final int point;
    private final OrderItem newDish;

    public OrderUpdatedEvent(String orderId, int point, OrderItem newDish) {
        super();
        this.orderId = orderId;
        this.point = point;
        this.newDish = newDish;
    }

    public String getOrderId() {
        return orderId;
    }

    public int getPoint() {
        return point;
    }

    public OrderItem getNewDish() {
        return newDish;
    }

    @Override
    public String toString() {
        return "OrderUpdatedEvent{" +
                "orderId='" + orderId + '\'' +
                ", point=" + point +
                ", newDish=" + newDish +
                '}';
    }
}
