package gpb.command.model;

public enum Menu {
    PIZZA_MARGHERITA("Пицца Маргарита", "Пицца", 1200.99, 1),
    PIZZA_PEPPERONI("Пицца Пепперони", "Пицца", 1400.99, 2),
    BURGER_CLASSIC("Классический Бургер", "Бургеры", 900.99, 3),
    BURGER_CHEESE("Чизбургер", "Бургеры", 1000.99, 4),
    SALAD_CAESAR("Салат Цезарь", "Салаты", 800.99, 5),
    SALAD_GREEK("Греческий Салат", "Салаты", 900.49, 6),
    PASTA_CARBONARA("Паста Карбонара", "Паста", 1100.99, 7),
    PASTA_BOLOGNESE("Паста Болоньезе", "Паста", 1100.49, 8),
    SOUP_TOMATO("Томатный Суп", "Супы", 600.99, 9),
    SOUP_CHICKEN_NOODLE("Куриный Суп с Лапшой", "Супы", 700.49, 10),
    DESSERT_CHEESECAKE("Чизкейк", "Десерты", 500.99, 11),
    DESSERT_ICE_CREAM("Мороженое", "Десерты", 400.99, 12),
    DRINK_COKE("Кока-Кола", "Напитки", 200.49, 13),
    DRINK_WATER("Бутилированная Вода", "Напитки", 100.99, 14),
    T("test", "Супы", 100000.99, 15);

    private final String name;
    private final String category;
    private final double price;
    private final double numberInMenu;

    Menu(String name, String category, double price, double numberInMenu) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.numberInMenu = numberInMenu;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public double getNumberInMenu() {
        return numberInMenu;
    }

    public static Menu getByNumberInMenu(double numberInMenu) {
        for (Menu menu : values()) {
            if (menu.getNumberInMenu() == numberInMenu) {
                return menu;
            }
        }
        throw new IllegalArgumentException("Блюдо с номером " + numberInMenu + " не найдено");
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %.2f руб.", name, category, price);
    }
}