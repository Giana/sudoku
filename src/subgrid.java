public class subgrid
{
    tile[][] tiles = new tile[3][3];
    boolean revealed;
    boolean correct;

    public int getTile(int x, int y) { return this.tiles[x][y].getValue(); };

    public void setTile(int x, int y, int value) { this.tiles[x][y].setValue(value); }

    public boolean getRevealed() { return this.revealed; }

    public void setRevealed(boolean revealed) { this.revealed = revealed; }

    public boolean getCorrect() { return this.correct; }

    public void setCorrect(boolean correct) { this.correct = correct; }

    public void clearSubgrid()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                tiles[i][j].clearTile();
            }
        }
    }
}
