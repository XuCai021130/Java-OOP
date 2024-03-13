package main;

/**
 * This is ArrayIntList class that contains many
 * array list methods 
 * @author Lenovo
 *
 */
public class ArrayIntList {
	private int [] integers;
	private int itsLength;
	
	/**
	 * constructor without parameter to create a list of default capacity 10
	 */
	public ArrayIntList () {
		this(10);
	}
	
	/**
	 * constructor to create a list of given capacity
	 * @param capacity
	 */
	public ArrayIntList (int capacity) {
		if (capacity < 0) { // ensure capacity larger than zero
			throw new IllegalArgumentException();
		}
		else {
			integers = new int [capacity];
			itsLength= 0;
		}
	}
	
	/**
	 * this add method add a integer to the end of the list
	 * @param value
	 */
	public void add(int value) {
		add(itsLength, value);
	}
	
	/**
	 * this add method add a integer in the given index location
	 * @param index
	 * @param value
	 */
	public void add(int index,int value) {
		checkIndex(index, 0, itsLength);
		ensureCapacity(itsLength + 1);
		
		for (int i = itsLength; i > index; i-- ) {
			integers[i] = integers[i - 1];  // move elements after the given index one unit back
		}
		
		integers[index] = value;
		itsLength++;
	}
	
	/**
	 * return value at given index 
	 * @param index
	 * @return
	 */
	public int get(int index) {
		checkIndex (index, 0, itsLength);
		return integers[index];
	}
	
	/**
	 * return the index of a given value 
	 * return -1 if the integer is not exist
	 * @param value
	 * @return
	 */
	public int indexOf(int value) {
		for (int i = 0; i < itsLength; i++) {
			if (value == integers[i]) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * remove the element at given index
	 * @param index
	 */
	public void remove(int index) {
		checkIndex (index, 0, itsLength);
		for (int i = index; i < itsLength - 1; i++) {
			integers[i] = integers[i+1];  // move elements with index larger than given index one unit forward
		}
		itsLength--;
	}
	
	/**
	 * return the length of the array list
	 * @return
	 */
	public int size() {
		return itsLength;
	}
	
	/**
	 * return the array list in a string version
	 */
	public String toString() {
		String list = "[";
		for (int i = 0; i < itsLength; i++) {
			if (i != itsLength -1) {
				list += integers[i] + ", ";
			}
			else {
				list += integers[i];
			}
		}
		list += "]";
		return list;	
	}
	
	/**
	 * clear the whole array list
	 */
	public void clear() {
		for (int i = 0; i < itsLength; i++) {
			integers[i] = 0;
		}
		itsLength = 0;
	}
	
	/**
	 * detect whether the array list contains given integer and return 
	 * true if yes and false if not
	 * @param value
	 * @return
	 */
	public boolean contains(int value) {
		for (int i = 0; i < itsLength; i++) {
			return integers[i] == value;
		}
		return false;
	}
	
	/**
	 * make sure the array list has enough space to store given capacity number of elements
	 * @param capacity
	 */
	public void ensureCapacity(int capacity) {
		if (capacity > integers.length) {
			int [] newArray = new int [capacity];
			for (int i = 0;i < itsLength; i++) {
				newArray[i] = integers[i]; // use a new array with larger space to replace the old array
			}
			integers = newArray;
		}
	}
	
	/**
	 * return whether the array list is empty
	 * @return
	 */
	public boolean isEmpty() {
		return itsLength == 0;
	}
	
	/**
	 * check whether the given index is out of bounds
	 * @param index
	 * @param min
	 * @param max
	 */
	private void checkIndex(int index, int min, int max) {
		if (index < min || index > max) {
            throw new IndexOutOfBoundsException();
        }
	}
	
	/**
	 * return the field integers [] array
	 * @return
	 */
	public int [] getIntegers() {
		return integers;
	}
	
	/**
	 * set a new array to replace the old array field
	 * @param integers
	 */
	public void setIntegers(int [] integers) {
		this.integers = integers;
	}
	
	/**
	 * return the field itsLength, which is the length of the array list
	 * @return
	 */
	public int getItsLength() {
		return itsLength;
	}

	/**
	 * set a new length for the array list
	 * @param itsLength
	 */
	public void setItsLength(int itsLength) {
		this.itsLength = itsLength;
	}
}
