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
    private JPanel topMenuPanel;
    private JPanel bottomMenuPanel;
    private JPanel switchPanel;
    private JLabel difficultyLogoImage;
    private JLabel easyLogoImage;
    private JLabel mediumLogoImage;
    private JLabel hardLogoImage;
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
            gridPanel.add(new JLabel());
        }
    }
}
