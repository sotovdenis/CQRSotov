package gpb.command.model;

public enum OrderStatus {
    CREATED("Создан"),
    IN_PROGRESS("Готовиться ..."),
    COMPLETED("Ща принесем"),
    CANCELLED("Сам отменил а теперь спрашиваешь..."),
    CLOSED("Заказ закрыт/убит");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
