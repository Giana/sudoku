package main.Code;

public class player
{
    static int N = 9;     // Length of row/column

    grid playerGrid;      // player's grid
    grid referenceGrid;   // Generated grid

    /**
     * This is the parameterized constructor for
     * player. It sets the referenceGrid variable
     * to what is passed into the method. It
     * instantiates the playerGrid variable and
     * links every tile in playerGrid to the
     * corresponding tile in referenceGrid. If
     * a tile in referenceGrid is not fixed, its
     * corresponding tile in playerGrid is cleared.
     * @param referenceGrid Generated grid to be
     *                      referenced
     */
    public player(grid referenceGrid)
    {
        this.referenceGrid = referenceGrid;
        playerGrid = new grid();

        // Iterate over rows
        for(int i = 0; i < N; i++)
        {
            // Iterate over columns
            for(int j = 0; j < N; j++)
            {
                playerGrid.getTiles()[i][j] = new tile(referenceGrid.getTiles()[i][j]);   // Link tiles

                // Current tile is not fixed
                if(!referenceGrid.getTiles()[i][j].getFixed())
                {
                    playerGrid.getTiles()[i][j].clearTile();   // Set value to 0 (needs to be guessed by player)
                }
            }
        }
    }

    /**
     * @return grid player's grid variable
     */
    public grid getPlayerGrid() { return this.playerGrid; }

    /**
     * This method is used to determine if
     * playerGrid is a valid victory or not.
     * @return boolean If playerGrid is a
     *                 valid victory
     */
    public boolean determineVictory()
    {
        // Iterate over rows
        for(int i = 0; i < N; i++)
        {
            // Iterate over columns
            for(int j = 0; j < N; j++)
            {
                // Current tile violates subgrid rules
                if(playerGrid.violation(playerGrid.getTiles()[i][j]))
                {
                    // playerGrid is not a victory
                    return false;
                }
            }
        }

        // playerGrid is a victory
        return true;
    }
}
