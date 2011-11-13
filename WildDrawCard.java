
/**
 * Write a description of class WildDrawCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WildDrawCard extends Card
{
    private int nDraw;
    /**
     * Constructor for objects of class WildDrawCard
     */
    public WildDrawCard(int nDraw)
    {
       super(COLOUR_NONE,"WildDraw");
       this.nDraw = nDraw;
    }
    public int getNDraw(){
        return nDraw;
    }
     /**
     * Check whether this card can be played on the given card. 
     * @param The card to be played on
     * @returns true if either the colours or the symbols match
     */
    public boolean canPlayOn(Card card) { 
        //change the wild draw card's colour
        this.setColour(card.getColour());
        // it should always return true;
        return true;       
    }
    
    
     /**
     * Take any effect when the card is played.
     * 
     * Wild Draw Card effect: the next player draws nDraw cards and their turn will
     * also be skipped
     */
    public void play (UnoGame game) {
        game.draw(game.getNextPlayer(1),nDraw);
        // the current player's turn will also be skipped
        game.goToNextPlayer();
    }
}
