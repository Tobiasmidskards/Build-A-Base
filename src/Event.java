import java.time.LocalDateTime;

public class Event {

	private LocalDateTime date;
	private String tableName;
	private EventType eventType;
	private int staffId;

	public Event(LocalDateTime date, String tableName, EventType eventType, int staffId) {
		this.date = date;
      this.tableName = tableName;
      this.eventType = eventType;
      this.staffId = staffId;
	}
   
   public void setDate(LocalDateTime date)
   {
      this.date = date;
   }
   
   public LocalDateTime getDate()
   {
      return this.date;
   }
   
   public void setTableName(String tableName)
   {
      this.tableName = tableName;
   }
   
   public String getTableName()
   {
      return this.tableName;
   }
   
   public void setEventType(EventType eventType)
   {
      this.eventType = eventType;
   }
   
   public EventType getEventType()
   {
      return this.eventType;
   }
   
   public void setStaffId(int staffId)
   {
      this.staffId = staffId;
   }
   
   public int getStaffId()
   {
      return this.staffId;
   }
}