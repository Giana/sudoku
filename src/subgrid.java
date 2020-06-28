public class subgrid
{
    tile[][] values = new tile[3][3];
    boolean revealed;
    boolean correct;

    public int getValue(int x, int y) { return this.values[x][y].getValue(); };

    public void setValue(int x, int y, int value) { this.values[x][y].setValue(value); }

    public boolean getRevealed() { return this.revealed; }

    public void setRevealed(boolean revealed) { this.revealed = revealed; }

    public boolean getCorrect() { return this.correct; }

    public void setCorrect(boolean correct) { this.correct = correct; }
}
