package edu.opendev.blackjack;

import edu.opendev.blackjack.players.Dealer;
import edu.opendev.blackjack.players.Player;

import java.util.Scanner;

public class GameCore {

    private Validation validation;
    private Player player;
    private Dealer dealer;
    private Deck mainDeck;

    public GameCore() {

    }

    public void startGame() {
        validation = new Validation();
        player = new Player("Jimm");
        dealer = new Dealer("Dealer");
        validation.inputBetPrice(player);
        outputCardsToPlayers();

        if (player.isBlackJack() && !dealer.isBlackJack()) {
            System.out.println("Поздравляем, вы выиграли! БЛЭКДЖЭК!");
            System.out.println("Дилер проиграл. Сумма очков дилера : " + dealer.getCountPoints());
        } else if (!player.isBlackJack() && dealer.isBlackJack()) {
            System.out.println("Вы проиграли! Сумму ваших очков : " + player.getCountPoints());
            System.out.println("У Дилера БЛЭКДЖЭК!");
        } else {
            playersTakeCards();
        }
        player.outputToScreenHands(true);
        dealer.outputToScreenHands(true);
        determineWinner();
    }

    public void outputCardsToPlayers() {
        mainDeck = new Deck();
        mainDeck.createDeck();
        mainDeck.mixCards();

        player.addCard(mainDeck.getNextCard());
        dealer.addCard(mainDeck.getNextCard());
        player.addCard(mainDeck.getNextCard());
        dealer.addCard(mainDeck.getNextCard());

        System.out.println("Полученные карты: ");
        player.outputToScreenHands(true);
        dealer.outputToScreenHands(false);
        System.out.println("\n");
    }

    public void playersTakeCards() {
        Scanner scanner = new Scanner(System.in);
        boolean playerStopped = false;
        boolean dealerStopped = false;
        boolean correctCommandPlayer = false;
        String userResponse;

        while (!dealerStopped || !playerStopped) {
            if (!playerStopped) {

                System.out.println("Взять ещё карту(1) или достаточно(2)?");
                userResponse = scanner.nextLine();
                if (validation.isDigit(userResponse)) {

                    switch (Integer.parseInt(userResponse)) {
                        case 1:
                            player.addCard(mainDeck.getNextCard());
                            playerStopped = !player.isNoMoreTwentyOne();
                            player.outputToScreenHands(true);
                            correctCommandPlayer = true;
                            break;
                        case 2:
                            playerStopped = true;
                            correctCommandPlayer = true;
                            break;
                        default:
                            System.out.println("Данная команда отсутствует.");
                    }
                } else {
                    System.out.println("Введены некорректные данные. ");
                }
            }

            if (!dealerStopped && correctCommandPlayer) {

                if (dealer.getCountPoints() < 17) {
                    System.out.println("\nДилер берет ещё карту.");
                    dealer.addCard(mainDeck.getNextCard());
                    dealerStopped = !dealer.isNoMoreTwentyOne();
                    dealer.outputToScreenHands(false);
                } else {
                    System.out.println("\nДилеру достаточно карт.");
                    dealerStopped = true;
                }
            }
            System.out.println();
        }
    }

    public void determineWinner() {
        double sum = 0;

        if (player.getCountPoints() > dealer.getCountPoints() &&
                player.getCountPoints() <= 21 ||
                dealer.getCountPoints() > 21) {
            sum += player.getBetPrice() + player.getBalance();
            player.setBalance(sum);
            System.out.println("\nВы победили!Сумма ваших очков: " + player.getCountPoints());
            System.out.println("Ваш баланс: " + player.getBalance());
            System.out.println("Дилер проиграл!Сумма очков дилера:" + dealer.getCountPoints());

        } else if (player.getCountPoints() == dealer.getCountPoints() &&
                player.getCountPoints() <= 21 &&
                dealer.getCountPoints() <= 21) {
            System.out.println("\nНичья!");
            System.out.println("Сумма ваших очков: " + player.getCountPoints());
            System.out.println("Сумма очков дилера: " + dealer.getCountPoints());

        } else if (player.getCountPoints() < dealer.getCountPoints() &&
                player.getCountPoints() > 21 ||
                dealer.getCountPoints() <= 21) {
            sum += player.getBalance() - player.getBetPrice();
            player.setBalance(sum);
            System.out.println("\nВы проиграли!Сумма ваших очков: " + player.getCountPoints());
            System.out.println("Ваш баланс: " + player.getBalance());
            System.out.println("Дилер выиграл!Сумма очков дилера:" + dealer.getCountPoints());
        }
    }
}
