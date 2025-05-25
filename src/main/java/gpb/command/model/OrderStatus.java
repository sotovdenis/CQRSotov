package gpb.command.model;

public enum OrderStatus {
    CREATED("Создан", 1),
    IN_PROGRESS("Готовиться ...", 2),
    COMPLETED("Заказ подан клиенту", 3),
    DENIED("Отказ от заказа", 4),
    CANCELLED("Заказ отменен", 5),
    CLOSED("Заказ закрыт/убит", 6);

    private final String displayName;
    private final int numberInEnum;

    OrderStatus(String displayName, int numberInEnum) {
        this.displayName = displayName;
        this.numberInEnum = numberInEnum;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getNumberInEnum() {
        return numberInEnum;
    }

    public static OrderStatus getByNumberInStatuses(double numberInStatuses) {
        for (OrderStatus orderStatus : values()) {
            if (orderStatus.getNumberInEnum() == numberInStatuses) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Блюдо с номером " + numberInStatuses + " не найдено");
    }

    @Override
    public String toString() {
        return displayName;
    }
}
