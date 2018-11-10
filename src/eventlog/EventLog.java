package eventlog;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.io.*;
import java.util.Scanner;

public class EventLog {

	private PrintWriter printWriter
	private List<Event> eventList;
	private String LOGFILE = "eventlog/eventlog.txt";

	public EventLog()
		throws FileNotFoundException
	{
		FileWriter logFile = new FileWriter(LOGFILE, true);
		BufferedWriter bufferedWriter = new BufferedWriter(logFile);
		PrintWriter printWriter = new PrintWriter(bufferedWriter);

		eventList = new ArrayList<>();
	}

	public List<Event> listAllEvents() {
		return eventList;
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

	public List<String> getAllLogFileEvents() {
		File logFile = new File(LOGFILE);
		if (!logFile.canRead())
		{
			return new ArrayList<>();
		}

		Scanner input = new Scanner(logFile);
		List<String> events = new ArrayList<>();

		while (input.hasNextLine())
		{
			events.add(input.nextLine());
		}

		return events;
	}

	/**
	 *
	 * @param event
	 */
	public void addEvent(Event event) {
		eventList.add(event);

		printWriter.println(event);
		printWriter.flush();
	}

}
