package blackjack;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {


    public static ArrayList<Player> players = new ArrayList<Player>();
    public static int numOfDecks = 0;
    public static int numPlayers = 0;
    public static Scanner keyboard = new Scanner(System.in);
    public static Deck deck;
    public static NumberFormat formatter = NumberFormat.getCurrencyInstance();
    public static Player dealer;
    public static boolean gameOver;

    public static void newGame() {
        System.out.println("Welcome to Blackjack!");

        //Create Deck
        System.out.println("How many decks of cards would you like to play with?");
        numOfDecks = keyboard.nextInt();
        keyboard.nextLine();
        while (numOfDecks < 1 || numOfDecks > 5) {
            System.out.println("Please enter a number of decks between 1 and 5.");
            numOfDecks = keyboard.nextInt();
            keyboard.nextLine();
        }
        deck = new Deck(numOfDecks);

        //Get Num of Players
        System.out.println("Please enter the number players?");
        numPlayers = keyboard.nextInt();
        keyboard.nextLine();
        while (numPlayers < 1 || numPlayers > 5) {
            System.out.println("Please enter a number of players between 1 and 5.");
            numPlayers = keyboard.nextInt();
            keyboard.nextLine();
        }

        // Create Players
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Please give a name for player " + (i + 1) + ": ");
            System.out.println("");
            String pName = keyboard.nextLine();
            if (pName.toLowerCase().equals("dealer")) {
                System.out.print("That name is reserved, please enter a new name: ");
                pName = keyboard.nextLine();
            }
            Player newPlayer = new Player(pName, i);
            players.add(newPlayer);
        }
    }

    public static void deal() {
        //Deal 2 cards to everyone who bet in the correct order
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < players.size(); i++) {
                if (deck.cards().size() == 0) {
                    deck = new Deck(numOfDecks);
                }
                if (players.get(i).hands().get(0).getBet() != 0) {
                    deck.dealCard(players.get(i));

                }
            }
            deck.dealCard(dealer);
        }
    }

    public static void placeBets() {
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            //Get bet from each player, more than 0 and less than their bank.
            boolean confirmNoBet = false;
            if (p.getBank() > 0) {
                System.out.println("Player " + (i + 1) + ": " + p.getName() + " bank: " + formatter.format(p.getBank()));
                System.out.println("How much would you like to bet?");
                double bet = keyboard.nextDouble();
                keyboard.nextLine();
                while ((bet <= 0 || bet > p.getBank())) {
                    if (confirmNoBet) {
                        break;
                    } else if (bet == 0) {
                        System.out.println("Are you sure you want to place no bet and pass this round? (yes/no)");
                        if (keyboard.nextLine().toLowerCase().equals("yes")) {
                            confirmNoBet = true;
                        } else {
                            bet = -1;
                        }
                    } else {
                        System.out.println("Please enter a bet more than 0 and less than or equal to your bank?");
                        bet = keyboard.nextDouble();
                        keyboard.nextLine();
                    }
                }
                p.setBank(p.getBank() - bet);
                p.hands().get(0).placeBet(bet);
            }
        }
    }

    public static boolean anyBets() {
        boolean anyBets = false;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).hands().get(0).getBet() > 0) {
                anyBets = true;
            }
        }
        return anyBets;
    }

    public static int getNumPlayers() {
        return numPlayers;
    }

    public static void setNumPlayers(int numPlayers) {
        Blackjack.numPlayers = numPlayers;
    }

    public static int getNumOfDecks() {
        return numOfDecks;
    }

    public static void setNumOfDecks(int numOfDecks) {
        Blackjack.numOfDecks = numOfDecks;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(ArrayList<Player> players) {
        Blackjack.players = players;
    }

}

	
