import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.*;
import java.util.Scanner;

public class EventLog {

	private Scanner input;
	private PrintStream output;
	private List<Event> eventList;
	private String LOGFILE = "eventlog.txt";

	public EventLog() throws FileNotFoundException{
		File logFile = new File(LOGFILE);

		eventList = new ArrayList<>();
		output = new PrintStream(logFile);
	}

	public List<Event> listAllEvents() {
		return eventList;
	}

	/**
	 *
	 * @param date
	 */
	public List<Event> listEvents(LocalDateTime date)
	{
		List<Event> tempList = new ArrayList<>();

     	for (Event e : eventList)
     	{
        	if (e.getDate() == date)
        	{
           		tempList.add(e);
        	}
     	}

     	return tempList;
	}

	/**
	 *
	 * @param type
	 */
	public List<Event> listEvents(EventType type) {
		List<Event> tempList = new ArrayList<>();

     	for (Event e : eventList)
     	{
        	if (e.getEventType() == type)
        	{
        		tempList.add(e);
        	}
     	}

    	return tempList;
	}

	/**
	 *
	 * @param userId
	 */
	public List<Event> listEvents(int userId) {
		List<Event> tempList = new ArrayList<>();

      for (Event e : eventList)
      {
         if (e.getStaffId() == userId)
         {
            tempList.add(e);
         }
      }

      return tempList;
	}

	/**
	 *
	 * @param event
	 */
	public void addEvent(Event event) {
		eventList.add(event);

		output.println(event.toString());
	}

}
