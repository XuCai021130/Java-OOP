/**
* Xu (Charles) Cai
* xucai@brandeis.edu
* 3,3,2022
* PA3
* Explanation: This program is a simple card game. User can guess whether the card user drew larger than computer's
* and get bet. 
*/
package main;

import java.util.*;

/**
 *This program is a simple card game. User can guess whether the card user drew larger than computer's
* and get bet
 * @author Lenovo
 */
public class Casino {

/**
 * The main method is the program for playing card game. 
 * @param args
 */
	public static void main(String[] args) {
		Deck deckOfCard = new Deck();  // create an object of deck
		boolean playGame = true;
		deckOfCard.shuffle();   // shuffle the deck
		Scanner initialDeposit = new Scanner (System.in);
		gameInstruction();
		double initialAmount = initialDeposit.nextInt();
		
		while (initialAmount > 0 && playGame){ // This game will not stop until either user don't have enough money or user chooses to quit
			Scanner betAmount = new Scanner (System.in);
			Scanner decision = new Scanner (System.in);
			System.out.println("How much do you want to bet? (min: 300, max: 3000)");
			int bet = betAmount.nextInt();
			initialAmount += playOneGame(deckOfCard, bet);  // Play the card game
			System.out.println("Your current have " + initialAmount);
			System.out.println("Whether to continue the game? (enter yes or no)");
			String whetherPlayNext = decision.next().toUpperCase();
			
			if (!whetherPlayNext.equals("YES")) { // test whether user want to play the game
				playGame = false;        // If user doesn't enter yes, the program will stop
			}
		}
	}

/**
 * This method will be used to compare the value of two cards and tell the user whether user win and user's card and computer's card
 * The bet is returned for main method to calculate the amount of money after each game
 * @param currentDeck
 * @param bet
 * @return
 */
	public static double playOneGame (Deck currentDeck, double bet) {
		Card userCard = currentDeck.drawNextCard();
		currentDeck.discard (userCard);
		Card computerCard = currentDeck.drawNextCard();
		System.out.println("Your draw " + userCard + " and Computer draws " + computerCard);
		currentDeck.discard (computerCard);
		if (userCard.value > computerCard.value) {
			System.out.println("Your guess is correct!");
			return bet;
		}else if (userCard.value == computerCard.value){
			System.out.println("It's a tie. Let's do it again!");
			return 0;
		}else {
			System.out.println("Your guess is incorrect!");
			return -bet; // Return negative bet because, in the main method, the expression for amount of money
		}                // is money += bet
	}

/**
 * This method will print all instructions for user to understand the rule of the card game
 * This method is created to reduce the redundancy of main method
 */
	public static void gameInstruction () {
		System.out.println("Let's play a simple card game!");
		System.out.println("You and computer will each draw a card from deck.");
		System.out.println("Then you make a bet.");
		System.out.println("If the user¡¯s card has a larger value than the computer¡¯s card");
		System.out.println("you win and you will get the value of the bet");
		System.out.println("Otherwise, you lose the  bet.");
		System.out.println();
		System.out.print("Please enter the money you bring (range from 1000 - 5000): ");
		System.out.println();
	}
}