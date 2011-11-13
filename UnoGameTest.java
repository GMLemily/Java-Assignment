import java.util.ArrayList;

/**
 * The test class UnoGameTest.
 *
 * @author  Malcolm Ryan
 * @version 26 September 2011
 */
public class UnoGameTest extends junit.framework.TestCase
{
    private ArrayList<Card> deck;
    private Card[][] cards;

    protected void setUp()
    {
        deck = new ArrayList<Card>();

        // create a bunch of cards but don't but them in the deck.
        cards = new Card[Card.COLOUR_NAMES.length][10];
        
        for (int c = 1; c <= 4; c++) {
            for (int i = 1; i <= 9; i++) {
                cards[c][i] = new Card(c, i);
            }
        }
    }

    public void testCreateStandard() {
        // create a default deck with just the numbers
        UnoGame game = new UnoGame(2);
        
        assertEquals(36, game.getDeck().size());
        assertEquals(0, game.getPile().size());
        assertEquals(2, game.getPlayers().size());
    }

    public void testCreateCustom1() {
        // create an empty deck
        UnoGame game = new UnoGame(deck, 3);
        
        assertEquals(0, game.getDeck().size());
        assertEquals(0, game.getPile().size());
        assertEquals(3, game.getPlayers().size());
    }

    public void testCreateCustom2() {
        // create a custom deck
        
        deck.add(cards[Card.COLOUR_RED][1]);
        deck.add(cards[Card.COLOUR_RED][2]);
        deck.add(cards[Card.COLOUR_GREEN][1]);
        UnoGame game = new UnoGame(deck, 3);
        
        assertEquals(3, game.getDeck().size());
        assertTrue(game.getDeck().contains(cards[Card.COLOUR_RED][1]));
        assertTrue(game.getDeck().contains(cards[Card.COLOUR_RED][2]));
        assertTrue(game.getDeck().contains(cards[Card.COLOUR_GREEN][1]));
        assertEquals(0, game.getPile().size());
        assertEquals(3, game.getPlayers().size());
    }
   
    public void testDrawCard1() {
        
        deck.add(cards[Card.COLOUR_RED][1]);
        deck.add(cards[Card.COLOUR_RED][2]);
        deck.add(cards[Card.COLOUR_GREEN][1]);
        UnoGame game = new UnoGame(deck, 2);

        assertEquals(3, game.getDeck().size());
        assertEquals(0, game.getPile().size());
       
        Player player0 = game.getPlayers().get(0);
        assertEquals(0, player0.getCards().size());        
        game.draw(player0, 1);
        assertEquals(1, player0.getCards().size());        
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_RED][1]));

        assertEquals(2, game.getDeck().size());
        assertTrue(game.getDeck().contains(cards[Card.COLOUR_RED][2]));
        assertTrue(game.getDeck().contains(cards[Card.COLOUR_GREEN][1]));
        assertEquals(0, game.getPile().size());
        
        game.draw(player0, 1);
        assertEquals(2, player0.getCards().size());        
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_RED][1]));
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_RED][2]));

        assertEquals(1, game.getDeck().size());
        assertTrue(game.getDeck().contains(cards[Card.COLOUR_GREEN][1]));
        assertEquals(0, game.getPile().size());
        
        game.draw(player0, 1);
        assertEquals(3, player0.getCards().size());        
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_RED][1]));
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_RED][2]));
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_GREEN][1]));

        assertEquals(0, game.getDeck().size());
        assertEquals(0, game.getPile().size());

        // can't draw any more
        game.draw(player0, 1);
        assertEquals(3, player0.getCards().size());        
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_RED][1]));
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_RED][2]));
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_GREEN][1]));

        assertEquals(0, game.getDeck().size());
        assertEquals(0, game.getPile().size());

    }
    
    
    public void testDrawCard3() {
        deck.add(cards[Card.COLOUR_RED][1]);
        deck.add(cards[Card.COLOUR_RED][2]);
        deck.add(cards[Card.COLOUR_GREEN][1]);
        UnoGame game = new UnoGame(deck, 2);
        
        assertEquals(3, game.getDeck().size());
        assertEquals(0, game.getPile().size());
        
        // draw 3 cards at once
        Player player0 = game.getPlayers().get(0);
        game.draw(player0, 3);
        assertEquals(3, player0.getCards().size());        
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_RED][1]));
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_RED][2]));
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_GREEN][1]));

        assertEquals(0, game.getDeck().size());
        assertEquals(0, game.getPile().size());

    }


    public void testDrawCard4() {
        deck.add(cards[Card.COLOUR_RED][1]);
        deck.add(cards[Card.COLOUR_RED][2]);
        deck.add(cards[Card.COLOUR_GREEN][1]);
        UnoGame game = new UnoGame(deck, 2);

        // attempt to draw 5 cards at once
        Player player0 = game.getPlayers().get(0);
        game.draw(player0, 5);
        assertEquals(3, player0.getCards().size());        
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_RED][1]));
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_RED][2]));
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_GREEN][1]));

        assertEquals(0, game.getDeck().size());
        assertEquals(0, game.getPile().size());
    }

    public void testDrawCard5() {
        
        // shuffle in the pile if necessary
        
        deck.add(cards[Card.COLOUR_RED][1]);
        deck.add(cards[Card.COLOUR_RED][2]);
        deck.add(cards[Card.COLOUR_GREEN][1]);
        UnoGame game = new UnoGame(deck, 2);
        
        // stack the pile
        ArrayList<Card> pile = game.getPile();
        pile.add(cards[Card.COLOUR_BLUE][3]);
        pile.add(cards[Card.COLOUR_BLUE][5]);
        
        assertEquals(3, game.getDeck().size());
        assertEquals(2, game.getPile().size());

        // attempt to draw 5 cards at once
        Player player0 = game.getPlayers().get(0);
        game.draw(player0, 5);
        assertEquals(4, player0.getCards().size());        
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_RED][1]));
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_RED][2]));
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_GREEN][1]));
        assertTrue(player0.getCards().contains(cards[Card.COLOUR_BLUE][5]));

        assertEquals(0, game.getDeck().size());
        // the top card of the pile is kept
        assertEquals(1, game.getPile().size());
        assertTrue(game.getPile().contains(cards[Card.COLOUR_BLUE][3]));
    }
    
    public void testDealCards() {
        UnoGame game = new UnoGame(2);
        game.dealCards();
        
        assertEquals(2, game.getPlayers().size());
        Player player0 = game.getPlayers().get(0);
        Player player1 = game.getPlayers().get(1);
        
        assertEquals(5, player0.getCards().size());
        assertEquals(5, player1.getCards().size());
        assertEquals(25, game.getDeck().size());
        assertEquals(1, game.getPile().size());
        
        assertEquals(player0, game.getCurrentPlayer());
        assertEquals(1, game.getPlayDirection());
    }


    public void testReset() {
        UnoGame game = new UnoGame(2);
        game.dealCards();
        
        assertEquals(2, game.getPlayers().size());
        Player player0 = game.getPlayers().get(0);
        Player player1 = game.getPlayers().get(1);
        
        assertEquals(5, player0.getCards().size());
        assertEquals(5, player1.getCards().size());
        assertEquals(25, game.getDeck().size());
        assertEquals(1, game.getPile().size());
        
        game.reset();
        assertEquals(0, player0.getCards().size());
        assertEquals(0, player1.getCards().size());
        assertEquals(36, game.getDeck().size());
        assertEquals(0, game.getPile().size());        
    }
    
    public void testGoToNextPlayer() {
        UnoGame game = new UnoGame(3);
        game.dealCards();

        assertEquals(3, game.getPlayers().size());
        Player player0 = game.getPlayers().get(0);
        Player player1 = game.getPlayers().get(1);
        Player player2 = game.getPlayers().get(2);

        assertEquals(player0, game.getCurrentPlayer());
        assertEquals(1, game.getPlayDirection());

        game.goToNextPlayer();
        assertEquals(player1, game.getCurrentPlayer());
        
        game.goToNextPlayer();
        assertEquals(player2, game.getCurrentPlayer());

        game.goToNextPlayer();
        assertEquals(player0, game.getCurrentPlayer());

    }

    public void testSetPlayDirection() {
        UnoGame game = new UnoGame(3);
        game.dealCards();

        assertEquals(3, game.getPlayers().size());
        Player player0 = game.getPlayers().get(0);
        Player player1 = game.getPlayers().get(1);
        Player player2 = game.getPlayers().get(2);

        game.setPlayDirection(-1);
        assertEquals(-1, game.getPlayDirection());

        assertEquals(player0, game.getCurrentPlayer());
        
        game.goToNextPlayer();
        assertEquals(player2, game.getCurrentPlayer());
        
        game.goToNextPlayer();
        assertEquals(player1, game.getCurrentPlayer());

        game.goToNextPlayer();
        assertEquals(player0, game.getCurrentPlayer());

        game.setPlayDirection(1);
        assertEquals(1, game.getPlayDirection());

        game.goToNextPlayer();
        assertEquals(player1, game.getCurrentPlayer());
        
        game.goToNextPlayer();
        assertEquals(player2, game.getCurrentPlayer());

        game.goToNextPlayer();
        assertEquals(player0, game.getCurrentPlayer());
    }

    
    public void testGetNextPlayer() {
        
        UnoGame game = new UnoGame(3);
        game.dealCards();

        assertEquals(3, game.getPlayers().size());
        Player player0 = game.getPlayers().get(0);
        Player player1 = game.getPlayers().get(1);
        Player player2 = game.getPlayers().get(2);

        assertEquals(player0, game.getCurrentPlayer());
        assertEquals(player0, game.getNextPlayer(0));
        assertEquals(player1, game.getNextPlayer(1));
        assertEquals(player2, game.getNextPlayer(2));
        assertEquals(player0, game.getNextPlayer(3));

        game.setPlayDirection(-1);
        assertEquals(player0, game.getNextPlayer(0));
        assertEquals(player1, game.getNextPlayer(2));
        assertEquals(player2, game.getNextPlayer(1));
        assertEquals(player0, game.getNextPlayer(3));
        
        game.setPlayDirection(1);
        game.goToNextPlayer();
        assertEquals(player1, game.getNextPlayer(0));
        assertEquals(player2, game.getNextPlayer(1));
        assertEquals(player0, game.getNextPlayer(2));
        assertEquals(player1, game.getNextPlayer(3));
        
        game.setPlayDirection(-1);
        assertEquals(player1, game.getNextPlayer(0));
        assertEquals(player0, game.getNextPlayer(1));
        assertEquals(player2, game.getNextPlayer(2));
        assertEquals(player1, game.getNextPlayer(3));
    }
    
    public void testPlayTurn() {
        // stack the deck
        
        // player 0's cards
        deck.add(cards[Card.COLOUR_RED][2]);
        deck.add(cards[Card.COLOUR_GREEN][2]);
        deck.add(cards[Card.COLOUR_GREEN][3]);
        deck.add(cards[Card.COLOUR_YELLOW][3]);
        deck.add(cards[Card.COLOUR_YELLOW][8]);

        // player 1's cards
        deck.add(cards[Card.COLOUR_BLUE][2]);
        deck.add(cards[Card.COLOUR_BLUE][4]);
        deck.add(cards[Card.COLOUR_BLUE][5]);
        deck.add(cards[Card.COLOUR_BLUE][6]);
        deck.add(cards[Card.COLOUR_YELLOW][7]);

        // the starting card
        deck.add(cards[Card.COLOUR_RED][1]);

        // the deck
        deck.add(cards[Card.COLOUR_RED][7]);
        
        UnoGame game = new UnoGame(deck, 2);
        assertEquals(2, game.getPlayers().size());
        Player player0 = game.getPlayers().get(0);
        Player player1 = game.getPlayers().get(1);

        game.dealCards();
        assertEquals(cards[Card.COLOUR_RED][1], game.getPile().get(0));
        assertEquals(5, player0.getCards().size());
        assertEquals(5, player1.getCards().size());
        assertEquals(1, game.getDeck().size());
        assertEquals(1, game.getPile().size());
        
        assertEquals(player0, game.getCurrentPlayer());
        // player 0 plays R2 on R1
        assertNull(game.playTurn());
        assertEquals(cards[Card.COLOUR_RED][2], game.getPile().get(0));
        assertEquals(4, player0.getCards().size());
        assertEquals(5, player1.getCards().size());
        assertEquals(1, game.getDeck().size());
        assertEquals(2, game.getPile().size());
        
        assertEquals(player1, game.getCurrentPlayer());
        // player 1 plays B2 on R2
        assertNull(game.playTurn());
        assertEquals(cards[Card.COLOUR_BLUE][2], game.getPile().get(0));
        assertEquals(4, player0.getCards().size());
        assertEquals(4, player1.getCards().size());
        assertEquals(1, game.getDeck().size());
        assertEquals(3, game.getPile().size());
        
        assertEquals(player0, game.getCurrentPlayer());
        // player 0 plays G2 on B2
        assertNull(game.playTurn());
        assertEquals(cards[Card.COLOUR_GREEN][2], game.getPile().get(0));
        assertEquals(3, player0.getCards().size());
        assertEquals(4, player1.getCards().size());
        assertEquals(1, game.getDeck().size());
        assertEquals(4, game.getPile().size());
        
        assertEquals(player1, game.getCurrentPlayer());
        // player 1 must draw 
        assertNull(game.playTurn());
        assertEquals(cards[Card.COLOUR_GREEN][2], game.getPile().get(0));
        assertEquals(3, player0.getCards().size());
        assertEquals(5, player1.getCards().size());
        assertEquals(0, game.getDeck().size());
        assertEquals(4, game.getPile().size());
    
        assertEquals(player0, game.getCurrentPlayer());
        // player 0 plays G3 on G2
        assertNull(game.playTurn());
        assertEquals(cards[Card.COLOUR_GREEN][3], game.getPile().get(0));
        assertEquals(2, player0.getCards().size());
        assertEquals(5, player1.getCards().size());
        assertEquals(0, game.getDeck().size());
        assertEquals(5, game.getPile().size());

        assertEquals(player1, game.getCurrentPlayer());
        // player 1 must draw again, shuffling the pile into the deck
        assertNull(game.playTurn());
        assertEquals(cards[Card.COLOUR_GREEN][3], game.getPile().get(0));
        assertEquals(2, player0.getCards().size());
        assertEquals(6, player1.getCards().size());
        assertEquals(3, game.getDeck().size());
        assertEquals(1, game.getPile().size());

        assertEquals(player0, game.getCurrentPlayer());
        // player plays Y3 on G3
        assertNull(game.playTurn());
        assertEquals(cards[Card.COLOUR_YELLOW][3], game.getPile().get(0));
        assertEquals(1, player0.getCards().size());
        assertEquals(6, player1.getCards().size());
        assertEquals(3, game.getDeck().size());
        assertEquals(2, game.getPile().size());

        assertEquals(player1, game.getCurrentPlayer());
        // player 1 plays Y7 on Y3
        assertNull(game.playTurn());
        assertEquals(cards[Card.COLOUR_YELLOW][7], game.getPile().get(0));
        assertEquals(1, player0.getCards().size());
        assertEquals(5, player1.getCards().size());
        assertEquals(3, game.getDeck().size());
        assertEquals(3, game.getPile().size());

    
        assertEquals(player0, game.getCurrentPlayer());
        // player 0 plays Y8 on Y7 and wins
        assertEquals(player0, game.playTurn());
        assertEquals(cards[Card.COLOUR_YELLOW][8], game.getPile().get(0));
        assertEquals(0, player0.getCards().size());
        assertEquals(5, player1.getCards().size());
        assertEquals(3, game.getDeck().size());
        assertEquals(4, game.getPile().size());

    }

    public void testPlay() {
        // stack the deck

        // player 0's cards
        deck.add(cards[Card.COLOUR_RED][2]);
        deck.add(cards[Card.COLOUR_GREEN][2]);
        deck.add(cards[Card.COLOUR_GREEN][3]);
        deck.add(cards[Card.COLOUR_YELLOW][3]);
        deck.add(cards[Card.COLOUR_YELLOW][8]);

        // player 1's cards
        deck.add(cards[Card.COLOUR_BLUE][2]);
        deck.add(cards[Card.COLOUR_BLUE][4]);
        deck.add(cards[Card.COLOUR_BLUE][5]);
        deck.add(cards[Card.COLOUR_BLUE][6]);
        deck.add(cards[Card.COLOUR_YELLOW][7]);

        // the starting card
        deck.add(cards[Card.COLOUR_RED][1]);

        // the deck
        deck.add(cards[Card.COLOUR_RED][7]);
        
        UnoGame game = new UnoGame(deck, 2);
        assertEquals(2, game.getPlayers().size());
        Player player0 = game.getPlayers().get(0);
        Player player1 = game.getPlayers().get(1);

        // player0 should win
        assertEquals(player0, game.play());
    }
    
}
