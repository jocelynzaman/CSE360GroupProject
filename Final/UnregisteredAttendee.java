public class UnregisteredAttendee {

    //attributes for UnregisteredAttendee
    private String name;
    private int time;

    public UnregisteredAttendee(String name, int time)
    {
        this.name = name;
        this.time = time;
    }

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
