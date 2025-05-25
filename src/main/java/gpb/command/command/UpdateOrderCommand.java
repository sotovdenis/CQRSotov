package gpb.command.command;

import gpb.command.model.OrderItem;

import java.util.UUID;

public class UpdateOrderCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final int point;
    private final OrderItem newDish;


    public UpdateOrderCommand(String orderId, int point, OrderItem newDish) {
        this.commandId = UUID.randomUUID().toString();
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
    public String getCommandId() {
        return commandId;
    }
}
