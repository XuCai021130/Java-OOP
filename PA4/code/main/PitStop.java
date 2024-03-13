/**
* Xu (Charles) Cai
* xucai@brandeis.edu
* 3,29,2022
* PA4
* Explanation: This pitstop class contain fields, constructor, behaviors necessary for the class 
*/
package main;

/**
 * pitstop class
 * @author Lenovo
 *
 */
public class PitStop {
		
	/**
	 * constructor
	 * @param car
	 */
	public void enterPitStop(Car car) { 
		car.speed = 0;
		car.damageStatus = true;
		car.damagedBefore = true;
		car.locator = (car.locator / 100) * 100 +75;
		car.enterPitStopTime = 0;
	}
	
	/**
	 * when leaving pitstop, reset speed of car to original value
	 * @param car
	 */
	public void leavePitstop(Car car) {
		car.recoverSpeed();
	}
	
	/**
	 * return if car is in pitstop
	 * @param car
	 * @return
	 */
	public boolean isInPitStop(Car car) {
		return car.speed == 0;
	}
}
