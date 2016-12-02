package edu.opendev.blackjack.players;

import edu.opendev.blackjack.Card;
import edu.opendev.blackjack.Value;

public abstract class CommonGambler {

    protected String name;
    protected int countCards;
    protected Card[] cardsOnHand = new Card[10];

    public CommonGambler(String name) {
        this.name = name;
    }

    public int getCountPoints() {
        int countPoints = 0;
        int countAces = 0;

        for (Value value : Value.values()) {
            for (int i = 0; i < countCards; i++) {
                if (cardsOnHand[i].getValue() == value) {
                    if (cardsOnHand[i].getValue() == Value.ACE) {
                        countAces += 1;
                    } else {
                        countPoints += value.getCardValue();
                    }
                }
            }

            for (int i = 0; i < countAces; i++) {
                if (countPoints > 10) {
                    countPoints += 1;
                } else {
                    countPoints += Value.ACE.getCardValue();
                }
            }
        }
        return countPoints;
    }

    public boolean isBlackJack(){
        return getCountPoints() == 21;
    }

    public void addCard(Card card){
        cardsOnHand[countCards] = card;
        countCards++;
    }

    public boolean isNoMoreTwentyOne(){
        return (getCountPoints() <= 21);
    }

    public void outputToScreenHands(boolean showFirstCard){
        System.out.printf("\n%s cards: \n" , name);
        for (int i = 0; i < countCards; i++){
            if(i == 0 && !showFirstCard){
                System.out.println("[Скрытая карта]");
            }
            else{
                System.out.printf("%s\n", cardsOnHand[i].toString());
            }
        }
    }
}
