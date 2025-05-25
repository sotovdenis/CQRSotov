package gpb.command.handler;

import gpb.command.command.UpdateOrderStatusCommand;
import gpb.command.model.Order;
import gpb.command.model.OrderStatus;
import gpb.command.repository.OrderRepository;
import gpb.common.exception.OrderNotFoundException;

public class UpdateOrderStatusCommandHandler implements CommandHandler<UpdateOrderStatusCommand> {
    private final OrderRepository orderRepository;

    public UpdateOrderStatusCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(UpdateOrderStatusCommand command) {
        Order order = orderRepository.findById(command.getOrderId());
        if (order == null) {
            throw new OrderNotFoundException("Заказа c ID: " + command.getOrderId() + " не существует");
        }

        OrderStatus newStatus = command.getNewStatus();

        order.updateStatus(newStatus);

        orderRepository.save(order);
    }
}