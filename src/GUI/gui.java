package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.Code.grid;

public class gui
{
    static int TOTAL_N = 81;
    static int N = 9;
    static int SUB_N = 3;

    private JPanel parentPanel;
    private JPanel switchPanel;
    private JPanel gamePanel;
    private JToolBar gameToolBar;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton solveButton;
    private JPanel boardPanel;
    private JPanel gridPanel;
    private JButton validateButton;
    private JButton sudokuButton;

    private JPanel[][] panelGrid = new JPanel[SUB_N][SUB_N];
    private JTextField[][] textGrid = new JTextField[N][N];
    private grid currentGrid;

    public gui()
    {
        initBoardGUI();

        easyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentGrid = new grid(new int[]{4, 5});
                currentGrid.printEntireGrid();
                fillBoardGUI();
            }
        });

        mediumButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentGrid = new grid(new int[]{5, 6});
                currentGrid.printEntireGrid();
                fillBoardGUI();
            }
        });

        hardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentGrid = new grid(new int[]{6, 7});
                currentGrid.printEntireGrid();
                fillBoardGUI();
            }
        });
    }

    private void clearBoardGUI()
    {

    }

    private void fillBoardGUI()
    {
        // Set up textGrid
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                textGrid[i][j].setText("");

                if(currentGrid.getTiles()[i][j].getFixed())
                {
                    textGrid[i][j].setText(Integer.toString(currentGrid.getTiles()[i][j].getValue()));
                }
                else
                {
                    textGrid[i][j].setEditable(true);
                }
            }
        }
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
