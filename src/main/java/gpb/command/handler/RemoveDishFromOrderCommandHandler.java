package gpb.command.handler;

import gpb.command.command.RemoveDishFromOrderCommand;
import gpb.command.model.Order;
import gpb.command.repository.OrderRepository;
import gpb.common.exception.OrderNotFoundException;

public class RemoveDishFromOrderCommandHandler implements CommandHandler<RemoveDishFromOrderCommand> {
    private final OrderRepository orderRepository;

    public RemoveDishFromOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void handle(RemoveDishFromOrderCommand command) {
        if (orderRepository.findById(command.getOrderId()) == null) {
            throw new OrderNotFoundException("Заказа c ID: " + command.getOrderId() + " не существует");
        }

        Order order = orderRepository.findById(command.getOrderId());


        order.removeItem(command.getPointer());

        orderRepository.save(order);
    }

}
