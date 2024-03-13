/**
* Xu (Charles) Cai
* xucai@brandeis.edu
* 3,3,2022
* PA3
* Explanation: This card class contain fields, constructor, behaviors necessary for class
*/
package main;

import java.util.Random;

/**
 * This class will initalize all fields of deck object 
 * @author Lenovo
 */
public class Deck {
	public static final int NUMOFCARD = 52; // create a class constant because 52 is frequently used and also it represents number of cards
	Card deck [];
	Card discardDeck [];
	int cardIndex;      // The field cardIndex represents how many cards in the deck is already been draw

/**
 * This deck constructor will create a full deck of 52 different cards and also a empty discardDeck
 */
	public Deck() {
		String [] suit = {"Hearts", "Diamonds", "Spades", "Clubs"};
		int [] value = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		int cardIndex = 0;
		deck = new Card [NUMOFCARD];
		discardDeck = new Card [NUMOFCARD];
		for (int i = 0; i < suit.length; i++) {
			for (int j = 0; j < value.length; j++) {
				deck [i * 13 + j] = new Card(value [j], suit [i]);
			}
		}
	}

/**
 * This program will shuffle the object deck by using algorithm
 */
	public void shuffle() {
		Random rand = new Random();
		for (int i = 0; i < deck.length - 1; i++ ) {
			int j = rand.nextInt(deck.length - i) + i;
			Card temp = deck [i];
			deck [i] = deck [j];
			deck [j] = temp;
		}
	}

/**
 * This method will draw next card of the object deck and make the current deck in the array to be null
 * Also, it will transfer the card object to the discardDeck array with the same index.
 * @return
 */
	public Card drawNextCard() {
		if (this.cardIndex < NUMOFCARD) {
			Card currentCard = deck [cardIndex];
			deck [cardIndex] = null;
			this.cardIndex++; // cardIndex will increase because the current index is already passed to the discard deck
			return currentCard;
		}else {                         // If all cards have been drew, it will move the discard deck to deck and shuffle them again 
			deck = discardDeck.clone(); // and do the same thing again
			discardDeck = new Card[52];
			this.cardIndex = 0;
			shuffle();
			Card currentCard = deck [cardIndex];
			deck [cardIndex] = null;
			this.cardIndex++;
			return currentCard;
		}
	}

/**
 * This method will move the card object c to discard deck
 * @param c
 */
	public void discard(Card c) {
				discardDeck [this.cardIndex - 1] = c; // Minus one is required because at this time, the cardIndex is already increase by one
	}                                                 // In order to make sure the position of chosen card matches the position in the discard deck
	
}