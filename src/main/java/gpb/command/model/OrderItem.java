package gpb.command.model;

public class OrderItem {
    private final String dishName;
    private final double price;
    private int quantity;

    public OrderItem(String dishName, double price, int quantity) {
        this.dishName = dishName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDishName() {
        return dishName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным");
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%s (x%d) - $%.2f",
                dishName,
                quantity,
                price * quantity);
    }
}