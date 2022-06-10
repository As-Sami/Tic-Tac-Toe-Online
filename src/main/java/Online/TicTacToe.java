/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Online;

/**
 *
 * @author Hp
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean myTurn;

    // networking area
    NetworkConnection nc;

    TicTacToe(NetworkConnection nc, boolean turn) {
        this.nc = nc;
        myTurn = turn;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setBackground(new Color(25, 250, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {

                if (myTurn == true) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        myTurn = false;
                        textfield.setText("its your opponents turn");
                        check();
                        
                        nc.sendString( Integer.toString(i) );
                        new Thread(new GetResponse()).start();
                    }
                } else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        myTurn = true;
                        textfield.setText("its your turn");
                        check();
                    }
                }
            }

        }

    }

    public void firstTurn() { 

        if (myTurn) {
            textfield.setText("its your turn");
        } else {
            textfield.setText("its your opponents turn");
            new Thread(new GetResponse()).start();
        }
    }

    public void check() {
        // check X win conditions
        if ((buttons[0].getText() == "X")
                && (buttons[1].getText() == "X")
                && (buttons[2].getText() == "X")) {

            XWins(0, 1, 2);
        } else if ((buttons[3].getText() == "X")
                && (buttons[4].getText() == "X")
                && (buttons[5].getText() == "X")) {

            XWins(3, 4, 5);
        } else if ((buttons[6].getText() == "X")
                && (buttons[7].getText() == "X")
                && (buttons[8].getText() == "X")) {

            XWins(6, 7, 8);
        } else if ((buttons[0].getText() == "X")
                && (buttons[3].getText() == "X")
                && (buttons[6].getText() == "X")) {

            XWins(0, 3, 6);
        } else if ((buttons[1].getText() == "X")
                && (buttons[4].getText() == "X")
                && (buttons[7].getText() == "X")) {

            XWins(1, 4, 7);
        } else if ((buttons[2].getText() == "X")
                && (buttons[5].getText() == "X")
                && (buttons[8].getText() == "X")) {

            XWins(2, 5, 8);
        } else if ((buttons[0].getText() == "X")
                && (buttons[4].getText() == "X")
                && (buttons[8].getText() == "X")) {

            XWins(0, 4, 8);
        } else if ((buttons[2].getText() == "X")
                && (buttons[4].getText() == "X")
                && (buttons[6].getText() == "X")) {

            XWins(2, 4, 6);
        }

        // check O win conditions
        else if ((buttons[0].getText() == "O")
                && (buttons[1].getText() == "O")
                && (buttons[2].getText() == "O")) {

            OWins(0, 1, 2);
        } else if ((buttons[3].getText() == "O")
                && (buttons[4].getText() == "O")
                && (buttons[5].getText() == "O")) {

            OWins(3, 4, 5);
        } else if ((buttons[6].getText() == "O")
                && (buttons[7].getText() == "O")
                && (buttons[8].getText() == "O")) {

            OWins(6, 7, 8);
        } else if ((buttons[0].getText() == "O")
                && (buttons[3].getText() == "O")
                && (buttons[6].getText() == "O")) {

            OWins(0, 3, 6);
        } else if ((buttons[1].getText() == "O")
                && (buttons[4].getText() == "O")
                && (buttons[7].getText() == "O")) {

            OWins(1, 4, 7);
        } else if ((buttons[2].getText() == "O")
                && (buttons[5].getText() == "O")
                && (buttons[8].getText() == "O")) {

            OWins(2, 5, 8);
        } else if ((buttons[0].getText() == "O")
                && (buttons[4].getText() == "O")
                && (buttons[8].getText() == "O")) {

            OWins(0, 4, 8);
        } else if ((buttons[2].getText() == "O")
                && (buttons[4].getText() == "O")
                && (buttons[6].getText() == "O")) {

            OWins(2, 4, 6);
        }

        else if ((buttons[0].getText() == "O" || buttons[0].getText() == "X")
                && (buttons[1].getText() == "O" || buttons[1].getText() == "X")
                && (buttons[2].getText() == "O" || buttons[2].getText() == "X")
                && (buttons[3].getText() == "O" || buttons[3].getText() == "X")
                && (buttons[4].getText() == "O" || buttons[4].getText() == "X")
                && (buttons[5].getText() == "O" || buttons[5].getText() == "X")
                && (buttons[6].getText() == "O" || buttons[6].getText() == "X")
                && (buttons[7].getText() == "O" || buttons[7].getText() == "X")
                && (buttons[8].getText() == "O" || buttons[8].getText() == "X")) {

            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }

            textfield.setText("No One Wins!!! Game Over!!!");
        }
    }

    public void XWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        textfield.setText("You win...!");

    }

    public void OWins(int a, int b, int c) {

        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        textfield.setText("You lose....");

    }

    class GetResponse implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  
            
            String s = nc.recieveString();
            int n = Integer.parseInt(s);
            buttons[n].doClick();
            return;
        }
    }
}
