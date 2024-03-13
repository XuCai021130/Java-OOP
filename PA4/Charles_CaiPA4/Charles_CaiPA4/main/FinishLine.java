/**
* Xu (Charles) Cai
* xucai@brandeis.edu
* 3,29,2022
* PA4
* Explanation: This finishline car contains field and methods for the finishline
* and will be useful for other type of cars
*/
package main;

/**
 * FinishLine class
 * @author Lenovo
 *
 */
public class FinishLine {

	public Car cars[];
	
	/**
	 * constructor
	 */
	public FinishLine() {
		cars = new Car [10];
	}
	
	/**
	 * If the car enters finishline, it will be removed from the array
	 * @param car
	 */
	public void enterFinishLine(Car car) { 
		for (int i=0; i < cars.length; i++) {
			if (car == cars[i]) {
				cars[i] = null;
			}
		}
	}
	
	/**
	 * check if all cars finished the game
	 * @return
	 */
	public boolean finished() {
		for (int i=0; i < cars.length; i++) {
			if (cars[i] != null) {
				return false;
			}
		}
		return true;
	}
	public int howManyFinished() {
		int a = 0;
		for (int i = 0; i < cars.length; i++) {
			if (cars [i] == null) {
				a++;
			}
		}
		return a;
	}
}

