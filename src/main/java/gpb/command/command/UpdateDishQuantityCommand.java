package gpb.command.command;

import java.util.UUID;

public class UpdateDishQuantityCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final String dishName;
    private final int newQuantity;

    public UpdateDishQuantityCommand(String orderId, String dishName, int newQuantity) {
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.dishName = dishName;
        this.newQuantity = newQuantity;
    }

    @Override
    public String getCommandId() {
        return commandId;
    }

    public String getOrderId() {
        return orderId;
    }

    public int getNewQuantity() {
        return newQuantity;
    }

    public String getDishName() {
        return dishName;
    }
}
