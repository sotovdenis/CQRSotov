package gpb.command.command;

import gpb.command.model.OrderItem;

import java.util.UUID;

public class UpdateOrderCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final int point;
    private final int newDishPoint;
    private final int quantity;


    public UpdateOrderCommand(String orderId, int point, int newDishPoint, int quantity) {
        this.newDishPoint = newDishPoint;
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.point = point;
        this.quantity = quantity;
    }


    public String getOrderId() {
        return orderId;
    }

    public int getPoint() {
        return point;
    }

    public int getNewDishPoint() {
        return newDishPoint;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getCommandId() {
        return commandId;
    }
}
