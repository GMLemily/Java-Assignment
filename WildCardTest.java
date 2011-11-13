
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WildCardTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WildCardTest extends junit.framework.TestCase
{
   public void testConstruct(){
       Card wildCard = new WildCard();
       
       assertEquals(Card.COLOUR_NONE, wildCard.getColour());
       assertEquals("Wild",wildCard.getSymbol());
   }
    
   public void testCanPlayOn() {
       // create a wild card
       WildCard wildCard = new WildCard();
       
       // create another 3 normal cards with dffirent colour and number(or symbol)
       Card card1 = new Card(Card.COLOUR_BLUE, 3);
       Card card2 = new Card(Card.COLOUR_RED,2);
       Card card3 = new Card(Card.COLOUR_RED,"abc");
       
       assertTrue(wildCard.canPlayOn(card1));
       assertTrue(wildCard.canPlayOn(card2));
       
       //test if the wild card has been changed colour, i.e. red
       assertTrue(card3.canPlayOn(wildCard));
       
   }

   public void testPlay()   {
       ArrayList <Card> deck = new ArrayList();
       
       // construct the wild card
       WildCard wildCard = new WildCard();
       
       // player 0's cards
       deck.add(new Card(Card.COLOUR_BLUE, 5));
       
       //add the wild card
       deck.add(wildCard);
       
       deck.add(new Card(Card.COLOUR_BLUE, 5));
       deck.add(new Card(Card.COLOUR_BLUE, 5));
       deck.add(new Card(Card.COLOUR_BLUE, 5));
       
       
       
       //the card that becomes the intial pile
       deck.add(new Card(Card.COLOUR_GREEN, 2));
       
       // create a game using this deck (unshuffled)
       UnoGame game = new UnoGame(deck, 1);
       game.dealCards();
       
       // get the player
       Player player0 = game.getPlayers().get(0);
       
       game.playTurn();
       
       // player 0 should have used the wild card..
       assertEquals(4, player0.getCards().size());
       
       // check the card played is the wild card
       assertEquals(wildCard, game.getPile().get(0));
       
       // check the wild card's colour
       assertEquals(Card.COLOUR_GREEN,game.getPile().get(0).getColour());
         
    }
}
