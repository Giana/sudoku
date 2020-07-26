import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class grid
{
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
                tiles[i][j] =  new tile();
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

    public int getRandomNumber(int upper)
    {
        Random rand = new Random();

        return rand.nextInt(upper - 1);
    }

    public boolean rowViolation(tile currentTile)
    {
        int row = currentTile.getRow();
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
        int column = currentTile.getColumn();
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

    public boolean subgridViolation()
    {
        
    }

    private boolean violation(tile[] currentValues, tile test)
    {

    }

    private void fillGrid()
    {

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
