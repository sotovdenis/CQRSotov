package gpb.command.handler;

import gpb.command.command.CreateOrderCommand;
import gpb.command.model.Order;
import gpb.command.repository.OrderRepository;
import gpb.common.exception.OrderNotFoundException;

public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {
    private final OrderRepository orderRepository;

    public CreateOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(CreateOrderCommand command) {
        if (orderRepository.findByCustomerName(command.getCustomerName()).isPresent()) {
            throw new OrderNotFoundException("Заказ для клиента " + command.getCustomerName() + " уже существует");
        }

        Order order = new Order(command.getCustomerName());

        orderRepository.save(order);
    }
}