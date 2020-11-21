

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public final String delimiter = ",";

    public ArrayList<ArrayList<String>> read(String csvFile) {
        ArrayList<ArrayList<String>> generatedTable = new ArrayList<ArrayList<String>>();
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            ArrayList<String> readLine = new ArrayList<String>();
            while((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                for (String tempStr : tempArr) {
                    readLine.add(tempStr);
                }
                generatedTable.add(readLine);
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return generatedTable;
    }


}