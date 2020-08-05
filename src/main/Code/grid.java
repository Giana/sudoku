package main.Code;

import java.util.ArrayList;
import java.util.Random;

public class grid
{
    static int TOTAL_N = 81;
    static int N = 9;
    static int SUB_N = 3;

    tile[][] tiles;
    boolean[][] tileVisibility;
    int[] possibleInvisible;
    boolean revealed;
    boolean correct;

    public boolean getRevealed() { return this.revealed; }

    public void setRevealed(boolean revealed) { this.revealed = revealed; }

    public boolean getCorrect() { return this.correct; }

    public void setCorrect(boolean correct) { this.correct = correct; }

    public grid(int[] possibleInvisible)
    {
        tiles = new tile[N][N];
        tileVisibility = new boolean[N][N];
        this.possibleInvisible = possibleInvisible;

        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                tiles[i][j] = new tile(i, j);
            }
        }

        fillGrid();
        generateVisibilityGrid();
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

    // Generates list of numbers to turn invisible of length numInvisible
    public ArrayList<Integer> generateInvisibleNumbers(int numInvisible)
    {
        ArrayList<Integer> possibleNumbers = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 1; i < N + 1; i++)
        {
            possibleNumbers.add(i);
        }

        for(int i = 0; i < numInvisible; i++)
        {
            Random rand = new Random();
            int index = rand.nextInt(possibleNumbers.size());
            result.add(possibleNumbers.get(index));
            possibleNumbers.remove(index);
        }

        return result;
    }

    public void generateVisibilitySubgrid(int x, int y)
    {
        Random rand = new Random();
        int index = rand.nextInt(possibleInvisible.length);   // Random index
        int numInvisible = possibleInvisible[index];          // Number of invisible numbers in this subgrid

        ArrayList<Integer> invisibleNumbers = generateInvisibleNumbers(numInvisible);   // Holds subgrid numbers
                                                                                        // which will not be visible
        // Iterate over subgrid rows
        for(int i = x; i < SUB_N + x; i++)
        {
            // Iterate over subgrid columns
            for (int j = y; j < SUB_N + y; j++)
            {
                // Tile visibility true if invisibleNumbers doesn't contain current tile's value
                tileVisibility[i][j] = !invisibleNumbers.contains(tiles[i][j].getValue());
            }
        }
    }

    public void generateVisibilityGrid()
    {
        // Iterate over grid rows
        for(int i = 0; i < N; i += 3)
        {
            // Iterate over grid columns
            for(int j = 0; j < N; j += 3)
            {
                generateVisibilitySubgrid(i, j);
            }
        }
    }

    public boolean rowViolation(tile currentTile)
    {
        int row = currentTile.getX();
        int tileVal = currentTile.getValue();

        // Iterate over row
        for(int i = 0; i < N; i++)
        {
            int currVal = tiles[row][i].getValue();

            // There is a row violation
            if(tileVal == currVal && tiles[row][i] != currentTile)
            {
                return true;
            }
        }

        // There is no row violation
        return false;
    }

    public boolean columnViolation(tile currentTile)
    {
        int column = currentTile.getY();
        int tileVal = currentTile.getValue();

        // Iterate over column
        for(int i = 0; i < N; i++)
        {
            int currVal = tiles[i][column].getValue();

            // There is a column violation
            if(tileVal == currVal && tiles[i][column] != currentTile)
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
                if(tileVal == currVal && tiles[i][j] != currentTile)
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
        // Return if there is a violation
        return rowViolation(currentTile) || columnViolation(currentTile) || subgridViolation(currentTile);
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
        if(y == 8)
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
        while(filled != TOTAL_N)
        {
            // There is an available number left to try for currTile
            if(currTile.setRandomValue())
            {
                // currTile does not violate sudoku rules
                if(!violation(currTile))
                {
                    // We are not on the last tile
                    if(filled != TOTAL_N - 1)
                    {
                        currTile = getNextTile(currTile);
                    }

                    filled++;
                }
            }
            // There is no available number left to try for currTile
            else
            {
                currTile.replenishAvailableNumbers();
                currTile.setValue(0);
                currTile = getPreviousTile(currTile);
                filled--;
            }
        }
    }

    public void printEntireGrid()
    {
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                System.out.print(tiles[i][j].getValue() + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    public void printGridWithInvisibleTiles()
    {
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                if(tileVisibility[i][j])
                {
                    System.out.print(tiles[i][j].getValue() + " ");
                }
                else
                {
                    System.out.print("X" + " ");
                }
            }

            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args)
    {
        int[] testInvisible = {4, 5};

        grid testGrid = new grid(testInvisible);

        testGrid.printEntireGrid();
        testGrid.printGridWithInvisibleTiles();
    }
}
