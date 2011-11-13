

/**
 * The test class PlayerTest.
 *
 * @author  Malcolm Ryan
 * @version 26 September 2011
 */
public class PlayerTest extends junit.framework.TestCase
{

    private Player player;
    private Card red9;
    private Card red9b;
    private Card yellow7;
    private Card red7;
    private Card blue5;
    
    public void setUp() {
        player = new Player("Malcolm");
        red9 = new Card(Card.COLOUR_RED, 9);
        red9b = new Card(Card.COLOUR_RED, 9);
        yellow7 = new Card(Card.COLOUR_YELLOW, 7);
        red7 = new Card(Card.COLOUR_RED, 7);
        blue5 = new Card(Card.COLOUR_BLUE, 5);
    }

    public void testConstructor() {
        assertEquals("Malcolm", player.getName());
        assertTrue(player.getCards().isEmpty()); // hand is initially empty
    }
    
    public void testGainCard() {

        // hand is initially empty
        assertTrue(player.getCards().isEmpty()); 

        player.gainCard(red9);
        assertEquals(1, player.getCards().size());
        assertTrue(player.getCards().contains(red9));
        
        player.gainCard(yellow7);
        assertEquals(2, player.getCards().size());
        assertTrue(player.getCards().contains(red9));
        assertTrue(player.getCards().contains(yellow7));
     
        // check duplicates are allowed
        player.gainCard(red9b);
        assertEquals(3, player.getCards().size());
        assertTrue(player.getCards().contains(red9));
        assertTrue(player.getCards().contains(red9b));
        assertTrue(player.getCards().contains(yellow7));

    }
    
    public void testClearCards() {
        player.gainCard(yellow7);
        player.gainCard(red9);
        assertEquals(2, player.getCards().size());

        player.clearCards();
        assertTrue(player.getCards().isEmpty()); 
    }


    public void testPlayCard1() {
        // when the player has no cards, playCard should always return null
        assertNull(player.playCard(red9));
        assertNull(player.playCard(yellow7));
    }
    
    public void testPlayCard2() {
        // play on an identical card
        player.gainCard(red9);
        assertEquals(1, player.getCards().size());

        assertEquals(red9, player.playCard(red9b));        
        // once the card is played, it is lost
        assertEquals(0, player.getCards().size());
    }
    
    public void testPlayCard2b() {
        // play on same colour
        player.gainCard(red9);
        assertEquals(1, player.getCards().size());

        assertEquals(red9, player.playCard(red7));        
        // once the card is played, it is lost
        assertEquals(0, player.getCards().size());
    }

    public void testPlayCard2c() {
        // play on same number
        player.gainCard(red7);
        assertEquals(1, player.getCards().size());

        assertEquals(red7, player.playCard(yellow7));        
        // once the card is played, it is lost
        assertEquals(0, player.getCards().size());
    }
    
    public void testPlayCard3() {
        player.gainCard(yellow7);
        player.gainCard(red9);
        assertEquals(2, player.getCards().size());

        assertEquals(red9, player.playCard(red9b));        
        assertEquals(1, player.getCards().size());
        // the yellow 7 is kept
        assertTrue(player.getCards().contains(yellow7));
    }
    
   
    public void testPlayCard4() {
        // can't play
        
        player.gainCard(yellow7);
        player.gainCard(red9);
        assertEquals(2, player.getCards().size());

        assertNull(player.playCard(blue5));        
        assertEquals(2, player.getCards().size());
        // both cards kept
        assertTrue(player.getCards().contains(red9));
        assertTrue(player.getCards().contains(yellow7));
    }

}
