import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    Scanner input = new Scanner(System.in);

    public SoundFile themeMusic;
    public SoundFile winBetSound;


    Player p1 = new Player("PlayerOne");
    Dealer d1 = new Dealer("Dealer");

    public static ArrayList<Player> playersAtTable = new ArrayList<>();
    public static ArrayList<Dealer> ds = new ArrayList<>();

    boolean doneHitting = false;

    public Game() {

        themeMusic = new SoundFile("My-Reverie-Hank-Mobley.wav"); // main game song

        winBetSound = new SoundFile("KaChing.wav"); // "win" sound / money is awarded


        if (playMusic() == 1) { // Scanner + evaluates the user's response
            for (int i = 0; i <= 5; i++) { // plays the game soundtrack 5 times or until the game is closed
                themeMusic.play();
            }
        }

        playersAtTable.add(p1);
        ds.add(d1);


        DrawImageApp myApp = new DrawImageApp(); // creates instance for the drawing of board + cards + hands
        new Thread(myApp).start();

        initiateRound(); // begins the actual game

    }

    private void initiateRound() {

        System.out.println("  ");
        System.out.println("[<<New Round " + p1.getName() + "!>>]");
        System.out.println("Your current balance is " + playersAtTable.get(0).bank);
        System.out.println("  ");

        int tempBet = 0; // creates and resets a temporary bet amount
        tempBet = p1.bettingQuantity(); // calls the Scanner betting method in the Player class

        doneHitting = false;
        d1.displayFull = false;

        Deck deck = new Deck(); // creates new deck


        deck.shuffleDeck(); // shuffles deck


        p1.hand.cardsInHand.clear(); // removes all cards from player hands after each round
        d1.hand.cardsInHand.clear(); // removes all cards from dealer


        p1.hand.cardsInHand.add(deck.takeCard());
        p1.hand.cardsInHand.add(deck.takeCard());
        p1.displayHand(); // two cards to player and displays those cards

        System.out.println(" ");

        d1.hand.cardsInHand.add(deck.takeCard());
        d1.hand.cardsInHand.add(deck.takeCard());
        d1.displayFirstHand(); // two cards to dealer and displays those cards


        System.out.println(" ");


        if (d1.hasBlackjack()) {

            d1.displayHand();
            d1.displayFull = true; // turns boolean displayFull true, which allows the dealer's second card to be revealed

            if (p1.hasBlackjack()) {

                System.out.println("You both have blackjack. Push!");
                pause(3500);
                initiateRound();

            } else {

                System.out.println("Dealer has won. Unfortunate!");
                pause(3500);
                p1.bank = p1.bank - tempBet;
                initiateRound();
            }


        }

        if (p1.hasBlackjack()) {

            System.out.println("How Lucky! Blackjack! You Win!"); // if the total value of the Player's cards is 21, they win instantly
            winBetSound.play();
            pause(3500);
            p1.bank = p1.bank + tempBet;
            initiateRound();
        }


        while (p1.hand.calculatedTotalHandValue() <= 21 && doneHitting == false) {
            if (p1.hitOrStand() == 1) {

                p1.hand.cardsInHand.add(deck.takeCard());
                p1.displayHand();

            } else {
                System.out.println(p1.name + " Stands");
                p1.displayHand();
                doneHitting = true;
            }
        }

        if (p1.hand.calculatedTotalHandValue() > 21) {
            System.out.println("You have gone over 21. You lose.");
            pause(3500);
            p1.bank = p1.bank - tempBet;
            initiateRound();

        }

        d1.displayHand();
        d1.displayFull = true; // Once the player has finished hitting/standing, the dealer's second card is unveiled
        pause(3500);

        while (d1.hand.calculatedTotalHandValue() < 17) { // Until the total value of the dealer's hand is higher than 17, cards are drawn from the deck
            d1.hand.cardsInHand.add(deck.takeCard());
            System.out.println("Dealer has just drawn a card.");
            d1.displayHand();
            System.out.println(" ");
            pause(2000);
        }

        if (d1.hand.calculatedTotalHandValue() > 21) {
            System.out.println("Dealer busts. You win!");
            p1.bank = p1.bank + tempBet;
            winBetSound.play();
            pause(2000);
            initiateRound();
        } else if (d1.hand.calculatedTotalHandValue() > p1.hand.calculatedTotalHandValue()) {
            System.out.println("You lose.");
            p1.bank = p1.bank - tempBet;
            pause(2000);
            initiateRound();
        } else if (p1.hand.calculatedTotalHandValue() > d1.hand.calculatedTotalHandValue()) {
            System.out.println("You win.");
            p1.bank = p1.bank + tempBet;
            winBetSound.play();
            pause(2000);
            initiateRound();
        } else {
            pause(3500);
            System.out.println("Push.");
            initiateRound();

        }
        pause(3500);
        initiateRound();
    }


    public void pause(int msToPause) { // pausing method
        try {
            Thread.sleep(msToPause);
        } catch (InterruptedException e) {

        }
    }

    public int playMusic() {

        int decision = 0;
        boolean getChar = true;

        System.out.println("Would you like to hear music? (1) Yes or (2) No");

        while (getChar) {
            //try to get that number

            if (input.hasNextInt()) {
                int val = input.nextInt();
                if (val >= 1 && val < 3) { // Sets a limit for valid responses to the question
                    decision = val;
                    getChar = false;
                } else if (val < 1 || val > 2){
                    while (getChar) { // Essentially a nested while loop that can be shut down by a boolean flip inside
                        System.out.println("Please Select Either 1 or 2!"); // Keep asking the prompt until an answer is given
                        decision = input.nextInt();
                        if(decision >= 1 && decision < 3){ // Evaluates the value with two conditions
                            getChar = false; // If both are met, the program stops and the method terminates
                        }
                    }
                } else {
                    getChar = false;
                }
            }
            System.out.println("You selected " + decision);
        }
        return decision;
    }

}
