package main.Code;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Sudoku program is a sudoku game with a solver.
 *
 * @author  Giana (Github: G-i-a-n-a - Website: Giana.dev)
 * @version 1.0.0 - 2020-08-12
 */
public class grid
{
    static int TOTAL_N = 81;   // Total number of tiles in grid
    static int N = 9;          // Length of row/column
    static int SUB_N = 3;      // Length of subgrid row/column

    tile[][] tiles;            // Tiles in grid
    boolean solved;            // If grid is currently solved
    int[] possibleInvisible;   // Possible invisible tiles per subgrid (values won't be shown in GUI)

    /**
     * This is the default constructor for grid.
     * It instantiates the tiles array and every
     * tile within it.
     */
    public grid()
    {
        tiles = new tile[N][N];

        // Iterate over rows
        for(int i = 0; i < N; i++)
        {
            // Iterate over columns
            for(int j = 0; j < N; j++)
            {
                tiles[i][j] = new tile(i, j);
            }
        }
    }

    /**
     * This is the parameterized constructor for
     * grid. It instantiates the tiles array and
     * sets the possibleInvisible variable to what
     * is passed into the method. It instantiates
     * every tile within the tiles array. It calls
     * the fill() method to fill every tile with a
     * value and create a valid sudoku board. It
     * calls the generateVisibility() method to
     * decide which tile's values will be visible
     * in the grid once displayed in the GUI.
     * @param possibleInvisible Possible invisible
     *                          tiles per subgrid
     */
    public grid(int[] possibleInvisible)
    {
        tiles = new tile[N][N];
        this.possibleInvisible = possibleInvisible;

        // Iterate over rows
        for(int i = 0; i < N; i++)
        {
            // Iterate over columns
            for(int j = 0; j < N; j++)
            {
                tiles[i][j] = new tile(i, j);
            }
        }

        fill();
        generateVisibility();
    }

    /**
     * @return tile[][] grid's tiles variable
     */
    public tile[][] getTiles() { return this.tiles; }

    /**
     * @return boolean tile's solved variable
     */
    public boolean getSolved() { return this.solved; }

    /**
     * This method is used to clear a grid,
     * which is to set every tile's value to
     * 0.
     */
    public void clear()
    {
        // Iterate over rows
        for(int i = 0; i < N; i++)
        {
            // Iterate over columns
            for(int j = 0; j < N; j++)
            {
                tiles[i][j].clearTile();   // Set current tile's value to 0
            }
        }
    }

    /**
     * This method is used to generate a list
     * of numbers to set "invisible" in a
     * subgrid of length numInvisible.
     * @param numInvisible Number of invisible
     *                     numbers to generate
     * @return ArrayList List of numbers to set
     *                   invisible in a subgrid
     */
    public ArrayList<Integer> generateInvisibleNumbers(int numInvisible)
    {
        ArrayList<Integer> possibleNumbers = new ArrayList<>();   // Possible numbers to be invisible
        ArrayList<Integer> result = new ArrayList<>();            // Resulting array of invisible numbers

        for(int i = 1; i <= N; i++)
        {
            possibleNumbers.add(i);   // Add i to possibleNumbers
        }

        for(int i = 0; i < numInvisible; i++)
        {
            Random rand = new Random();                         // Random seed
            int index = rand.nextInt(possibleNumbers.size());   // Get random index from possibleNumbers
            result.add(possibleNumbers.get(index));             // Add possibleNumbers at random index to result
            possibleNumbers.remove(index);                      // Remove possibleNumbers at random index from possibleNumbers
        }

        return result;
    }

    /**
     * This method is used to generate which
     * numbers are to be fixed/visible and
     * which are to be not fixed/"invisible"
     * for a given subgrid in the grid.
     * @param x x-coordinate of subgrid
     * @param y y-coordinate of subgrid
     */
    public void generateVisibilityPerSubgrid(int x, int y)
    {
        Random rand = new Random();                           // Random seed
        int index = rand.nextInt(possibleInvisible.length);   // Get random index from possibleInvisible
        int numInvisible = possibleInvisible[index];          // Number of invisible numbers is possibleInvisible
                                                              // at random index

        ArrayList<Integer> invisibleNumbers = generateInvisibleNumbers(numInvisible);   // Holds subgrid numbers
                                                                                        // which will not be visible
        // Iterate over subgrid rows
        for(int i = x; i < SUB_N + x; i++)
        {
            // Iterate over subgrid columns
            for (int j = y; j < SUB_N + y; j++)
            {
                tile currTile = tiles[i][j];   // Grab current tile

                // Tile visibility/fixed true if invisibleNumbers doesn't contain current tile's value
                currTile.setVisible(!invisibleNumbers.contains(currTile.getValue()));
                currTile.setFixed(!invisibleNumbers.contains(currTile.getValue()));
            }
        }
    }

    /**
     * This method is used to generate which
     * numbers are to be fixed/visible and
     * which are to be not fixed/"invisible"
     * for every subgrid in the grid.
     */
    public void generateVisibility()
    {
        // Iterate over rows
        for(int i = 0; i < N; i += 3)
        {
            // Iterate over columns
            for(int j = 0; j < N; j += 3)
            {
                generateVisibilityPerSubgrid(i, j);   // Generate visible/fixed tiles for current subgrid
            }
        }
    }

    /**
     * This method is used to check if the
     * given tile is in violation of row rules
     * for sudoku.
     * @param tileToCheck Tile to be checked
     * @return boolean If tileToCheck is in
     *                 violation of sudoku
     *                 row rules
     */
    public boolean rowViolation(tile tileToCheck)
    {
        int row = tileToCheck.getX();           // tileToCheck's x-coordinate (row number)
        int tileVal = tileToCheck.getValue();   // tileToCheck's value

        // Iterate over row
        for(int i = 0; i < N; i++)
        {
            int currVal = tiles[row][i].getValue();   // current tile's value

            // Current tile's value is the same at tileToCheck's value
            // and they are not the same tile
            if(tileVal == currVal && tiles[row][i] != tileToCheck)
            {
                // There is a row violation
                return true;
            }
        }

        // There is no row violation
        return false;
    }

    /**
     * This method is used to check if the
     * given tile is in violation of column
     * rules for sudoku.
     * @param tileToCheck Tile to be checked
     * @return boolean If tileToCheck is in
     *                 violation of sudoku
     *                 column rules
     */
    public boolean columnViolation(tile tileToCheck)
    {
        int column = tileToCheck.getY();        // tileToCheck's y-coordinate (column number)
        int tileVal = tileToCheck.getValue();   // tileToCheck's value

        // Iterate over column
        for(int i = 0; i < N; i++)
        {
            int currVal = tiles[i][column].getValue();   // current tile's value

            // Current tile's value is the same at tileToCheck's value
            // and they are not the same tile
            if(tileVal == currVal && tiles[i][column] != tileToCheck)
            {
                // There is a column violation
                return true;
            }
        }

        // There is no column violation
        return false;
    }

    /**
     * This method is used to check if the
     * given tile is in violation of subgrid
     * rules for sudoku.
     * @param tileToCheck Tile to be checked
     * @return boolean If tileToCheck is in
     *                 violation of sudoku
     *                 subgrid rules
     */
    public boolean subgridViolation(tile tileToCheck)
    {
        int x = tileToCheck.getSubgrid()[0];    // tileToCheck's subgrid's x-coordinate
        int y = tileToCheck.getSubgrid()[1];    // tileToCheck's subgrid's y-coordinate
        int tileVal = tileToCheck.getValue();   // tileToCheck's value

        // Iterate over subgrid rows
        for(int i = x; i < SUB_N + x; i++)
        {
            // Iterate over subgrid columns
            for(int j = y; j < SUB_N + y; j++)
            {
                int currVal = tiles[i][j].getValue();   // current tile's value

                // Current tile's value is the same at tileToCheck's value
                // and they are not the same tile
                if(tileVal == currVal && tiles[i][j] != tileToCheck)
                {
                    // There is a subgrid violation
                    return true;
                }
            }
        }

        // There is no subgrid violation
        return false;
    }

    /**
     * This method is used to check if the
     * given tile is in violation of row,
     * column, or subgrid rules for sudoku.
     * @param tileToCheck Tile to be checked
     * @return boolean If tileToCheck is in
     *                 violation of sudoku
     *                 rules
     */
    public boolean violation(tile tileToCheck)
    {
        // Return if there is a violation
        return rowViolation(tileToCheck) || columnViolation(tileToCheck) || subgridViolation(tileToCheck);
    }

    /**
     * This method is used to retrieve the
     * tile that comes before the given tile
     * in the grid.
     * @param currTile Tile to retrieve the
     *                 previous of
     * @return tile Previous tile
     */
    private tile getPreviousTile(tile currTile)
    {
        int x = currTile.getX();   // currTile's x-coordinate
        int y = currTile.getY();   // currTile's y-coordinate

        // currTile is the first tile in a row
        if(y == 0)
        {
            // currTile is the first tile in the grid
            if(x == 0)
            {
                // There is no previous tile
                return null;
            }

            // currTile is not the first tile in the grid
            return tiles[x - 1][N - 1];
        }
        // currTile is not the first tile in a row
        else
        {
            return tiles[x][y - 1];
        }
    }

    /**
     * This method is used to retrieve the
     * tile that comes after the given tile
     * in the grid.
     * @param currTile Tile to retrieve the
     *                 next of
     * @return tile Next tile
     */
    private tile getNextTile(tile currTile)
    {
        int x = currTile.getX();   // currTile's x-coordinate
        int y = currTile.getY();   // currTile's y-coordinate

        // currTile is the last tile in a row
        if(y == N - 1)
        {
            // currTile is the last tile in the grid
            if(x == N - 1)
            {
                // There is no next tile
                return null;
            }

            // currTile is not the last tile in the grid
            return tiles[x + 1][0];
        }
        // currTile is not the last tile in a row
        else
        {
            return tiles[x][y + 1];
        }
    }

    /**
     * This method is used to fill the grid
     * with values to generate a valid sudoku
     * board.
     */
    private void fill()
    {
        clear();   // Clear the grid

        int filled = 0;                // Number of tiles that have been filled with new values
        tile currTile = tiles[0][0];   // Start at the first tile in the grid

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
                        currTile = getNextTile(currTile);   // Move forward a tile
                    }

                    filled++;   // +1 tile has been filled
                }
            }
            // There is not an available number left to try for currTile
            else
            {
                currTile.replenishAvailableNumbers();   // Refill available numbers to try for currTile
                currTile.clearTile();                   // Set tile value to 0
                currTile = getPreviousTile(currTile);   // Move back a tile
                filled--;                               // -1 tile has been filled
            }
        }
    }

    /**
     * This method is used to replenish
     * available numbers for all tiles
     * in the grid.
     */
    public void replenish()
    {
        // Iterate over rows
        for(int i = 0; i < N; i++)
        {
            // Iterate over columns
            for(int j = 0; j < N; j++)
            {
                tiles[i][j].replenishAvailableNumbers();   // Replenish available numbers for current tile
            }
        }
    }

    /**
     * This method is used solve one "step" of
     * a grid, given a tile to start from. It
     * will look at the current tile and move
     * forward in solution steps as is necessary.
     * @param currTile Tile to start from
     * @return tile Tile to proceed from
     */
    public tile solveOneStep(tile currTile)
    {
        // While all tiles have not been solved
        if(currTile != null)
        {
            // currTile is fixed (does not need to be solved)
            if(currTile.getFixed())
            {
                // Move forward a tile
                return getNextTile(currTile);
            }
            // currTile is not fixed (potentially needs to be solved)
            else
            {
                // There is an available number left to try for currTile
                if(currTile.setRandomValue())
                {
                    currTile.setVisible(true);   // Value filled, make visible

                    // currTile does not violate sudoku rules
                    if(!violation(currTile))
                    {
                        // Move forward a tile
                        return getNextTile(currTile);
                    }
                    // currTile violates sudoku rules
                    else
                    {
                        // Proceed from currTile
                        return currTile;
                    }
                }
                // There is not an available number left to try for currTile
                else
                {
                    currTile.replenishAvailableNumbers();   // Refill available numbers to try for currTile
                    currTile.clearTile();                   // Set tile value to 0
                    currTile.setVisible(false);             // Make currTile invisible/not visible
                    currTile = getPreviousTile(currTile);   // Move back a tile

                    // While currTile is fixed (we need to move back further)
                    while(currTile.getFixed())
                    {
                        currTile = getPreviousTile(currTile);   // Move back a tile
                    }

                    // Proceed from this previous non-fixed tile
                    return currTile;
                }
            }
        }
        // All tiles have been solved
        else
        {
            solved = true;   // Grid has been solved

            // Nothing to proceed from
            return null;
        }
    }
}
