package main.Code;

public class player
{
    static int N = 9;

    grid playerGrid;
    grid actualGrid;

    public grid getPlayerGrid() { return this.playerGrid; }

    public void setPlayerGrid(grid playerGrid) { this.playerGrid = playerGrid; }

    public player(grid actualGrid)
    {
        this.actualGrid = actualGrid;
        playerGrid = new grid();

        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                playerGrid.getTiles()[i][j] = new tile(actualGrid.getTiles()[i][j]);

                // Current tile is not fixed
                if(!actualGrid.getTiles()[i][j].getFixed())
                {
                    playerGrid.getTiles()[i][j].clearTile();
                }
            }
        }
    }

    public boolean determineVictory()
    {
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(playerGrid.violation(playerGrid.getTiles()[i][j]))
                {
                    return false;
                }
            }
        }

        return true;
    }
}
