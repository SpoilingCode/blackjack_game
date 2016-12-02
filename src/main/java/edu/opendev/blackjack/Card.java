package edu.opendev.blackjack;

public class Card {

    private Lear lear;
    private Value value;

    public Card(Lear lear, Value value) {
        this.lear = lear;
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        String cardValue;
       if(value != Value.JACK &&
          value != Value.KING &&
          value != Value.QUEEN &&
          value != Value.ACE ){
           cardValue = value.getCardValue() + " - " + lear.toString();
       } else {
           cardValue = value.toString() + " - " + lear.toString();
       }
        return cardValue;
    }
}
