public class tile
{
    private int value;
    private int subRow;
    private int superRow;
    private int subColumn;
    private int superColumn;
    private boolean revealed;
    private boolean correct;

    public int getValue() { return this.value; }

    public void setValue(int value) { this.value = value; }

    public int getSubRow() { return this.subRow; }

    public void setSubRow(int subRow) { this.subRow = subRow; }

    public int getSuperRow() { return this.superRow; }

    public void setSuperRow(int superRow) { this.superRow = superRow; }

    public int getSubColumn() { return this.subColumn; }

    public void setSubColumn(int subColumn) { this.subColumn = subColumn; }

    public int getSuperColumn() { return this.superColumn; }

    public void setSuperColumn(int superColumn) { this.superColumn = superColumn; }

    public boolean getRevealed() { return this.revealed; }

    public void setRevealed(boolean revealed) { this.revealed = revealed; }

    public boolean getCorrect() { return this.correct; }

    public void setCorrect(boolean correct) { this.correct = correct; }
}
