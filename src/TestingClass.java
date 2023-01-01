import java.io.File;

public class TestingClass {
    public static void main(String[] args) {
        File file = new File("basket.txt");
        Basket basket = new Basket(new String[]{}, new int[]{});
        if (file.exists()) {
            basket = basket.loadFromTxtFile(file);
        } else {
            basket = new Basket(new String[]{"milk", "chocolate", "cake"}, new int[]{5, 10, 12});
        }
        basket.addToCart(1, 2);
        basket.addToCart(2, 1);
        basket.printCart();

        basket.saveTxt(file);
        basket.addToCart(3, 3);
        basket = basket.loadFromTxtFile(file);
    }
}
