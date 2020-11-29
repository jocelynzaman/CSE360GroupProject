// package net.javacode.swing;

import java.util.ArrayList;

public class Roster extends ArrayList<Student>{

    //StudentList attributes
    //private ArrayList<Student> sList;
    private int size;
    private CSVReader fileReader; //this is used to read CSV files and store their data in tables
    ArrayList<ArrayList<String>> newTable = new ArrayList<ArrayList<String>>(); //this table is filled and returned

    //Constructor for the Roster class
    //Roster extends arraylist so it can be treated as such
    public Roster(CSVReader reader)
    {
        //sList = new ArrayList<Student>();
        fileReader = reader;
        size = 0;
    }

    //this fills a roster based on a given CSV file
    public ArrayList<ArrayList<String>> fill(String fileName)
    {
        {
            //generating table of student information
            ArrayList<ArrayList<String>> table = fileReader.read(fileName);

            //grabbing the y dimension of the table
            int ySize = table.size();

            for (int yy = 0; yy < ySize; yy++)
            {
                //checking if given ID is a duplicate
                if (!duplicateCheck(table.get(yy).get(0)))
                {
                    ArrayList<String> temp = table.get(yy);
                    if (temp.size() == 6) {
                        newTable.add(temp);
                        addStudent(temp.get(0), temp.get(1), temp.get(2), temp.get(3), temp.get(4), temp.get(5));
                    }
                    else{
                        System.out.println(temp.get(1));
                    }
                }
                //else duplicate id has been found and not added
            }
            return newTable;
        }
    }

    //This method adds a student to the roster and increments the roster's size attribute
    public boolean addStudent(String id, String first, String last, String program, String level, String ASURITE)
    {
        try
        {
            Student newStudent = new Student(id, first, last, program, level, ASURITE);
            add(newStudent);
            size++;
        } catch(Exception e) //try for any exception to see if adding was successful
        {
            return false;
        }
        return true;
    }

    //Returns index if found, -1 if not
    public int findIndex(String ASURITE)
    {
        for (int i = 0; i < size; i++)
        {
            if (get(i).getASURITE().toLowerCase().equals((ASURITE).toLowerCase()))
            {
                return i;
            }
        }
        return -1;
    }

    //Returns true if matching ID is found in roster,
    //false otherwise
    public boolean duplicateCheck(String ID)
    {
        for (int i = 0; i < size; i++)
        {
            if (get(i).getID().equals(ID))
            {
                return true;
            }
        }
        return false;
    }

    //Accessor method for the size attribute
    public int getSize()
    {
        return size;
    }
}