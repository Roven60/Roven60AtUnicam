package Progetti_Personali.Angela_Game;

import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Questo gioco fu presentato come programma "demo" per la Olivetti Programma 101 (sigla P101)
 * tale macchina fu sviluppata dalla Olivetti tra il 1962 e il 1964 e prodotta tra il 1965 e il 1971
 * senza voler accendere polemiche, viene da molti considerata come il primo personal computer della storia
 * o, quanto meno, il primo desk-top computer.
 * <p>
 * Angela Game è stato il primo gioco per computer della storia.
 * Fu programmato nel 1965 per il P101 in linguaggio esclusivamente numerico
 * e utilizzava tutta la memoria del calcolatore, 240 bytes.
 * Non ce ne voglia il celebre Spacewar! del MIT, conosciuto per essere il primo videogioco della storia:
 * il computer necessario per farlo funzionare occupava un'intera stanza e costava 120.000 dollari.
 * Per Angela's Game, nato solo quattro anni dopo, era sufficiente una scrivania
 * e i 3.200 dollari necessari per acquistare il personal computer di Olivetti.
 * <p>
 * Si tratta di un gioco matematico, dove l'utente sfida il computer,
 * la sfida consiste nel raggiungimento di un numero che si decide come obbiettivo di gioco.
 * Utente e computer giocano (alternativamente) un numero compreso tra 1 e 6 (come fosse un dado)
 * e tale numero viene sommato ai precedenti giocati da entrambi i giocatori.
 * Scopo del gioco è raggiungere (con l'ultimo numero giocato) il valore scelto inizialmente come obiettivo o
 * costringere l'avversario a superare tale obiettivo.
 * Regole: non si può giocare lo stesso numero dell'avversario ne il suo complementare a 7 (faccia opposta del dado)
 * Esempio: obiettivo 8 - Umano gioca 4 il computer non può giocare 4 (che lo farebbe vincere) ne 3 (7-4)
 * <p>>
 * La logica di gioco del computer è esposta nel sito di Pier Giorgio Perotto (il suo creatore) con un emulatore
 * della P101 che utilizza i vari registri della P101. Qui ho provato a studiare il gioco ed estrapolare una mia
 * logica di gioco che potrebbe o meno assomigliare all'algoritmo originale.
 *
 * @author Roberto Venturi aka Roven60
 */
public class AngelaGame {

  static final String INTRO =
      """
          Angela Game: primo "computer game" della storia!\r
          Scelto un numero come "obiettivo", giocatore e computer, a turno,\r
          giocano un numero tra 1 e 6 (come se fosse un dado)\r
          che viene sommato ai numeri precedenti (di entrambi)\r
          Scopo è giocare il numero che, sommato, ottenga il valore "obiettivo"\r
          o forzare l'avversario a superare tale valore (sballare)\r
          \r
          """;
  static int target = 0;
  static int human = -1;
  static int computer = -1;
  static int progressive = 0;
  static final int[][] goodMoves = {  //remaining and good move(s)
      {18, 1, 4}, //1 OK, 4+3 = 3/4
      {17, 4, 3}, //4 OK, 3+1 = 3/4
      {16, 5, 4}, //OK
      {15, 6, 3}, //OK
      {14, 5, 6}, //5 OK, 6+3+1 = 3/4
      {13, 4, 2}, //OK
      {12, 4, 2}, //4 OK, 2+1 = 3/4
      {11, 3, 2}, //OK
      {10, 5, 1}, //OK
      {9, 3, 1},  //3+1 = 3/4
      {8, 4, 3},  //4 OK, 3 = 2/3
      {7, 6, 4},  //OK
      {6, 6, 3},  //OK
      {5, 5, 2},  //5 OK, 2 = 1/2
      {4, 4, 2},  //4 OK
      {3, 3, 2},  //3 OK
      {2, 2, 1},  //OK
      {1, 1, 2},  //1 OK
  };

  //****************//
  //*** ROUTINES ***//
  //****************//

  /**
   * Metodo che chiede un numero intero all'utente
   *
   * @param msg messaggio da mostrare all'utente
   * @param min numero minimo accettabile
   * @param max numero massimo accettabile
   * @return il numero immesso dall'utente
   */
  static int getIntInput(String msg, int min, int max) {
    String s1;
    int num = min - 1;
    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    while (num < min || num > max) {
      System.out.print(msg + ": ");
      s1 = myObj.nextLine();  // Read user input
      try {
        num = Integer.parseInt(s1);
      } catch (NumberFormatException e) {
        System.out.printf("Invalid input [%s]: need an integer number [%d ... %d]!\n", s1, min, max);
      }
    }
    return num;
  }

  /**
   * Metodo che chiede all'utente il valore da usare come obiettivo
   *
   * @return il valore (intero) dell'obiettivo
   */
  static void getTarget() {
    while (target < 7 && target != -1) {
      target = getIntInput("Obiettivo [30 ... 100]", 30, 100);
    }
  }

  /**
   * Metodo di comodo che fornisce la riga di intestazione
   *
   * @return riga dell'intestazione del gioco
   */
  static String getHeaderRow() {
    return "Hum.  CPU   Tot. ";
  }

  /**
   * Metodo di comodo per formattare la riga di gioco
   *
   * @return stringa di testo con le mosse e il parziale
   */
  static String getProgressRow() {
    return String.format("%3s   %3s   %3s  ", human, computer, progressive);
  }

  /**
   * Metodo che chiede all'utente il numero da giocare
   */
  static void getHumanMove() {
    String msg;
    if (computer == -1) { //first move
      msg = "                  Numero (0 = passo)";
      human = -1;
      while (human < 0 || human > 6) {
        human = getIntInput(msg, 0, 6);
      }
      System.out.println(getHeaderRow());
      if (human == 0)
        return;
    } else {
      msg = String.format("%s numero (non %d ne %d)", getProgressRow(), computer, (7 - computer));
      human = 0;
      while (human < 1 || human > 6 || human == computer || human == (7 - computer)) {
        human = getIntInput(msg, 1, 6);
      }
    }
    progressive += human;
  }

  /**
   * Metodo che calcola il numero da giocare per il computer
   */
  static void getComputerMove() {
    computer = 0;
    int remaining = target - progressive;
    // una ricerca binaria sarebbe più efficiente ma vale la pena?
    for (int ii = 0; ii < goodMoves.length; ii++) {
      if (goodMoves[ii][0] == remaining) {
        if (human != goodMoves[ii][1] && human != (7 - goodMoves[ii][1]))
          computer = goodMoves[ii][1];
        else {
          if (goodMoves[ii].length > 2)
            computer = goodMoves[ii][2];
        }
        break;
      }
    }
    if (computer == 0) {  //non abbiamo mosse studiate: che fare?
      //TODO
      if (human != 6 && human != 1)
        computer = 1;
      else
        computer = 2;
    }
    progressive += computer;
  }


  /***   M A I N   ***/
  static void main(String[] args) {
    System.out.println(INTRO);
    getTarget();
    while (progressive < target) {
      getHumanMove();
      if (progressive == target) {
        System.out.println("*** Vabbè: hai Vinto. ***");
        break;
      }
      if (progressive > target) {
        System.out.println("***** Hai sballato ed io ho vinto! *****");
        break;
      }
      getComputerMove();
      if (progressive == target) {
        System.out.println(getProgressRow() + "***** Ho vinto! *****");
        break;
      }
      if (progressive > target) {
        System.out.println(getProgressRow() + "*** ho sballato: hai Vinto. ***");
        break;
      }
    }
  }

}
