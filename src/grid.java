import java.util.List;
import java.util.Random;

public class grid
{
    subgrid[][] subgrids = new subgrid[3][3];
    boolean revealed;
    boolean correct;
    boolean filled;

    public subgrid getSubgrid(int x, int y) { return this.subgrids[x][y]; };

    public void setSubgrid(int x, int y, subgrid subgrid) { this.subgrids[x][y] = subgrid; }

    public boolean getRevealed() { return this.revealed; }

    public void setRevealed(boolean revealed) { this.revealed = revealed; }

    public boolean getCorrect() { return this.correct; }

    public void setCorrect(boolean correct) { this.correct = correct; }

    public boolean getFilled() { return this.filled; }

    public void setFilled(boolean filled) { this.filled = filled; }

    public void clearGrid()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                subgrids[i][j].clearSubgrid();
            }
        }
    }

    public int generateRandomNumber(List<Integer> available)
    {
        int upper = available.size();
        Random rand = new Random();
        int randomIndex = rand.nextInt(upper);

        return available.get(randomIndex);
    }

    private void fillGrid()
    {
        int numFilled = 0;   // Tiles filled

        clearGrid();

        while(numFilled != 81)
        {

        }
    }
}
