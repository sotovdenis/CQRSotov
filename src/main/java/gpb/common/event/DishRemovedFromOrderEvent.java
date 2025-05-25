package gpb.common.event;

import gpb.common.event.base.Event;

public class DishRemovedFromOrderEvent extends Event {
    private final String orderId;
    private final String dishName;

    public DishRemovedFromOrderEvent(String orderId, String dishName) {
        super();
        this.orderId = orderId;
        this.dishName = dishName;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getDishName() {
        return dishName;
    }

    @Override
    public String toString() {
        return "DishRemovedFromOrderEvent{" +
                "orderId='" + orderId + '\'' +
                ", dishName='" + dishName + '\'' +
                '}';
    }
}
