package gpb.common.event;

import gpb.common.event.base.Event;

public class OrderCreatedEvent extends Event {
    private final String orderId;
    private final String customerName;

    public OrderCreatedEvent(String orderId, String customerName) {
        super();
        this.orderId = orderId;
        this.customerName = customerName;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}