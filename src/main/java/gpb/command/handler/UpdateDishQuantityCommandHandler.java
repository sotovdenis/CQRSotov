package gpb.command.handler;

import gpb.command.command.UpdateDishQuantityCommand;
import gpb.command.model.Order;
import gpb.command.repository.OrderRepository;
import gpb.common.exception.OrderNotFoundException;

public class UpdateDishQuantityCommandHandler implements CommandHandler<UpdateDishQuantityCommand> {
    private final OrderRepository orderRepository;

    public UpdateDishQuantityCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void handle(UpdateDishQuantityCommand command) {
        if (orderRepository.findById(command.getOrderId()) == null) {
            throw new OrderNotFoundException("Заказа c ID: " + command.getOrderId() + " не существует");
        }

        Order order = orderRepository.findById(command.getOrderId());

        order.updateItem(command.getPointer(), command.getNewQuantity());

        orderRepository.save(order);
    }

}
