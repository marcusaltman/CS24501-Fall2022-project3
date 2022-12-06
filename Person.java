package Project3;

import java.util.ArrayList;

/**
 * This class creates objects that represent people. Each person has instance variables for name, year of birth, year of death, 
 * number of marriages, number of children, and ArrayLists of spouses, children, and events
 * @author marcusaltman
 *
 */
public class Person implements Comparable<Person> {
	private String name;
	private int yearOfBirth;
	private int yearOfDeath;
	private int numMarriages = 0;
	private int numChildren = 0;
	private ArrayList<Person> spouses = new ArrayList<Person>();
	private ArrayList<Person> children = new ArrayList<Person>();
	private ArrayList<Event> events = new ArrayList<Event>();
	
	/**
	 * This constructor is used to create a record of a person who is still alive using their name and yearOfBirth
	 * @param name The name of the person to create
	 * @param yearOfBirth The birth year of the person to create
	 */
	public Person (String name, int yearOfBirth) {
		this.name = name;
		this.yearOfBirth = yearOfBirth;
	}
	
	/**
	 * This constructor creates a record of a person who has died using their name, yearOfBirth, and yearOfDeath
	 * @param name The name of the person to create
	 * @param yearOfBirth The birth year of the person to create
	 * @param yearOfDeath The death year of the person to create
	 */
	public Person (String name, int yearOfBirth, int yearOfDeath) {
		this.name = name;
		this.yearOfBirth = yearOfBirth;
		this.yearOfDeath = yearOfDeath;
	}

	/**
	 * This method is the getter for name
	 * @return Returns the person's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method is the getter for numMarriages
	 * @return Returns the number of marriages a person has
	 */
	public int getNumMarriages() {
		return numMarriages;
	}

	/**
	 * This method adds the provided spouse to the spouse list and increments numRecords
	 * @param spouse The spouse to add to the person's spouse list
	 */
	public void addSpouse(Person spouse) {
		spouses.add(spouse);
		numMarriages++;
	}
	
	/**
	 * This method returns the spouse at the specified index
	 * @param index The index of the desired spouse
	 * @return Returns the spouse at the specified index in spouses
	 * @throws Exception Throws an exception if the index is out of bounds for the spouses array
	 */
	public Person getSpouse(int index) throws Exception {
		if (index < 0 || index > spouses.size()) {
			throw new Exception("Index Out of Bounds");
		}
		return spouses.get(index);
	}
	
	/**
	 * This method is the getter for numChildren
	 * @return Returns the number of children the person has
	 */
	public int getNumChildren() {
		return numChildren;
	}
	
	
	/**
	 * This method returns the child in the person's children list at the given index 
	 * @param index The index of the child to return
	 * @return Returns the child at the specified index
	 */
	public Person getChild(int index) {
		return children.get(index);
	}
	
	/**
	 * This method adds a child to the person's children list
	 * @param child The child to add
	 */
	public void addChild(Person child) {
		children.add(child);
		numChildren++;
	}
	
	/**
	 * This method puts the person in both of their parents children lists
	 * @param parent1 One of the person's parents
	 * @param parent2 Another one of the person's parents
	 */
	public void addParents(Person parent1, Person parent2) {
		parent1.addChild(this);
		parent2.addChild(this);
	}
	
	/**
	 * This method creates an event and adds it to the person's event list
	 * @param year The start year of the event
	 * @param description The description of the event
	 */
	public void addEvent(int year, String description) {
		Event newEvent = new Event(year, description);
		
		// If the event list is empty, add the event to the list
		if (events.size() == 0) {
			events.add(newEvent);
			return;
		}
		
		// If the event list is not empty, iterate over the list and insert the event in its place in chronological order
		for (int i = 0; i < events.size(); i++) {
			if (newEvent.getStartYear() < events.get(i).getStartYear()) {
				events.add(i, newEvent);
				return;
			}
		}
		events.add(newEvent);
	}
	
	/**
	 * This method compares people by name so that they can be sorted and searched by name.
	 * @param otherPerson The person to compare against
	 * @return Returns -1 if the otherPerson's name goes after the person, 0 if the names are the same, and 1 if the otherPerson's name
	 * goes before the person
	 */
	@Override
	public int compareTo(Person otherPerson) {
		return this.name.compareTo(otherPerson.name);
	}
	
	/**
	 * This method creates a string representation of a person. It includes their name, birth year, death year if applicable, any
	 * events recorded for the person, and any spouses the person had.
	 * @return Returns a string representation of the person
	 */
	@Override
	public String toString() {
		int i;
		String str = name + " (" + yearOfBirth + "-";
		if (yearOfDeath > 0) {
			str += yearOfDeath + ")";
		}
		else {
			str += ")";
		}
		for (i = 0; i < events.size(); i++) {
			str += "; " + events.get(i);
		}
		str += " ";
		if (numMarriages > 1) {
			str += "m. ";
			for (i = 0; i < numMarriages - 1; i++) {
				str += spouses.get(i).name + ", ";
			}
			str += spouses.get(i).name;
		}
		else if (numMarriages == 1) {
			str += "m. " + spouses.get(0).name;
		}
		return str;
	}
	
	
}
