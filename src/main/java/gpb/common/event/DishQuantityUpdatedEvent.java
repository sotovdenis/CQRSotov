package gpb.common.event;

import gpb.common.event.base.Event;

public class DishQuantityUpdatedEvent extends Event {
    private final String orderId;
    private final String dishName;
    private final int newQuantity;

    public DishQuantityUpdatedEvent(String orderId, String dishName, int newQuantity) {
        super();
        this.orderId = orderId;
        this.dishName = dishName;
        this.newQuantity = newQuantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getDishName() {
        return dishName;
    }

    public int getNewQuantity() {
        return newQuantity;
    }

    @Override
    public String toString() {
        return "DishQuantityUpdatedEvent{" +
                "orderId='" + orderId + '\'' +
                ", dishName='" + dishName + '\'' +
                ", newQuantity=" + newQuantity +
                '}';
    }
}
