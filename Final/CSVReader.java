/**
 * Author: Thao Vu
 * SID: 127349445
 */

import java.io.*;
import java.util.ArrayList;

/**
 * This class read a .csv file and generate a dynamic 2D array for its content
 */

public class CSVReader {

    public final String delimiter = ",";

    /**
     * This method always returns a dynamic 2D array of the file'scontent: rows as number of lines,
     * and columns as the number of fields separated by commas
     * @param  csvFile the name of the csv file needs to be read
     * @return  a dynamic 2D array of the data being fetch with each line in the .csv file is an ArrayList
     */
    public ArrayList<ArrayList<String>> read(String csvFile) {
        ArrayList<ArrayList<String>> generatedTable = new ArrayList<ArrayList<String>>();
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;

            while((line = br.readLine()) != null) {
                ArrayList<String> readLine = new ArrayList<String>();
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