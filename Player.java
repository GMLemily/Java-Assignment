import java.util.ArrayList;
/**
 * A Player in the Uno Game.
 * 
 * @author Malcolm Ryan
 * @version 23 September 2010
 */
public class Player
{
    private String myName;
    private ArrayList<Card> myCards;
        
    /**
     * Create a player. The player's hand is initially empty.
     * 
     * @params The player's name
     */
    public Player(String name) {
        myName = name;
        myCards = new ArrayList<Card>();            
    }
    
    /**
     * Access the player's name
     */
    public String getName() {
        return myName;
    }
    
    /**
     * Access the player's hand
     */
    public ArrayList<Card> getCards() {
        return myCards;
    }

    /**
     * Clear the player's hand
     */
    public void clearCards() {
        myCards.clear();
    }
    
    /**
     * Add a card to the player's hand
     * 
     * @param card The card to add.
     */
    public void gainCard(Card card) {
        myCards.add(card);
    }
    
    /**
     * Choose a card to play.
     * 
     * @param pileCard The card on the top of the pile to be played on.
     * @return A card that can be played on the pileCard, or null if none is available
     */
    public Card playCard(Card pileCard) {
        
        // play the first playable card we can find
        for (Card c : myCards) {
            if (c.canPlayOn(pileCard)) {
                myCards.remove(c);
                return c;
            }
        }
        
        // no card to play
        return null;
        
    }
    
    /**
     * The string representation of the player
     */
    public String toString() {
        return myName;
    }
}



