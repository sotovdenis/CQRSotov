package gpb.command.command;

import gpb.command.model.Menu;

import java.util.UUID;

public class AddDishToOrderCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final Menu dish;
    private final int quantity;

    public AddDishToOrderCommand(String orderId, Menu dish, int quantity) {
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.dish = dish;
        this.quantity = quantity;
    }

    @Override
    public String getCommandId() {
        return commandId;
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
}
