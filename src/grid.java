import java.util.Random;

public class grid
{
    tile[][] tiles = new tile[9][9];
    boolean revealed;
    boolean correct;

    public boolean getRevealed() { return this.revealed; }

    public void setRevealed(boolean revealed) { this.revealed = revealed; }

    public boolean getCorrect() { return this.correct; }

    public void setCorrect(boolean correct) { this.correct = correct; }

    public grid()
    {
        fillGrid();
        revealed = false;
        correct = false;
    }

    public void clearGrid()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                // subgrids[i][j].clearSubgrid();
            }
        }
    }

    public int getRandomNumber(int upper)
    {
        Random rand = new Random();

        return rand.nextInt(upper - 1);
    }

    private void fillGrid()
    {
        int numFilled = 0;   // Tiles filled

        clearGrid();

        while(numFilled != 81)
        {

        }
    }

    private void printGrid()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                // System.out.print(subgrids[i][j]);
            }
        }
    }
}
