import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class grid
{
    int TOTAL_N = 81;
    static int N = 9;
    static int SUB_N = 3;

    tile[][] tiles;
    int[] vals;
    boolean revealed;
    boolean correct;

    public boolean getRevealed() { return this.revealed; }

    public void setRevealed(boolean revealed) { this.revealed = revealed; }

    public boolean getCorrect() { return this.correct; }

    public void setCorrect(boolean correct) { this.correct = correct; }

    public grid()
    {
        tiles = new tile[N][N];

        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                tiles[i][j] = new tile(i, j);
            }
        }

        fillGrid();
        revealed = false;
        correct = false;
    }

    public void clearGrid()
    {
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                tiles[i][j].clearTile();
            }
        }
    }

    public boolean rowViolation(tile currentTile)
    {
        int row = currentTile.getY();
        int tileVal = currentTile.getValue();

        // Iterate over row
        for(int i = 0; i < N; i++)
        {
            int currVal = tiles[i][row].getValue();

            // There is a row violation
            if(tileVal == currVal)
            {
                return true;
            }
        }

        // There is no row violation
        return false;
    }

    public boolean columnViolation(tile currentTile)
    {
        int column = currentTile.getX();
        int tileVal = currentTile.getValue();

        // Iterate over column
        for(int i = 0; i < N; i++)
        {
            int currVal = tiles[column][i].getValue();

            // There is a column violation
            if(tileVal == currVal)
            {
                return true;
            }
        }

        // There is no column violation
        return false;
    }

    public boolean subgridViolation(tile currentTile)
    {
        int x = currentTile.getSubgrid()[0];
        int y = currentTile.getSubgrid()[1];
        int tileVal = currentTile.getValue();

        // Iterate over subgrid rows
        for(int i = x; i < SUB_N + x; i++)
        {
            // Iterate over subgrid columns
            for(int j = y; j < SUB_N + y; j++)
            {
                int currVal = tiles[i][j].getValue();

                // There is a subgrid violation
                if(tileVal == currVal)
                {
                    return true;
                }
            }
        }

        // There is no subgrid violation
        return false;
    }

    private boolean violation(tile currentTile)
    {
        // There is a violation
        if(rowViolation(currentTile) || columnViolation(currentTile) || subgridViolation(currentTile))
        {
            return true;
        }

        // There is no violation
        return false;
    }

    private tile getPreviousTile(tile currTile)
    {
        int x = currTile.getX();
        int y = currTile.getY();

        // currTile is the first tile in a row
        if(y == 0)
        {
            return tiles[x - 1][8];
        }
        // currTile is not the first tile in a row
        else
        {
            return tiles[x][y - 1];
        }
    }

    private tile getNextTile(tile currTile)
    {
        int x = currTile.getX();
        int y = currTile.getY();

        // currTile is the last tile in a row
        if(y == 0)
        {
            return tiles[x + 1][0];
        }
        // currTile is not the last tile in a row
        else
        {
            return tiles[x][y + 1];
        }
    }

    private void fillGrid()
    {
        clearGrid();

        int filled = 0;
        tile currTile = tiles[0][0];

        // While all tiles have not been filled with values
        while(filled != 81)
        {
            // There is an available number left to try for currTile
            if(currTile.setRandomValue())
            {
                // currTile does not violate sudoku rules
                if(!violation(currTile))
                {
                    currTile = getNextTile(currTile);
                    filled++;
                }
            }
            // There is no available number left to try for currTile
            else
            {
                currTile.replenishAvailableNumbers();
                currTile = getPreviousTile(currTile);
                filled--;
            }
        }
    }

    private void printGrid()
    {
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                System.out.print(tiles[i][j].getValue() + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        grid testGrid = new grid();

        testGrid.printGrid();
    }
}
