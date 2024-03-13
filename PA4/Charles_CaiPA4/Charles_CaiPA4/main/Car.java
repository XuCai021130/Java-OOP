/**
* Xu (Charles) Cai
* xucai@brandeis.edu
* 3,29,2022
* PA4
* Explanation: This abstract car class contain fields, constructor, behaviors necessary for the class 
* and will be useful for other type of cars
*/
package main;
/**
 * 
 * @author Lenovo
 *
 */
public abstract class Car {
	
	int speed;  // all fields
	int strength;
	boolean damageStatus;
	double locator;
	int tick;
	boolean damagedBefore;
	int enterPitStopTime;
	
	/**
	 * constructor
	 * @param speed
	 * @param strength
	 */
	public Car(int speed, int strength) { // constructor
		if (speed > 55) {
			this.speed = 55;
		}
		
		if (strength > 4) {
			this.strength = 4;
		}
		
		if (speed < 30) {
			this.speed = 30;
		}
		
		if (strength < 2) {
			this.strength = 2;
		}
		this.speed = speed;
		this.strength = strength;
		this.enterPitStopTime = -1;
		this.damageStatus = false;
		this.damagedBefore = false;
		double locator = 0;
		int tick = 0;
	}
	
	/**
	 * default constructor
	 */
	public Car() { // default constructor 
		this.speed = 40;
		this.strength = 3;
	}

	/**
	 * return the current location of car
	 * @return
	 */
	public double getLocation() {
		return locator;
	}
	
	/**
	 * toString method
	 */
	public abstract String toString();
	
	/**
	 * reset the speed of car
	 */
	public void recoverSpeed() {  // this method will reset the speed of the car after repair
		this.speed += this.strength * 5;
	}
	
	/**
	 * return the type of car
	 * @return
	 */
	public abstract String getType();
	
}
