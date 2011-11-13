
/**
 * Write a description of class WildCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WildCard extends Card
{

    /**
     * Constructor for objects of class WildCard
     */
    public WildCard(){
        super(COLOUR_NONE,"Wild");
    }
    
    /**
     * Check whether this card can be played on the given card. 
     * @param The card to be played on
     * @returns true if either the colours or the symbols match
     */
    public boolean canPlayOn(Card card)  { 
        //change to current card's colour
        this.setColour(card.getColour());
        // wild card: it should always return true
        return true;       
    }
    
}
