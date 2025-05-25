package gpb.query.service;


import gpb.common.event.*;
import gpb.common.event.base.Event;
import gpb.common.event.base.EventBus;
import gpb.query.model.OrderItemView;
import gpb.query.model.OrderView;
import gpb.query.repository.OrderViewRepository;

public class EventHandler implements EventBus.EventHandler {
    private final OrderViewRepository orderRepository;

    public EventHandler(OrderViewRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(Event event) {
        if (event instanceof OrderCreatedEvent) {
            handleOrderCreated((OrderCreatedEvent) event);
        } else if (event instanceof DishAddedToOrderEvent) {
            handleDishAddedToOrder((DishAddedToOrderEvent) event);
        } else if (event instanceof DishUpdatedInOrderEvent) {
            handleDishUpdatedInOrder((DishUpdatedInOrderEvent) event);
        } else if (event instanceof DishQuantityUpdatedEvent) {
            handleUpdateQuantity((DishQuantityUpdatedEvent) event);
        } else if (event instanceof DishRemovedFromOrderEvent) {
            handleDishRemovedFromOrder((DishRemovedFromOrderEvent) event);
        } else if (event instanceof OrderStatusUpdatedEvent) {
            handleOrderStatusUpdated((OrderStatusUpdatedEvent) event);
        } else if (event instanceof OrderClosedEvent) {
            handleOrderClosed((OrderClosedEvent) event);
        } else if (event instanceof OrderUpdatedEvent) {
            handleOrderUpdated((OrderUpdatedEvent) event);
        }
    }

    private void handleUpdateQuantity(DishQuantityUpdatedEvent orderUpdatedEvent) {
        OrderView order = orderRepository.findById(orderUpdatedEvent.getOrderId());

        order.updateItem(orderUpdatedEvent.getDishName(), orderUpdatedEvent.getNewQuantity());

        orderRepository.save(order);
    }

    private void handleOrderUpdated(OrderUpdatedEvent event) {
        OrderView orderView = orderRepository.findById(event.getOrderId());

        int point = event.getPoint();
        if (point < 0 || point >= orderView.getItems().size()) {
            throw new IllegalArgumentException("Недопустимая позиция для замены блюда");
        }

        OrderItemView newItem = new OrderItemView(
                event.getNewDish().getDishName(),
                event.getNewDish().getPrice(),
                event.getNewDish().getQuantity()
        );

        orderView.getItems().set(point, newItem);

        orderRepository.save(orderView);
    }

    private void handleOrderCreated(OrderCreatedEvent event) {
        OrderView orderView = new OrderView(
                event.getOrderId(),
                event.getCustomerName(),
                gpb.command.model.OrderStatus.CREATED,
                0,
                event.getTimestamp()
        );

        orderRepository.save(orderView);
    }

    private void handleDishAddedToOrder(DishAddedToOrderEvent event) {
        OrderView orderView = orderRepository.findById(event.getOrderId());

        OrderItemView newItem = new OrderItemView(
                event.getDish().getName(),
                event.getDish().getPrice(),
                event.getQuantity()
        );

        orderView.addItem(newItem);
        orderRepository.save(orderView);
    }

    private void handleDishUpdatedInOrder(DishUpdatedInOrderEvent event) {
        OrderView orderView = orderRepository.findById(event.getOrderId());
        orderView.getItems().stream()
                .filter(item -> item.getDishName().equals(event.getDishName()))
                .findFirst()
                .ifPresent(item -> item.setQuantity(event.getNewQuantity()));

        orderRepository.save(orderView);
    }

    private void handleDishRemovedFromOrder(DishRemovedFromOrderEvent event) {
        OrderView orderView = orderRepository.findById(event.getOrderId());
        orderView.getItems().removeIf(item -> item.getDishName().equals(event.getDishName()));
        orderRepository.save(orderView);
    }

    private void handleOrderStatusUpdated(OrderStatusUpdatedEvent event) {
        OrderView orderView = orderRepository.findById(event.getOrderId());
        orderView.setStatus(event.getNewStatus());
        orderRepository.save(orderView);
    }

    private void handleOrderClosed(OrderClosedEvent event) {
        OrderView orderView = orderRepository.findById(event.getOrderId());
        orderView.setStatus(gpb.command.model.OrderStatus.CLOSED);
        orderRepository.save(orderView);
    }
}