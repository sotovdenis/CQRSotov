package gpb.api.console;

import gpb.api.facade.RestaurantFacade;
import gpb.command.model.Menu;
import gpb.command.model.OrderStatus;
import gpb.common.exception.OrderNotFoundException;
import gpb.query.dto.OrderDto;
import gpb.query.dto.OrderStatisticDto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleInterface {
    private final RestaurantFacade restaurantFacade;
    private final Scanner scanner;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public ConsoleInterface(RestaurantFacade restaurantFacade) {
        this.restaurantFacade = restaurantFacade;
        this.scanner = new Scanner(System.in);
    }


    public void start() {
        int choice;
        System.out.println();
        System.out.println("██████╗ ███████╗███████╗████████╗██╗ ██████╗ ██╗  ██╗    ██████╗ ███████╗███████╗████████╗██╗ ██████╗██╗  ██╗");
        System.out.println("██╔══██╗██╔════╝██╔════╝╚══██╔══╝██║██╔════╝ ██║ ██╔╝    ██╔══██╗██╔════╝██╔════╝╚══██╔══╝██║██╔════╝██║ ██╔╝");
        System.out.println("██████╔╝█████╗  ███████╗   ██║   ██║██║      █████╔╝     ██████╔╝█████╗  ███████╗   ██║   ██║██║     █████╔╝ ");
        System.out.println("██╔██╗  ██╔══╝  ╚════██║   ██║   ██║██║      ██╔═██╗     ██╔═══╝ ██╔══╝  ╚════██║   ██║   ██║██║     ██╔═██╗ ");
        System.out.println("██║ ██╗ ███████╗███████║   ██║   ██║╚██████╗ ██║  ██╗    ██║     ███████╗███████║   ██║   ██║╚██████╗██║  ██╗");
        System.out.println("╚═╝ ╚═╝ ╚══════╝╚══════╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═╝    ╚═╝     ╚══════╝╚══════╝   ╚═╝   ╚═╝ ╚═════╝╚═╝  ╚═╝");
        System.out.println();
        do {
            showMainMenu();
            choice = readIntInput();
            scanner.nextLine();
            handleMainMenuChoice(choice);
        } while (choice != 0);
    }


    private void showMainMenu() {
//        System.out.println("\n-----------  Рестик-Пестик ----------- ");
//        System.out.println("----------- by  Denis  Sotov ----------- ");
//        System.out.println("1. Создать новый заказ");
//        System.out.println("2. Показать все заказы");
//        System.out.println("3. Информация о заказе");
//        System.out.println("4. Добавить блюдо в заказ");
//        System.out.println("5. Изменить количество блюда");
//        System.out.println("6. Обновить заказ");
//        System.out.println("7. Убрать блюдо из заказа");
//        System.out.println("8. Обновить статус заказа");
//        System.out.println("9. Закрыть заказ");
//        System.out.println("10. Статистика заказов");
//        System.out.println("----------- ® all rights reserved ® ----------- ");
//        System.out.println("0. Выход");
//        System.out.print("Выберите действие: ");
        String border = "╔═══════════════════════════════════════════════╗";
        String footer = "╚═══════════════════════════════════════════════╝";

        System.out.println(border);
        System.out.println("║               РЕСТИК - ПЕСТИК                 ║");
        System.out.println("║               by Denis Sotov                  ║");
        System.out.println("║             all rights reserved ®             ║");
        System.out.println(footer);

        System.out.println("║ 1.  Создать новый заказ                       ║");
        System.out.println("║ 2.  Показать все заказы                       ║");
        System.out.println("║ 3.  Информация о заказе                       ║");
        System.out.println("║ 4.  Добавить блюдо в заказ                    ║");
        System.out.println("║ 5.  Изменить количество блюда                 ║");
        System.out.println("║ 6.  Обновить заказ                            ║");
        System.out.println("║ 7.  Убрать блюдо из заказа                    ║");
        System.out.println("║ 8.  Обновить статус заказа                    ║");
        System.out.println("║ 9.  Закрыть заказ                             ║");
        System.out.println("║ 10. Статистика заказов                        ║");
        System.out.println("║ 11. МЕНЮ                                      ║");
        System.out.println("║ 0.  Выход                                     ║");

        System.out.println(footer);
        System.out.println();
        System.out.print("--->> Выберите действие: ");
    }

    private void handleMainMenuChoice(int choice) {
        try {
            switch (choice) {
                case 0:
                    System.out.println("Выход из программы...");
                    break;
                case 1:
                    createOrder();
                    break;
                case 2:
                    showAllOrders();
                    break;
                case 3:
                    showOrderDetails();
                    break;
                case 4:
                    addDishToOrder();
                    break;
                case 5:
                    updateDishQuantity();
                    break;
                case 6:
                    updateOrder();
                    break;
                case 7:
                    removeDishFromOrder();
                    break;
                case 8:
                    updateOrderStatus();
                    break;
                case 9:
                    closeOrder();
                    break;
                case 10:
                    showOrderStatistics();
                    break;
                case 11:
                    showMenu();
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } catch (OrderNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private void createOrder() {
        System.out.print("Введите имя клиента: ");
        String customerName = scanner.nextLine().trim();
        restaurantFacade.createOrder(customerName);
        System.out.println("Заказ успешно создан!");
    }

    private void showAllOrders() {
        List<OrderDto> orders = restaurantFacade.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("Нет доступных заказов.");
            return;
        }

        String border = "╔══════════════════════════════════════════════════════════════════════════════════════════════╗";
        String footer = "╚══════════════════════════════════════════════════════════════════════════════════════════════╝";

        System.out.println("\n-----------------------------------------  ВСЕ ЗАКАЗЫ ----------------------------------------- ");
        System.out.println(border);
        System.out.printf("%-36s %-15s %-25s %-15s%n", "ID", "Имя клиента", "Статус", "Общая цена");
        System.out.println("------------------------------------------------------------------------------------------------");
        for (OrderDto order : orders) {
            System.out.printf("%-36s %-15s %-25s %,.2f руб.%n",
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getStatus(),
                    order.getPrice());
        }
        System.out.println(footer);
        System.out.println();
    }

    private void showOrderDetails() {

        String border = "╔═══════════════════════════════════════════════╗";
        String footer = "╚═══════════════════════════════════════════════╝";

        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine().trim();
        OrderDto order = restaurantFacade.getOrderById(orderId);
        System.out.println("\n -------------  Информация о заказе ------------- ");
        System.out.println(border);
        System.out.println("ID заказа: " + order.getOrderId());
        System.out.println("Имя клиента: " + order.getCustomerName());
        System.out.println("Статус: " + order.getStatus());
        System.out.println("Общая цена: " + String.format("%,.2f руб.", order.getPrice()));
        System.out.println("Создан: " + order.getCreatedAt().format(dateFormatter));
        System.out.println("Блюда:");
        for (var item : order.getItems()) {
            System.out.printf("- %s (%d шт.) - %,.2f руб.%n",
                    item.getDishName(),
                    item.getQuantity(),
                    item.getPrice());
        }
        System.out.println(footer);
        System.out.println();
    }

    private void addDishToOrder() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine().trim();

        String border = "╔═════════════════════════════════════════╗";
        String footer = "╚═════════════════════════════════════════╝";

        OrderDto order = restaurantFacade.getOrderById(orderId);

        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        System.out.println("Доступные блюда:");

        System.out.println(border);
        for (Menu dish : Menu.values()) {
            System.out.printf(Double.valueOf(dish.getNumberInMenu()).intValue() + ". %s (%,.2f руб.)%n", dish.getName(), dish.getPrice());
        }
        System.out.println(footer);

        System.out.print("Выберите номер блюда: ");
        int dishName = scanner.nextInt();
        Menu dish = Menu.getByNumberInMenu(dishName);
        System.out.print("Введите количество: ");
        int quantity = readIntInput();
        scanner.nextLine();
        restaurantFacade.addDishToOrder(orderId, dish, quantity);
        System.out.println("Блюдо успешно добавлено в заказ!");
    }

    private void updateDishQuantity() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine().trim();

        OrderDto order = restaurantFacade.getOrderById(orderId);
        for (int i = 0; i < order.getItems().size(); i++) {
            System.out.println(i + 1 + ". " + order.getItems().get(i).getDishName());
        }

        System.out.print("Введите номер блюда: ");
        int pointer = scanner.nextInt();
        Menu dish = Menu.getByNumberInMenu(pointer);

        System.out.print("Введите новое количество: ");
        int newQuantity = readIntInput();
        scanner.nextLine();
        restaurantFacade.updateDishInOrder(orderId, pointer - 1, newQuantity);
        System.out.println("Количество блюда успешно изменено!");
    }

    private void updateOrder() {

        String border = "╔═════════════════════════════════════════╗";
        String footer = "╚═════════════════════════════════════════╝";

        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine().trim();

        OrderDto order = restaurantFacade.getOrderById(orderId);
        for (int i = 0; i < order.getItems().size(); i++) {
            System.out.println(i + 1 + ". " + order.getItems().get(i).getDishName());
        }

        System.out.print("Введите номер блюда: ");
        int pointer = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.println("Доступные блюда:");

        System.out.println(border);
        for (Menu dish : Menu.values()) {
            System.out.printf(Double.valueOf(dish.getNumberInMenu()).intValue() + ". %s (%,.2f руб.)%n", dish.getName(), dish.getPrice());
        }
        System.out.println(footer);

        System.out.print("Введите номер нового блюда: ");
        int newDishPointer = scanner.nextInt();

        boolean answerNotGiven = true;

        scanner.nextLine();

        while (answerNotGiven) {
            System.out.println("Вы уверены, что хотите изменить блюдо?(y/n)");
            String answer = scanner.nextLine();

            switch (answer) {
                case "y" -> {
                    System.out.println("Окей, но придется заново готовить!");

                    restaurantFacade.updateOrderStatus(orderId, OrderStatus.IN_PROGRESS);

                    System.out.print("Введите новое количество: ");
                    int newQuantity = readIntInput();
                    scanner.nextLine();
                    restaurantFacade.updateOrder(orderId, pointer, newDishPointer, newQuantity);
                    System.out.println("Блюдо в заказе успешно изменено!");

                    answerNotGiven = false;
                }
                case "n" -> {
                    System.out.println("Ну ладно....");
                    answerNotGiven = false;
                }
            }
        }
    }

    private void removeDishFromOrder() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine().trim();
        OrderDto order = restaurantFacade.getOrderById(orderId);
        for (int i = 0; i < order.getItems().size(); i++) {
            System.out.println(i + 1 + ". " + order.getItems().get(i).getDishName());
        }

        System.out.print("Введите номер блюда, которое хотите удалить из заказа: ");
        int pointer = scanner.nextInt() - 1;
        scanner.nextLine();
        restaurantFacade.removeDishFromOrder(orderId, pointer);
        System.out.println("Блюдо успешно удалено из заказа!");
    }


    private void updateOrderStatus() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine().trim();
        System.out.println("Доступные статусы:");
        for (OrderStatus status : OrderStatus.values()) {
            if (!status.equals(OrderStatus.CLOSED)) {
                System.out.println(status.getNumberInEnum() + ". " + status.getDisplayName());
            }
        }
        System.out.print("Выберите номер нового статуса: ");
        int statusNumber = scanner.nextInt();

        OrderStatus newStatus = OrderStatus.getByNumberInStatuses(statusNumber);

        restaurantFacade.updateOrderStatus(orderId, newStatus);
        System.out.println("Статус заказа успешно обновлен!");
    }

    private void closeOrder() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine().trim();
        restaurantFacade.closeOrder(orderId);
        System.out.println("Заказ успешно закрыт!");
    }

    private void showOrderStatistics() {
        OrderStatisticDto stats = restaurantFacade.getOrderStatistics();

        double number = stats.getAveragePrice();
        String str = String.valueOf(number);

        Pattern pattern = Pattern.compile("^\\d+\\.\\d{2}");
        Matcher matcher = pattern.matcher(str);

        String num = null;
        if (matcher.find()) {
            num = matcher.group();
        } else {
            System.out.println("Формат не соответствует ожиданиям.");
        }

        String border = "╔═══════════════════════════════════════════╗";
        String footer = "╚═══════════════════════════════════════════╝";

        System.out.println("\n ------------ Статистика заказов ------------ ");

        System.out.println(border);

        System.out.println("  Всего заказов:                    " + stats.getTotalOrders());
        System.out.println("  Завершенных заказов:              " + stats.getCompletedOrders());
        System.out.println("  Заказов в процессе:               " + stats.getInProgressOrders());
        System.out.println("  Отмененных заказов:               " + stats.getCancelledOrders());
        System.out.println("  Средний чек:                      " + num);
        System.out.println("  Среднее количество блюд на заказ: " + String.format("%.2f", stats.getAverageItemsPerOrder()));

        System.out.println(footer);
    }

    public void showMenu() {
        String border = "╔═════════════════════════════════════════╗";
        String footer = "╚═════════════════════════════════════════╝";

        System.out.println("--------------- НАШЕ МЕНЮ ---------------");

        System.out.println(border);
        for (Menu dish : Menu.values()) {
            System.out.printf(Double.valueOf(dish.getNumberInMenu()).intValue() + ". %s (%,.2f руб.)%n", dish.getName(), dish.getPrice());
        }
        System.out.println(footer);
    }

    private int readIntInput() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            return -1;
        }
    }
}
