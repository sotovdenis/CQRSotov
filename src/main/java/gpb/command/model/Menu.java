package gpb.command.model;

public enum Menu {
    PIZZA_MARGHERITA("Пицца Маргарита", "Пицца", 1200.99),
    PIZZA_PEPPERONI("Пицца Пепперони", "Пицца", 1400.99),
    BURGER_CLASSIC("Классический Бургер", "Бургеры", 900.99),
    BURGER_CHEESE("Чизбургер", "Бургеры", 1000.99),
    SALAD_CAESAR("Салат Цезарь", "Салаты", 800.99),
    SALAD_GREEK("Греческий Салат", "Салаты", 900.49),
    PASTA_CARBONARA("Паста Карбонара", "Паста", 1100.99),
    PASTA_BOLOGNESE("Паста Болоньезе", "Паста", 1100.49),
    SOUP_TOMATO("Томатный Суп", "Супы", 600.99),
    SOUP_CHICKEN_NOODLE("Куриный Суп с Лапшой", "Супы", 700.49),
    DESSERT_CHEESECAKE("Чизкейк", "Десерты", 500.99),
    DESSERT_ICE_CREAM("Мороженое", "Десерты", 400.99),
    DRINK_COKE("Кока-Кола", "Напитки", 200.49),
    DRINK_WATER("Бутилированная Вода", "Напитки", 100.99),
    T("test", "Супы", 100000.99);

    private final String name;
    private final String category;
    private final double price;

    Menu(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
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

    @Override
    public String toString() {
        return String.format("%s (%s) - %.2f руб.", name, category, price);
    }
}