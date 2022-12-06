package Project3;

import java.util.ArrayList;

/**
 * This class represents a FamilyTree. It holds all of the members in an ArrayList.
 * @author marcusaltman
 *
 */
public class FamilyTree {
	
	private ArrayList<Person> family = new ArrayList<Person>();
	
	
	/**
	 * This method receives the name and birth year of a living Person to add to the family list, checks the list to see if the 
	 * person is already in the list, and adds the person in alphabetical order by name if they are not in the list
	 * @param name The name of the person to add to the family list
	 * @param birthYear The year the person to add was born
	 * @return Returns the new person or the existing person if the name is already in the list
	 */
	public Person addPerson(String name, int birthYear) {
		
		// If the list is empty, add the person to the list
		if (family.size() == 0) {
			Person newPerson = new Person(name, birthYear);
			family.add(newPerson);
		}
		
		// Find the insertion point for the new person
		int index = binarySearch(name);
		
		// If the person is already in the list, do not add them
		if (index < family.size() && name.compareTo(family.get(index).getName()) == 0) {
			return family.get(index);
		}
		
		// If the person is not in the list, add them at the specified index
		else {
			Person newPerson = new Person(name, birthYear);
			family.add(index, newPerson);
			return newPerson;
		}
	}
	
	/**
	 * This method receives the name, birth year, and death year of a deceased Person to add to the family list, checks the list 
	 * to see if the person is already in the list, and adds the person in alphabetical order by name if they are not in the list
	 * @param name The name of the person to add to the family list
	 * @param birthYear The year the person to add was born
	 * @param deathYear The year the person to add died
	 * @return Returns the new person or the existing person if the name is already in the list
	 */
	public Person addPerson(String name, int birthYear, int deathYear) {
		
		// If the list is empty, add the person to the list
		if (family.size() == 0) {
			Person newPerson = new Person(name, birthYear, deathYear);
			family.add(newPerson);
		}
		
		// Find the insertion point for the new person
		int index = binarySearch(name);
		
		// If the person is already in the list, do not add them
		if (index < family.size() && name.compareTo(family.get(index).getName()) == 0) {
			return family.get(index);
		}
		
		// If the person is not in the list, add them at the specified index
		else {
			Person newPerson = new Person(name, birthYear, deathYear);
			family.add(index, newPerson);
			return newPerson;
		}
	}
	
	/**
	 * This method prints the family tree starting with the Person named, and then using the printChildren method to print subsequent
	 * generations
	 * @param name The name of the Person at the root of the tree
	 */
	public void printTree(String name) {
		System.out.println(); // Add a space between the last command and the family tree printout
		Person rootPerson = getPerson(name);
		if (rootPerson == null) {
			System.out.println(name + " has no tree.");
			return;
		}
		else {
			System.out.println(rootPerson + "\n");
			printChildren(rootPerson, 1);
		}
	}
	
	/**
	 * This method recursively prints all of the children and grand-children using a depth-first search
	 * @param person The person whose children need to be printed
	 * @param generation The generation of the person parameter. Used to determine how far to indent when printing a person.
	 */
	private void printChildren(Person person, int generation) {
		for (int i = 0; i < person.getNumChildren(); i++) {
			System.out.println(("\t".repeat(generation)) + person.getChild(i) + "\n");
			printChildren(person.getChild(i), generation + 1);
		}
	}
	
	/**
	 * This method uses the binary search method to find a person in the family by their name if they have already been added.
	 * @param name The name of the person to find
	 * @return Returns the Person if found and null if not found
	 */
	private Person getPerson(String name) { 
		
		int index = binarySearch(name);
		if (name.compareTo(family.get(index).getName()) == 0) {
			Person p = family.get(index);
			return p;
		}
		else {
			return null;
		}
	}
	
	/**
	 * This method searches the list for a Person with the specified name using binary search.
	 * @param name The name of the Person to search for
	 * @return Returns the index of the Person if found or the insertion point for the Person if not found
	 */
	private int binarySearch(String name) {
		int min = 0;
		int max = family.size() - 1;
		int mid = (min + max)/2;
		while (min <= max) {
			if (name.compareTo(family.get(mid).getName()) > 0) {
				min = mid + 1;
			}
			else if (name.compareTo(family.get(mid).getName()) < 0) {
				max = mid - 1;
			}
			else {
				return mid;
			}
			mid = (min + max)/2;
		}
		if (name.compareTo(family.get(mid).getName()) > 0) {
			return mid + 1;
		}
		return mid;
	}
}
