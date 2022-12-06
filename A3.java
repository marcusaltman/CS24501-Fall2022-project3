package Project3;

import java.util.Scanner;

/**
 * This is the required driver for the program. It contains the main method, which builds a family tree and prints it. The family
 * tree can be entered manually of the user can print the default Roosevelt family tree with Anna Eleanor Roosevelt as the root.
 * @author marcusaltman
 *
 */
public class A3 {
	
	private static Scanner input = new Scanner(System.in).useDelimiter("\n");
	private static String rootName; // The root of the family tree
	
	/**
	 * The main method builds a FamilyTree and prints the family tree starting with the root.
	 * @param args No arguments
	 */
	public static void main(String[] args) {
		FamilyTree tree = buildTree();
		tree.printTree(rootName);
		input.close();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static FamilyTree buildTree() {
		
		System.out.print("Would you like to build a family tree manually (Y/N)? ");
		String manualBuild = input.nextLine();
		if (manualBuild.equalsIgnoreCase("y")) {
			FamilyTree tree = new FamilyTree();
			Queue q = new Queue();
			Person root = getNewPerson("the root person", tree);
			rootName = root.getName();
			getSpouses(root, tree);
			getChildren(root, tree, q);
			return tree;
		}
		else {
			System.out.println("\nBuilding Roosevelt family tree...");
			FamilyTree tree = RooseveltTree.build();
			rootName = "Anna Eleanor Roosevelt";
			return tree;
		}
		
	}	
	
	/**
	 * This method creates a newPerson, and populates their biographical information, except for their children. It then returns 
	 * the newPerson to the calling method.
	 * @param relationship A combination of the person being referenced and their relationship to the newPerson, (e.g. Anna's spouse)
	 * @param tree The family tree that is being built
	 * @return Returns the new person that was just created
	 */
	private static Person getNewPerson(String relationship, FamilyTree tree) {
		Person newPerson;

		// Gets the name and birthYear of the newPerson
		System.out.print("Enter " + relationship + "'s name: ");
		String name = input.nextLine();
		System.out.print("Enter " + name + "'s year of birth: ");
		int birthYear = Integer.parseInt(input.nextLine());
		
		// Gets the deathYear if the Person is deceased
		System.out.print("Is " + name + " deceased (Y/N)? ");
		String deceased = input.nextLine();
		
		// Adds the newPerson to the FamilyTree
		if (deceased.equalsIgnoreCase("y")) {
			System.out.print("Enter " + name + "'s year of death: ");
			int deathYear = Integer.parseInt(input.nextLine());
			newPerson = tree.addPerson(name, birthYear, deathYear);
		}
		else {
			newPerson = tree.addPerson(name, birthYear);
		}
		
		// Gets any significant events in the Person's life
		getEvents(newPerson);
		
		
		return newPerson;
	}
	
	/**
	 * This method collects the startYear and description of an Event and adds the event to the Person p's record. It loops until
	 * the user says there are no more events to add.
	 * @param p The person the events belong to
	 */
	private static void getEvents (Person p) {
		
		System.out.print("Does " + p.getName() + " have any significant life events to add (Y/N)? ");
		String events = input.nextLine();
		while (!events.equalsIgnoreCase("n")) {
			System.out.print("Enter the start year of the event: ");
			int startYear = Integer.parseInt(input.nextLine());
			System.out.print("Enter a description of the event: ");
			String description = input.nextLine();
			p.addEvent(startYear, description);
			System.out.print("Enter another event (Y/N)? ");
			events = input.nextLine();
		}
	}
	
	/**
	 * This method gather p's spouses. It calls getSpouse() to create the spouse and addSpouse() to add them to p's spouse list.
	 * @param p The person whose spouses need to be collected
	 * @param tree The family tree that is being built
	 */
	private static void getSpouses (Person p, FamilyTree tree) {
		System.out.print("Was " + p.getName() + " ever married (Y/N)? ");
		String married = input.nextLine();
		if (married.equalsIgnoreCase("y")) {
			System.out.print("How many times? ");
			int numMarriages = Integer.parseInt(input.nextLine());
			for (int i = 0; i < numMarriages; i++) {
				Person spouse = getSpouse(p, tree);
				p.addSpouse(spouse);
			}
		}
	}
	
	/**
	 * This method creates the spouse and returns them to be added to p's list of spouses. 
	 * @param p The person whose spouse is being created
	 * @param tree The family tree that is being built
	 * @return Returns the spouse that was just created
	 */
	private static Person getSpouse (Person p, FamilyTree tree) {
		return getNewPerson(p.getName() + "'s spouse", tree);
	}
	
	/**
	 * This method recursively gathers the children of each Person, starting with the root. It performs a breadth-first search,
	 * meaning that it collects all of the children of the root before it collects the grandchildren of the root.
	 * @param parent The parent of the children being gathered in this call to getChildren
	 * @param tree The family tree that is being built
	 * @param q The Queue of people who still need to have their children entered
	 */
	private static void getChildren (Person parent, FamilyTree tree, Queue q) {
		Person child;

		System.out.print("Does " + parent.getName() + " have any children (Y/N)? ");
		String children = input.nextLine();
		if (children.equalsIgnoreCase("y")) {
			System.out.print("How many children? ");
			int numChildren = Integer.parseInt(input.nextLine());
			
			try { // Handles exceptions in both for loops
				
				// For each child, create the child and add them to their parent's child list
				for (int i = 0; i < numChildren; i++) {
					child = getChild(parent, tree);
					parent.addChild(child);
					
					// If the parent was only married once, the program assumes the spouse is also the parent of the child
					if (parent.getNumMarriages() == 1) {
						parent.getSpouse(0).addChild(child);
					}
					
					// Enqueues the child so that their children can be collected in order
					q.enqueue(child);
				}
				for (int i = 0; i < numChildren; i++) {
					getChildren(q.dequeue(), tree, q); // Throws an exception if the queue is empty
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method creates a child and returns them to be added to their parent's child list.
	 * @param parent The parent of the child being created
	 * @param tree The family tree that is being built
	 * @return Returns the child that was just created
	 */
	private static Person getChild(Person parent, FamilyTree tree) {
		return getNewPerson(parent.getName() + "'s child", tree);
	}
}

