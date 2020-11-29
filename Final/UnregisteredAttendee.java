public class UnregisteredAttendee {

    //attributes for UnregisteredAttendee
    private String name;
    private int time;

    //Constructor for the UnregisteredAttendee class
    //This class is used to store data for meeting attendees who
    //do not have matching ASURITE ids
    public UnregisteredAttendee(String name, int time)
    {
        this.name = name;
        this.time = time;
    }

    //Accessor method for the name method
    public String getName()
    {
        return name;
    }

    public int getTime()
    {
        return time;
    }

    public void setTime(int newTime)
    {
        time = newTime;
    }
}