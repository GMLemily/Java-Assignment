
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DrawCardTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DrawCardTest extends junit.framework.TestCase
{
    public void testConstruct() {
        DrawCard redDraw2 = new DrawCard(Card.COLOUR_RED, 2);
        
        assertEquals(Card.COLOUR_RED, redDraw2.getColour());
        assertEquals(2, redDraw2.getNDraw());
    }
    
    public void testCanPlayOn() {
        //create a draw card
        
        DrawCard redDraw2 = new DrawCard(Card.COLOUR_RED, 2);
        
        // create another 4 normal cards
        
        Card card1 = new Card(Card.COLOUR_BLUE, 5);
        Card card2 = new Card(Card.COLOUR_BLUE,2);
        
        //card 1 and card 2 should yield false on the method
        assertFalse(redDraw2.canPlayOn(card1));
        assertFalse(redDraw2.canPlayOn(card2));
        
        Card card3 = new Card(Card.COLOUR_RED, 5);
        Card card4 = new Card(Card.COLOUR_RED,2);
        
        // card 3 and card 4 should gives true
        
        assertTrue(redDraw2.canPlayOn(card3));
        assertTrue(redDraw2.canPlayOn(card4));
    
    }

    public void testPlay()   {
        // stack the deck
        ArrayList <Card> deck = new ArrayList();

        DrawCard redDraw2 = new DrawCard(Card.COLOUR_RED, 2);

        // player 0's cards - one playable and 4 unplayable
        deck.add(redDraw2);
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));

        // player 1's cards - it doesn't matter what these are
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));
        deck.add(new Card(Card.COLOUR_BLUE, 5));

        // the card that becomes the initial pile
        // chosen to allow player 0 to play the red draw 2

        deck.add(new Card(Card.COLOUR_RED, 1));

        // the draw pile -- add enough cards for player1 to draw 2

        deck.add(new Card(Card.COLOUR_GREEN, 2));
        deck.add(new Card(Card.COLOUR_GREEN, 3));
        deck.add(new Card(Card.COLOUR_GREEN, 4));

        // create a game using this deck (unshuffled)
        UnoGame game = new UnoGame(deck, 2);
        game.dealCards();

        // get the two players
        Player player0 = game.getPlayers().get(0);
        Player player1 = game.getPlayers().get(1);

        // player 0 plays a turn
        game.playTurn();

        // check the card played is the red draw 2
        assertEquals(redDraw2, game.getPile().get(0));

        // check how many cards each player has
        assertEquals(4, player0.getCards().size());
        assertEquals(7, player1.getCards().size());

        // check that we skipped player1's turn
        assertEquals(player0, game.getCurrentPlayer());        

    }
}
