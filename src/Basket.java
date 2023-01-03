import java.io.*;

public class Basket {
    String[] products;
    int[] prices;
    int[] amounts;
    File file = new File("basket.txt");


    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        amounts = new int[products.length];
        for (int i = 0; i < products.length; i++) {
            amounts[i] = 0;
        }
        saveTxt(file);
    }

    private Basket(String[] products, int[] prices, int[] amounts) {
        this.products = products;
        this.prices = prices;
        this.amounts = amounts;
    }

    public void addToCart(int productNum, int amount) {
        amounts[productNum - 1] += amount;
        saveTxt(file);
    }

    public void printCart() {
        System.out.println("Ваша корзина:");
        int sum = 0;
        for (int i = 0; i < products.length; i++) {
            int currentProductAmount = amounts[i];
            if (currentProductAmount != 0) {
                System.out.println(products[i] + " "
                        + currentProductAmount + " шт " + prices[i] + " руб/шт "
                        + currentProductAmount * prices[i] + " руб в сумме");
            }
            sum += currentProductAmount * prices[i];
        }
        System.out.println("Итого " + sum + " руб");
    }

    public void saveTxt(File textFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            if (!textFile.exists()) {
                textFile.createNewFile();
            }
            for (String product : products) {
                writer.write(product + " ");
            }
            writer.newLine();
            for (int price : prices) {
                writer.write(price + " ");
            }
            writer.newLine();
            for (int amount : amounts) {
                writer.write(amount + " ");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Basket loadFromTxtFile(File textFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader("basket.txt"))) {
            String[] loadedProducts = reader.readLine().split(" ");
            String[] loadedPricesString = reader.readLine().split(" ");
            int[] loadedPrices = new int[loadedPricesString.length];
            for (int i = 0; i < loadedPricesString.length; i++) {
                loadedPrices[i] = Integer.parseInt(loadedPricesString[i]);
            }
            String[] loadedAmountsString = reader.readLine().split(" ");
            int[] loadedAmounts = new int[loadedAmountsString.length];
            for (int i = 0; i < loadedAmountsString.length; i++) {
                loadedAmounts[i] = Integer.parseInt(loadedAmountsString[i]);
            }
            return new Basket(loadedProducts,loadedPrices,loadedAmounts);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
