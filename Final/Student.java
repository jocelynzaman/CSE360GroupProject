public class Student {

    //Student attributes
    private String first_name;
    private String last_name;
    private String id;
    private String program;
    private String level;
    private String ASURITE;

    //Constructor for the Student calss
    public Student(String id, String first, String last, String program, String level, String ASURITE)
    {
        this.first_name = first;
        this.last_name = last;
        this.id = id;
        this.program = program;
        this.level = level;
        this.ASURITE = ASURITE;
    }

    //Accessor method for the first_name attribute
    public String getFirst_name()
    {
        return first_name;
    }

    //Accessor method for the last_name attribute
    public String getLast_name()
    {
        return last_name;
    }

    //Accessor method for the ID attribute
    public String getID()
    {
        return id;
    }

    //Accessor method for the program attribute
    public String getProgram()
    {
        return program;
    }

    //Accessor method for the level attribute
    public String getLevel()
    {
        return level;
    }

    //Accessor method for the ASURITE attribute
    public String getASURITE()
    {
        return ASURITE;
    }
}
