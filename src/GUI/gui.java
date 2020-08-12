package GUI;

import java.awt.*;
import javax.swing.border.Border;
import javax.swing.*;
import main.Code.grid;
import main.Code.player;
import main.Code.tile;

/**
 * The Sudoku program is a sudoku game with a solver.
 *
 * @author  Giana (Github: G-i-a-n-a - Website: Giana.dev)
 * @version N/A
 */
public class gui
{
    static int N = 9;       // Length of row/column
    static int SUB_N = 3;   // Length of row/column

    static int[] easy = new int[] {4, 5};     // Easy possible invisible
    static int[] medium = new int[] {5, 6};   // Medium possible invisible
    static int[] hard = new int[] {6, 7};     // Hard possible invisible

    private JButton continueButton;
    private JButton easyButton;
    private JButton forfeitButton;
    private JButton hardButton;
    private JButton mediumButton;
    private JButton solveButton;
    private JButton sudokuButton;
    private JButton validateButton;
    private JLabel correctLogoImage;
    private JLabel incorrectLogoImage;
    private JPanel boardPanel;
    private JPanel gamePanel;
    private JPanel gridPanel;
    private JPanel choicesPanel;
    private JPanel correctPanel;
    private JPanel creditsPanel;
    private JPanel incorrectPanel;
    private JPanel parentPanel;
    private JPanel switchPanel;
    private JTextPane creditsTextPane1;
    private JTextPane creditsTextPane2;
    private JToolBar gameToolBar;

    private JPanel[][] panelGrid = new JPanel[SUB_N][SUB_N];   // "Subgrids"
    private JTextField[][] textGrid = new JTextField[N][N];    // "Tiles"

    private grid currentGrid;
    private player currentPlayer;
    private Thread solver;

    public gui()
    {
        initBoardGUI();   // Fill in board GUI

        /*
         * This listener outlines what happens if you click the
         * "Sudoku" button on the toolbar.
         */
        sudokuButton.addActionListener(e -> displayPanel(creditsPanel));

        /*
         * This listener outlines what happens if you click the
         * "Easy" button on the toolbar.
         */
        easyButton.addActionListener(e ->
        {
            currentGrid = new grid(easy);
            currentPlayer = new player(currentGrid);
            clearGridGUI();
            fillGridGUI();
            displayPanel(gamePanel);
        });

        /*
         * This listener outlines what happens if you click the
         * "Medium" button on the toolbar.
         */
        mediumButton.addActionListener(e ->
        {
            currentGrid = new grid(medium);
            currentPlayer = new player(currentGrid);
            clearGridGUI();
            fillGridGUI();
            displayPanel(gamePanel);
        });

        /*
         * This listener outlines what happens if you click the
         * "Hard" button on the toolbar.
         */
        hardButton.addActionListener(e ->
        {
            currentGrid = new grid(hard);
            currentPlayer = new player(currentGrid);
            clearGridGUI();
            fillGridGUI();
            displayPanel(gamePanel);
        });

        /*
         * This listener outlines what happens if you click the
         * "Solve" button on the toolbar.
         */
        solveButton.addActionListener(e ->
        {
            clearGridGUI();
            fillGridGUI();
            displayPanel(gamePanel);
            setUpSolver();
            solver.start();
        });

        /*
         * This listener outlines what happens if you click the
         * "Validate" button on the toolbar.
         */
        validateButton.addActionListener(e ->
        {
            updatePlayerGridFromGUI();

            // Board is valid
            if(currentPlayer.determineVictory())
            {
                displayPanel(correctPanel);
            }
            // Board is not valid
            else
            {
                displayPanel(incorrectPanel);
            }
        });

        /*
         * This listener outlines what happens if you click the
         * "Forfeit" button on the incorrectPanel.
         */
        forfeitButton.addActionListener(e ->
        {
            clearGridGUI();
            displayPanel(gamePanel);
        });

        /*
         * This listener outlines what happens if you click the
         * "Continue" button on the incorrectPanel.
         */
        continueButton.addActionListener(e -> displayPanel(gamePanel));
    }

    /**
     * This method is used to clear the grid in the
     * GUI.
     */
    private void clearGridGUI()
    {
        // Iterate over rows
        for(int i = 0; i < N; i++)
        {
            // Iterate over columns
            for(int j = 0; j < N; j++)
            {
                textGrid[i][j].setText("");
                textGrid[i][j].setBackground(Color.decode("#E4E4E4"));
                textGrid[i][j].setEditable(false);
            }
        }
    }

    /**
     * This method is used to refresh the grid in the
     * GUI, displaying all values which are visible
     * in the current player's grid.
     */
    private void refreshBoardGUI()
    {
        grid playerGrid = currentPlayer.getPlayerGrid();

        // Iterate over rows
        for(int i = 0; i < N; i++)
        {
            // Iterate over columns
            for(int j = 0; j < N; j++)
            {
                // Current tile is visible
                if(playerGrid.getTiles()[i][j].getVisible())
                {
                    textGrid[i][j].setText(Integer.toString(playerGrid.getTiles()[i][j].getValue()));
                }
                // Current tile is not visible
                else
                {
                    textGrid[i][j].setText("");
                }
            }
        }
    }

    /**
     * This method is used to fill the grid in
     * the GUI with fixed values and to set the
     * background colors and ability to be edited
     * for each tile as necessary.
     */
    private void fillGridGUI()
    {
        // Iterate over rows
        for(int i = 0; i < N; i++)
        {
            // Iterate over columns
            for(int j = 0; j < N; j++)
            {
                // Current tile is fixed
                if(currentGrid.getTiles()[i][j].getFixed())
                {
                    textGrid[i][j].setText(Integer.toString(currentGrid.getTiles()[i][j].getValue()));
                    textGrid[i][j].setBackground(Color.decode("#BABABA"));
                    textGrid[i][j].setEditable(false);
                }
                // Current tile is not fixed
                else
                {
                    textGrid[i][j].setEditable(true);
                }
            }
        }
    }

    /**
     * This method is used to initialize the grid
     * in the GUI with appropriate layouts, fonts,
     * borders, dimensions, etc.
     */
    private void initBoardGUI()
    {
        gridPanel.setLayout(new GridLayout(3, 3));                // Holds N subgrids (JPanels)
        gridPanel.setPreferredSize(new Dimension(600, 600));

        Font boardFont = new Font("Monospaced", Font.BOLD, 30);
        Border tileBorder = BorderFactory.createLineBorder(Color.decode("#1E7BCA"), 1);
        Border subgridBorder = BorderFactory.createLineBorder(Color.decode("#60B0F4"), 2);

        // Set up panelGrid (holds N JPanels/subgrids) to add to gridPanel
        for(int i = 0; i < SUB_N; i++)
        {
            for(int j = 0; j < SUB_N; j++)
            {
                panelGrid[i][j] = new JPanel();
                panelGrid[i][j].setLayout(new GridLayout(SUB_N, SUB_N));
                panelGrid[i][j].setBorder(subgridBorder);
                gridPanel.add(panelGrid[i][j]);
            }
        }

        // Set up textGrid (holds N * N JTextFields/tiles)
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                textGrid[i][j] = new JTextField();
                textGrid[i][j].setBorder(tileBorder);
                textGrid[i][j].setBackground(Color.decode("#E4E4E4"));
                textGrid[i][j].setOpaque(true);
                textGrid[i][j].setEditable(false);
                textGrid[i][j].setFont(boardFont);
                textGrid[i][j].setHorizontalAlignment(JTextField.CENTER);
            }
        }

        int x = 0;   // Subgrid x-coordinate
        int y = 0;   // Subgrid y-coordinate

        // Iterate over panelGrid
        for(int i = 0; i < SUB_N; i++)
        {
            for(int j = 0; j < SUB_N; j++)
            {
                // Iterate over textGrid
                for(int k = x; k < SUB_N + x; k++)
                {
                    for(int m = y; m < SUB_N + y; m++)
                    {
                        // Link textGrid to panelGrid
                        panelGrid[i][j].add(textGrid[k][m]);
                    }
                }

                y += 3;
            }

            y = 0;
            x += 3;
        }
    }

    /**
     * This method is used to update the values in
     * player grid based on what is present in the
     * grid in the GUI.
     */
    public void updatePlayerGridFromGUI()
    {
        // Iterate over rows
        for(int i = 0; i < N; i++)
        {
            // Iterate over columns
            for(int j = 0; j < N; j++)
            {
                String currentString = textGrid[i][j].getText();

                // Current tile is a player entry
                if(!currentGrid.getTiles()[i][j].getFixed())
                {
                    // Current tile is a valid player entry
                    if(currentString.length() == 1 &&
                            (Character.getNumericValue(currentString.charAt(0)) >= 1 &&
                                    Character.getNumericValue(currentString.charAt(0)) <= 9))
                    {
                        currentPlayer.getPlayerGrid().getTiles()[i][j].setValue(Integer.parseInt(currentString));
                    }
                    // Current tile is not a valid player entry
                    else
                    {
                        currentPlayer.getPlayerGrid().getTiles()[i][j].setValue(0);
                    }
                }
            }
        }
    }

    /**
     * This method is used to set up the solver
     * thread.
     */
    public void setUpSolver()
    {
        solver = new Thread(() ->
        {
            grid playerGrid = currentPlayer.getPlayerGrid();
            playerGrid.replenish();
            refreshBoardGUI();
            tile currentTile = playerGrid.getTiles()[0][0];   // Start at the first tile in the grid

            // While current grid hasn't been solved
            while(!playerGrid.getSolved())
            {
                currentTile = playerGrid.solveOneStep(currentTile);   // Perform a solution step
                refreshBoardGUI();                                    // Update GUI
            }

            solver.interrupt();
        });
    }

    /**
     * This method is used to display a
     * specified JPanel in switchPanel.
     * @param panel JPanel to be displayed
     */
    public void displayPanel(JPanel panel)
    {
        switchPanel.removeAll();
        switchPanel.add(panel);
        switchPanel.repaint();
        switchPanel.revalidate();
    }

    public static void main(String[] args)
    {
        JFrame gameFrame = new JFrame("Sudoku");
        gameFrame.setContentPane(new gui().parentPanel);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
}
