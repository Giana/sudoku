package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.Code.tile;
import main.Code.grid;
import main.Code.player;

public class gui
{
    static int TOTAL_N = 81;
    static int N = 9;
    static int SUB_N = 3;

    private JPanel parentPanel;
    private JPanel switchPanel;
    private JPanel gamePanel;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JPanel boardPanel;
    private JPanel gridPanel;
    private JPanel correctPanel;
    private JToolBar gameToolBar;
    private JButton sudokuButton;
    private JButton solveButton;
    private JButton validateButton;
    private JLabel correctLogoImage;
    private JPanel incorrectPanel;
    private JLabel incorrectLogoImage;
    private JPanel choicesPanel;
    private JButton continueButton;
    private JButton forfeitButton;

    private JPanel[][] panelGrid = new JPanel[SUB_N][SUB_N];
    private JTextField[][] textGrid = new JTextField[N][N];
    private grid currentGrid;
    private player currentPlayer;
    private Thread solver;

    public gui()
    {
        initBoardGUI();

        easyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentGrid = new grid(new int[]{4, 5});
                currentPlayer = new player(currentGrid);
                currentGrid.print();
                clearGridGUI();
                fillGridGUI();
            }
        });

        mediumButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentGrid = new grid(new int[]{5, 6});
                currentPlayer = new player(currentGrid);
                currentGrid.print();
                clearGridGUI();
                fillGridGUI();
            }
        });

        hardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentGrid = new grid(new int[]{6, 7});
                currentPlayer = new player(currentGrid);
                currentGrid.print();
                clearGridGUI();
                fillGridGUI();
            }
        });

        solveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentPlayer.getPlayerGrid().replenish();
                clearGridGUI();
                fillGridGUI();
                setUpSolver();
                solver.start();
            }
        });

        validateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                updatePlayerGridFromGUI();
                currentPlayer.getPlayerGrid().print();

                // Board is valid
                if(currentPlayer.determineVictory())
                {
                    displayCorrectPanel();
                }
                // Board is invalid
                else
                {
                    displayIncorrectPanel();
                }
            }
        });

        forfeitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clearGridGUI();
                displayGamePanel();
            }
        });

        continueButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                displayGamePanel();
            }
        });
    }

    private void clearGridGUI()
    {
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                textGrid[i][j].setText("");
                textGrid[i][j].setBackground(Color.decode("#E4E4E4"));
                textGrid[i][j].setEditable(false);
            }
        }
    }

    private void refreshBoardGUI()
    {
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                // Current tile is visible
                if(currentPlayer.getPlayerGrid().getTiles()[i][j].getVisible())
                {
                    textGrid[i][j].setText(Integer.toString(currentPlayer.getPlayerGrid().getTiles()[i][j].getValue()));
                }
                // Current tile is not visible
                else
                {
                    textGrid[i][j].setText("");
                }
            }
        }
    }

    private void fillGridGUI()
    {
        // Set up textGrid
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {

                if(currentGrid.getTiles()[i][j].getFixed())
                {
                    textGrid[i][j].setText(Integer.toString(currentGrid.getTiles()[i][j].getValue()));
                    textGrid[i][j].setBackground(Color.decode("#BABABA"));
                    textGrid[i][j].setEditable(false);
                }
                else
                {
                    textGrid[i][j].setEditable(true);
                }
            }
        }

        displayGamePanel();
    }

    private void initBoardGUI()
    {
        gridPanel.setLayout(new GridLayout(3, 3));
        Font boardFont = new Font("Monospaced", Font.BOLD, 30);
        Border tileBorder = BorderFactory.createLineBorder(Color.decode("#1E7BCA"), 1);
        Border subgridBorder = BorderFactory.createLineBorder(Color.decode("#60B0F4"), 2);

        // Set up panelGrid
        for(int i = 0; i < SUB_N; i++)
        {
            for(int j = 0; j < SUB_N; j++)
            {
                panelGrid[i][j] = new JPanel();
                panelGrid[i][j].setLayout(new GridLayout(SUB_N, SUB_N));
                panelGrid[i][j].setBorder(subgridBorder);
                gridPanel.add(panelGrid[i][j]);
                gridPanel.setPreferredSize(new Dimension(600, 600));
            }
        }

        // Set up textGrid
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                textGrid[i][j] = new JTextField();
                textGrid[i][j].setBorder(tileBorder);
                textGrid[i][j].setBackground(Color.decode("#E4E4E4"));
                textGrid[i][j].setOpaque(true);
                textGrid[i][j].setFont(boardFont);
                textGrid[i][j].setEditable(false);
                textGrid[i][j].setHorizontalAlignment(JTextField.CENTER);
            }
        }

        int x = 0;
        int y = 0;

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

    public void updatePlayerGridFromGUI()
    {
        for(int i = 0; i < N; i++)
        {
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
                    // Current tile is an invalid player entry
                    else
                    {
                        currentPlayer.getPlayerGrid().getTiles()[i][j].setValue(0);
                    }
                }
            }
        }
    }

    public void setUpSolver()
    {
        // Thread for solver
        solver = new Thread(() ->
        {
            grid playerGrid = currentPlayer.getPlayerGrid();

            // Sleep for .2 second before starting solver
            try
            {
                Thread.sleep(200);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            refreshBoardGUI();
            //updatePlayerGridFromGUI();
            tile currentTile = playerGrid.getTiles()[0][0];

            // While current board hasn't been solved
            while(!playerGrid.getSolved())
            {
                currentTile = playerGrid.solveOneStep(currentTile);
                refreshBoardGUI();
                //updatePlayerGridFromGUI();

                // Sleep for .05 seconds before continuing
                try
                {
                    Thread.sleep(50);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            solver.interrupt();
        });
    }

    public void displayCorrectPanel()
    {
        switchPanel.removeAll();
        switchPanel.add(correctPanel);
        switchPanel.repaint();
        switchPanel.revalidate();
    }

    public void displayIncorrectPanel()
    {
        switchPanel.removeAll();
        switchPanel.add(incorrectPanel);
        switchPanel.repaint();
        switchPanel.revalidate();
    }

    public void displayGamePanel()
    {
        switchPanel.removeAll();
        switchPanel.add(gamePanel);
        switchPanel.repaint();
        switchPanel.revalidate();
    }

    public static void main(String[] args)
    {
        JFrame gameFrame = new JFrame("Sudoku");
        gameFrame.setContentPane(new gui().parentPanel);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
}
