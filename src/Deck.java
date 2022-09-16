import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    public static String suits[] = {"Spades", "Clubs", "Diamonds", "Hearts"};
    public static String ranks[] = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    public static ArrayList<Card> cardDeck = new ArrayList<Card>(suits.length * ranks.length);

    public Deck() { // Methodically creates a set of 52 cards using a nested loop, which is later shuffled using the shuffleDeck() method
        for (int s = 0; s < 4; s++) {
            for (int r = 0; r < 13; r++) {
                cardDeck.add(new Card(suits[s], ranks[r]));
            }

        }
    }

    public static void shuffleDeck() {

        ArrayList<Card> shuffledDeck = new ArrayList<Card>(); // a temporary deck which is used to assign new values to the actual deck after shuffling

        while (cardDeck.size() > 0) {

            int pulledIndex = (int) (Math.random() * cardDeck.size()); //generates a random number from 0-51, giving the index of a single random card.
            shuffledDeck.add(cardDeck.get(pulledIndex)); // essentially "duplicates" a card and puts it into the new deck
            cardDeck.remove(pulledIndex); // now, remove the original card

        }

        cardDeck = shuffledDeck; //after removing all cards, we now copy the new shuffled deck into the original one

    }

    public Card takeCard() {
        Card takenCard = new Card(cardDeck.get(0)); // takes first card from card deck and duplicates it
        cardDeck.remove(0); // removes that card from original deck
        return takenCard; // get the taken card
    }


}
