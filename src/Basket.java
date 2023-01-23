import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Scanner;

public class Basket implements Serializable {
    private String[] products;
    private int[] prices;
    private int[] amounts;


    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        amounts = new int[products.length];
        for (int i = 0; i < products.length; i++) {
            amounts[i] = 0;
        }
//        saveTxt(txtFile);
        Main.saveConfig(this);
    }

    public Basket(String[] products, int[] prices, int[] amounts) {
        this.products = products;
        this.prices = prices;
        this.amounts = amounts;
    }

    public Basket() {
    }

    public void addToCart(int productNum, int amount) {
        Main.log.log(productNum, amount);
        amounts[productNum - 1] += amount;
//        saveTxt(txtFile);
        Main.saveConfig(this);
    }

    public void printCart() {
        if (products == null || prices == null) {
            System.out.println("Ничего не загружено. Корзина пуста.");
            return;
        }
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFile, false))) {
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
            return new Basket(loadedProducts, loadedPrices, loadedAmounts);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void saveBin(File file) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Basket loadFromBinFile(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Basket) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void setProductsAndPrices(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        amounts = new int[products.length];
        for (int i = 0; i < products.length; i++) {
            amounts[i] = 0;
        }
//        saveTxt(txtFile);
        Main.saveConfig(this);
    }

    public void saveJson(File file) {
        try (FileWriter writer = new FileWriter(file, false)) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String json = gson.toJson(this);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Basket loadFromJson(File file) {
        try (Scanner scanner = new Scanner(file)) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String jsonStr = scanner.nextLine();
            return gson.fromJson(jsonStr, Basket.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
