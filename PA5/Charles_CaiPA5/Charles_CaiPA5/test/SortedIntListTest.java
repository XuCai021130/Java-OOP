package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.SortedIntList;

class SortedIntListTest {
	
	SortedIntList list1 = new SortedIntList ();
	SortedIntList list2 = new SortedIntList (true);
	SortedIntList list3 = new SortedIntList (9);
	SortedIntList list4 = new SortedIntList (true, 8);
	
	@Test
	/**
	 * test four constructors and also getUnique method
	 */
	void test1() {
		assertEquals(list1.getUnique(), false);
		assertEquals(list1.getIntegers().length, 10);
		assertEquals(list2.getUnique(), true);
		assertEquals(list2.getIntegers().length, 10);
		assertEquals(list3.getUnique(), false);
		assertEquals(list3.getIntegers().length, 9);
		assertEquals(list4.getUnique(), true);
		assertEquals(list4.getIntegers().length, 8);
	}

	/**
	 * test add, indexOf, max, min and toString method
	 */
	@Test
	void test2() {
			list3.add(0);
			list3.add(3);
			list3.add(5);
			list3.add(1);
			list3.add(9);
			list3.add(15);
			list3.add(15);
			list3.add(12);
			
			list2.add(-1);
			list2.add(3);
			list2.add(5);
			list2.add(1);
			list2.add(18);
			list2.add(9);
			list2.add(15);
			
			assertEquals(list2.toString(), "S:[-1, 1, 3, 5, 9, 15, 18]U");
			assertEquals(list3.toString(), "S:[0, 1, 3, 5, 9, 12, 15, 15]");
			
			assertEquals(list3.indexOf(13), -7);
			assertEquals(list3.indexOf(2), -3);
			
			assertEquals(list3.max(), 15);
			assertEquals(list3.min(), 0);
			
			assertEquals(list2.max(), 18);
			assertEquals(list2.min(), -1);
			
	}
	
	/**
	 * test setUnique method
	 */
	@Test
	void test3() {
		list3.add(0);
		list3.add(0);
		list3.add(0);
		list3.add(2);
		list3.add(5);
		list3.add(5);
		list3.add(9);
		list3.add(9);
		
		assertEquals(list3.toString(), "S:[0, 0, 0, 2, 5, 5, 9, 9]");
		
		list3.setUnique(true);
		
		assertEquals(list3.toString(), "S:[0, 2, 5, 9]U");
	}	
}
