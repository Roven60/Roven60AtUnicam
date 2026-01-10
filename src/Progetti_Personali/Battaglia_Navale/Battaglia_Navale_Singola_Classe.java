package Progetti_Personali.Battaglia_Navale;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * This class implements the BattleShip game between human and computer;
 * both them plays at the same time (every turn)
 * <p>
 * You can simply "run" and enjoy the game or change some parameter (first 5 {@code final} rows)
 * and study the game logic. Sorry: texts are in italian.
 *
 * @author Roberto Venturi - Unicam student - accademic year 2025/26
 *
 */
public class Battaglia_Navale_Singola_Classe {

  static final int SIZE = 10;  // Grid size (personalizable)
  static final boolean SHOWMYSHIPS = false;  //if true prints out computer ships (not honest for playing)
  static final boolean DEBUGMODE = false;   //if true prints out debug messages
  static final boolean RANDOMHUMAN = false;  //if true human is supposed to fire randomly without logic
  static final boolean SAMESHIPS = false;    //if true human ships are supposed to be as computer's ones

  static final String INTRO =
      "E' il classico gioco della Battaglia Navale.\r\n"
          + "In una griglia " + SIZE + " x " + SIZE + " posizioniamo le navi, 1 per tipo, con lunghezza da 1 a " + (SIZE - 1) + "\r\n"
          + "le navi possono toccare i bordi ma non toccarsi tra loro.\r\n"
          + "Si segnala se nave colpita o no; non si segnala se affondata.\r\n"
          + "(ad inizio codice le costanti da personalizzare)";
  static final String SEPARATOR = "   ";  // video messages spacing
  static final byte EMPTY = ' ';
  static final byte HOLE = '.';
  static final byte SHIP = 'S';
  static final byte HIT = '*';
  static char[][] mySea = new char[SIZE][SIZE]; //computer battlefield
  static char[][] yourSea = new char[SIZE][SIZE]; //human battlefield
  static int shipCells = 0; //total cells with ship in every sea
  static int mySeaHitCells = 0;  //total Your successfully hits
  static int yourSeaHitCells = 0;  //total My successfully hits
  static boolean scanning = false; //true if there was a hit and scanning for whole ship
  static char scanDir = 'L'; // the ways to scan for ship (Left, Right, Up, Down)
  static int shipSize = 0;  //current scanning ship
  static int myRow = 0, myCol = 0;
  static int myHi1Row = 0, myHit1Col = 0;

  //*************************//
  //*** TERMINAL ROUTINES ***//
  //*************************//
  static void print(char msg) {
    System.out.print(msg);
  }

  static void print(String msg) {
    System.out.print(msg);
  }

  static void println(char msg) {
    System.out.println(msg);
  }

  static void println(String msg) {
    System.out.println(msg);
  }

  static void println() {
    System.out.println();
  }

  static String getUserInput() {
    Scanner myObj = new Scanner(System.in);
    return myObj.nextLine().toUpperCase();
  }

  //****************//
  //*** ROUTINES ***//
  //****************//

  /*** set all matrix cell as EMPTY ***/
  static void emptyMatrix(char[][] arr) {
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        arr[row][col] = EMPTY;
      }
    }
  }

  static void setUp() {
    println(INTRO);
    //*** Init Seas ***//
    emptyMatrix(yourSea);
    if (DEBUGMODE) println("----- Posizionmento navi - INIZIO -----");
    boolean shipOk = false;
    while (!shipOk) {
      emptyMatrix(mySea);
      for (int ii = 6; ii > 0; ii--) {
        if (ii < SIZE) {
          shipOk = putShip(ii);
          if (!shipOk)
            break;
        }
      }
    }
    if (DEBUGMODE) println("----- Posizionmento navi - FINE -----");
  }

  /*** return true if there is a ship to the left or to the right of given row, col ***/
  static boolean shipLeftOrRight(char[][] sea, int row, int col) {
    if (col > 0 && sea[row][col - 1] == SHIP) { //touching ship left
      return true;
    }
    return col < SIZE - 1 && sea[row][col + 1] == SHIP; //touching ship right
  }

  /*** return true if there is a ship in the cell up or down of given row, col ***/
  static boolean shipUpOrDown(char[][] sea, int row, int col) {
    if (row > 0 && sea[row - 1][col] == SHIP) { //touching ship top
      return true;
    }
    return row < SIZE - 1 && sea[row + 1][col] == SHIP; //touching ship down
  }

  /*** try to put a ship of given length in mySea starting at row, col moving UP  ***/
  static boolean tryToPutShipUp(int row, int col, int shipLen) {
    if (row < SIZE - 1 && mySea[row + 1][col] == SHIP) {
      return false;  //there is a touching ship just 1 cell down
    }
    for (int ii = 0; ii < shipLen; ii++) {
      if (row - ii < 0 || mySea[row - ii][col] != EMPTY || shipLeftOrRight(mySea, row - ii, col)) {
        return false;
      }
    }
    if (row - shipLen >= 0 && mySea[row - shipLen][col] == SHIP) {
      return false;
    }
    //ok to put ship vertical up
    if (DEBUGMODE) println("Ship " + shipLen + " UP from " + row + ", " + col);
    for (int ii = 0; ii < shipLen; ii++) {
      mySea[row - ii][col] = SHIP;
    }
    return true;
  }

  /*** try to put a ship of given length in mySea starting at row, col moving DOWN  ***/
  static boolean tryToPutShipDown(int row, int col, int shipLen) {
    if (row > 0 && mySea[row - 1][col] == SHIP) {
      return false;  //there is a touching ship just1 cell up
    }
    for (int ii = 0; ii < shipLen; ii++) {
      if (row + ii >= SIZE || mySea[row + ii][col] == SHIP || shipLeftOrRight(mySea, row + ii, col)) {
        return false;
      }
    }
    if (row + shipLen < SIZE && mySea[row + shipLen][col] == SHIP) {
      return false;
    }
    //ok to put ship vertical down
    if (DEBUGMODE) println("Ship " + shipLen + " DOWN from " + row + ", " + col);
    for (int ii = 0; ii < shipLen; ii++) {
      mySea[row + ii][col] = SHIP;
    }
    return true;
  }

  /*** try to put a ship of given length in mySea starting at row, col moving LEFT  ***/
  static boolean tryToPutShipLeft(int row, int col, int shipLen) {
    if (col < SIZE - 1 && mySea[row][col + 1] == SHIP) {
      return false;  //there is a touching ship just right
    }
    for (int ii = 0; ii < shipLen; ii++) {
      if (col - ii < 0 || mySea[row][col - ii] == SHIP || shipUpOrDown(mySea, row, col - ii)) {
        return false;
      }
    }
    if (col - shipLen >= 0 && mySea[row][col - shipLen] == SHIP) {
      return false;
    }
    //ok to put ship horizontal LEFT
    if (DEBUGMODE) println("Ship " + shipLen + " LEFT from " + row + ", " + col);
    for (int ii = 0; ii < shipLen; ii++) {
      mySea[row][col - ii] = SHIP;
    }
    return true;
  }

  /*** try to put a ship of given length in mySea starting at row, col moving RIGHT  ***/
  static boolean tryToPutShipRight(int row, int col, int shipLen) {
    if (col > 0 && mySea[row][col - 1] == SHIP) {
      return false;  //there is a touching ship just left
    }
    for (int ii = 0; ii < shipLen; ii++) {
      if (col + ii >= SIZE || mySea[row][col + ii] == SHIP || shipUpOrDown(mySea, row, col + ii)) {
        return false;
      }
    }
    if (col + shipLen < SIZE && mySea[row][col + shipLen] == SHIP) {
      return false;
    }
    //ok to put ship
    if (DEBUGMODE) println("Ship " + shipLen + " RIGHT from " + row + ", " + col);
    for (int ii = 0; ii < shipLen; ii++) {
      mySea[row][col + ii] = SHIP;
    }
    return true;
  }

  /*** tries to put a ship of given length in mySea ***/
  static boolean tryToPutShip(int shipLen) {
    int row, col;
    //--- Find first empty cell with no ship around ---//
    do {
      row = (int) (SIZE * Math.random());
      col = (int) (SIZE * Math.random());
      if (DEBUGMODE) print('.');
    } while (mySea[row][col] != EMPTY);
    //--- Choose a causal direction to follow ---//
    if (Math.random() < 0.5) {  // vertical / horizontal
      if (Math.random() < 0.5) {  // Try to put ship vertical
        if (!tryToPutShipUp(row, col, shipLen)) return false;
      } else {
        if (!tryToPutShipDown(row, col, shipLen)) return false;
      }
    } else {  // Try to put ship horizontal
      if (Math.random() < 0.5) {
        if (!tryToPutShipLeft(row, col, shipLen)) return false;
      } else {
        if (!tryToPutShipRight(row, col, shipLen)) return false;
      }
    }
    //--- Updates number of cells with a ship
    shipCells += shipLen;
    return true;
  }

  /*** put a ship of given length in mySea ***/
  static boolean putShip(int shipLen) {
    /* There are some combination of already put ship that avoid to put a new one
        so we try few times then start again from scratch */
    int maxTries = 20;
    while (!tryToPutShip(shipLen)) {
      if (DEBUGMODE) print('X');
      maxTries--;
      if (maxTries == 0) {
        if (DEBUGMODE) println("---ABORTED---");
        return false;
      }
    }
    return true;
  }

  /*** write to Stdout the border (top or bottom one) of battlefield ***/
  static void hBorderPrint() {
    for (int mx = 0; mx < 2; mx++) {
      print(" +");
      for (int ii = SIZE; ii > 0; ii--) {
        print('-');
      }
      print('+' + SEPARATOR);
    }
    println();
  }

  /*** write to Stdout computer and human battlefields ***/
  static void matrixesPrint() {
    for (int mx = 0; mx < 2; mx++) {
      print("  ");
      for (int ii = 0; ii < SIZE; ii++) {
        print((char) ('A' + ii));
      }
      print(" " + SEPARATOR);
    }
    println();
    hBorderPrint();
    for (int row = 0; row < SIZE; row++) {
      //Print mysea row
      print(row + "|");
      for (int col = 0; col < mySea[row].length; col++) {
        if (!SHOWMYSHIPS && mySea[row][col] == SHIP)
          print((char) EMPTY);
        else
          print(mySea[row][col]);
      }
      print('|' + SEPARATOR + row + '|');
      for (int col = 0; col < yourSea[row].length; col++) {
        print(yourSea[row][col]);
      }
      println('|');
    }
    hBorderPrint();
  }

  /*** perform human move: gets human input and write the result  ***/
  static void humanMove() {
    String s1 = "";
    //******      Simulating random human if test mode
    if (RANDOMHUMAN) {
      s1 += (char) ('A' + (int) (Math.random() * SIZE));
      s1 += (char) ('0' + (int) (Math.random() * SIZE));
      println("Human move " + s1);
    }
    while (true) {
      while (s1.length() != 2) {
        print("Colonna e Riga (2 caratteri)? ");
        s1 = getUserInput();
      }
      if (s1.charAt(0) < 'A' || s1.charAt(0) >= 'A' + SIZE) {
        println("Il primo carattere deve essere tra 'A' e '" + (char) ('A' + SIZE - 1) + "' compresi");
        s1 = "";
        continue;
      }
      if (s1.charAt(1) < '0' || s1.charAt(1) >= '0' + SIZE) {
        println("Il secondo carattere deve essere tra '0' e '" + (char) ('0' + SIZE - 1) + "' compresi");
        s1 = "";
        continue;
      }
      break;
    }
    int playerCol = s1.charAt(0) - 'A';
    int playerRow = s1.charAt(1) - '0';
    if (mySea[playerRow][playerCol] == HIT || mySea[playerRow][playerCol] == HOLE) {
      print("ATTENTO: avevi già sparato qui!" + SEPARATOR);
    } else if (mySea[playerRow][playerCol] == EMPTY) {
      mySea[playerRow][playerCol] = HOLE;
    } else {  // SHIP hit
      print("Colpita!" + SEPARATOR);
      mySea[playerRow][playerCol] = HIT;
      mySeaHitCells++;
    }
  }

  /*** fire to row, col ; and return if is a hit or not after setting cells according ***/
  static boolean fireTo(int row, int col) {
    boolean isAHit;
    if (SAMESHIPS) {
      print((char) (col + 'A') + ", " + (char) (row + '0') + " Colpita? ");
      isAHit = (mySea[row][col] == SHIP || mySea[row][col] == HIT);
      if (isAHit) println('S');
      else println('N');
    } else {
      char c1 = '?';
      while (c1 != 'S' && c1 != 'N' && c1 != 'T' && c1 != 'F' && c1 != '1' && c1 != '0') {
        print((char) (col + 'A') + ", " + (char) (row + '0') + " Colpita? ");
        String s1 = getUserInput();
        c1 = s1.charAt(0);
      }
      isAHit = (c1 == 'S' || c1 == 'T' || c1 == '1');
    }
    if (isAHit) {
      yourSea[row][col] = HIT;
      yourSeaHitCells++;
      shipSize++;
    } else {
      yourSea[row][col] = HOLE;
    }
    return isAHit;
  }

  /*** get a random EMPTY cell and fires to it ***/
  static void fireToRandomCell() {
    if (DEBUGMODE) print("Not scanning: random move ");
    do {
      myRow = (int) (SIZE * Math.random());
      myCol = (int) (SIZE * Math.random());
    } while (yourSea[myRow][myCol] != EMPTY);
    scanning = fireTo(myRow, myCol);
    if (scanning) {
      myHi1Row = myRow;
      myHit1Col = myCol;
    }
  }

  /*** all current ship hit: clean up the scanning system ***/
  static void shipSunk() {
    println("Ho affondato una nave da " + shipSize + "!");
    shipSize = 0;
    scanning = false;
    scanDir = 'L';
    //TODO put only area around ships as already HIT without scanning entire grid
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        if (yourSea[row][col] == HIT) {
          if (row > 0 && yourSea[row - 1][col] == EMPTY)
            yourSea[row - 1][col] = HOLE;
          if (row < SIZE - 1 && yourSea[row + 1][col] == EMPTY)
            yourSea[row + 1][col] = HOLE;
          if (col > 0 && yourSea[row][col - 1] == EMPTY)
            yourSea[row][col - 1] = HOLE;
          if (col < SIZE - 1 && yourSea[row][col + 1] == EMPTY)
            yourSea[row][col + 1] = HOLE;
        }
      }
    }
  }


  /*** Scans for whole ship after a successful hit. Try left ***/
  static void scanLeft() {
    if (DEBUGMODE) print("Scanning LEFT   ");
    //--- scan LEFT
    if (myCol == 0 || yourSea[myRow][myCol - 1] != EMPTY) { //ship can't be left
      myCol = myHit1Col;  //restart from first hit to current ship
      scanDir = 'R'; //no left cell empty: go to scan right
      scanRight();
    } else {
      myCol--;
      boolean isAHit = fireTo(myRow, myCol);
      if (!isAHit) { //no more ship left
        myCol = myHit1Col;  //restart from first hit to current ship
        scanDir = 'R';  //next move scan right
      }
    }
  }

  /*** Scans for whole ship after a successful hit. Try right ***/
  static void scanRight() {
    if (DEBUGMODE) print("Scanning RIGHT   ");
    if (myCol == SIZE - 1 || yourSea[myRow][myCol + 1] != EMPTY) {
      if (shipSize == 1) {  //may be vertical ship
        scanDir = 'U';
        scanUp();
      } else {//whole ship scanned
        shipSunk();
        fireToRandomCell();
      }
    } else {
      myCol++;
      boolean isAHit = fireTo(myRow, myCol);
      if (!isAHit) {
        if (shipSize == 1) {  //may be vertical ship
          myCol = myHit1Col;  //restart from first hit to current ship
          scanDir = 'U';  //next move scan up
        } else {  //whole ship scanned
          shipSunk();
        }
      } else if (myCol == SIZE - 1) {
        shipSunk();
      }
    }
  }

  /*** Scans for whole ship after a successful hit. Try up ***/
  static void scanUp() {
    if (DEBUGMODE) print("Scanning UP   ");
    if (myRow == 0 || yourSea[myRow - 1][myCol] != EMPTY) {
      myRow = myHi1Row;  //restart from first hit to current ship
      scanDir = 'D'; //no upper cell: scan down
      scanDown();
    } else {
      myRow--;
      boolean isAHit = fireTo(myRow, myCol);
      if (!isAHit) {
        myRow = myHi1Row;   //restart from first hit to current ship
        scanDir = 'D';  //next move scan down
      }
    }

  }

  /*** Scans for whole ship after a successful hit. Try down ***/
  static void scanDown() {
    if (DEBUGMODE) print("Scanning DOWN   ");
    if (myRow == SIZE - 1 || yourSea[myRow + 1][myCol] != EMPTY) {
      shipSunk();
      fireToRandomCell();
    } else {
      myRow++;
      boolean isAHit = fireTo(myRow, myCol);
      if (!isAHit) {
        shipSunk();
      }
    }
  }

  /*** plays a computer move. Implements the game logic for computer ***/
  static void computerMove() {
    if (!scanning) { //not scanning: fire to a random cell
      fireToRandomCell();
      return;
    }
    //After a hit scans for the whole ship trying Left, Right, Up and Down.
    // TODO: random direction will be better?
    switch (scanDir) {
      case 'L':
        scanLeft();
        break;
      case 'R':
        scanRight();
        break;
      case 'U':
        scanUp();
        break;
      case 'D':
        scanDown();
        break;
    }
  }

  /*** return true if all computer's or human's ship are hit ***/
  static boolean noMoreShips() {
    if (DEBUGMODE) println("Your hits: " + mySeaHitCells + " / " + shipCells);
    boolean yourWin = mySeaHitCells == shipCells; //allShipsSunk(mySea);
    if (DEBUGMODE) println("My hits: " + yourSeaHitCells + " / " + shipCells);
    boolean myWin = yourSeaHitCells == shipCells; //allShipsSunk(yourSea);
    if (yourWin && myWin) {
      matrixesPrint();
      println("*****************************");
      println("*   E' finita in pareggio   *");
      println("*                           *");
      println("*   Ma io sono più bravo!   *");
      println("*****************************");
      return true;
    }
    if (yourWin) {
      matrixesPrint();
      println("******************");
      println("*   Hai vinto.   *");
      println("*                *");
      println("* Tutta fortuna! *");
      println("******************");
      return true;
    }
    if (myWin) {
      matrixesPrint();
      println("************************************");
      println("*           Ho vinto!!!            *");
      println("*                                  *");
      println("* Modestamente sono proprio bravo! *");
      println("************************************");
      return true;
    }
    return false;
  }

  /* return true if there are less empty cell than ships to sink
  sometimes humans can give wrong answer ... */
  static boolean wrongHumanAnswer() {
    int shipsLeft = shipCells - yourSeaHitCells;
    int emptyCells = 0;
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        if (yourSea[row][col] == EMPTY)
          emptyCells++;
      }
    }
    if (DEBUGMODE) println("Your sea has " + emptyCells + " empty cells and " + shipsLeft + " ships");
    if (emptyCells < shipsLeft) {
      matrixesPrint();
      println("************************************");
      println("*                                  *");
      println("*   Credo che tu mi abbia dato     *");
      println("*   una o più risposte sbagliate   *");
      println("*   circa l'aver colpito o meno!   *");
      println("*                                  *");
      println("*   Dev'essere stato un errore:    *");
      println("*   non voglio neppure pensare     *");
      println("*                                  *");
      println("*       CHE TU ABBIA BARATO        *");
      println("*                                  *");
      println("************************************");
      return true;
    }
    return false;

  }

  /***   M A I N   ***/
  static void main(String[] args) {
    setUp();  //empties sead and put ships on mySea
    boolean battleEnded = false;
    //*** LET'S PLAY ***//
    while (!battleEnded) {
      matrixesPrint();
      humanMove();  //*** PLAYER TURN ***//
      computerMove(); //*** COMPUTER TURN ***//
      //*** END TESTS ***//
      battleEnded = noMoreShips() || wrongHumanAnswer();
    }
  }

}
