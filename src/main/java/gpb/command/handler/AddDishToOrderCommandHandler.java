package gpb.command.handler;

import gpb.command.command.AddDishToOrderCommand;
import gpb.command.model.Order;
import gpb.command.repository.OrderRepository;
import gpb.common.exception.OrderNotFoundException;

public class AddDishToOrderCommandHandler implements CommandHandler<AddDishToOrderCommand> {
    OrderRepository orderRepository;

    public AddDishToOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(AddDishToOrderCommand command) {
        if (orderRepository.findById(command.getOrderId()) == null) {
            throw new OrderNotFoundException("Заказ с ID: " + command.getOrderId() + " не существует");
        }

        Order order = orderRepository.findById(command.getOrderId());

        order.addItem(command.getDish(), command.getQuantity());

        orderRepository.save(order);
    }
}
