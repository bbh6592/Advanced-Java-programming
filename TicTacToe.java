import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe implements ActionListener {
  JButton[] btns = new JButton[10];
  JFrame frame = new JFrame("Tic Tac Toe");
  Label label = new Label("");
  JPanel board = new JPanel();
  JPanel panel = new JPanel();
  String letter = "";
  int count = 0;
  boolean win = false;
  public TicTacToe() {
    initUI();
    manipulateActionEvents(true);
  }
  public void actionPerformed(ActionEvent a) {
    count++;
    if (count <= 9) {
      if (count % 2 != 0) {
        letter = "X";
      } else {
        letter = "O";
      }
    }
    for (int i = 0; i < 9; i++) {
      if (a.getSource() == btns[i]) {
        btns[i].setFont(new Font("Arial", Font.PLAIN, 40));
        btns[i].setForeground(Color.decode("#fefbef"));
        btns[i].setBackground(count % 2 != 0 ? Color.decode("#ff4136") : Color.decode("#357edd"));
        btns[i].setText(letter);
        btns[i].removeActionListener(this);
      }
    }
    if (a.getSource() == btns[9]) {
      letter = "";
      count = 0;
      for (int i = 0; i < 9; i++) {
        btns[i].addActionListener(this);
        btns[i].setBackground(Color.decode("#fefbef"));
        btns[i].setText("");
      }
      label.setText("");
      win = false;
    }
    checkWinner();
  }
  private void initUI() {
    board.setLayout(new GridLayout(3, 3));
    panel.setLayout(new FlowLayout());
    for (int i = 0; i < 10; i++) {
      btns[i] = new JButton("");
      btns[i].setBackground(Color.decode("#fefbef"));
    }
    panel.setBackground(Color.decode("#fefbef"));

    btns[9].setText("RESET");
    for (int i = 0; i < 9; i++) {
      btns[i].setBorder(
        BorderFactory.createLineBorder(Color.decode("#808080"))
      );
      board.add(btns[i]);
    }
    panel.add(btns[9]);
    panel.add(label);
    frame.add(board, BorderLayout.CENTER);
    frame.add(panel, BorderLayout.SOUTH);
    frame.setVisible(true);
    frame.setSize(550, 550);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  public void manipulateActionEvents(Boolean status) {
    for (int i = 0; i < (status ? 10 : 9); i++) {
      if (status) {
        btns[i].addActionListener(this);
      } else {
        btns[i].removeActionListener(this);
      }
    }
  }
  private Boolean isEmpty(String val) {
    return val == "";
  }
  private Boolean validateValues(String val1, String val2, String val3) {
    Boolean ret = !isEmpty(val1) && !isEmpty(val2) && !isEmpty(val3) && val1 == val2 && val2 == val3;
    return ret;
  }
  private void checkWinner() {
    win =
      // Vertical

      validateValues(btns[3].getText(), btns[4].getText(), btns[5].getText()) ||
      validateValues(btns[6].getText(), btns[7].getText(), btns[8].getText()) ||
      // Diagonal
      validateValues(btns[0].getText(), btns[3].getText(), btns[6].getText()) ||
      validateValues(btns[1].getText(), btns[4].getText(), btns[7].getText()) ||
      validateValues(btns[2].getText(), btns[5].getText(), btns[8].getText()) ||
      // Horizontal
      validateValues(btns[0].getText(), btns[4].getText(), btns[8].getText()) ||
      validateValues(btns[2].getText(), btns[4].getText(), btns[6].getText()) ||
      validateValues(btns[2].getText(), btns[5].getText(), btns[8].getText());
    if (win) {
      label.setText("Hurray! Player " + letter + " wins!");
      manipulateActionEvents(false);
    } else if (!win && count == 9) {
      label.setText("The game ended in a tie.");
      manipulateActionEvents(false);
    }
  }
  public static void main(String[] args) {
    new TicTacToe();
  }
}
