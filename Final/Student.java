public class Student {

    //Student attributes
    private String first_name;
    private String last_name;
    private int id;
    private String program;
    private String level;
    private String ASURITE;

    public Student(String first, String last, int id, String program, String level, String ASURITE)
    {
        this.first_name = first;
        this.last_name = last;
        this.id = id;
        this.program = program;
        this.level = level;
        this.ASURITE = ASURITE;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public int getId()
    {
        return id;
    }

    public String getProgram()
    {
        return program;
    }

    public String getLevel()
    {
        return level;
    }

    public String getASURITE()
    {
        return ASURITE;
    }
}
