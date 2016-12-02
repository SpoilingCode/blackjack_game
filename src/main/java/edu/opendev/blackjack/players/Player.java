package edu.opendev.blackjack.players;

public class Player extends CommonGambler {

    private double balance = 250.00;
    private double betPrice;

    public Player(String name) {
        super(name);
    }

    public double getBalance() {
        return balance;
    }

    public double getBetPrice() {
        return betPrice;
    }

    public void setBetPrice(double betPrice) {
        this.betPrice = betPrice;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isBetPriceCorrect() {
        return (betPrice > 0 &&
                betPrice <= balance);
    }
}
