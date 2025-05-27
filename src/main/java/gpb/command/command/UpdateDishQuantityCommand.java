package gpb.command.command;

import java.util.UUID;

public class UpdateDishQuantityCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final int pointer;
    private final int newQuantity;

    public UpdateDishQuantityCommand(String orderId, int pointer, int newQuantity) {
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.pointer = pointer;
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

    public int getPointer() {
        return pointer;
    }
}
