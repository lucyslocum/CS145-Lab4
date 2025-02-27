public class CardGameTest{
    public static void main(String[] args) {
            int playerScore = 0;
            int compScore = 0;
            CardDealTest myDeck = new CardDealTest();
            
            myDeck.shuffle();
            myDeck.instructions();
            myDeck.computerDeal();
            myDeck.playerDeal();
            myDeck.compHit(compScore);
            myDeck.hit(playerScore);
            myDeck.gameResults(playerScore, compScore);
        
        }
    
}
