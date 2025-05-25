package gpb.command.handler;

import gpb.command.command.CloseOrderCommand;
import gpb.command.model.Order;
import gpb.command.repository.OrderRepository;
import gpb.common.exception.OrderNotFoundException;

public class CloseOrderCommandHandler implements CommandHandler<CloseOrderCommand> {
    private final OrderRepository orderRepository;

    public CloseOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(CloseOrderCommand command) {
        Order order = orderRepository.findById(command.getOrderId());
        if (order == null) {
            throw new OrderNotFoundException("Заказа c ID: " + command.getOrderId() + " не существует");
        }

        order.closeOrder();

        orderRepository.save(order);
    }
}
