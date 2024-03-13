/**
* Xu (Charles) Cai
* xucai@brandeis.edu
* 3,29,2022
* PA4
* Explanation: This race Car class contain fields, constructor, behaviors necessary for the class 
* and it overrides some methods in car class
*/
package main;

/**
 * race car class
 * @author Lenovo
 *
 */
public class RaceCar extends Car {

	/**
	 * constructor
	 * @param speed
	 * @param strength
	 */
	public RaceCar(int speed, int strength) {
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
	}
	
	/**
	 *default constructor
	 */
	public RaceCar() {	
		this.speed = 40;
		this.strength = 3;
	}
	
	/**
	 * return location
	 */
	public double getLocation() {
		return locator;
		
	}
	
	/**
	 * return information of car
	 */
	public String toString() {
		return "RaceCar" + speed + "/" + strength;
	}
	
	/**
	 * return type of car
	 */
	public String getType() {
		return "RaceCar";
	}
}
