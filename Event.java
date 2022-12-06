package Project3;

/**
 * This class creates objects that represent events in a person's life. Each event has two variables, a start year and a description.
 * @author marcusaltman
 *
 */
public class Event implements Comparable<Event>{
	private int startYear;
	private String description;
	
	/**
	 * This constructor creates a record of an event
	 * @param startYear The year the event started
	 * @param description A description of the event
	 */
	public Event (int startYear, String description) {
		this.startYear = startYear;
		this.description = description;
	}
	
	/**
	 * This method is a getter for startYear
	 * @return Returns startYear
	 */
	public int getStartYear() {
		return startYear;
	}
	
	/**
	 * This method compares events by year. It is used to sort the events in chronological order.
	 * @param event The event to compare against
	 * @return Returns -1 if the otherEvent occurred after the event, 0 if the events started the same year, and 1 if the otherEvent 
	 * occurred before the event
	 */
	@Override
	public int compareTo(Event otherEvent) {
		if (this.startYear < otherEvent.startYear) {
			return -1;
		}
		else if (this.startYear > otherEvent.startYear) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * This method creates a string representation of an event, including the year and description.
	 * @return Returns a string representation of the event
	 */
	@Override
	public String toString() {
		return startYear + ": " + description;
	}
}