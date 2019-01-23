package blackjack;

import java.util.ArrayList;

public class Player {

	private String name;
	private ArrayList<Hand> hands;
	private int winCount;
	private int drawCount;
	private int loseCount;
	private double bank;
	private int boardPosition;

	
	public Player(String name, int position){		
		this.setName(name);
		this.setBoardPosition(position);
		this.hands = new ArrayList<Hand>();
		hands.add(new Hand());
		this.winCount = 0;
		this.loseCount = 0;
		this.setBank(500);
	}
		
	//GETTERS AND SETTERS
	public ArrayList<Hand> hands() {
		return hands;
	}
	
	public void clearHand(){
		hands.clear();
		hands.add(new Hand());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWinCount() {
		return winCount;
	}

	public void addWin() {
		this.winCount++;
	}

	public int getLostCount() {
		return loseCount;
	}

	public void addLose() {
		this.loseCount++;
	}
	
	public int getDrawCount() {
		return drawCount;
	}

	public void addDraw() {
		this.drawCount++;
	}

	public void resetStats(){
		this.winCount = 0;
		this.loseCount = 0;
	}

	public double getBank() {
		return bank;
	}

	public void setBank(double bank) {
		this.bank = bank;
	}

	public int getBoardPosition() {
		return boardPosition;
	}

	public void setBoardPosition(int boardPosition) {
		this.boardPosition = boardPosition;
	}
}
