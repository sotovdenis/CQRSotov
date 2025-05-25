package gpb.common.event;

import gpb.common.event.base.Event;

public class OrderClosedEvent extends Event {
    private final String orderId;

    public OrderClosedEvent(String orderId) {
        super();
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "OrderClosedEvent{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}