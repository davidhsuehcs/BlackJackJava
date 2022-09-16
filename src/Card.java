import java.util.Arrays;

public class Card implements java.io.Serializable {

    public String suit;
    public String rank;

    public Card(String insuit, String inrank) {
        suit = insuit;
        rank = inrank;

    }

    public int retrieveRankCord() {
        return Arrays.asList(Deck.ranks).indexOf(rank);
    }

    public String toString() {
        return "The " + rank + " of " + suit;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }


    public int toValue() {
        if (rank.equals("Ace")) {
            return 11;
        } else if (rank.equals("Two")) {
            return 2;
        } else if (rank.equals("Three")) {
            return 3;
        } else if (rank.equals("Four")) {
            return 4;
        } else if (rank.equals("Five")) {
            return 5;
        } else if (rank.equals("Six")) {
            return 6;
        } else if (rank.equals("Seven")) {
            return 7;
        } else if (rank.equals("Eight")) {
            return 8;
        } else if (rank.equals("Nine")) {
            return 9;
        } else if (rank.equals("Ten") || rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
            return 10;
        }


        return 00000;
    }

    public Card(Card card) { // a method for duplication used in the takeCard() method of Deck.java
        this.suit = card.getSuit();
        this.rank = card.getRank();
    }


}
