package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class gui
{
    static int TOTAL_N = 81;
    static int N = 9;
    static int SUB_N = 3;

    private JPanel parentPanel;
    private JPanel switchPanel;
    private JPanel gamePanel;
    private JToolBar gameToolBar;
    private JLabel sudokuLogoImage;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton solveButton;
    private JPanel boardPanel;
    private JPanel gridPanel;
    private JTextField[][] textGrid = new JTextField[N][N];

    public gui()
    {
        initializeGridGUI();
    }

    private void initializeGridGUI()
    {
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                textGrid[i][j] = new JTextField();
            }
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

    private void createUIComponents()
    {
        /*textGrid = new JTextField[N][N];
        Border tileBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                textGrid[i][j] = new JTextField();
                textGrid[i][j].setBorder(tileBorder);
            }
        }

        boardPanel.add(textGrid);*/

        Border tileBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        gridPanel.setLayout(new GridLayout(N, N));

        for(int i = 0; i < TOTAL_N; i++)
        {
            gridPanel.add(new JLabel("0"));
        }

        gridPanel.setVisible(true);
    }
}
