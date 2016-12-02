package edu.opendev.blackjack;

import java.util.Random;

public class Deck {

    private int countCards;
    private Card[] poolCards;

    public Deck() {
        this.countCards = 52;
        this.poolCards = new Card[countCards];

    }

    public void createDeck(){
        int i = 0;
        for(Lear lear: Lear.values()){
            for (Value value : Value.values()){
                poolCards[i] = new Card(lear, value);
                i++;
            }
        }
    }

    public void mixCards(){
        int randomIndex;
        Card tempCard;
        Random random = new Random();

        for(int i = 0; i < countCards; i++){
            randomIndex = random.nextInt(countCards);
            tempCard = poolCards[i];
            poolCards[i] = poolCards[randomIndex];
            poolCards[randomIndex] = tempCard;
        }
    }

    public Card getNextCard() {
        Card card = poolCards[0];
        moveToCardsInPool();
        deleteCardInPool();
        return card;
    }

    public void moveToCardsInPool(){
        for (int i = 1; i < countCards; i++){
            poolCards[i - 1] = poolCards[i];
        }
    }

    public void deleteCardInPool(){
        poolCards[countCards - 1] = null;
        countCards--;
    }
}
