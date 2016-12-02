package edu.opendev.blackjack;

import edu.opendev.blackjack.players.Player;

import java.util.Scanner;

public class Validation {

    public boolean isDigit(String userInput) {
        if (userInput == null ||
                userInput.length() == 0) {
            return false;
        }

        for (int i = 0; i < userInput.length(); i++) {
            if (!Character.isDigit(userInput.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void inputBetPrice(Player player) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.println("\nНа вашем счету " + player.getBalance() +
                    " $, какую сумму будете ставить?");
            userInput = scanner.nextLine();

            if (isDigit(userInput)) {
                player.setBetPrice(Double.parseDouble(userInput));
            } else {
                System.out.println("\nВведены некорректные данные."
                        + " \nСумма ставки должна быть положительным числом"
                        + " и не содержать строковых или пустых значений.");
            }

            if (!player.isBetPriceCorrect() && isDigit(userInput)) {
                System.out.println("\nСумма ставки не корректна."
                        + " Попробуйте ещё раз.");
            }
        } while (!isDigit(userInput) ||
                !player.isBetPriceCorrect());
    }
}

