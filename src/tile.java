public class tile
{
    private int value;
    int x;
    int y;
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
        this.value = 0;
        this.x = x;
        this.y = y;
    }

    public tile(int value)
    {
        this.value = value;
    }

    public void clearTile()
    {
        this.value = 0;
    }
}
