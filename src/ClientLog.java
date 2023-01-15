import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;

public class ClientLog {

    public void log(int productNum, int amount, String logFileName) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(logFileName, true))) {
            writer.writeNext(new String[]{String.valueOf(productNum), String.valueOf(amount)});
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void exportAsCSV(File txtFile, String logFileName) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(logFileName, true));
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
