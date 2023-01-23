import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

public class Main {
    static ClientLog log = new ClientLog();

    public static void main(String[] args) {
//        File file = new File("basket.txt");
        File file = new File("basket.json");
        Basket basket = createBasket(file);
        basket.setProductsAndPrices(new String[]{"milk", "chocolate", "cake"}, new int[]{5, 10, 12});
        basket.addToCart(1, 2);
        basket.addToCart(2, 1);
        basket.addToCart(3, 3);
        logConfig();
        basket.printCart();

        Basket basket2 = createBasket(file);
        basket2.printCart();
    }

    public static Basket createBasket(File file) {
        if (file.exists()) {
//            return Basket.loadFromTxtFile(file);
//            return Basket.loadFromJson(file);
            return loadConfig();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.getMessage();
            }
            return new Basket();
        }
    }

    static XPath xPath = XPathFactory.newInstance().newXPath();


    public static Basket loadConfig() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("shop.xml");
            String loadFileName = xPath.compile("/config/load/fileName").evaluate(doc);
            boolean load = Boolean.parseBoolean(xPath
                    .compile("/config/load/enabled").evaluate(doc));
            String loadFormat = xPath.compile("/config/load/format").evaluate(doc);
            File loadFile = new File(loadFileName);
            Basket basket;
            if (load) {
                switch (loadFormat) {
                    case "json":
                        basket = Basket.loadFromJson(loadFile);//TODO
                        break;
                    case "txt":
                        basket = Basket.loadFromTxtFile(loadFile);
                        break;
                    default:
                        basket = new Basket();
                }
                return basket;
            } else {
                return new Basket();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveConfig(Basket basket) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("shop.xml");
            String saveFileName = xPath.compile("/config/save/fileName").evaluate(doc);
            boolean save = Boolean.parseBoolean(xPath
                    .compile("/config/save/enabled").evaluate(doc));
            String saveFormat = xPath.compile("/config/save/format").evaluate(doc);
            File saveFile = new File(saveFileName);
            if (save) {
                switch (saveFormat) {
                    case "json":
                        basket.saveJson(saveFile);
                        break;
                    case "txt":
                        basket.saveTxt(saveFile);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logConfig() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("shop.xml");
            String logFileName = xPath.compile("/config/log/fileName").evaluate(doc);
            boolean doLog = Boolean.parseBoolean(xPath
                    .compile("/config/log/enabled").evaluate(doc));
            if (doLog) {
                log.exportAsCSV(new File(logFileName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}