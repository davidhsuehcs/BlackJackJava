import java.util.ArrayList;

public class gameHand{

    public ArrayList<Card> cardsInHand;

    public gameHand (){
        cardsInHand = new ArrayList<>();
    }

    public String toString(){ // method to print out a person's hand
        String output = "";
        for (int i = 0; i < cardsInHand.size(); i++){
            output +=  "[ " + cardsInHand.get(i) + " ]" + " - ";
        }
        return output;
    }

    public int calculatedTotalHandValue(){
        int totalValue = 0;
        int numberofAces = 0;

        for (Card card : cardsInHand){
            totalValue += card.toValue();
            if (card.toValue() == 11){
                numberofAces ++;
            }
        }

        if (numberofAces > 0 && totalValue > 21) { // handy logic that works to figure out the hand's value with aces
            while(numberofAces > 0 && totalValue > 21){
                numberofAces --;
                totalValue -= 10;
            }
        }
        return totalValue;
    }


}
