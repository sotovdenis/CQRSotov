package gpb.common.event;

import gpb.command.model.Menu;
import gpb.common.event.base.Event;

public class DishAddedToOrderEvent extends Event {
    private final String orderId;
    private final Menu dish;
    private final int quantity;

    public DishAddedToOrderEvent(String orderId, Menu dish, int quantity) {
        super();
        this.orderId = orderId;
        this.dish = dish;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public Menu getDish() {
        return dish;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "DishAddedToOrderEvent{" +
                "orderId='" + orderId + '\'' +
                ", dish=" + dish +
                ", quantity=" + quantity +
                '}';
    }
}