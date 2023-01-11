import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File file = new File("basket.txt");
        Basket basket = createBasket(file);
        basket.setProductsAndPrices(new String[]{"milk", "chocolate", "cake"}, new int[]{5, 10, 12});
        basket.addToCart(1, 2);
        basket.addToCart(2, 1);
        basket.printCart();

        basket.addToCart(3, 3);
        Basket basket2 = createBasket(file);
        basket2.printCart();

        //Задача 2
        File binFile = new File("binBasket.txt");
        basket2.saveBin(binFile);
        Basket basket3 = Basket.loadFromBinFile(binFile);
        System.out.println("\n(basket from bin file)");
        basket3.printCart();
    }

    public static Basket createBasket(File file) {
        if (file.exists()) {
            return Basket.loadFromTxtFile(file);
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.getMessage();
            }
            return new Basket();
        }
    }
}
