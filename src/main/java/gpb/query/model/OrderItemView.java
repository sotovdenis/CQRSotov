package gpb.query.model;

public class OrderItemView {
    private String dishName;
    private double price;
    private int quantity;

    public OrderItemView(String dishName, double price, int quantity) {
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
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Блюдо ='" + dishName +
                ", цена =" + price +
                ", кол-во =" + quantity;
    }
}
