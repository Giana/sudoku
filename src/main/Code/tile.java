package main.Code;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Sudoku program is a sudoku game with a solver.
 *
 * @author  Giana (Github: G-i-a-n-a - Website: Giana.dev)
 * @version N/A
 */
public class tile
{
    static int N = 9;                      // Length of row/column

    private int value;                     // Value assigned to tile
    private int x;                         // x-coordinate of tile
    private int y;                         // y-coordinate of tile
    private boolean visible;               // Visibility status of tile
    private boolean fixed;                 // Fixed status of tile
    int[] subgrid;                         // x-coordinate & y-coordinate of tile's subgrid - {x, y}
    ArrayList<Integer> availableNumbers;   // Numbers available to test for tile

    /**
     * This is the parameterized constructor for
     * tile. It sets the value to 0, and sets
     * the x and y variables to what is passed into
     * the method. It sets the visibility and fixed
     * variables to false. It sets the subgrid array
     * to what is calculated by the calculateSubgrid()
     * method knowing x and y. It instantiates the
     * availableNumbers ArrayList, and uses the
     * replenishAvailableNumbers() method to fill
     * availableNumbers.
     * @param x x-coordinate to assign to tile
     * @param y y-coordinate to assign to tile
     */
    public tile(int x, int y)
    {
        this.value = 0;
        this.x = x;
        this.y = y;
        this.visible = false;
        this.fixed = false;
        this.subgrid = calculateSubgrid();
        this.availableNumbers = new ArrayList<>();
        replenishAvailableNumbers();
    }

    /**
     * This is the copy constructor for
     * tile. It copies over the value, x
     * variable, y variable, visible variable,
     * fixed variable, and the availableNumbers
     * ArrayList. It sets the subgrid array to
     * what is calculated by the calculateSubgrid()
     * method knowing x and y.
     * @param copyTile tile to be copied
     */
    public tile(tile copyTile)
    {
        this.value = copyTile.value;
        this.x = copyTile.x;
        this.y = copyTile.y;
        this.visible = copyTile.visible;
        this.fixed = copyTile.fixed;
        this.availableNumbers = copyTile.availableNumbers;
        this.subgrid = calculateSubgrid();
    }

    /**
     * @return int tile's value
     */
    public int getValue() { return this.value; }

    /**
     * @param value value to assign to tile
     */
    public void setValue(int value) { this.value = value; }

    /**
     * @return int tile's x-coordinate
     */
    public int getX() { return this.x; }

    /**
     * @return int tile's y-coordinate
     */
    public int getY() { return this.y; }

    /**
     * @return int[] x-coordinate and y-coordinate of tile's subgrid
     */
    public int[] getSubgrid() { return this.subgrid; }

    /**
     * @return boolean tile's visible variable
     */
    public boolean getVisible() { return this.visible; }

    /**
     * @param visible value for visible to assign to tile
     */
    public void setVisible(boolean visible) { this.visible = visible; }

    /**
     * @return boolean tile's fixed variable
     */
    public boolean getFixed() { return this.fixed; }

    /**
     * @param fixed value for fixed to assign to tile
     */
    public void setFixed(boolean fixed) { this.fixed = fixed; }

    /**
     * This method is used to clear a tile,
     * which is to set its value to 0.
     */
    public void clearTile()
    {
        this.value = 0;
    }

    /**
     * This method is used to replenish a
     * tile's available numbers. It empties
     * the availableNumbers ArrayList and
     * fills it with numbers 1-9.
     */
    public void replenishAvailableNumbers()
    {
        this.availableNumbers.clear();   // Empty availableNumbers

        // Iterate from 1 to N
        for(int i = 1; i <= N; i++)
        {
            this.availableNumbers.add(i);   // Add i to availableNumbers
        }
    }

    /**
     * This method is used to set a random
     * value for a tile. It picks a random
     * value out of availableNumbers, removes
     * that number from availableNumbers, and
     * sets the tile's value variable to that
     * number.
     * @return boolean If a new value was set for tile
     */
    public boolean setRandomValue()
    {
        // availableNumbers is empty
        if(availableNumbers.isEmpty())
        {
            // No available numbers left to select for the tile
            return false;
        }
        // availableNumbers isn't empty
        else
        {
            Random rand = new Random();                          // Random seed
            int index = rand.nextInt(availableNumbers.size());   // Get random index from availableNumbers
            int newVal = availableNumbers.get(index);            // newVal is availableNumbers at random index
            availableNumbers.remove(index);                      // Remove newVal from availableNumbers
            this.value = newVal;                                 // Set tile's value to newVal

            // There was an available number left to select for tile
            return true;
        }
    }

    /**
     * This method is used to get the x and
     * y-coordinates of a tile's subgrid.
     * @return int[] x and y-coordinates of
     *         tile's subgrid in the format
     *         {x, y}
     */
    public int[] calculateSubgrid()
    {
        int x = this.x;   // tile's x-coordinate
        int y = this.y;   // tile's y-coordinate

        // Upper left subgrid
        if((x >= 0 && x <= 2) && (y >= 0 && y <= 2))
        {
            return new int[] {0, 0};
        }
        // Upper center subgrid
        else if((x >= 0 && x <= 2) && (y >= 3 && y <= 5))
        {
            return new int[] {0, 3};
        }
        // Upper right subgrid
        else if((x >= 0 && x <= 2) && (y >= 6 && y <= 8))
        {
            return new int[] {0, 6};
        }
        // Middle left subgrid
        else if((x >= 3 && x <= 5) && (y >= 0 && y <= 2))
        {
            return new int[] {3, 0};
        }
        // Middle center subgrid
        else if((x >= 3 && x <= 5) && (y >= 3 && y <= 5))
        {
            return new int[] {3, 3};
        }
        // Middle right subgrid
        else if((x >= 3 && x <= 5) && (y >= 6 && y <= 8))
        {
            return new int[] {3, 6};
        }
        // Lower left subgrid
        else if((x >= 6 && x <= 8) && (y >= 0 && y <= 2))
        {
            return new int[] {6, 0};
        }
        // Lower center subgrid
        else if((x >= 6 && x <= 8) && (y >= 3 && y <= 5))
        {
            return new int[] {6, 3};
        }
        // Lower right subgrid
        else if((x >= 6 && x <= 8) && (y >= 6 && y <= 8))
        {
            return new int[] {6, 6};
        }
        // Error
        else
        {
            return new int[] {-1, -1};
        }
    }
}
