package edu.opendev.blackjack.players;

import edu.opendev.blackjack.Card;

public class Dealer extends CommonGambler {

    public Dealer(String name) {
        super(name);
        super.cardsOnHand = new Card[10];
    }
}
