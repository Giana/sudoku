public class grid
{
    subgrid[][] subgrids = new subgrid[3][3];
    boolean revealed;
    boolean correct;

    public subgrid getSubgrid(int x, int y) { return this.subgrids[x][y]; };
    
    public boolean getRevealed() { return this.revealed; }

    public void setRevealed(boolean revealed) { this.revealed = revealed; }

    public boolean getCorrect() { return this.correct; }

    public void setCorrect(boolean correct) { this.correct = correct; }
}
