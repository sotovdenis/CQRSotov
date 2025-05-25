package gpb.command.command;

import java.util.UUID;

public class CloseOrderCommand implements Command {
    private final String commandId;
    private final String orderId;

    public CloseOrderCommand(String orderId) {
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
    }

    @Override
    public String getCommandId() {
        return commandId;
    }

    public String getOrderId() {
        return orderId;
    }
}
