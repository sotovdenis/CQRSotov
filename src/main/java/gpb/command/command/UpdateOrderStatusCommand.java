package gpb.command.command;

import gpb.command.model.OrderStatus;

import java.util.UUID;

public class UpdateOrderStatusCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final OrderStatus newStatus;

    public UpdateOrderStatusCommand(String orderId, OrderStatus newStatus) {
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.newStatus = newStatus;
    }

    @Override
    public String getCommandId() {
        return commandId;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getNewStatus() {
        return newStatus;
    }
}
