package com.BankAccount.java;

import java.util.Arrays;
import java.util.Random;

public class DeckOfCards {
    private static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private static final int TOTAL_CARDS = SUITS.length * RANKS.length;

    public static void main(String[] args) {
        String[][] deck = initializeDeck();
        shuffleDeck(deck);

        String[][] players = distributeCards(deck);
        printPlayersCards(players);
    }

    private static String[][] initializeDeck() {
        String[][] deck = new String[TOTAL_CARDS][2];
        int index = 0;
        for (String suit : SUITS) {
            for (String rank : RANKS) {
                deck[index][0] = rank;
                deck[index][1] = suit;
                index++;
            }
        }
        return deck;
    }

    private static void shuffleDeck(String[][] deck) {
        Random rand = new Random();
        for (int i = TOTAL_CARDS - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String[] temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    private static String[][] distributeCards(String[][] deck) {
        String[][] players = new String[4][9];
        int cardIndex = 0;
        for (int player = 0; player < 4; player++) {
            for (int card = 0; card < 9; card++) {
                players[player][card] = deck[cardIndex][0] + " of " + deck[cardIndex][1];
                cardIndex++;
            }
        }
        return players;
    }

    private static void printPlayersCards(String[][] players) {
        for (int player = 0; player < 4; player++) {
            System.out.println("Player " + (player + 1) + " Cards: ");
            for (int card = 0; card < 9; card++) {
                System.out.println(players[player][card]);
            }
            System.out.println();
        }
    }
}