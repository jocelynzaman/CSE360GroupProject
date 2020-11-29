/**
 * This class stores information for a single student
 */

public class Student {

    //Student attributes
    private String first_name;
    private String last_name;
    private String id;
    private String program;
    private String level;
    private String ASURITE;

    /**
     * Constructor for the Student class
     * @param id, used to initialize id attribute
     * @param first, used to initialize first_name attribute
     * @param last, used to initialize last_name attribute
     * @param program, used to initialize program attribute
     * @param level, used to initialize level attribute
     * @param ASURITE, used to initialize ASURITE attribute
     */
    public Student(String id, String first, String last, String program, String level, String ASURITE)
    {
        this.first_name = first;
        this.last_name = last;
        this.id = id;
        this.program = program;
        this.level = level;
        this.ASURITE = ASURITE;
    }

    /**
     * Accessor method for the first_name attribute
     * @return first_name attribute
     */
    public String getFirst_name()
    {
        return first_name;
    }

    /**
     * Accessor method for the last_name attribute
     * @return last_name attribute
     */tribute
    public String getLast_name()
    {
        return last_name;
    }

    /**
     * Accessor method for the id attribute
     * @return id attribute
     */
    public String getID()
    {
        return id;
    }

    /**
     * Accessor method for the program attribute
     * @return program attribute
     */
    public String getProgram()
    {
        return program;
    }

    /**
     * Accessor method for the level attribute
     * @return level attribute
     */
    public String getLevel()
    {
        return level;
    }

    /**
     * Accessor method for the ASURITE attribute
     * @return ASURITE attribute
     */
    public String getASURITE()
    {
        return ASURITE;
    }
}
