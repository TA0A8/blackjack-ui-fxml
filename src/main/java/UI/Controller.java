package UI;


import blackjack.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static blackjack.Blackjack.*;

public class Controller {

    @FXML
    private TextField pl_one_label;
    @FXML
    private TextField pl_one;
    @FXML
    private TextField pl_one_bank;
    @FXML
    public TextField pl_one_Bet;
    @FXML
    public TextField gameMessage;

    double plBet;
    Deck deck = new Deck();

    Player playerOne = new Player(null, 0);

    @FXML
        // dec = kalade
    void numOfDecs1(ActionEvent event) {
        Blackjack.setNumOfDecks(1);
        System.out.println("pasirinkta kaladziu: " + Blackjack.getNumOfDecks());
        deck = new Deck(1);

    }

    @FXML
    void numOfDecs2(ActionEvent event) {
        Blackjack.setNumOfDecks(2);
        System.out.println("pasirinkta kaladziu: " + Blackjack.getNumOfDecks());
        deck = new Deck(2);
    }

    @FXML
    void numOfDecs3(ActionEvent event) {
        Blackjack.setNumOfDecks(3);
        System.out.println("pasirinkta kaladziu: " + Blackjack.getNumOfDecks());
        deck = new Deck(3);
    }

    @FXML
    void numOfDecs4(ActionEvent event) {
        Blackjack.setNumOfDecks(4);
        System.out.println("pasirinkta kalandziu: " + Blackjack.getNumOfDecks());
        deck = new Deck(4);
    }

    @FXML
    void numOfDecs5(ActionEvent event) {
        Blackjack.setNumOfDecks(5);
        System.out.println("pasirinkta kaladziu: " + Blackjack.getNumOfDecks());
        deck = new Deck(5);
    }


    @FXML
    public void changeGoToGameTable(ActionEvent click) throws IOException {
        int numberOfPlayers = Blackjack.getNumPlayers();
        switch (numberOfPlayers) {
            case 1:
                Parent gameViewParent = FXMLLoader.load(getClass().getResource("../UI/GameTable.fxml"));
                Scene gameViewScene = new Scene(gameViewParent);
                Stage window = (Stage) ((Node) click.getSource()).getScene().getWindow();
                window.setScene(gameViewScene);
                window.setTitle("One player table");
                window.show();
                break;
            case 2:
                Parent gameViewParentTwo = FXMLLoader.load(getClass().getResource("../UI/GameTableTwo.fxml"));
                Scene gameViewSceneTwo = new Scene(gameViewParentTwo);
                Stage windowTwo = (Stage) ((Node) click.getSource()).getScene().getWindow();
                windowTwo.setScene(gameViewSceneTwo);
                windowTwo.setTitle("Two player table");
                windowTwo.show();
                break;
            case 3:
                Parent gameViewParentTree = FXMLLoader.load(getClass().getResource("../UI/GameTableTree.fxml"));
                Scene gameViewSceneTree = new Scene(gameViewParentTree);
                Stage windowTree = (Stage) ((Node) click.getSource()).getScene().getWindow();
                windowTree.setScene(gameViewSceneTree);
                windowTree.setTitle("Tree player table");
                windowTree.show();
                break;
            default:
                noPlayersAlert(click);
                System.out.println("game is for one to tree players! ");
        }
    }
///////////////////////////////  ALERTS /////////////////////////////////

    @FXML
    public void noPlayersAlert(ActionEvent alert) {
        Alert a1 = new Alert(Alert.AlertType.WARNING);
        a1.setTitle("Meniu Alert");
        a1.setContentText("You didn't chose number of players!!!");
        a1.showAndWait();
    }

    @FXML
    public void endGamesAlert(ActionEvent alert) {
        Alert a1 = new Alert(Alert.AlertType.INFORMATION);
        a1.setContentText("Thank you for playing !!!");
        a1.showAndWait();
    }

    @FXML
    public void wrongInputAlert(ActionEvent alert) {
        Alert a2 = new Alert(Alert.AlertType.WARNING);
        a2.setContentText("WRONG imput");
        a2.showAndWait();
    }

    @FXML
    public void PlayerIsalreadyCreated(ActionEvent alert) {
        Alert a2 = new Alert(Alert.AlertType.WARNING);
        a2.setContentText("player name info");
        a2.setContentText("player already created");
        a2.showAndWait();
    }

/////////////////////////////////////////////////////////////////////////

    @FXML
    public void onePlayergame(ActionEvent event) {
        Blackjack.setNumPlayers(1);
    }

    @FXML
    public void treePlayerGame(ActionEvent event) {
        Blackjack.setNumPlayers(3);
    }

    @FXML
    public void twoPlayerGame(ActionEvent event) {
        Blackjack.setNumPlayers(2);
    }

    @FXML
    void enterPlayerOneName(ActionEvent event) {
        System.out.println(pl_one.getText());
    }

    @FXML
    public void submitPlOneName(ActionEvent event) {
        if (players.isEmpty()) {
            Player playerOne = new Player(pl_one.getText(), 0);
            pl_one_label.setText(pl_one.getText());
            players.add(playerOne);

            dealer = new Player("Dealer", players.size());
            players.add(dealer);
        } else {
            PlayerIsalreadyCreated(event);
        }
        System.out.println(players.toString());
        String temp = String.valueOf(playerOne.getBank()); // playerOne pradinis bankas
        pl_one_bank.setText(String.valueOf(temp));
    }


    @FXML
    public void submitPLOneBet(ActionEvent event) {
        boolean isImputRight = true;

        do {
            plBet = Integer.parseInt(pl_one_Bet.getText()); // int ivesta suma
            if (plBet > playerOne.getBank() || plBet <= 0) {

                wrongInputAlert(event);
                pl_one_Bet.clear();

                isImputRight = false;
                System.out.println("wrong imput - " + plBet);
            } else {
                isImputRight = true;

                Hand playerOneHand = new Hand();

                playerOneHand.placeBet((double) plBet);
                System.out.println("player one bet is: " + playerOneHand.getBet());
                // rodomas minusuojamas bankas:
                pl_one_bank.setText(String.valueOf(playerOne.getBank() - playerOneHand.getBet()));
                playerOne.setBank(playerOne.getBank() - playerOneHand.getBet());
                playerOne.hands().get(0).placeBet(playerOneHand.getBet());

            }
        } while (isImputRight = false);

    }


    @FXML
    void dealCards(ActionEvent deal) {
        do {
            Hand dHand = dealer.hands().get(0);
            gameOver = true;
            double bet = Integer.parseInt(pl_one_Bet.getText()); // play 1 last bet from txt win
            if (bet != 0) {
                gameMessage.setText("Let the games begin!");

                for (int j = 0; j < 2; j++) {
                    if (playerOne.hands().get(0).getBet() != 0) {
                        deck.dealCard(playerOne);
                    }
                    deck.dealCard(dealer);
                }
                //TODO Check for insurance
                //TODO only play if there are bets

                ///////////////////////// cia perziureti zemyn

                //Commence Play, loop through players until reach Dealer
                //      for (int i = 0; i < players.size(); i++) {
              //  Player p = playerOne; //players.get(i);
                for (int j = 0; j < playerOne.hands().size(); j++) {
                    Hand h = playerOne.hands().get(j);
                    while (!h.turnOver() && h.getBet() > 0) {
                        //Print Status before asking for action

                        System.out.println("Player " + ": " + playerOne.getName() + " - Hand " + (j + 1));
                        System.out.println("Bet: " + formatter.format(h.getBet()));   //cia
                        for (int cardNum = 0; cardNum < h.cards().size(); cardNum++) {
                            System.out.println("  " + h.cards().get(cardNum).toString());
                        }
                        System.out.println("Total Value: " + h.getValue());
                        System.out.println("");
                        System.out.println("Dealer Face-up Card:");
                        System.out.println("  " + dHand.cards().get(1).toString());
                        System.out.println("");

                        int validAction = 2;
                        System.out.println("Please type the number for the action you would like to do:");
                        System.out.println("1 - Hit");
                        System.out.println("2 - Stay");
                        if (h.cards().size() == 2) {
                            System.out.println("3 - Double Down");
                            validAction++;
                        }
                        if (h.splitable()) {
                            System.out.println("4 - Split");
                            validAction++;
                        }
                        int action = keyboard.nextInt();   //  veiksmas bus per fxml

                        //Catch invalid action entry
                        while (action < 1 || action > validAction) {
                            System.out.println("Please enter a valid action:");
                            System.out.println("Please type the number for the action you would like to do:");
                            System.out.println("1 - Hit");
                            System.out.println("2 - Stay");
                            if (h.cards().size() == 2 && playerOne.getBank() > h.getBet()) {
                                System.out.println("3 - Double Down");
                            }
                            if (h.splitable() && playerOne.getBank() > h.getBet()) {
                                System.out.println("4 - Split");
                            }
                            action = keyboard.nextInt();
                            keyboard.nextLine();
                        }

                        //Execute Actions
                        switch (action) {
                            case 1: //Hit
                                h.hit(deck.pop());
                                break;
                            case 2: //Stay
                                h.stay();
                                break;
                            case 3: //Double Down
                                if (h.cards().size() == 2 && playerOne.getBank() >= h.getBet()) {
                                    playerOne.setBank(playerOne.getBank() - h.getBet());
                                    h.doubledown(deck.pop());
                                } else if (h.cards().size() == 2) {
                                    System.out.println("You can not afford to Double Down");
                                } else {
                                    System.out.println("You can only Double Down with two cards");
                                }
                                break;
                            case 4: //Split
                                if (h.splitable() && playerOne.getBank() >= h.getBet()) {
                                    playerOne.setBank(playerOne.getBank() - h.getBet());
                                    playerOne.hands().add(h.split());
                                    h.hit(deck.pop());
                                    playerOne.hands().get(j + 1).hit(deck.pop());
                                } else if (h.splitable()) {
                                    System.out.println("You can not afford to Split");
                                } else {
                                    System.out.println("Your cards are not splitable");
                                }
                                break;
                        }

                        // 21 or bust
                        if (h.getValue() > 20) {
                            if (h.getValue() == 21) {
                                System.out.println("Blackjack!");
                                System.out.println();
                            } else {
                                System.out.println("Bust");
                                System.out.println();
                            }
                        }
                    }
                    //Print Status after finish turn
                    System.out.println("");
                    System.out.println("Player " + 1 + ": " + playerOne.getName() + " - Hand " + (j + 1));
                    System.out.println("Bet: " + formatter.format(h.getBet()));
                    for (int cardNum = 0; cardNum < h.cards().size(); cardNum++) {
                        System.out.println("" + h.cards().get(cardNum).toString());
                    }
                    System.out.println("Total Value: " + h.getValue());
                }
                players.set(0, playerOne);
                // }

                //DEALER ACTION
                System.out.println("");
                System.out.println("Dealer: ");
                while (dHand.getValue() < 17 || (dHand.getValue() == 17 && dHand.getHighAceCount() > 0)) {
                    for (int cardNum = 0; cardNum < dHand.cards().size(); cardNum++) {
                        System.out.println("" + dHand.cards().get(cardNum).toString());
                    }
                    System.out.println("Total Value: " + dHand.getValue());
                    System.out.println("");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    dHand.hit(deck.pop());
                    System.out.println("Dealer hits");
                }
                for (int cardNum = 0; cardNum < dHand.cards().size(); cardNum++) {
                    System.out.println("" + dHand.cards().get(cardNum).toString());
                }
                System.out.println("Total Value: " + dHand.getValue());
                System.out.println("");


                System.out.println("Action Over");
                System.out.println("");

                //pay players
                //reset player hands
                //see if games over
                int dScore = dHand.getValue();
                for (int i = 0; i < players.size(); i++) {
                    Player p = players.get(i);
                    for (int j = 0; j < p.hands().size(); j++) {
                        Hand h = p.hands().get(j);
                        int score = h.getValue();

                        // Pay Players
                        // Player Blackjack
                        if (score == 21) {
                            if (dScore == 21) {
                                //push
                                p.setBank(p.getBank() + h.getBet());
                                p.addDraw();
                                System.out.println("Player " + (i + 1) + " " + p.getName());
                                System.out.println("Hand " + (j + 1) + " - " + "Bet: " + h.getBet());
                                System.out.println("(" + h.getValue() + "/" + dHand.getValue() + ")" + ": Push");
                                System.out.println("");
                            } else {
                                // Blackjack win
                                p.setBank(p.getBank() + h.getBet() * 2.5);
                                p.addWin();
                                System.out.println("Player " + (i + 1) + ": " + p.getName());
                                System.out.println("Hand " + (j + 1) + " - " + "Bet: " + h.getBet());
                                System.out.println("(" + h.getValue() + "/" + dHand.getValue() + ")" + ": BlackJack!");
                                System.out.println("Winnings: " + h.getBet() * 1.5);
                                System.out.println("");
                            }
                        }
                        // Dealer Bust or beat dealer by score
                        else if ((dScore > 21) || (score < 21 && score > dScore)) {
                            p.setBank(p.getBank() + h.getBet() * 2);
                            p.addWin();
                            System.out.println("Player " + (i + 1) + ": " + p.getName());
                            System.out.println("Hand " + (j + 1) + " - " + "Bet: " + h.getBet());
                            System.out.println("(" + h.getValue() + "/" + dHand.getValue() + ")" + ": Win!");
                            System.out.println("Winnings: " + h.getBet());
                            System.out.println("");
                        }
                        //Push
                        else if (score == dScore) {
                            p.setBank(p.getBank() + h.getBet());
                            p.addDraw();
                            System.out.println("Player " + (i + 1) + ": " + p.getName());
                            System.out.println("Hand " + (j + 1) + " - " + "Bet: " + h.getBet());
                            System.out.println("(" + h.getValue() + "/" + dHand.getValue() + ")" + ": Push");
                            System.out.println("");
                        }
                        //Lose
                        else if (score > 21) {
                            p.addLose();
                            System.out.println("Player " + (i + 1) + ": " + p.getName());
                            System.out.println("Hand " + (j + 1) + " - " + "Bet: " + h.getBet());
                            System.out.println("BUST" + ": Lose");
                            System.out.println("");
                        } else {
                            p.addLose();
                            System.out.println("Player " + (i + 1) + ": " + p.getName());
                            System.out.println("Hand " + (j + 1) + " - " + "Bet: " + h.getBet());
                            System.out.println("(" + h.getValue() + "/" + dHand.getValue() + ")" + ": Lose");
                            System.out.println("");
                        }


                    }
                    // Reset Hands
                    p.clearHand();

                    // Check if gameOver
                    if (p.getBank() > 0) {
                        gameOver = false;
                    }
                }
                dealer.clearHand();
                System.out.println("DEALER CLEARED");
            } else {
                gameOver = false;
            }
        } while (!gameOver);
        System.out.println("GAME OVER");
//        for (int j = 0; j < 2; j++) {
//            System.out.println(playerOne.getBank());
//            for (int i = 0; i < players.size(); i++) {
//                if (deck.cards().size() == 0) {
//                    deck = new Deck(numOfDecks);
//                }
//                if (playerOne.hands().get(0).getBet() != 0) {
//                    deck.dealCard(playerOne);
//                    System.out.println(playerOne.hands().size());
//                    System.out.println(playerOne.hands().get(0).getBet());
//                }
//            }
//            deck.dealCard(dealer);
//            System.out.println(dealer.hands().get(0).getBet());
//        }
    }

    @FXML
    public void changeGoMenuTable(ActionEvent click) throws IOException {

        Parent gameViewParent = FXMLLoader.load(getClass().getResource("../UI/MeniuWindow.fxml"));
        Scene gameViewScene = new Scene(gameViewParent);

        Stage window = (Stage) ((Node) click.getSource()).getScene().getWindow();
        window.setScene(gameViewScene);
        window.show();
    }

    @FXML
    public void endButton(ActionEvent event) {
        endGamesAlert(event);
        System.exit(0);
    }

}


