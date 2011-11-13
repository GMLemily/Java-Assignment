
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WildDrawCardTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WildDrawCardTest extends junit.framework.TestCase
{
    public void testConstruct() {
        WildDrawCard wildDraw = new WildDrawCard(2);
        
        assertEquals(Card.COLOUR_NONE,wildDraw.getColour());
        assertEquals("WildDraw",wildDraw.getSymbol());
        
        assertEquals(2, wildDraw.getNDraw());
    }
    public void testCanPlayOn() {
       // create a wild draw card
       WildCard wildDraw = new WildCard();
       
       // create another 3 normal cards with dffirent colour and number(or symbol)
       Card card1 = new Card(Card.COLOUR_BLUE, 3);
       Card card2 = new Card(Card.COLOUR_RED,2);
       Card card3 = new Card(Card.COLOUR_RED,"abc");
       
       assertTrue(wildDraw.canPlayOn(card1));
       assertTrue(wildDraw.canPlayOn(card2));
       
       //test if the wild card has been changed colour, i.e. red
       assertTrue(card3.canPlayOn(wildDraw));
       
    }

    public void testPlay()   {
        // stack the deck
        ArrayList <Card> deck = new ArrayList();

        WildDrawCard wildDraw = new WildDrawCard(2);

        // player 0's cards - one playable and 4 unplayable
        deck.add(wildDraw);
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
        // chosen to allow player 0 to play the wild draw card

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
        assertEquals(wildDraw, game.getPile().get(0));

        // check how many cards each player has
        assertEquals(4, player0.getCards().size());
        assertEquals(7, player1.getCards().size());

        // check that we skipped player1's turn
        assertEquals(player0, game.getCurrentPlayer()); 
       
    }
}
