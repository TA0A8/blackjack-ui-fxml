package blackjack;

import java.util.Random;
import java.util.Stack;

public class Deck {
	private Stack<Card> cards = new Stack<Card>();
	
	//CONSTRUCTORS
	//SingleDeck
	public Deck(){
		this(1);
	}
	
	public Deck(int numberOfDecks){
		for (int k=0; k<numberOfDecks; k++){			
			// 4 Suites
			for (int i=1; i<=4;i++){
				// Faces and Aces
				cards.push(new Card("Ace", i));
				cards.push(new Card("King", i));
				cards.push(new Card("Queen", i));
				cards.push(new Card("Jack", i));
				// Number Cards
				for (int j=2;j<=10;j++){
					cards.push(new Card(String.valueOf(j), i));
				}
			}
		}
		this.shuffle();
	}
	
	public Deck shuffle(){
		Random r = new Random();
		for (int i=0;i<cards.size();i++){
			int randomCard = r.nextInt(cards.size());
			cards.setElementAt(cards.set(randomCard, cards.elementAt(i)), i);
		}
		return this;
	}
	
	public Card pop(){
		return cards.pop();
	}
	
	public void dealCard(Player p){
		p.hands().get(0).hit(cards.pop());
	}
	
	public Stack<Card> cards() {
		return cards;
	}
}