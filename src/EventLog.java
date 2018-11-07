import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class EventLog {

	private List<Event> eventList;

	public EventLog() {
		// TODO - implement EventLog.EventLog
		throw new UnsupportedOperationException();
	}

	public List<Event> listAllEvents() {
		return eventList;
	}

	/**
	 * 
	 * @param date
	 */
	public List<Event> listEvents(LocalDateTime date) {
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
		// TODO - implement EventLog.listEvents
		throw new UnsupportedOperationException();
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
	}

	/**
	 * 
	 * @param event
	 */
	public void removeEvent(Event event) {
		eventList.remove(event);
	}

}