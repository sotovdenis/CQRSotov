package gpb;

import gpb.api.console.ConsoleInterface;
import gpb.api.facade.RestaurantFacade;
import gpb.command.command.*;
import gpb.command.handler.*;
import gpb.command.model.Menu;
import gpb.command.model.OrderStatus;
import gpb.command.repository.OrderRepository;
import gpb.common.event.base.EventBus;
import gpb.query.repository.OrderViewRepository;
import gpb.query.service.EventHandler;
import gpb.query.service.OrderQueryService;

public class Main {
    public static void main(String[] args) {
        OrderRepository commandOrderRepository = new OrderRepository();
        OrderViewRepository queryOrderRepository = new OrderViewRepository();

        EventBus.EventHandler eventHandler = new EventHandler(queryOrderRepository);
        EventBus.getInstance().register(eventHandler);

        CommandBus commandBus = new CommandBus();
        commandBus.register(CreateOrderCommand.class, new CreateOrderCommandHandler(commandOrderRepository));
        commandBus.register(AddDishToOrderCommand.class, new AddDishToOrderCommandHandler(commandOrderRepository));
        commandBus.register(RemoveDishFromOrderCommand.class, new RemoveDishFromOrderCommandHandler(commandOrderRepository));
        commandBus.register(UpdateDishQuantityCommand.class, new UpdateDishQuantityCommandHandler(commandOrderRepository));
        commandBus.register(UpdateOrderCommand.class, new UpdateOrderCommandHandler(commandOrderRepository));
        commandBus.register(UpdateOrderStatusCommand.class, new UpdateOrderStatusCommandHandler(commandOrderRepository));
        commandBus.register(CloseOrderCommand.class, new CloseOrderCommandHandler(commandOrderRepository));

        OrderQueryService queryService = new OrderQueryService(queryOrderRepository);

        RestaurantFacade restaurantFacade = new RestaurantFacade(commandBus, queryService);

        try {
            restaurantFacade.createOrder("John Doe");
            restaurantFacade.createOrder("John Posle");

            String order1Id = restaurantFacade.getAllOrders().get(0).getOrderId();
            String order2Id = restaurantFacade.getAllOrders().get(1).getOrderId();

            restaurantFacade.addDishToOrder(order1Id, Menu.PIZZA_MARGHERITA, 2);
            restaurantFacade.addDishToOrder(order1Id, Menu.PASTA_CARBONARA, 1);
            restaurantFacade.addDishToOrder(order2Id, Menu.BURGER_CLASSIC, 3);

            restaurantFacade.updateOrderStatus(order1Id, OrderStatus.IN_PROGRESS);
            restaurantFacade.updateOrderStatus(order2Id, OrderStatus.IN_PROGRESS);
            restaurantFacade.updateOrderStatus(order2Id, OrderStatus.COMPLETED);

            restaurantFacade.closeOrder(order2Id);

            System.out.println("Тестовые данные успешно созданы");
        } catch (Exception e) {
            System.out.println("Ошибка при создании тестовых данных: " + e.getMessage());
        }

        ConsoleInterface consoleUI = new ConsoleInterface(restaurantFacade);
        consoleUI.start();
    }
}