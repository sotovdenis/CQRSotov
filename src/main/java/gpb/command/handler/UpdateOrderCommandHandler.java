package gpb.command.handler;

import gpb.command.command.UpdateOrderCommand;
import gpb.command.model.Order;
import gpb.command.model.OrderItem;
import gpb.command.repository.OrderRepository;
import gpb.common.exception.OrderNotFoundException;

public class UpdateOrderCommandHandler implements CommandHandler<UpdateOrderCommand> {
    private final OrderRepository orderRepository;

    public UpdateOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(UpdateOrderCommand command) {
        if (orderRepository.findById(command.getOrderId()) == null) {
            throw new OrderNotFoundException("Заказа c ID: " + command.getOrderId() + " не существует");
        }

        Order order = orderRepository.findById(command.getOrderId());

        order.replaceItem(command.getPoint(), command.getNewDishPoint(), command.getQuantity());

        orderRepository.save(order);

    }
}
