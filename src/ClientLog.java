import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {
    private List logList = new ArrayList<Integer>();

    public void log(int productNum, int amount/*, String logFileName*/) {
        logList.add(productNum);
        logList.add(amount);
    }

    public void exportAsCSV(File file) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(file, true))) {
            file.createNewFile();
            for (int i = 0; i < logList.size(); i = i + 2) {
                writer.writeNext(new String[]{String.valueOf(logList.get(i)),
                        String.valueOf(logList.get(i + 1))});
                writer.flush();
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
