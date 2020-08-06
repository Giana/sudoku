package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.Code.game;

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

    game currentGame;

    public gui()
    {
        currentGame = new game(0);
        createUIComponents();

        easyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentGame = new game(0);
            }
        });

        mediumButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentGame = new game(1);
            }
        });

        hardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentGame = new game(2);
            }
        });
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
        Border tileBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        Font boardFont = new Font("Monospaced", Font.BOLD, 20);
        gridPanel.setLayout(new GridLayout(N, N));

        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < N; j++)
            {
                textGrid[i][j] = new JTextField();
                textGrid[i][j].setBorder(tileBorder);
                textGrid[i][j].setBackground(Color.decode("#E4E4E4"));
                textGrid[i][j].setOpaque(true);
                textGrid[i][j].setFont(boardFont);
                textGrid[i][j].setHorizontalAlignment(JTextField.CENTER);

                if(currentGame.getPlayerBoard().getTiles()[i][j].getFixed())
                {
                    textGrid[i][j].setText(Integer.toString(currentGame.getPlayerBoard().getTiles()[i][j].getValue()));
                }
                gridPanel.add(textGrid[i][j]);
            }
        }
    }
}
