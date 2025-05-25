package gpb.api.facade;

import gpb.command.command.*;
import gpb.command.handler.CommandBus;
import gpb.command.model.Menu;
import gpb.command.model.OrderItem;
import gpb.command.model.OrderStatus;
import gpb.query.dto.OrderDto;
import gpb.query.dto.OrderStatisticDto;
import gpb.query.service.OrderQueryService;

import java.util.List;

public class RestaurantFacade {
    private final CommandBus commandBus;
    private final OrderQueryService queryService;

    public RestaurantFacade(CommandBus commandBus, OrderQueryService queryService) {
        this.commandBus = commandBus;
        this.queryService = queryService;
    }

    public void createOrder(String customerName) {
        commandBus.dispatch(new CreateOrderCommand(customerName));
    }

    public void addDishToOrder(String orderId, Menu dish, int quantity) {
        commandBus.dispatch(new AddDishToOrderCommand(orderId, dish, quantity));
    }

    public void updateDishInOrder(String orderId, String dishName, int newQuantity) {
        commandBus.dispatch(new UpdateDishQuantityCommand(orderId, dishName, newQuantity));
    }

    public void updateOrder(String orderId, int pointer, Menu dish, int quantity ) {
        OrderItem item = new OrderItem(dish.getName(), dish.getPrice(), quantity);
        commandBus.dispatch(new UpdateOrderCommand(orderId, pointer, item));
    }

    public void updateOrderStatus(String orderId, OrderStatus newStatus) {
        commandBus.dispatch(new UpdateOrderStatusCommand(orderId, newStatus));
    }

    public void closeOrder(String orderId) {
        commandBus.dispatch(new CloseOrderCommand(orderId));
    }





    public OrderDto getOrderById(String orderId) {
        return queryService.getOrderById(orderId);
    }

    public List<OrderDto> getAllOrders() {
        return queryService.getAllOrders();
    }

    public List<OrderDto> getOrdersByCustomer(String customerName) {
        return queryService.getOrdersByCustomer(customerName);
    }

    public OrderStatisticDto getOrderStatistics() {
        List<OrderDto> orders = queryService.getAllOrders();

        int totalOrders = orders.size();
        int completedOrders = (int) orders.stream()
                .filter(order -> order.getStatus() == gpb.command.model.OrderStatus.COMPLETED)
                .count();
        int inProgressOrders = (int) orders.stream()
                .filter(order -> order.getStatus() == gpb.command.model.OrderStatus.IN_PROGRESS)
                .count();
        int cancelledOrders = (int) orders.stream()
                .filter(order -> order.getStatus() == gpb.command.model.OrderStatus.CANCELLED)
                .count();

        double averageItemsPerOrder = orders.isEmpty() ? 0 :
                orders.stream().mapToDouble(order -> order.getItems().size()).average().orElse(0);

        double averagePrice = orders.stream().mapToDouble(OrderDto::getPrice).sum();

        return new OrderStatisticDto(
                totalOrders,
                completedOrders,
                inProgressOrders,
                cancelledOrders,
                averagePrice,
                averageItemsPerOrder
        );
    }
}
