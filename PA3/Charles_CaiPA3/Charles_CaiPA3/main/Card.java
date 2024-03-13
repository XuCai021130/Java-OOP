/**
* Xu (Charles) Cai
* xucai@brandeis.edu
* 3,3,2022
* PA3
* Explanation: This card class contain fields, constructor, behaviors necessary for class
*/
package main;

/**
 * Card contains three fields: value, suit, color
 * @author Lenovo
 */
public class Card {
	int value;
	String suit;
	String color;

/**
 * This constructor will store fields value, suit and color to the object. 
 * @param value
 * @param suit
 */
	public Card(int value, String suit)  {
		this.value = value;
		this.suit = suit;
		if (this.suit.equals("Hearts") || this.suit.equals("Diamonds")) { // Use if statement to judge whether assign red or black to a card
			this.color = "red";
		}else {
			this.color = "black";
		}
	}
/**
 * return the value of the card
 * @return
 */
	public int getValue() {
		return value;
		
	}
	
/**
 * 	return the color of the card
 * @return
 */
	public String getColor() {
		return color;
	}

/**
 * return the suit of the card
 * @return
 */
	public String getSuit() {
		return suit;
	}
	
	@Override
/**
 * This method can be used when printing a object card
 */
	public String toString() {
		if (this.value == 1) {       // Use if statement to give special name if the value is 1, 11, 12 or 13 
			return "Ace of " + this.suit;
		}else if (this.value == 11) {
			return "Jack of " + this.suit;
		}else if (this.value == 12) {
			return "Queen of " + this.suit;
		}else if (this.value == 13) {
			return "King of " + this.suit;
		}else {
			return this.value + " of " + this.suit;
	}
}
}