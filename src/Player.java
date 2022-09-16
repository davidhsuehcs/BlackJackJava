import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    transient Scanner input = new Scanner(System.in);

    public String name;
    int bank = 10000;
    public gameHand hand;

    public Player(String pname) { // construction of a player
        this.hand = new gameHand();
        this.name = pname;
    }

    public void displayHand() {
        System.out.println(this.name + "'s hand looks like this:");
        System.out.println(hand.toString() + "" + "valued at " + hand.calculatedTotalHandValue());
    }

    public String getName() {
        return this.name;
    }

    public boolean hasBlackjack() {
        if (this.hand.calculatedTotalHandValue() == 21) {
            return true;
        } else {
            return false;
        }
    }


    public int hitOrStand() {

        int decision = 0;
        boolean getChar = true;

        while (getChar) {
            //try to get that number
            try {
                System.out.println("Would you like to: (1) Hit or (2) Stand");
                decision = input.nextInt();
                getChar = false;

            }
            //catch any exceptions and try again
            catch (Exception e) { // if the answer provided is a 'char' for instance, we need to prompt the user again
                System.out.println("Invalid");
                input.next();
            }

            System.out.println("You selected " + decision);

        }

        return decision;


    }

    public int bettingQuantity() {
        int bet = 0;
        boolean getBet = true;

        while (getBet) {
            //try to get that number
            try {
                System.out.println("How much would you like to bet?");
                bet = input.nextInt();
                getBet = false;

            }
            //catch any exceptions and try again
            catch (Exception e) {
                System.out.println("Invalid");
                //we need to go to the next line of input or this will not work
                input.next();
            }

            System.out.println("You have bet " + bet + "!!!");
        }

        return bet;
    }


}

