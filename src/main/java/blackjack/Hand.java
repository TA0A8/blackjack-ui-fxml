package blackjack;

import java.util.Stack;

public class Hand {
	private Stack<Card> cards;
	private int value;
	private int highAceCount;
	private double bet;
	private double insuranceBet;
	private boolean insured=false;
	private boolean splitable=false;
	private boolean doubledDown=false;
	private boolean turnOver=false;
	
	//CONSTRUCTORS
	public Hand(){
		this(new Stack<Card>(), 0);
	}
	
	public Hand(double bet){
		this(new Stack<Card>(), bet);
	}
	
	public Hand(Stack<Card> cards, double bet) {
		this.cards = cards;
		this.placeBet(bet);
		this.highAceCount=0;
		this.value = 0;
		recalculateValue();
		this.insuranceBet = 0;
	}

	//HELPERS
	public int getValue(){
		return value;
	}
	
	public void recalculateValue(){
		value=0;
		for (Card c: cards){
			if (c.isAce()) highAceCount++;
			value+=c.getValue();
			if (value>21 && highAceCount>0){
				value-=10;
				highAceCount--;
			}
		}
	}
	
	private void isSplitable() {
		splitable = false;
		if (cards.size()==2){
			if (cards.get(0).getValue()==cards.elementAt(1).getValue()){
				splitable = true;
			}
		}
	}
	
	//BLACKJACK ACTIONS
	public void stay(){
		turnOver = true;
	}
	
	public void hit(Card newCard){
		cards.add(newCard);
		if (newCard.isAce()){
			highAceCount++;
		}
		if (value+newCard.getValue()>21 && highAceCount>0){
			value-=10;
			highAceCount--;
		}
		value+=newCard.getValue();
		if (cards.size()==2){
			isSplitable(); //toggles splitable if splitable			
		}
		if (doubledDown || value>=21){
			turnOver = true;
		}
	}
	
	public Hand split(){
		splitable=false;
		Stack<Card> newCard = new Stack<Card>();
		newCard.push(this.cards.pop());
		recalculateValue();
		Hand newHand = new Hand(newCard, bet);
		return newHand;
	}
	
	public void doubledown(Card newCard){
		bet+=bet;
		doubledDown = true;
		hit(newCard);
	}

	//TODO put this exception in the super
	public void purchaseInsurance(double insurance) throws Exception {
		if (insurance/bet>0.5) {
			throw new Exception("You can only purchase insurance up to half of the bet per hand.");
		}
		insured = true;
		setInsuranceBet(insurance);
	}
	
	//GETTERS AND SETTERS
	public Stack<Card> cards() {
		return cards;
	}

	public boolean isInsured() {
		return insured;
	}

	public double getBet() {
		return bet;
	}

	public void placeBet(double bet) {
		this.bet = bet;
	}
		
	public boolean splitable(){
		return splitable;
	}
	
	public boolean hasDoubledDown(){
		return doubledDown;
	}

	public double getInsuranceBet() {
		return insuranceBet;
	}

	public void setInsuranceBet(double insuranceBet) {
		this.insuranceBet = insuranceBet;
	}

	public boolean turnOver() {
		return turnOver;
	}
	
	public int getHighAceCount() {
		return highAceCount;
	}
}
