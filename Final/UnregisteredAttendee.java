/**
 * This class is used to store data for meeting attendees who
 * do not have matching ASURITE ids
 */

public class UnregisteredAttendee {

    //attributes for UnregisteredAttendee
    private String name;
    private int time;

    /**
     * Constructor for the UnregisteredAttendee class
     * @param name, used to initialize name attribute
     * @param time, used to initialize time attribute
     */
    public UnregisteredAttendee(String name, int time)
    {
        this.name = name;
        this.time = time;
    }

    /**
     * Accessor method for the name attribute
     * @return name attribute
     */
    public String getName()
    {
        return name;
    }

    /**
     * Accessor method for the time attribute
     * @return time attribute
     */
    public int getTime()
    {
        return time;
    }

    /**
     * Sets the time method to whatever newTime equals
     * @param newTime,
     */
    public void setTime(int newTime)
    {
        time = newTime;
    }
}