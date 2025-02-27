import java.security.SecureRandom;
import java.util.*;

public class CardDealTest{
    Scanner input = new Scanner(System.in);
    private Stack <CardData> deck;
    private Random random;
    private int playerScore;
    private int compScore;

    public CardDealTest() {
        String[] ranks = {"2", "3", "4", "5", "6",
            "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        deck = new Stack <>();
        List deckInteract = new ArrayList<>();
        random = new Random();
        for (String suit : suits) {
            for (String rank : ranks) {
                CardData card = (new CardData(rank, suit));
                deckInteract.add(card);
            }
        }
        deck.addAll(deckInteract);
    }
    public void shuffle() {
        Collections.shuffle(deck, random);
    }
    public CardData dealCard() {
        if (deck.size() > 0) {
            return deck.pop();
        }
        else {
            return null;
        }
    }
    public int findCardValue(CardData card) {
        int cardValue = 0;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).getRank().equals("Ace")) {
                cardValue = 11;
            }
            else if (deck.get(i).getRank().equals("Jack") || deck.get(i).getRank().equals("Queen") || deck.get(i).getRank().equals("King")) {
                cardValue = 10;
            }
            else {
                cardValue = Integer.parseInt(deck.get(i).getRank());
            }
        }
        return cardValue;
    }
    public void instructions() {
        System.out.println("Welcome to the game of BlackJack!");
        System.out.println("Both you and the computer will be dealt two cards.");
        System.out.println("You will be able to see one of the computer's cards.");
        System.out.println("You can draw another card if you wish, as can the computer.");
        System.out.println("When you are finished, your score and the computer's score will be shown.");
        System.out.println("The player with the highest score wins, unless they score over 21, in which case they lose.");
    }
    public int playerDeal() {
        playerScore = 0;
        CardData playerCard1 = dealCard();
        System.out.println("Card 1: " + playerCard1);
        System.out.println("Card Value: " + findCardValue(playerCard1));
        playerScore += findCardValue(playerCard1);
        CardData playerCard2 = dealCard();
        System.out.println("Card 2: " + playerCard2);
        System.out.println("Card Value: " + findCardValue(playerCard2));
        playerScore += findCardValue(playerCard2);
        System.out.println("Current Score: " + playerScore);
        return playerScore;
    }
    public int computerDeal() {
        compScore = 0;
        CardData compCard1 = dealCard();
        System.out.println("Dealer's Card: " + compCard1);
        compScore += findCardValue(compCard1);
        CardData compCard2 = dealCard();
        compScore += findCardValue(compCard2);
        return compScore;
    }
    public int hit(int playerScore) {
        System.out.println("Draw another card? (yes or no): ");
        String hit = input.next();
        if (hit.equals("yes")) {
            CardData playerCard = dealCard();
            System.out.println("Card: " + playerCard);
            System.out.println("Card Value: " + findCardValue(playerCard));
            playerScore += findCardValue(playerCard);
            System.out.println("Current Score: " + playerScore);
        hit(playerScore);
        }
        return playerScore;
    }
    public int compHit(int compScore) {
        if (compScore < 16) {
            CardData compCard = dealCard();
            compScore += findCardValue(compCard);
            compHit(compScore);
        }
        return compScore;
    }
    public void gameResults(int playerScore, int compScore) {
        System.out.println("Your Score: " + playerScore);
        System.out.println("Dealer's Score: " + compScore);
        if (playerScore > 21) {
            System.out.println("You busted! Dealer wins.");
        }
        else if (compScore > 21) {
            System.out.println("Dealer busted! You win.");
        }
        else if (playerScore > compScore) {
            System.out.println("You win!");
        }
        else if (compScore > playerScore) {
            System.out.println("Dealer wins.");
        }
        else {
            System.out.println("It's a tie.");
        }
    }

}
