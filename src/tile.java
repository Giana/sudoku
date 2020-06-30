public class tile
{
    private int value;
    private int row;
    private int column;
    private int index;
    private int section;
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

    public int getSection() { return this.section; }

    public void setSection(int section) { this.section = section; }

    public boolean getRevealed() { return this.revealed; }

    public void setRevealed(boolean revealed) { this.revealed = revealed; }

    public boolean getCorrect() { return this.correct; }

    public void setCorrect(boolean correct) { this.correct = correct; }

    public void clearTile()
    {
        this.value = 0;
    }
}
