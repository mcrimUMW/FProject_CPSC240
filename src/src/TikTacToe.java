import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


    public class TikTacToe implements ActionListener {

        private JFrame frame;
        private JButton[][] buttons;
        private JPanel boardPanel, buttonPanel;
        private boolean AxisX = true;
        private JLabel messageLabel;


        public TikTacToe() {
            frame = new JFrame(" The Tik Tac Toe Mania");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Scanner input=new Scanner(System.in);
            System.out.println("Choose from the following game size option " +
                    "A. 3 by 3 game " +
                    "B. 5 by 5");
            String reply=input.next();



            boardPanel = new JPanel(new GridLayout(3, 3));
            if(reply.equals("A")) {
                buttons = new JButton[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int b = 0; b < 3; b++) {
                        buttons[i][b] = new JButton("");
                        buttons[i][b].setFont(new Font("Serif", Font.ITALIC, 50));
                        buttons[i][b].addActionListener(this);
                        boardPanel.add(buttons[i][b]);
                    }
                }
            }

            else{
                buttons = new JButton[5][5];
                for (int i = 0; i < 5; i++) {
                    for (int b = 0; b < 5; b++) {
                        buttons[i][b] = new JButton("");
                        buttons[i][b].setFont(new Font("Serif", Font.ITALIC, 50));
                        buttons[i][b].addActionListener(this);
                        boardPanel.add(buttons[i][b]);
                    }
                }

            }

            buttonPanel = new JPanel(new FlowLayout());
            messageLabel = new JLabel("It's X's turn");
            messageLabel.setFont(new Font("Serif", Font.ITALIC, 20));
            buttonPanel.add(messageLabel);

            frame.add(boardPanel, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.setSize(400, 400);
            frame.setVisible(true);
        }

        private void WinnerChecker() {
            String winningPlayer = "";
            if (RowCheck() || ColumnCheck() ||  DiagonalCheck()) {
                if (AxisX) {
                    winningPlayer = "O";
                } else {
                    winningPlayer = "X";
                }
                messageLabel.setText(winningPlayer + " wins!");
                disableButtons();

            } else if (SpaceCapacity()) {
                messageLabel.setText("Tied!");
            }
        }

        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("")) {
                if (AxisX) {
                    button.setText("X");
                    messageLabel.setText("It's O's turn");
                } else {
                    button.setText("O");
                    messageLabel.setText("It's X's turn");
                }
                AxisX = !AxisX;
                WinnerChecker();
            }
        }


        private boolean RowCheck() {
            for (int rc = 0; rc < 3; rc++) {
                if (!buttons[rc][0].getText().equals("") && buttons[rc][0].getText().equals(buttons[rc][1].getText()) && buttons[rc][0].getText().equals(buttons[rc][2].getText())) {
                    return true;
                }
            }
            return false;
        }

        private boolean ColumnCheck() {
            for (int cc = 0; cc < 3; cc++) {
                if (!buttons[0][cc].getText().equals("") && buttons[0][cc].getText().equals(buttons[1][cc].getText()) && buttons[0][cc].getText().equals(buttons[2][cc].getText())) {
                    return true;
                }

            }

            return false;
        }


        private boolean DiagonalCheck() {
            if (!buttons[0][0].getText().equals("") && buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[0][0].getText().equals(buttons[2][2].getText())) {
                return true;
            }
            if (!buttons[0][2].getText().equals("") && buttons[0][2].getText().equals(buttons[2][0].getText())) {
                return true;
            }
            return false;
        }

        private boolean SpaceCapacity() {
            for (int s = 0; s < 3; s++) {
                for (int c = 0; c < 3; c++) {
                    if (buttons[s][c].getText().equals("")) {
                        return false;
                    }
                }
            }

            return true;
        }


        private void disableButtons() {
            for (int d = 0; d < 3; d++) {
                for (int b = 0; b < 3; b++) {
                    buttons[d][b].setEnabled(false);
                }
            }
        }
        private  int topScore;

        private void loadTopScore() {
            try {
                File file = new File("topscore.txt");
                if (file.exists()) {
                    Scanner scanner = new Scanner(file);

                    topScore = scanner.nextInt();

                } else {
/* I am going to make it so the user inputs a name for the top score!!!
Maybe make it top 3?
 */
                    topScore = 0;

                    PrintWriter writer = new PrintWriter(file);
                    writer.println(topScore);
                }
            } catch (Exception e) {
                System.err.println("Error");
            }
        }
        private void saveTopScore() {
            try {
                PrintWriter writer = new PrintWriter("topscore.txt");

                writer.println(topScore);

            } catch (Exception e) {
                System.err.println("Error");
            }
        }

    }


