package net.javacode.swing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private static ArrayList<ArrayList<String>> generatedTable = new ArrayList<ArrayList<String>>();

    public final String delimiter = ",";
    public void read(String csvFile) {
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
    }

    public ArrayList<ArrayList<String>> getGeneratedTable(){
        return generatedTable;
    }

    public void printGeneratedTable(){
        for (List<String> getRow : generatedTable) {
            for (String data : getRow) {
                System.out.print(data + " ");
            }

            System.out.println();
        }
    }

}