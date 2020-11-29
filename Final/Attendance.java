/**
 * This class stores the attendence information for students and unregistered
 * attendees for a single date
 */

import java.util.ArrayList;

import javax.lang.model.util.ElementScanner14;

public class Attendance{

    //Attendance attributes
    private String month;
    private int day;
    private int year;
    private int attendees; //amount of registered attendees
    private int additional; //amount of unregistered attendees
    private int[] times; //This array stores attendance time data for registered users
    private int timesSize; //Size of array times
    private ArrayList<UnregisteredAttendee> unregistered; //An arrayList used to store unregistereAttendee objects
    private String fileName; //The name of the file that the attendence will pull data

    /**
     * Constructor for the attendence class
     * @param month, used to initialize month attribute
     * @param day, used to initialize day attribute
     * @param year, used to initialize year attribute
     * @param rosterSize, used to initialize times array
     * @param fileName, used to initialize fileName attribute
     */
    public Attendance(int month, int day, int year, int rosterSize, String fileName)
    {
        this.month = convertMonth(month);
        this.day = day;
        this.year = year;
        this.attendees = 0;
        this.additional = 0;
        times = initArray(times, rosterSize);
        timesSize = rosterSize;
        unregistered = new ArrayList<UnregisteredAttendee>();
        this.fileName = fileName;
    }

    /**
     * initializes every value in an int array to be 0
     * @param array, array to be initialized
     * @param size, size of array to be initialized
     * @return
     */
    public int[] initArray(int[] array, int size)
    {
        array = new int[size];
        for(int xx = 0; xx < size; xx++)
        {
            array[xx] = 0;
        }
        return array;
    }

    /**
     * This method fills the times array and unregistered arrayList with
     *  registered and unregistered user data respectively
     * @param sRoster, Roster to be accessed in order to correctly insert attendence data in the correct index
     * @param fileReader, CSVReader object to be used to read in a file and store in a 2D array
     */
    public void fill(Roster sRoster, CSVReader fileReader)
    {
        //temp values that will be used to find ASURITE index and fill times array
        String testASURITE;
        int testUIndex; //used for testing whether or not unregistered user has appeared earlier in the file
        int index;
        int time;

        //generating table of ASURITES and times
        ArrayList<ArrayList<String>> table = fileReader.read(fileName);

        //creating arrayList of used indices for tracking # of attendees
        ArrayList<Integer> track = new ArrayList<Integer>();

        //grabbing the y dimension of the table
        int ySize = table.size();

        for (int yy = 0; yy < ySize; yy++)
        {
            testASURITE = table.get(yy).get(0);
            index = sRoster.findIndex(testASURITE); //find the index in the roster corresponding to the given ASURITE
            time = Integer.parseInt(table.get(yy).get(1));

            if (index != -1)
            {
                times[index] += time;

                //checking to see if this attendee has already been accounted for, if not, increment attendees
                if (!track.contains(index))
                {
                    track.add(index);
                    attendees++;
                }
            }
            else //user is unregistered
            {
                //check if this unregistered attendee has been logged yet
                testUIndex = unregisteredDupeCheck(unregistered, testASURITE);
                if (testUIndex == -1) //if not add new unregistered attendee object
                {
                    UnregisteredAttendee UA = new UnregisteredAttendee(testASURITE, time);
                    unregistered.add(UA);
                }
                else //if yes, then sum both times together
                {
                    unregistered.get(testUIndex).setTime(unregistered.get(testUIndex).getTime() + time);
                }
            }
        }
        //count how many unregistered attendees were present
        additional = unregistered.size();
    }

    /**
     * iterates through unregistered arrayList searching for duplicates by name
     * @param list, ArrayList of unregisteredAttendees
     * @param testName, name to be checked against when searching for duplicates
     * @return the integer index of the object in list containing the testName, -1 if the obect is not found
     */
    public int unregisteredDupeCheck(ArrayList<UnregisteredAttendee> list, String testName)
    {
        int size = list.size();
        for (int xx = 0; xx < size; xx++)
        {
            //check if this name already exists
            if (list.get(xx).getName().equals(testName))
            {
                return xx;
            }
        }
        //else
        return -1;
    }

    /**
     * Converts an integer from 1-12 into
     * a corresponding month using a switch statement
     * @param intMonth, integer representing the month to be converted to a String
     * @return the String name of the month which was converted from an integer
     */
    public String convertMonth(int intMonth)
    {
        String month;
        switch (intMonth) {
            case 1:
                month = "Jan";
                break;
            case 2:
                month = "Feb";
                break;
            case 3:
                month = "Mar";
                break;
            case 4:
                month = "Apr";
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
                month = "Aug";
                break;
            case 9:
                month = "Sept";
                break;
            case 10:
                month = "Oct";
                break;
            case 11:
                month = "Nov";
                break;
            case 12:
                month = "Dec";
                break;
            default:
                month = "Invalid month";
                break;
        }
        return month;
    }

    /**
     * Accessor method for the month attribute
     * @return the month attribute
     */
    public String getMonth()
    {
        return month;
    }

    /**
     * Accessor method for the day attribute
     * @return the day attribute
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Accessor method for the year attribute
     * @return the year attribute
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Accessor method for the attendees attribute
     * @return the attendees attribute
     */
    public int getAttendance()
    {
        return attendees;
    }

    /**
     * Accessor method for the additional attribute
     * @return the additional attribute
     */
    public int getAdditional()
    {
        return additional;
    }

    /**
     * returns the array of time students spent in the lecture as strings
     * @return a String[] of of the times integer array converted into String format
     */
    public String[] getData()
    {
        String stringTimes[] = new String[timesSize];
        for (int i = 0; i < timesSize; i++)
        {
            stringTimes[i] = "" + (times[i]);
        }
        return stringTimes;
    }

    /**
     * returns the times array attribute as is, an integer array
     * @return the times integer array attribute
     */
    public int[] getTimes()
    {
        return times;
    }

    /**
     * Accessor method for the size of the times array
     * @return timesSize
     */
    public int getTimesSize()
    {
        return timesSize;
    }

    /**
     * This method retuns a sring message which will be displayed in a
     * window every time a new attendence object is added
     * @return the concatenated message
     */
    public String getMessage()
    {
        String message = "";

        message += "<html>Data loaded for " + attendees
                + " users in the roster.<br>";
        if (additional > 1)
        {
            message +=  additional + " additional attendees were found:<br>";
            message += listUnregistered();
        }
        else if (additional == 0)
        {
            message += "No additional attendees were found<br>";
        }
        else
        {
            message += additional + " additional attendee was found:<br>";
            message += listUnregistered();
        }
        return message;
    }

    /**
     * returns a string of all unregistered attendees and their attendence times
     * @return concatenated message of unregistered attendee data
     */
    public String listUnregistered()
    {
        String message = "";
        int size = unregistered.size();
        for (int xx = 0; xx < size; xx++)
        {
            message += unregistered.get(xx).getName()
                    + ", connected for ";

            if (unregistered.get(xx).getTime() == 1)
            {
                message += "1 minute<br>";
            }
            else
            {
                message += unregistered.get(xx).getTime()
                        + " minutes<br>";
            }
        }
        return message;
    }
}


