import javax.swing.*;
import java.util.ArrayList;

/**
 * An interface for a data structure, DB API, file reader, etc. that
 * tells us how many games were played that took some number of guesses
 * (e.g., How many games took 8 guesses? --> 17)
 *
 * You can edit this file, but the two abstract methods listed below must remain
 */
public abstract class GameStats {
    /**
     * @return the number of games played that took numGuesses
     */
    public abstract int numGames(int numGuesses);

    /**
     * @return the maximum number of guesses that any game took
     */
    public abstract int maxNumGuesses();

    /**
     * Generic update for stats screen
     * @param bin_edges
     */
    public ArrayList<String> getGuessList(int[] bin_edges) {
        ArrayList<String> labels = new ArrayList<String>();

        for(int binIndex=0; binIndex<bin_edges.length; binIndex++){
            final int lowerBound = bin_edges[binIndex];
            int numGames = 0;

            if(binIndex == bin_edges.length-1){
                // last bin
                // Sum all the results from lowerBound on up
                for(int numGuesses=lowerBound; numGuesses<this.maxNumGuesses(); numGuesses++){
                    numGames += this.numGames(numGuesses);
                }
            }
            else{
                int upperBound = bin_edges[binIndex+1];
                for(int numGuesses=lowerBound; numGuesses <= upperBound; numGuesses++) {
                    numGames += this.numGames(numGuesses);
                }
            }

            labels.add(Integer.toString(numGames));
        }
        return labels;
    }
}
