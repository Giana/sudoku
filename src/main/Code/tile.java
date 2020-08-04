package main.Code;

import java.util.ArrayList;
import java.util.Random;

public class tile
{
    private int value;
    int x;
    int y;
    int[] subgrid;
    ArrayList<Integer> availableNumbers;
    private boolean revealed;
    private boolean correct;

    public int getValue() { return this.value; }

    public void setValue(int value) { this.value = value; }

    public int getX() { return this.x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return this.y; }

    public void setY(int y) { this.y = y; }

    public int[] getSubgrid() { return this.subgrid; }

    public void setSubgrid(int[] subgrid) { this.subgrid = subgrid; }

    public boolean getRevealed() { return this.revealed; }

    public void setRevealed(boolean revealed) { this.revealed = revealed; }

    public boolean getCorrect() { return this.correct; }

    public void setCorrect(boolean correct) { this.correct = correct; }

    public tile(int x, int y)
    {
        this.value = 0;
        this.x = x;
        this.y = y;
        subgrid = calculateSubgrid(x, y);
        this.availableNumbers = new ArrayList<>();
        replenishAvailableNumbers();
    }

    public void clearTile()
    {
        this.value = 0;
    }

    public void replenishAvailableNumbers()
    {
        this.availableNumbers.clear();

        for(int i = 1; i <= 9; i++)
        {
            this.availableNumbers.add(i);
        }
    }

    public boolean setRandomValue()
    {
        if(this.availableNumbers.isEmpty())
        {
            return false;
        }
        else
        {
            Random rand = new Random();
            int index = rand.nextInt(availableNumbers.size());
            int newVal = availableNumbers.get(index);
            availableNumbers.remove(index);
            this.value = newVal;

            return true;
        }
    }

    public int[] calculateSubgrid(int x, int y)
    {
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
