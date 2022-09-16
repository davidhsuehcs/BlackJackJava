public class Dealer {

    public String name;
    public int bank;
    public gameHand hand;

    public boolean displayFull;

    public Dealer (String pname){
        this.hand = new gameHand();
        this.name = pname;
    }

    public void displayHand(){ // method to print whole hand
        System.out.println(this.name + "'s hand looks like this:");
        System.out.println(hand.toString() + "" + "valued at " + hand.calculatedTotalHandValue());
    }

    public void displayFirstHand(){ // method to print only first card
        System.out.println("The Dealer's hand looks like this:");
        System.out.println("[ " + this.hand.cardsInHand.get(0) + " ]" + " - " + "{{Second Card is Hidden}}");
    }

    public boolean hasBlackjack(){ // check for blackjack
        if(this.hand.calculatedTotalHandValue() == 21){
            return true;
        }
        else{
            return false;
        }
    }

}
