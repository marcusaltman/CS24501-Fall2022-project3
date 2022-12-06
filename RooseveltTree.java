package Project3;

/**
 * This class is a test case that builds the Roosevelt family tree provided in the Project 4 instructions.
 * @author marcusaltman
 *
 */
public class RooseveltTree {
	
	/**
	 * This method builds the Roosevelt family tree
	 * @return Returns the Roosevelt family tree
	 */
	public static FamilyTree build() {

		FamilyTree tree = new FamilyTree();
			
		// Details for Eleanor Roosevelt
		Person p1 = tree.addPerson("Anna Eleanor Roosevelt", 1884, 1962); // DOB, death
		p1.addEvent(1961,"1st Chair of the Presidential Commission on the Status of Women");
		p1.addEvent(1947,"1st United States Representative to the United Nations Commission on Human Rights");
		p1.addEvent(1946,"1st Chair of the United Nations Commission on Human Rights");
			
		// Details for Franklin Delano Roosevelt
		Person p2 = tree.addPerson("Franklin Delano Roosevelt", 1882, 1945); // DOB, death
		p1.addSpouse(p2);
		p2.addSpouse(p1);
			
		// Details for Franklin Delano Roosevelt Jr.
		Person p3 = tree.addPerson("Franklin Delano Roosevelt Jr.", 1914);
		p3.addParents(p1, p2);
		p3.addChild(tree.addPerson("Franklin Delano Roosevelt III", 1938));
		p3.addEvent(1949, "Member of the U.S. House of Representatives from New York's 20th district");
		p3.addEvent(1963, "United States Under Secretary of Commerce");
		
		// Details for Anna Eleanor Roosevelt Halsted
		Person p4 = tree.addPerson("Anna Eleanor Roosevelt Halsted", 1906);
		p4.addParents(p1, p2);
		p4.addChild(tree.addPerson("Anna Eleanor Roosevelt Dall", 1927));
		p4.addChild(tree.addPerson("Curtis Roosevelt Dall", 1930));
		p4.addChild(tree.addPerson("John Roosevelt Boettiger", 1939));
		
		// Details for Elliott Roosevelt
		Person p5 = tree.addPerson("Elliott Roosevelt", 1910);
		p5.addParents(p1, p2);
		
		// Details for James Roosevelt II
		Person p6 = tree.addPerson("James Roosevelt II", 1907);
		p6.addParents(p1, p2);
		p6.addChild(tree.addPerson("Sara Delano Roosevelt", 1932));
		p6.addChild(tree.addPerson("James Roosevelt III", 1945));
		
		// Details for John Aspinwall Roosevelt II
		Person p7 = tree.addPerson("John Aspinwall Roosevelt II", 1916);
		p7.addParents(p1, p2);
		
		
		
		return tree;
		
	}
}
