package net.javacode.swing;


import java.io.*;
import java.util.ArrayList;

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

        for (int i = 0; i < generatedTable.size(); i++) {
            for (int j = 0; j < generatedTable.get(i).size(); j++) {
                System.out.print(generatedTable.get(i).get(j) + " ");
            }
            System.out.println();
        }

        return generatedTable;
    }


}