import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;

public class ClientLog {

    public void log(int productNum, int amount) {
        try (CSVWriter writer = new CSVWriter(new FileWriter("log.csv", true))) {
            writer.writeNext(new String[]{String.valueOf(productNum), String.valueOf(amount)});
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void exportAsCSV(File txtFile) {
        try (CSVWriter writer = new CSVWriter(new FileWriter("log.csv", true));
             BufferedReader reader = new BufferedReader(new FileReader(txtFile))) {
            for (int i = 0; i < 3; i++) {
                String line = reader.readLine();
                writer.writeNext(line.split(" "));
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
