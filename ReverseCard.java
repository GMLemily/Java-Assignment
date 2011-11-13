
/**
 * Write a description of class ReverseCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ReverseCard extends Card
{
 
    /**
     * Constructor for objects of class ReverseCard
     */
    public ReverseCard(int colour)
    {
        super(colour,"Reverse");
    }

    /**
     * Check whether this card can be played on the given card. 
     * @param The card to be played on
     * @returns true if either the colours or the symbols match
     */
    public boolean canPlayOn(Card card) {        
        return (card.myColour == myColour);        
    }
    
    
     /**
     * Take any effect when the card is played.
     * 
     * Drawcard effect: the next player draws nDraw cards and their turn will
     * also be skipped
     */
    public void play (UnoGame game) {
        game.setPlayDirection(-1);
    }
}
