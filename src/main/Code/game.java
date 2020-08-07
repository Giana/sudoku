package main.Code;

import java.util.Random;

public class game
{
    static int[] easyRange = {4, 5};     // Range of invisible numbers per subgrid in easy mode
    static int[] mediumRange = {5, 6};   // Range of invisible numbers per subgrid in medium mode
    static int[] hardRange = {6, 7};     // Range of invisible numbers per subgrid in hard mode

    private int mode;   // Game mode (0 - 2)
    grid playerBoard;   // Player's sudoku board

    public grid getPlayerBoard() { return this.playerBoard; }

    public void setPlayerBoard(grid playerBoard) { this.playerBoard = playerBoard; }

    public game() { }

    public game(int mode)
    {
        if(mode == 0)
        {
            grid playerBoard = new grid(easyRange);
        }
        else if(mode == 1)
        {
            grid playerBoard = new grid(mediumRange);
        }
        else
        {
            grid playerBoard = new grid(hardRange);
        }
    }
}
