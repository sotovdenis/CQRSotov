package gpb.api.facade;

import gpb.command.command.*;
import gpb.command.handler.CommandBus;
import gpb.command.model.Menu;
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

    public void updateDishInOrder(String orderId, int pointer, int newQuantity) {
        commandBus.dispatch(new UpdateDishQuantityCommand(orderId, pointer, newQuantity));
    }

    public void updateOrder(String orderId, int pointer, int newDishPointer, int quantity) {
        commandBus.dispatch(new UpdateOrderCommand(orderId, pointer, newDishPointer, quantity));
    }

    public void updateOrderStatus(String orderId, OrderStatus newStatus) {
        commandBus.dispatch(new UpdateOrderStatusCommand(orderId, newStatus));
    }

    public void closeOrder(String orderId) {
        commandBus.dispatch(new CloseOrderCommand(orderId));
    }

    public void removeDishFromOrder(String orderId, int pointer) {
        commandBus.dispatch(new RemoveDishFromOrderCommand(orderId, pointer));
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
        return queryService.getOrderStatistics();
    }
}
