import java.io.File;

public class TestingClass {
    public static void main(String[] args) {
        File file = new File("basket.txt");
        Basket basket;
        if (file.exists()) {
            basket = Basket.loadFromTxtFile(file);
        } else {
            basket = new Basket(new String[]{"milk", "chocolate", "cake"}, new int[]{5, 10, 12});
        }
        basket.addToCart(1, 2);
        basket.addToCart(2, 1);
        basket.printCart();

        basket.addToCart(3, 3);
        Basket basket2 = Basket.loadFromTxtFile(file);
        basket2.printCart();

        //Задача 2
        File binFile = new File("binBasket.txt");
        basket2.saveBin(binFile);
        Basket basket3 = Basket.loadFromBinFile(binFile);
        System.out.println("\n(basket from bin file)");
        basket3.printCart();
    }
}
