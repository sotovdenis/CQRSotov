package gpb.query.dto;


public class OrderStatisticDto {
    private int totalOrders;
    private int completedOrders;
    private int inProgressOrders;
    private int cancelledOrders;
    private double averagePrice;
    private double averageItemsPerOrder;

    public OrderStatisticDto(int totalOrders, int completedOrders, int inProgressOrders,
                             int cancelledOrders, double averagePrice, double averageItemsPerOrder) {
        this.totalOrders = totalOrders;
        this.completedOrders = completedOrders;
        this.inProgressOrders = inProgressOrders;
        this.cancelledOrders = cancelledOrders;
        this.averagePrice = averagePrice;
        this.averageItemsPerOrder = averageItemsPerOrder;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public int getCompletedOrders() {
        return completedOrders;
    }

    public int getInProgressOrders() {
        return inProgressOrders;
    }

    public int getCancelledOrders() {
        return cancelledOrders;
    }

    public double getAverageItemsPerOrder() {
        return averageItemsPerOrder;
    }

    public double getAveragePrice() {
        return averagePrice;
    }
}