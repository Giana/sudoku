package main.Code;

import java.util.Random;

public class game
{
    static int[] easyRange = {4, 5};     // Range of invisible numbers per subgrid in easy mode
    static int[] mediumRange = {5, 6};   // Range of invisible numbers per subgrid in medium mode
    static int[] hardRange = {6, 7};     // Range of invisible numbers per subgrid in hard mode

    private int mode;   // Game mode (0 - 2)

    public game(int mode)
    {
        if(mode == 0)
        {
            grid playerGrid = new grid(easyRange);
        }
        else if(mode == 1)
        {
            grid playerGrid = new grid(mediumRange);
        }
        else
        {
            grid playerGrid = new grid(hardRange);
        }
    }
}
