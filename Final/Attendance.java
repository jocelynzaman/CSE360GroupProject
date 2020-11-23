import java.util.ArrayList;

public class Attendance {

    //Attendance attributes
    private String month;
    private int day;
    private int attendees;
    private int additional;
    private int[] times;
    private ArrayList<UnregisteredAttendee> unregistered;
    private String fileName;

    public Attendance(int month, int day, int rosterSize, String fileName)
    {
        this.month = convertMonth(month);
        this.day = day;
        this.attendees = 0;
        this.additional = 0;
        times = new int[rosterSize];
        unregistered = new ArrayList<UnregisteredAttendee>();
        this.fileName = fileName;
    }

    public void fill(Roster sRoster, CSVReader fileReader)
    {
        //temp values that will be used to find ASURITE index and fill times array
        String testASURITE;
        int index;
        int time;

        //generating table of ASURITES and times
        ArrayList<ArrayList<String>> table = fileReader.read(fileName);

        //grabbing the y dimension of the table
        int ySize = table.get(0).size();

        for (int yy = 0; yy < ySize; yy++)
        {
            testASURITE = table.get(yy).get(0);
            index = sRoster.findIndex(testASURITE); //find the index in the roster corresponding to the given ASURITE
            time = Integer.parseInt(table.get(yy).get(1));

            if (index != -1)
            {
                times[index] = time;
                attendees++;
            }
            else
            {
                UnregisteredAttendee UA = new UnregisteredAttendee(testASURITE, time);
                unregistered.add(UA);
            }
        }
        additional = unregistered.size();
    }

    public String convertMonth(int intMonth)
    {
        String month;
        switch (intMonth) {
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
            default:
                month = "Invalid month";
                break;
        }
        return month;
    }

    public String getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public int getAttendance()
    {
        return attendees;
    }

    public int getAdditional()
    {
        return additional;
    }

    public String getMessage()
    {
        String message = "";
        int size = unregistered.size();

        message += "Data loaded for " + attendees
                + " users in the roster.\n"
                + additional;
         if (additional > 1)
         {
             message += " additional attendees were found:\n";
         }
         else
         {
             message += " additional attendee was found:\n";
         }

        for (int xx = 0; xx < size; xx++)
        {
            message += unregistered.get(xx).getName()
                    + ", connected for "
                    + unregistered.get(xx).getTime()
                    + " minute\n";
        }

        return message;
    }
}


