import java.util.ArrayList;

public class Roster extends ArrayList<Student>{

    //StudentList attributes
    //private ArrayList<Student> sList;
    private int size;
    private CSVReader fileReader;

    public Roster(CSVReader reader)
    {
        //sList = new ArrayList<Student>();
        fileReader = reader;
        size = 0;
    }

    public void fill(String fileName)
    {
        //generating table of student information
        ArrayList<ArrayList<String>> table = fileReader.read(fileName);

        //grabbing the y dimension of the table
        int ySize = table.size();

        for (int yy = 0; yy < ySize; yy++)
        {
            ArrayList<String> temp = table.get(yy);
            addStudent(Integer.parseInt(temp.get(0)), temp.get(1), temp.get(2), temp.get(3), temp.get(4), temp.get(5));
        }
    }

    public boolean addStudent(int id, String first, String last, String program, String level, String ASURITE)
    {
        try
        {
            Student newStudent = new Student(first, last, id, program, level, ASURITE);
            add(newStudent);
            size++;
        } catch(Exception e) //try for any exception to see if adding was successful
        {
            return false;
        }
        return true;
    }

    //return index if found, -1 if not
    public int findIndex(String ASURITE)
    {
        for (int i = 0; i < size; i++)
        {
            if ((get(i).getASURITE().equals(ASURITE)))
            {
                return i;
            }
        }
        return -1;
    }

    public int getSize()
    {
        return size;
    }
}
