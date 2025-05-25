package gpb.command.command;

import java.util.UUID;

public class RemoveDishFromOrderCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final int pointer;

    public RemoveDishFromOrderCommand(String orderId, int pointer) {
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.pointer = pointer;
    }

    @Override
    public String getCommandId() {
        return commandId;
    }

    public String getOrderId() {
        return orderId;
    }

    public int getPointer() {
        return pointer;
    }
}
