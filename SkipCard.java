
/**
 * Write a description of class SkipCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SkipCard extends Card
{
    // instance variables - replace the example below with your own
    private int nSkip;
  
    /**
     * Constructor for objects of class SkipCard
     */
    public SkipCard(int colour, int nSkip)
    {
        super(colour, "Skip");
        this.nSkip = nSkip;
    }
    
    // getter method
    public int getNSkip() {
        return nSkip;
    }
    
     /**
     * Take any effect when the card is played.
     * 
     * SkipCard effect: it skips the nSkip player
     * also be skipped
     */
    public void play (UnoGame game) {
        //skip the nSkip player
        for (int i = 0; i<this.nSkip; i++) {
            game.goToNextPlayer();
        }
    }
}
