import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] products = {"Молоко", "Хлеб", "Гречневая крупа"};
    static int[] prices = {50, 14, 80};

    public static void main(String[] args) {
        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " "
                    + prices[i] + " руб/шт");
        }
        int sum = 0;
        int productCountsInBasket[] = {0, 0, 0};
        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                System.out.println("Ваша корзина:");
                for (int i = 0; i < products.length; i++) {
                    int currentProductCount = productCountsInBasket[i];
                    if (currentProductCount != 0) {
                        System.out.println(products[i] + " "
                                + currentProductCount + " шт " + prices[i] + " руб/шт "
                                + currentProductCount * prices[i] + " руб в сумме");
                    }
                }
                System.out.println("Итого " + sum + " руб");
                break;
            }
            int productNumber = Integer.parseInt(input.split(" ")[0]) - 1;
            int productCount = Integer.parseInt(input.split(" ")[1]);
            productCountsInBasket[productNumber] += productCount;
            sum += prices[productNumber] * productCount;
        }
    }
}
