package Progetti_Personali.MineSweeper;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class MineSweeper_Singola_Classe {
  static final int ATO0 = 17;
  static final char BOMB = '*';
  static final int LVLGAME = 0;
  static final int LVLSHOW = 1;
  static final int LVLDEBUG = 2;

  static int ROWS = 10;
  static int COLS = 10;
  static int MINES = 5;
  static char[][] field;
  static int usrRow, usrCol;

  //*************************//
  //*** TERMINAL ROUTINES ***//
  //*************************//

  static void print(char msg) {
    System.out.print(msg);
  }

  static void println(char msg) {
    System.out.println(msg);
  }

  static void print(String msg) {
    System.out.print(msg);
  }

  static void println(String msg) {
    System.out.println(msg);
  }

  static void print(int msg) {
    System.out.print(msg);
  }

  static void println() {
    System.out.println();
  }

  //**********************//
  //*** OTHER ROUTINES ***//
  //**********************//

  static void settings() {
    String msg = "Game difficulty:\n"
        + "1=Easy 9x9 10 bombs\n"
        + "2=Hard 16x16 40 bombs\n"
        + "3=Expert 16x30 99 bombs";
    println(msg);
    Scanner myObj = new Scanner(System.in);
    int choice = -1;
    while (choice < 1 || choice > 3) {
      print("Choice [1..3]: ");
      try {
        choice = myObj.nextInt();
      } catch (NoSuchElementException | IllegalStateException e) {
        //TODO
      }
    }
    switch (choice) {
      case 1:
        ROWS = 9;
        COLS = 9;
        MINES = 10;
        break;
      case 2:
        ROWS = 16;
        COLS = 16;
        MINES = 40;
        break;
      case 3:
        ROWS = 16;
        COLS = 30;
        MINES = 99;
        break;
    }
    field = new char[ROWS][COLS];
  }

  static void printHBorder() {
    print(ROWS > 10 ? "  +" : " +");
    for (int col = 0; col < COLS; col++) {
      print('-');
    }
    println('+');
  }

  static void printField(int level) {
    print(ROWS > 10 ? "   " : "  ");
    if (COLS > 10)
      for (int col = 1; col <= COLS; col++) {
        if (col >= 10)
          print((int) (col / 10));
        else
          print(' ');
      }
    println();
    print(ROWS > 10 ? "   " : "  ");
    for (int col = 1; col <= COLS; col++) {
      print((int) col % 10);
    }
    println();
    printHBorder();
    for (int row = 0; row < ROWS; row++) {
      if (ROWS > 10) {
        if (row + 1 >= 10)
          print((int) ((row + 1) / 10));
        else
          print(' ');
      }
      print((int) (row + 1) % 10);
      print('|');
      for (int col = 0; col < COLS; col++) {
        if (level == LVLDEBUG) {
          print(field[row][col]);
        } else if (field[row][col] == '0' + ATO0) {
          print(' ');
        } else if (field[row][col] > '9') {
          print((char) (field[row][col] - ATO0));
        } else if (field[row][col] == BOMB && level == LVLSHOW)
          print('*');
        else
          print('.');
      }
      println('|');
    }
    printHBorder();
  }

  static void putMines() {
    int mines = MINES;
    int prob = (ROWS * COLS) / MINES;
    while (mines > 0) {
      for (int row = 0; row < ROWS; row++) {
        for (int col = 0; col < COLS; col++) {
          if (field[row][col] != BOMB && (int) (Math.random() * prob) == 0) {
            field[row][col] = BOMB;
            mines--;
          }
          if (mines == 0)
            break;
        }
        if (mines == 0)
          break;
      }
    }
  }

  static void evaluateField() {
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        if (field[row][col] != BOMB) {
          int nearMines = 0;
          if (row > 0) {
            nearMines += field[row - 1][col] == BOMB ? 1 : 0;
            if (col > 0) {
              nearMines += field[row - 1][col - 1] == BOMB ? 1 : 0;
            }
          }
          if (row < ROWS - 1) {
            nearMines += field[row + 1][col] == BOMB ? 1 : 0;
            if (col > 0) {
              nearMines += field[row + 1][col - 1] == BOMB ? 1 : 0;
            }
          }
          if (col > 0) {
            nearMines += field[row][col - 1] == BOMB ? 1 : 0;
          }
          if (col < COLS - 1) {
            nearMines += field[row][col + 1] == BOMB ? 1 : 0;
            if (row > 0) {
              nearMines += field[row - 1][col + 1] == BOMB ? 1 : 0;
            }
            if (row < ROWS - 1) {
              nearMines += field[row + 1][col + 1] == BOMB ? 1 : 0;
            }
          }
          field[row][col] = (char) (nearMines + '0');
        }
      }
    }
  }

  static void getHumanMove() {
    usrRow = usrCol = 0;
    Scanner myObj = new Scanner(System.in);
    String msg = String.format("Riga [1..%d] e colonna [1..%d] separate da spazio: ", ROWS, COLS);
    while (usrRow < 1 || usrCol < 1 || usrRow > ROWS || usrCol > COLS) {
      print(msg);
      try {
        usrRow = myObj.nextInt();
        usrCol = myObj.nextInt();
      } catch (NoSuchElementException | IllegalStateException e) {
        //TODO
      }
    }
    usrRow--;
    usrCol--;
  }

  static boolean clearCell(int row, int col) {
    if (row < 0 || row >= ROWS || col < 0 || col >= COLS)
      return false;
    if (field[row][col] == '0' + ATO0)
      return false;
    if (field[row][col] >= '0' && field[row][col] <= '8')
      field[row][col] += ATO0;
    if (field[row][col] == '0' + ATO0)
      return true;
    return false;
  }

  static void clearAround(int row, int col) {
    if (clearCell(row, col))
      clearAround(row, col);
    if (clearCell(row - 1, col - 1))
      clearAround(row - 1, col - 1);
    if (clearCell(row - 1, col))
      clearAround(row - 1, col);
    if (clearCell(row - 1, col + 1))
      clearAround(row - 1, col + 1);
    if (clearCell(row, col - 1))
      clearAround(row, col - 1);
    if (clearCell(row, col + 1))
      clearAround(row, col + 1);
    if (clearCell(row + 1, col - 1))
      clearAround(row + 1, col - 1);
    if (clearCell(row + 1, col))
      clearAround(row + 1, col);
    if (clearCell(row + 1, col + 1))
      clearAround(row + 1, col + 1);
  }

  static void main() {
    settings();
    putMines();
    //printField(LVLDEBUG); //for debug only!!!
    evaluateField();
    //printField(LVLDEBUG); //for debug only!!!
    println();
    while (true) {
      printField(LVLGAME);
      getHumanMove();
      if (field[usrRow][usrCol] == BOMB) {
        println("Mina colpita!");
        printField(LVLSHOW);
        break;
      }
      if (clearCell(usrRow, usrCol))
        clearAround(usrRow, usrCol);
    }
  }
}
