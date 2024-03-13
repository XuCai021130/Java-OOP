/**
* Xu (Charles) Cai
* xucai@brandeis.edu
* 3,29,2022
* PA4
* Explanation: This Sports car class contain fields, constructor, behaviors necessary for the class 
* and it overrides some methods in car class
*/
package main;

public class SportsCar extends Car{
	
	public SportsCar(int speed, int strength) {
		if (speed > 45) {
			this.speed = 45;
		}
		
		if (strength > 3) {
			this.strength = 3;
		}
		
		if (speed < 20) {
			this.speed = 20;
		}
		
		if (strength < 1) {
			this.strength = 1;
		}
		this.speed = speed;
		this.strength = strength;
	}

	public SportsCar() {
		this.speed = 30;
		this.strength = 2;
	}
	
	public String toString() {
		return "SportsCar" + speed + "/" + strength;
	}
	
	public String getType() {
		return "SportsCar";
	}
}
