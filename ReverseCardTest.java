
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ReverseCardTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ReverseCardTest extends junit.framework.TestCase
{
    public void testConstruct() {
        ReverseCard redReverse = new ReverseCard(Card.COLOUR_RED);
        // check colour
        assertEquals(Card.COLOUR_RED, redReverse.getColour());
        //check symbol
        assertEquals("Reverse", redReverse.getSymbol());
    }
    
    public void testCanPlayOn() {
       ReverseCard redReverse = new ReverseCard(Card.COLOUR_RED);
       
        // create another 3 normal cards
        
       Card card1 = new Card(Card.COLOUR_BLUE,5);
     
  
       //card 1 should yield false on the method
       assertEquals(false, redReverse.canPlayOn(card1));
 
        
       Card card3 = new Card(Card.COLOUR_RED, 5);
       Card card4 = new Card(Card.COLOUR_RED,2);
        
       // card 3 and card 4 should gives true
        
       assertTrue(redReverse.canPlayOn(card3));
       assertTrue(redReverse.canPlayOn(card4));
    }

    public void testPlay()   {
         // stack the deck
        ArrayList <Card> deck = new ArrayList();

        ReverseCard redReverse = new ReverseCard(Card.COLOUR_RED);

        // player 0's cards - one playable and 4 unplayable
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));

        // player 1's cards - it doesn't matter what these are
        deck.add(redReverse);
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        
         // player 2's cards - it doesn't matter what these are
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));

        // allow player 1 to use the red reverse card, as play 0 cannot play and skips
        deck.add(new Card(Card.COLOUR_RED, 2));
        deck.add(new Card(Card.COLOUR_GREEN, 1));


        // create a game using this deck (unshuffled)
        UnoGame game = new UnoGame(deck, 3);
        game.dealCards();
        
        // current game direction
        assertEquals(1, game.getPlayDirection());

        // get the two players
        Player player0 = game.getPlayers().get(0);
        Player player1 = game.getPlayers().get(1);
        Player player2 = game.getPlayers().get(2);
        
        // player 0 plays a turn
        game.playTurn();
        
  
        // player 1 plays a turn
        game.playTurn();
        // check the card played is the red reverse card after play 0's turn
        assertEquals(redReverse, game.getPile().get(0));
         
         // now we check the game direction again
        assertEquals(-1, game.getPlayDirection());
        
        // check the next player is player0
        assertEquals(player0, game.getCurrentPlayer());
        
        // check how many cards each player has
        assertEquals(6, player0.getCards().size());
        assertEquals(4, player1.getCards().size());
        assertEquals(5, player2.getCards().size());
        
       
        
        // checked the game is back to player 0's turn
        assertEquals(player0, game.getCurrentPlayer());
        
    }
    
}
