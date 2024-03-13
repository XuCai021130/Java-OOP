/**
* Xu (Charles) Cai
* xucai@brandeis.edu
* 3,29,2022
* PA4
* Explanation: This FormulaOne class contain fields, constructor, behaviors necessary for the class 
* and it overrides some methods in car class
*/
package main;

/**
 * FormulaOne class
 * @author Lenovo
 *
 */
public class FormulaOne extends Car {

	/**
	 * constructor
	 * @param speed
	 * @param strength
	 */
	public FormulaOne(int speed, int strength) {
		if (speed < 30 || speed > 70 || strength > 5 ||strength < 3) {
			throw new IllegalArgumentException();
		}
		if (speed > 70) {
			this.speed = 70;
		}
		
		if (strength > 5) {
			this.strength = 5;
		}
		
		if (speed < 30) {
			this.speed = 30;
		}
		
		if (strength < 3) {
			this.strength = 3;
		}
		this.speed = speed;
		this.strength = strength;
	}
	
	/**
	 * default constructor 
	 */
	public FormulaOne() {
		this.speed = 50;
		this.strength = 4;
	}
	
	/**
	 * toString method
	 */
	public String toString() {
		return "FormulaOne" + speed + "/" + strength;
	}
	
	/**
	 * return type of car
	 */
	public String getType() {
		return "FormulaOne";
	}
}
