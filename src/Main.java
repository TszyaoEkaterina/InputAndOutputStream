import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        File file = new File("basket.txt");
        File file = new File("basket.json");
        Basket basket = createBasket(file);
        basket.setProductsAndPrices(new String[]{"milk", "chocolate", "cake"}, new int[]{5, 10, 12});
        basket.addToCart(1, 2);
        basket.addToCart(2, 1);
        basket.addToCart(3, 3);
        basket.printCart();
        ClientLog log = new ClientLog();
        log.exportAsCSV(new File("basket.txt"));//csv file completed
        basket.saveJson(file);//json file completed

        Basket basket2 = createBasket(file);//load from json file
        basket2.printCart();

//        File binFile = new File("binBasket.txt");
//        basket2.saveBin(binFile);
//        Basket basket3 = Basket.loadFromBinFile(binFile);
//        System.out.println("\n(basket from bin file)");
//        basket3.printCart();
    }

    public static Basket createBasket(File file) {
        if (file.exists()) {
//            return Basket.loadFromTxtFile(file);
            return Basket.loadFromJson(file);
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