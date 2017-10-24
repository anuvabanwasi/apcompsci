/* 
 * Example code for Think Java (http://thinkapjava.com)
 *
 * Copyright(c) 2011 Allen B. Downey
 * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
 *
 * @author Allen B. Downey
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the solution to exercise 15 question 1-5 in Think Java
 * http://greenteapress.com/thinkjava5/html/thinkjava017.html#toc153
 * Each sub question is specified in the comments as 2, 3, 4, 5, 6, 7, 8, 9
 */
public class CardSoln3_AB {
	
	public static void main(String[] args) {
			
		Deck deck = new Deck();
		deck.shuffle();

		generatePokerHands(deck);
		
		estimateProbabilities(5000);
		//estimateProbabilities(2598960);
		System.out.print("\n");
		estimateProbabilitiesSeven(1000);
		
		/*
		Card card = new Card(1, 1);
		card.print();
		String s = card.toString();
		System.out.println(s);
		System.out.println(card);

		Card card2 = new Card(1, 1);
		System.out.println(card.equals(card2));

		Deck deck = new Deck();

		check sortDeck
		deck.shuffle();
		deck.sortByRank();

		deck.print();
		System.out.println();
		checkSorted(deck);

		//check that findBisect finds each card
		int index;
		
		for (int i=0; i<52; i++) { 
		  index = deck.findBisect(deck.cards[i], 0, deck.cards.length-1); 
		  if (index != i) { System.out.println("Not found!"); 
		  } 
		}
		
		//make a subdeck
		Deck hand = deck.subdeck(8, 12);
		hand.print();

		// check that findBisect doesn't find a card that's not there
		Card badCard = new Card(1, 1);
		index = hand.findBisect(badCard, 0, hand.cards.length - 1);
		if (index != -1) {
			System.out.println("Found?");
		}

		// check mergeSort
		deck.shuffle();
		deck = deck.mergeSort();
		checkSorted(deck);
		*/
	}
	
//4.In main use shuffle and deal to generate and print four PokerHands.
	
	/**
	 * Generate Poker Hands from a Deck of Cards. Each Poker Hand has 5 cards.
	 * @param deck Deck of Cards
	 * @return void
	 */
	
	private static void generatePokerHands(Deck deck) {
		int j = 0;
		for (int i = 0; i < 4; i++) {
			
			PokerHand hand = deck.deal(j, j + 4);
			hand.print();
			j = j + 5;
		}
	}
	/**
	 * Compute Probabilities of Different Types of Poker Hands
	 * https://en.wikipedia.org/wiki/List_of_poker_hands
	 * https://en.wikipedia.org/wiki/Poker_probability
	 * @param trials number of times deck is shuffled and poker hands are generated
	 * @return void
	 */
	private static void estimateProbabilities(int trials) {
		Deck deck = new Deck();
		int straightFlush = 0;
		int fourKind = 0;
		int fullHouse = 0;
		int straight = 0;
		int flush = 0;
		int threeKind = 0;
		int twoPair = 0;
		int onePair = 0;
		int highCard = 0;
		
		int j = 0;
		deck.shuffle();
		
// 7. Write a loop that generates a few thousand hands and checks whether they contain:
//    straight flush, four of a kind, full house, flush, straight, three of a kind, two pair, one pair, high card
		
		for(int i = 0; i < trials; i++) {
			
			if(j > 47){
				deck.shuffle();
				j = 0;
			}
			
			PokerHand hand = deck.deal(j, j + 4);
			
			if(hand.hasStraightFlush())
				straightFlush++;
			
			else if(hand.hasFourKind())
				fourKind++;
			
			else if(hand.hasFullHouse())
				fullHouse++;
			
			else if(hand.hasFlush())
				flush++;
			
			else if(hand.hasStraight())
				straight++;
			
			else if(hand.hasThreeKind())
				threeKind++;
			
			else if(hand.hasTwoPair())
				twoPair++;
			
			else if(hand.hasOnePair())
				onePair++;
			
			else if(hand.hasHighCard())
				highCard++;
			
			j = j + 5;
		}

// 7. Estimate the probability of getting one of those hands. 
// Compare your results to the probabilities at http://en.wikipedia.org/wiki/List_of_poker_hands.
		
		//Round probability percentage to 5 decimals
		//Reference: https://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java

		System.out.println("\n% age probablilities for 5 card poker hand\n");
		System.out.println("%age probability of straight flush " + (double) Math.round((straightFlush / (double)trials * 100)* 100000d) / 100000d);
		System.out.println("%age probability of four kind      " + (double) Math.round((fourKind / (double)trials * 100)* 100000d) / 100000d);
		System.out.println("%age probability of full house     " + (double) Math.round((fullHouse / (double)trials * 100)* 100000d) / 100000d);
		System.out.println("%age probability of flush          " + (double) Math.round((flush / (double)trials) * 100* 100000d) / 100000d);
		System.out.println("%age probability of straight       " + (double) Math.round((straight / (double)trials) * 100* 100000d) / 100000d);
		System.out.println("%age probability of three kind     " + (double) Math.round((threeKind / (double)trials * 100)* 100000d) / 100000d);
		System.out.println("%age probability of two pair       " + (double) Math.round((twoPair / (double)trials) * 100* 100000d) / 100000d);
		System.out.println("%age probability of one pair       " + (double) Math.round((onePair / (double)trials * 100)* 100000d) / 100000d);
		System.out.println("%age probability of high card      " + (double) Math.round((highCard / (double)trials * 100)* 100000d) / 100000d);
	    
		double total = straightFlush / (double)trials * 100+ fourKind / (double)trials * 100+ fullHouse / (double)trials * 100
	    	+ flush / (double)trials * 100 +straight / (double)trials * 100+threeKind / (double)trials * 100+twoPair / (double)trials * 100
	    	+ onePair / (double)trials * 100 +highCard / (double)trials * 100;
	    
	    System.out.println("Total %age probability             "+ total);
	
	}	

// 9. In some poker games, players get seven cards each, and they form a hand with the best five of the seven. 
//Modify your program to generate seven-card hands and recompute the probabilities.
	
	/**
	 * Compute Probabilities of Different Types of Poker Hands
	 * https://en.wikipedia.org/wiki/List_of_poker_hands
	 * https://en.wikipedia.org/wiki/Poker_probability
	 * 
	 * @param trials number of times deck is shuffled and poker hands are generated
	 * return void
	 */
	
	private static void estimateProbabilitiesSeven(int trials) {
		Deck deck = new Deck();
		int straightFlush = 0;
		int fourKind = 0;
		int fullHouse = 0;
		int straight = 0;
		int flush = 0;
		int threeKind = 0;
		int twoPair = 0;
		int onePair = 0;
		int highCard = 0;

		int j = 0;
		int k = 21;

		double denom = (double) (k * trials);
		
		deck.shuffle();

		for (int i = 0; i < trials; i++) {

			if (j > 45) {
				deck.shuffle();
				j = 0;
			}
			//Create a 7 card subdeck
			PokerHand subdeck = deck.deal(j, j + 6);
			//generate every 5 card combination from the 7 card poker hand
			Combinations c = new Combinations();
			List<int[]> result = c.combinations(subdeck.cards.length);

			for (int[] combination : result) {
				PokerHand hand = generateHand(subdeck, combination);

				if (hand.hasStraightFlush())
					straightFlush++;

				else if (hand.hasFourKind())
					fourKind++;

				else if (hand.hasFullHouse())
					fullHouse++;

				else if (hand.hasFlush())
					flush++;

				else if (hand.hasStraight())
					straight++;

				else if (hand.hasThreeKind())
					threeKind++;

				else if (hand.hasTwoPair())
					twoPair++;

				else if (hand.hasOnePair())
					onePair++;

				else if (hand.hasHighCard())
					highCard++;
			}

			j = j + 7;
		}

		System.out.println("% age probablilities for 7 card poker hand\n");
		System.out.println("%age probability of straight flush " + (double) Math.round((straightFlush / denom * 100)* 100000d) / 100000d);
		System.out.println("%age probability of four kind      " + (double) Math.round((fourKind / denom * 100)* 100000d) / 100000d);
		System.out.println("%age probability of full house     " + (double) Math.round((fullHouse / denom * 100)* 100000d) / 100000d);
		System.out.println("%age probability of flush          " + (double) Math.round((flush / denom * 100)* 100000d) / 100000d);
		System.out.println("%age probability of straight       " + (double) Math.round((straight / denom * 100)* 100000d) / 100000d);
		System.out.println("%age probability of three kind     " + (double) Math.round((threeKind / denom * 100)* 100000d) / 100000d);
		System.out.println("%age probability of two pair       " + (double) Math.round((twoPair / denom * 100)* 100000d) / 100000d);
		System.out.println("%age probability of one pair       " + (double) Math.round((onePair / denom * 100)* 100000d) / 100000d);
		System.out.println("%age probability of high card      " + (double) Math.round((highCard / denom * 100)* 100000d) / 100000d);
		
		double total = straightFlush / (double)denom * 100+ fourKind / (double)denom * 100+ fullHouse / (double)denom * 100
		    	+ flush / (double)denom * 100 +straight / (double)denom * 100+threeKind / (double)denom * 100+twoPair / (double)denom * 100
		    	+ onePair / (double)denom * 100 +highCard / (double)denom * 100;
		System.out.println("Total %age probability             "+ total);
	}
	
	/**
	 * Generate Poker Hand for the specified combination
	 * @param subdeck PokerHand
	 * @param combination specified combination
	 * @return PokerHand
	 */

	private static PokerHand generateHand(PokerHand subdeck, int[] combination) {
		PokerHand hand = new PokerHand();
		int numHand = 5;
		hand.cards = new Card[numHand];
		
		for (int m = 0; m < numHand; m++) {
			hand.cards[m] = subdeck.cards[combination[m]];
		}
		return hand;
	}
	
	/*
	 * Checks that the deck is sorted.
	 */
	public static void checkSorted(Deck deck) {
		for (int i = 0; i < 51; i++) {
			int flag = deck.cards[i].compareTo(deck.cards[i + 1]);
			if (flag != -1) {
				System.out.println("Not sorted!");
			}
		}
	}
}

/*
 * A Card represents a playing card with rank and suit.
 */
class Card {
	int suit, rank;

	static String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
	static String[] ranks = { "narf", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

	/*
	 * No-argument constructor.
	 */
	public Card() {
		this.suit = 0;
		this.rank = 0;
	}

	/*
	 * Constructor with arguments.
	 */
	public Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}

	/*
	 * Prints a card in human-readable form.
	 */
	public void print() {
		System.out.println(ranks[rank] + " of " + suits[suit]);
	}

	/*
	 * Prints a card in human-readable form.
	 */
	public String toString() {
		return ranks[rank] + " of " + suits[suit];
	}

	/*
	 * Return true if the cards are equivalent.
	 */
	public boolean equals(Card that) {
		return (this.suit == that.suit && this.rank == that.rank);
	}

	/*
	 * Compares two cards: returns 1 if the first card is greater -1 if the
	 * seconds card is greater, and 0 if they are the equivalent.
	 */
	public int compareTo(Card that) {

		// first compare the suits
		if (this.suit > that.suit)
			return 1;
		if (this.suit < that.suit)
			return -1;

		// if the suits are the same,
		// use modulus arithmetic to rotate the ranks
		// so that the Ace is greater than the King.
		// WARNING: This only works with valid ranks!
		int rank1 = (this.rank + 11) % 13;
		int rank2 = (that.rank + 11) % 13;

		// compare the rotated ranks

		if (rank1 > rank2)
			return 1;
		if (rank1 < rank2)
			return -1;
		return 0;
	}

	/*
	 * Compares two cards ignoring suit: returns 1 if the first card is greater -1 if the
	 * seconds card is greater, and 0 if they are the equivalent.
	 */	
	public int compareToRank(Card that) {

		// use modulus arithmetic to rotate the ranks
		// so that the Ace is greater than the King.
		// WARNING: This only works with valid ranks!
		int rank1 = (this.rank + 11) % 13;
		int rank2 = (that.rank + 11) % 13;

		// compare the rotated ranks

		if (rank1 > rank2)
			return 1;
		if (rank1 < rank2)
			return -1;
		return 0;
	}
	
	public boolean sequential(Card that) {

		// if the suits are the same,
		// use modulus arithmetic to rotate the ranks
		// so that the Ace is greater than the King.
		// WARNING: This only works with valid ranks!
		int rank1 = (this.rank + 11) % 13;
		int rank2 = (that.rank + 11) % 13;

		// compare the rotated ranks
		return (Math.abs(rank1 - rank2) == 1);
	}

	public boolean sameRank(Card that) {
		int rank1 = (this.rank + 11) % 13;
		int rank2 = (that.rank + 11) % 13;

		//System.out.println("original rank " + rank1 + " that original rank " + rank2);
		//System.out.println("this = " + rank1 + " that " + rank2);
		return rank1 == rank2;
	}
}

/*
 * A Deck contains an array of cards.
 */
class Deck {
	Card[] cards;

	/*
	 * Makes a Deck with room for n Cards (but no Cards yet).
	 */
	public Deck(int n) {
		this.cards = new Card[n];
	}

	/*
	 * Makes an array of 52 cards.
	 */
	public Deck() {
		this.cards = new Card[52];

		int index = 0;
		for (int suit = 0; suit <= 3; suit++) {
			for (int rank = 1; rank <= 13; rank++) {
				this.cards[index] = new Card(suit, rank);
				index++;
			}
		}
	}

	/*
	 * Prints a deck of cards.
	 */
	public void print() {
		System.out.println("=======Printing Deck/Hand======");
		for (int i = 0; i < cards.length; i++) {
			cards[i].print();
		}
	}

	/*
	 * Returns index of the first location where card appears in deck. Or -1 if
	 * it does not appear. Uses a linear search.
	 */
	public int findCard(Card card) {
		for (int i = 0; i < cards.length; i++) {
			if (card.equals(cards[i])) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * Returns index of a location where card appears in deck. Or -1 if it does
	 * not appear. Uses a bisection search.
	 *
	 * Searches from low to high, including both.
	 *
	 * Precondition: the cards must be sorted from lowest to highest.
	 */
	public int findBisect(Card card, int low, int high) {
		if (high < low)
			return -1;

		int mid = (high + low) / 2;
		int comp = card.compareTo(cards[mid]);

		if (comp == 0) {
			return mid;
		} else if (comp < 0) {
			// card is less than cards[mid]; search the first half
			return findBisect(card, low, mid - 1);
		} else {
			// card is greater; search the second half
			return findBisect(card, mid + 1, high);
		}
	}

	/*
	 * Chooses a random integer between low and high, including low, not
	 * including high.
	 */
	public int randInt(int low, int high) {
		// Because Math.random can't return 1.0, and (int)
		// always rounds down, this method can't return high.
		int x = (int) (Math.random() * (high - low) + low);
		return x;
	}

	/*
	 * Swaps two cards in a deck of cards.
	 */
	public void swapCards(int i, int j) {
		Card temp = cards[i];
		cards[i] = cards[j];
		cards[j] = temp;
	}

	/*
	 * Shuffles the cards in a deck.
	 */
	public void shuffle() {
		for (int i = 0; i < cards.length; i++) {
			//int j = randInt(i, cards.length-1);
			//fixed a bug in the code. Above statement will force King of Spades to always be the last card
			int j = randInt(i, cards.length);
			swapCards(i, j);
		}
	}

	/*
	 * Sorts a deck from low to high.
	 */
	public void sort() {
		for (int i = 0; i < cards.length; i++) {
			int j = indexLowestCard(i, cards.length - 1);
			swapCards(i, j);
		}
	}
	
	/*
	 * Sorts a deck from low to high ignoring suit
	 */
	public void sortByRank() {
		for (int i = 0; i < cards.length; i++) {
			int j = indexLowestCardByRank(i, cards.length - 1);
			swapCards(i, j);
		}
	}

	/*
	 * Finds the index of the lowest card between low and high, including both.
	 */
	public int indexLowestCard(int low, int high) {
		int winner = low;
		for (int i = low + 1; i <= high; i++) {
			if (cards[i].compareTo(cards[winner]) < 0) {
				winner = i;
			}
		}
		return winner;
	}
	
	/*
	 * Finds the index of the lowest card between low and high, including both ignoring suit
	 */
	public int indexLowestCardByRank(int low, int high) {
		int winner = low;
		for (int i = low + 1; i <= high; i++) {
			if (cards[i].compareToRank(cards[winner]) < 0) {
				winner = i;
			}
		}
		return winner;
	}

	/*
	 * Makes a new deck of cards with a subset of cards from the original. The
	 * old deck and new deck share references to the same card objects.
	 */
	public Deck subdeck(int low, int high) {
		Deck sub = new Deck(high - low + 1);

		for (int i = 0; i < sub.cards.length; i++) {
			sub.cards[i] = cards[low + i];
		}
		return sub;
	}

	/*
	 * Merges two sorted decks into a new sorted deck.
	 */
	public Deck merge(Deck d2) {
		// create the new deck
		Deck result = new Deck(cards.length + d2.cards.length);

		int choice; // records the winner (1 means d1, 2 means d2)
		int i = 0; // traverses the first input deck
		int j = 0; // traverses the second input deck

		// k traverses the new (merged) deck
		for (int k = 0; k < result.cards.length; k++) {
			choice = 1;

			// if d1 is empty, d2 wins; if d2 is empty, d1 wins; otherwise,
			// compare the two cards
			if (i == cards.length)
				choice = 2;
			else if (j == d2.cards.length)
				choice = 1;
			else if (cards[i].compareTo(d2.cards[j]) > 0)
				choice = 2;

			// make the new deck refer to the winner card
			if (choice == 1) {
				result.cards[k] = cards[i];
				i++;
			} else {
				result.cards[k] = d2.cards[j];
				j++;
			}
		}
		return result;
	}

	/*
	 * Sort the Deck using merge sort.
	 */
	public Deck mergeSort() {
		if (cards.length < 2) {
			return this;
		}
		int mid = (cards.length - 1) / 2;

		// divide the deck roughly in half
		Deck d1 = subdeck(0, mid);
		Deck d2 = subdeck(mid + 1, cards.length - 1);

		// sort the halves
		d1 = d1.mergeSort();
		d2 = d2.mergeSort();

		// merge the two halves and return the result
		// (d1 and d2 get garbage collected)
		return d1.merge(d2);
	}
	
	//3. Write a Deck method named deal that creates a PokerHand, transfer cards from the deck to the hand, and returns the hand.
	/**
	 * Deal a poker hand from a sub deck of cards. Poker hand is a subset of the deck from start to end inclusive
	 * @param start begin index of the subdeck
	 * @param end 	end index of the subdeck
	 * @return PokerHand
	 */
	
	public PokerHand deal(int start, int end) {

		Deck subdeck = subdeck(start, end);

		PokerHand hand = new PokerHand();

		hand.cards = subdeck.cards;

		return hand;
	}
}


//2. Write a definition for a class named PokerHand that extends Deck.

/**
 * PokerHand inherits from Deck class
 * @author anuvabanwasi
 *
 */
class PokerHand extends Deck {
	int num = 5;

	PokerHand() {}
	
//8. Write methods that test for the other poker hands.  nd it useful to write some general-purpose
	
	/**
	 * Checks if the poker hand is a straight flush
	 * @return true if poker hand has a straight flush 
	 */
	boolean hasStraightFlush() {
		sort();
		boolean sameSuit = isSameSuit();
		boolean sequentialRank = isSequentialRank();
		return sameSuit && sequentialRank;
	}

//5. Write a PokerHand method called hasFlush returns a boolean indicating whether the hand contains a flush.
	/**
	 * Checks if the poker hand is a flush
	 * @return true if poker hand has a flush 
	 */
	boolean hasFlush() {
		sort();

		boolean sameSuit = isSameSuit();
		boolean sequentialRank = isSequentialRank();

		return sameSuit && !sequentialRank;
	}
	/**
	 * Checks if the poker hand has all cards of same suit
	 * @return true if poker hand has has all cards of same suit
	 */
	private boolean isSameSuit() {
		for (int i = 0; i < cards.length - 1; i++) {
			if (cards[i].suit != cards[i + 1].suit) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Checks if the poker hand is sequentially ranked
	 * @return true if poker hand is sequentially ranked
	 */
	private boolean isSequentialRank() {
		for (int i = 0; i < cards.length - 1; i++) {
			if (!cards[i].sequential(cards[i + 1])) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Checks if the poker hand is a straight
	 * @return true if poker hand has a straight 
	 */
	boolean hasStraight(){
		
		sortByRank();
				
		boolean sameSuit = isSameSuit();
		boolean sequentialRank = isSequentialRank();
		
		return !sameSuit && sequentialRank;
	}
	/**
	 * Checks if the poker hand is a full house
	 * @return true if poker hand has a full house 
	 */
	boolean hasFullHouse(){
		
		sortByRank();
		
		if(cards[0].sameRank(cards[1]) && cards[0].sameRank(cards[2]) && !cards[2].sameRank(cards[3]) && cards[3].sameRank(cards[4])){
			return true;
		} else if(cards[0].sameRank(cards[1]) && !cards[1].sameRank(cards[2]) && cards[2].sameRank(cards[3]) && cards[2].sameRank(cards[4]) ){
			return true;
		} else 
			return false;
	}
	/**
	 * Checks if the poker hand is four of a kind
	 * @return true if poker hand is four of kind 
	 */
	boolean hasFourKind() {
		sortByRank();
			
		boolean fourKind = false;
		for (int i = 0; i < cards.length - 3; i++) {
			if(cards[i].sameRank(cards[i+1]) && cards[i].sameRank(cards[i+2]) && cards[i].sameRank(cards[i+3])) {
				return true;
			} 
		}
		
		return fourKind;
	}
	
//6. Write a method called hasThreeKind that indicates whether the hand contains Three of a Kind.
	/**
	 * Checks if the poker hand is three of a kind
	 * @return true if poker hand is three of kind 
	 */
	boolean hasThreeKind() {
		sortByRank();
		
		if(!hasFourKind()){
			if(cards[0].sameRank(cards[1]) && cards[0].sameRank(cards[2]) && !cards[3].sameRank(cards[4])){
				return true;
			} else if(cards[2].sameRank(cards[3]) && cards[2].sameRank(cards[4]) && !cards[0].sameRank(cards[1]) ){
				return true;
			} else if(cards[1].sameRank(cards[2]) && cards[1].sameRank(cards[3]) && !cards[0].sameRank(cards[4]) ){
				return true;
			}  else
				return false;
		}  
		else
			return false;
		
	}
	/**
	 * Checks if the poker hand is two pair
	 * @return true if poker hand is two pair
	 */
	boolean hasTwoPair(){
		sortByRank();
		
		if(cards[0].sameRank(cards[1]) && !cards[1].sameRank(cards[2]) && cards[2].sameRank(cards[3]) && !cards[3].sameRank(cards[4]))
			return true;
		else if(!cards[0].sameRank(cards[1]) && cards[1].sameRank(cards[2]) && !cards[2].sameRank(cards[3]) && cards[3].sameRank(cards[4]))
			return true;
		else
			return false;
	}
	
	/**
	 * Checks if the poker hand is one pair
	 * @return true if poker hand is one pair
	 */
	
	boolean hasOnePair(){
		sortByRank();
		if(!hasTwoPair()){
			for(int i = 0; i < cards.length - 1; i++){
				if(cards[i].sameRank(cards[i+1]))
					return true;
			}
		}
		return false;
	}
	/**
	 * Checks if the poker hand is a high card hand
	 * @return true if poker hand is a high card hand
	 */
	boolean hasHighCard(){
		sortByRank();
				
		boolean sameSuit = isSameSuit();
		
		boolean sequentialRank = isSequentialRank();
		
		boolean onePair = hasOnePair();
		
		return !sameSuit && !sequentialRank && !onePair;
		
	}
	/**
	 * PokerHand constructor
	 * @param type Poker Hand type
	 */
	PokerHand(String type) {
		this.cards = new Card[num];

		if (type.equals("straightflush")) {
			cards[0] = new Card(0, 1);
			cards[1] = new Card(0, 11);
			cards[2] = new Card(0, 13);
			cards[3] = new Card(0, 12);
			cards[4] = new Card(0, 10);
		}
		
		if (type.equals("flush")) {
			cards[0] = new Card(0, 1);
			cards[1] = new Card(0, 2);
			cards[2] = new Card(0, 3);
			cards[3] = new Card(0, 4);
			cards[4] = new Card(0, 5);
		}

		if (type.equals("fourkind")) {
			cards[0] = new Card(0, 4);
			cards[1] = new Card(0, 2);
			cards[2] = new Card(1, 2);
			cards[3] = new Card(2, 2);
			cards[4] = new Card(3, 2);
		}
		
		if (type.equals("threekind")) {
			cards[0] = new Card(0, 2);
			cards[1] = new Card(1, 2);
			cards[2] = new Card(2, 2);
			cards[3] = new Card(3, 4);
			cards[4] = new Card(3, 2);
		}
		
		if (type.equals("fullhouse")) {
			cards[0] = new Card(0, 12);
			cards[1] = new Card(1, 12);
			cards[2] = new Card(2, 12);
			cards[3] = new Card(1, 1);
			cards[4] = new Card(2, 1);
		}
		
		if (type.equals("straight")) {
			cards[0] = new Card(0, 2);
			cards[1] = new Card(1, 3);
			cards[2] = new Card(2, 4);
			cards[3] = new Card(3, 5);
			cards[4] = new Card(2, 6);
		}
		
		if (type.equals("twopair")) {
			cards[0] = new Card(0, 2);
			cards[1] = new Card(1, 2);
			cards[2] = new Card(1, 3);
			cards[3] = new Card(2, 3);
			cards[4] = new Card(3, 4);
		}
		
		if (type.equals("onepair")) {
			cards[0] = new Card(0, 2);
			cards[1] = new Card(1, 2);
			cards[2] = new Card(1, 3);
			cards[3] = new Card(2, 4);
			cards[4] = new Card(3, 5);
		}
		
		if (type.equals("highcard")) {
			cards[0] = new Card(0, 2);
			cards[1] = new Card(1, 7);
			cards[2] = new Card(0, 3);
			cards[3] = new Card(0, 4);
			cards[4] = new Card(0, 5);
		}
	}
}

/**
 * Class to generate combinations of length r of an array of numbers of length n. For example, generate all combinations of 2 numbers of the array {1,2,3} of length 3 returns {1,2}, {1,3}, {1,2} 
 * @author anuvabanwasi
 * References: 
 * 1. https://stackoverflow.com/questions/4640034/calculating-all-of-the-subsets-of-a-set-of-numbers
 * 2. https://stackoverflow.com/questions/2920315/permutation-of-array
 * 3. https://stackoverflow.com/questions/29910312/algorithm-to-get-all-the-combinations-of-size-n-from-an-array-java
 * 
 */
class Combinations {
	
	public List<int[]> combinations(int length) {
		int[] arr = new int[length];

		for (int i = 0; i < length; i++)
			arr[i] = i;

		return combinations(arr);
	}
	
	/**
	 * Generate combinations of the given input array
	 * @param set input array 
	 * @return List of integer array representing the combinations of the input array
	 * References: 
	 * https://stackoverflow.com/questions/29910312/algorithm-to-get-all-the-combinations-of-size-n-from-an-array-java
	 */
	public List<int[]> combinations(int[] set) {
		int[] subset = new int[set.length];
		List<int[]> result = new ArrayList<int[]>();
		combinations(set, 0, subset, 0, result);
		return result;
	}

	/**
	 * Recursive function to generate combinations of an array
	 * @param set input integer array 
	 * @param read integer representing index into the original array
	 * @param subset output integer array  representing a combination of the input array
	 * @param write integer representing index into the output array
	 * @param result List containing all combinations of the input array
	 * References: 
	 * https://stackoverflow.com/questions/29910312/algorithm-to-get-all-the-combinations-of-size-n-from-an-array-java
	 */
	void combinations(int[] set, int read, int[] subset, int write, List<int[]> result) {
		if (read == set.length) {
			if (write == 5) {
				int[] comb = new int[5];
				for (int j = 0; j < write; j++) {
					comb[j] = subset[j];
				}

				result.add(comb);
			}
			return;
		}

		// As we iterate through the input array, there are 2 possibilities. Select the integer and add to output array or don't select
		combinations(set, read + 1, subset, write, result); // dont select
		
		subset[write++] = set[read++];					   // select
		combinations(set, read, subset, write, result);	   // recurse
	}

	/**
	 * Print generated combination
	 * @param combination integer array that represent a combination
	 */
	private static void print(int[] combination) {
		for (int i = 0; i < combination.length; i++)
			System.out.print(combination[i]);
		System.out.println();
	}
}