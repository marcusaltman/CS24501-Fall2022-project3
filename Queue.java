package Project3;

/**
 * This class is a basic array-based implementation of a queue. The only difference is that this implementation stores the
 * number of elements in the queue as an instance variable called size to make it easier to see if the queue is empty or 
 * full. The size variable is also used when growing the queue. The class uses a default constructor.
 * @author marcusaltman
 *
 */
public class Queue {
	private Person[] queue = new Person[20];
	private int size = 0;
	private int front = 0;
	private int back = 0;
	
	
	/**
	 * This method adds a Person to the queue 
	 * @param person The Person to add to the queue
	 * @return Returns true once the Person is added to the Queue
	 */
	public boolean enqueue(Person person) {
		if (size == queue.length) {
			queue = growQueue();
		}
		queue[back++ % queue.length] = person;
		size++;
		return true;
	}
	
	/**
	 * This method returns the Person at the front of the Queue and advances the front to the next Person
	 * @return Returns the Person at the front of the Queue
	 * @throws Exception Throws an exception if the Queue is empty
	 */
	public Person dequeue() throws Exception {
		if (empty()) {
			throw new Exception ("Queue is Empty");
		}
		size--;
		return queue[front++ % queue.length];
	}
	
	/**
	 * This method checks to see if the Queue is empty using the size variable
	 * @return Returns true if the Queue is empty and false if it is not empty
	 */
	public boolean empty() {
		return (size == 0);
	}
	
	/**
	 * This method doubles the size of the queue array. It makes a newQueue of size * 2 and then populates the array with 
	 * the people in the original queue array, taking wrap-around into consideration. It updates the front and the back
	 * indices and returns the newQueue.
	 * @return Returns a newQueue that is twice the size
	 */
	private Person[] growQueue() {
		// Make a new queue that is double the size
		Person[] newQueue = new Person[size * 2];
		
		//Populate the new queue starting at the front of the queue and wrapping around if needed
		for (int i = 0; i < size; i++) {
			newQueue[i] = queue[front++ % size];
		}
		front = 0;
		back = size;
		
		return newQueue;
	}
	
}


