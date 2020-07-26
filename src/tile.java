public class tile
{
    private int value;
    private int row;
    private int column;
    private int index;
    private int subgrid;
    private boolean revealed;
    private boolean correct;

    public int getValue() { return this.value; }

    public void setValue(int value) { this.value = value; }

    public int getRow() { return this.row; }

    public void setRow(int row) { this.row = row; }

    public int getColumn() { return this.column; }

    public void setColumn(int column) { this.column = column; }

    public int getIndex() { return this.index; }

    public void setIndex(int index) { this.index = index; }

    public int getSubgrid() { return this.subgrid; }

    public void setSubgrid(int section) { this.subgrid = section; }

    public boolean getRevealed() { return this.revealed; }

    public void setRevealed(boolean revealed) { this.revealed = revealed; }

    public boolean getCorrect() { return this.correct; }

    public void setCorrect(boolean correct) { this.correct = correct; }

    public tile()
    {
        this.value = 0;
    }

    public tile(int index, int value)
    {
        index++;
        this.row = calculateRow(index);
        this.column = calculateColumn(index);
        this.value = value;
    }

    private int calculateRow(int index)
    {
        int i = index % 9;

        if(i == 0)
        {
            return 9;
        }
        else
        {
            return i;
        }
    }

    private int calculateColumn(int index)
    {
        if(calculateRow(index) == 9)
        {
            return index / 9;
        }
        else
        {
            return (index / 9) + 1;
        }
    }

    private int calculateSubgrid(int index)
    {
        int subgrid = 0;
        int row = calculateRow(index);
        int column = calculateColumn(index);

        if((row >= 1 && row <= 3) && (column >=1 && column <= 3))
        {
            subgrid = 1;
        }
        else if((row >= 4 && row <= 6) && (column >= 1 && column <= 3))
        {
            subgrid = 2;
        }
        else if((row >= 7 && row <= 9) && (column >= 1 && column <= 3))
        {
            subgrid = 3;
        }
        else if((row >= 1 && row <= 3) && (column >= 4 && column <= 6))
        {
            subgrid = 4;
        }
        else if((row >= 4 && row <= 6) && (column >= 4 && column <= 6))
        {
            subgrid = 5;
        }
        else if((row >= 7 && row <= 9) && (column >= 4 && column <= 6))
        {
            subgrid = 6;
        }
        else if((row >= 1 && row <= 3) && (column >= 7 && column <= 9))
        {
            subgrid = 7;
        }
        else if((row >= 4 && row <= 6) && (column >= 7 && column <= 9))
        {
            subgrid = 8;
        }
        else if((row >= 7 && row <= 9) && (column >= 7 && column <= 9))
        {
            subgrid = 9;
        }

        return subgrid;
    }

    public void clearTile()
    {
        this.value = 0;
    }
}
