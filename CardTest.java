/**
 * The test class CardTest.
 *
 * @author  Malcolm Ryan
 * @version 26 September 2011
 */
public class CardTest extends junit.framework.TestCase
{
    public void testConstruct() {
        
        // test the number constructor
        Card card1 = new Card(Card.COLOUR_GREEN, 1);
        
        assertEquals(Card.COLOUR_GREEN, card1.getColour());
        assertEquals("1", card1.getSymbol());

        // test the symbol constructor
        Card card2 = new Card(Card.COLOUR_RED, "7");
        
        assertEquals(Card.COLOUR_RED, card2.getColour());
        assertEquals("7", card2.getSymbol());

    }

    public void testSetColour() {
        
        Card card = new Card(Card.COLOUR_GREEN, 1);        
        assertEquals(Card.COLOUR_GREEN, card.getColour());
        card.setColour(Card.COLOUR_RED);
        assertEquals(Card.COLOUR_RED, card.getColour());
        
    }

    public void testCanPlayOn() {

        Card blue1 = new Card(Card.COLOUR_BLUE, 1);        
        Card blue1b = new Card(Card.COLOUR_BLUE, 1);        
        Card blue7 = new Card(Card.COLOUR_BLUE, 7);        
        Card yellow7 = new Card(Card.COLOUR_YELLOW, 7);        
        
        // same colour and number
        assertTrue(blue1.canPlayOn(blue1b));
        assertTrue(blue1b.canPlayOn(blue1));        
        
        // same colour
        assertTrue(blue1.canPlayOn(blue7));
        assertTrue(blue7.canPlayOn(blue1));

        // same nunmber
        assertTrue(blue7.canPlayOn(yellow7));
        assertTrue(yellow7.canPlayOn(blue7));
    
        // no match
        assertFalse(blue1.canPlayOn(yellow7));
        assertFalse(yellow7.canPlayOn(blue1));

    }
    
    
}
