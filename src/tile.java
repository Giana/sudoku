public class tile
{
    private int value;
    int x;
    int y;
    int[] subgrid;
    private boolean revealed;
    private boolean correct;

    public int getValue() { return this.value; }

    public void setValue(int value) { this.value = value; }

    public int getX() { return this.x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return this.y; }

    public void setY(int y) { this.y = y; }

    public boolean getRevealed() { return this.revealed; }

    public void setRevealed(boolean revealed) { this.revealed = revealed; }

    public boolean getCorrect() { return this.correct; }

    public void setCorrect(boolean correct) { this.correct = correct; }

    public tile()
    {
        this.value = 0;
    }

    public tile(int x, int y)
    {
        subgrid = new int[2];
        subgrid = calculateSubgrid(x, y);
        this.value = 0;
        this.x = x;
        this.y = y;
    }

    public void clearTile()
    {
        this.value = 0;
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
