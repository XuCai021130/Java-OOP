package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.ArrayIntList;

class ArrayIntListTest {
	
	ArrayIntList list1 = new ArrayIntList ();
	ArrayIntList list2 = new ArrayIntList (5);
	
	/**
	 * test many methods such as two add methods, get, contains, isEmpty,
	 * ensureCapacity, indexOf
	 */
	@Test
	void test1() {
		
		for (int i = 0; i < 10; i++) {
			list1.add(i);         // test add(int value) method
		}
		
		assertEquals(list1.get(0), 0);
		assertEquals(list1.get(3), 3);
		assertEquals(list1.size(), 10);
		assertEquals(list1.get(2), 2);
		assertTrue(list1.indexOf(9) == 9); // test indexOf, size and get methods
		
		list1.add(9,9); //test add(int index, int value) method
		assertEquals(list1.get(9),9);
		assertEquals(list1.contains(12), false); // test contains and isEmpty methods
		assertEquals(list1.isEmpty(), false);
		
	}

	/**
	 * test remove() method
	 */
	@Test
	void test2() {
		for (int i = 0; i < 10; i++) {
			list1.add(i);
		}
		
		list1.remove(5);
		for (int i = 0; i < 4; i++) {
			assertEquals(list1.get(i), i);
		}
		
		for (int i = 5; i < 9; i++) {
			assertEquals(list1.get(i), i + 1);
		}
		
		assertEquals("[0, 1, 2, 3, 4, 6, 7, 8, 9]", list1.toString());
		assertEquals(list1.size(), 9);
		assertEquals(list1.indexOf(5), -1);
	}
	
	/**
	 * test ensureCapacity method
	 */
	@Test
	void test3() {
		for (int i = 0; i < 3; i++) {
			list2.add(i);
		}
		
		list2.ensureCapacity(12);
		assertEquals(list2.getIntegers().length, 12);
		
		for (int i = 0; i < 3; i++) {
			assertEquals(list2.get(i), i);
		}
		
		assertEquals(list2.toString(), "[0, 1, 2]");
		
		for (int i = 3; i < 12; i++) {
			list2.add(i);
		}
		
		assertEquals(list2.toString(), "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]");
		assertEquals(list2.size(),12);
	}
	
	/**
	 * test getter and setter methods
	 */
	@Test
	void test4() {
		for (int i = 0; i < 10; i++) {
			list1.add(i);
		}
		
		for (int i = 0; i < 5; i++) {
			list2.add(i + 1);
		}
		
		assertEquals(list1.getItsLength(), 10);
		assertEquals(list2.getItsLength(), 5);
		
		list1.setItsLength(8);
		list2.setItsLength(6);
		
		assertEquals(list1.getItsLength(), 8);
		assertEquals(list2.getItsLength(), 6);
		
		
	}
	

}

