package main;

import java.util.*;

/**
 * this is the SortedIntList class that inherit attributes and methods from the ArrayIntList class
 * and have some new methods and a new field
 * @author Lenovo
 *
 */
public class SortedIntList extends ArrayIntList {
	private boolean uniqueness;
	
	/**
	 * constructor that creates a sorted array list with capacity of 10 and allow duplicate
	 */
	public SortedIntList() {
		super();
		uniqueness = false;
	}
	
	/**
	 * constructor that creates a sorted array list with capacity of 10 and user will determine whether
	 * allow duplicate
	 */
	public SortedIntList(boolean unique) {
		super();
		uniqueness = unique;
	}
	
	/**
	 * constructor that creates a sorted array list with given capacity and allow duplicate
	 */
	public SortedIntList(int capacity) {
		super(capacity);
		uniqueness = false;
	}
	
	/**
	 * constructor that creates a sorted array list with given capacity and user will determine whether
	 * allow duplicate
	 */
	public SortedIntList(boolean unique, int capacity) {
		super(capacity);
		uniqueness = unique;
	}
	
	/**
	 * add elements in the list and ensure the array list is still sorted
	 * this method will not add elements if the array list doesn't allow duplicate and the value is already
	 * exist
	 */
	public void add(int value) {
		if (indexOf(value) >= 0 && uniqueness) { // do nothing when the element already exists and 
		}                                        // doesn't allow duplicate
		else {
			if (indexOf(value) < 0) {
				int newIndex = - (indexOf(value) + 1); // add element to array list since the value is not 
				super.add(newIndex,value);             // in the array list, but need to change the negative
			}                                          // index to positive
			else {
				super.add(indexOf(value), value); // add element to the array list 
			}                                     // when the value exists but allow duplicate 
		}
	}
	
	/**
	 * this method should not be used because sorted array list must add elements in sorted order
	 * cannot add element in any given index
	 */
	public void add(int index, int value) {
		 throw new UnsupportedOperationException();
	}
	
	/**
	 * return whether duplicate is allowed, false for allow duplicate and true for not allow duplicate
	 * @return
	 */
	public boolean getUnique() {
		return uniqueness;
	}
	
	/**
	 * return the index of the given value by using binary search.
	 * returns a negative number one less than the index at which the 
	 * value would have been found if it had been in the array
	 * @return
	 */
	public int indexOf(int value) {
		return Arrays.binarySearch(getIntegers(), 0, getItsLength(), value);
	}
	
	/**
	 * return the largest value in the array list
	 * @return
	 */
	public int max() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			return getIntegers()[getItsLength() - 1];
		}
	}
	
	/**
	 * return the smallest value in the array list
	 * @return
	 */
	public int min() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			return getIntegers()[0];
		}
	}
	
	/**
	 * allow user to change the uniqueness of the array list
	 * delete all duplicates if the array list change from allow duplicate to not allow doplicate
	 * and there are duplicates
	 * @param unique
	 */
	public void setUnique(boolean unique) {
		uniqueness = unique;
		if (uniqueness) {
			int next = getIntegers()[0];
			for (int i = 1; i< getItsLength(); i++) {
				if (getIntegers()[i] == next) { // if previous element equals to next one, delete it
					super.remove(i);
					i--;
				}
				next = getIntegers()[i]; // test whether the previous element equals to next one
			}
		}
	}
	
	/**
	 * return a string version of array list
	 */
	public String toString() {
		String list = "S:" + super.toString();
		if (uniqueness) {
			list += "U"; // if the array list does not allow duplicate, use "U" to 
		}                //  represent it is unique
		return list;
	}
}
