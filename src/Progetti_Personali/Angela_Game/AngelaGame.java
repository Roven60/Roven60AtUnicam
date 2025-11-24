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
  static int human = 0;
  static int computer = 0;
  static int progressive = 0;


  //****************//
  //*** ROUTINES ***//
  //****************//

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
   * Method for getting target value from human
   *
   * @return the target value
   */
  static void getTarget() {
    while (target < 7 && target != -1) {
      target = getIntInput("Obiettivo [30 ... 100]", 30, 100);
    }
  }

  static String getProgressRow() {
    return String.format("%3s   %3s   %3s  ", human, computer, progressive);
  }

  /**
   * Method to get human move
   *
   * @return human move
   */
  static boolean getHumanMove() {
    String msg;
    if (computer == 0)
      msg = "                  Numero ";
    else
      msg = String.format("%s numero (non %d ne %d)", getProgressRow(), computer, (7 - computer));
    human = 0;
    while (human < 1 || human > 7 || human == computer || human == (7 - computer)) {
      human = getIntInput(msg, 1, 6);
    }
    progressive += human;
    if (progressive == target) {
      System.out.println("*** Vabbè: hai Vinto. ***");
      return true;
    }
    if (progressive > target) {
      System.out.println("***** Ho vinto! *****");
      return true;
    }
    return false;
  }

  /**
   * Method to get computer move
   *
   * @return computer move
   */
  static boolean getComputerMove() {
    computer = 0;
    int remaining = target - progressive;
    switch (remaining) {
      case 11:
        if (human != 4 && human != 3)
          computer = 4;
        else
          computer = 2;
        break;
      case 10:
        if (human != 5 && human != 2)
          computer = 5;
        else
          computer = 1;
        break;
      case 9: // vincita non sicura
        if (human != 6 && human != 1)
          computer = 1; //forse
        else
          computer = 2; //senza speranza
        break;
      case 8:
        if (human != 4 && human != 3)
          computer = 4;
        else
          computer = 1; //senza speranza
        break;
      case 7:
        if (human != 6 && human != 1)
          computer = 6;
        else
          computer = 1; //senza speranza
        break;
      case 6, 5, 4, 3, 2, 1:
        if (human != remaining && human != (7 - remaining))
          computer = remaining;
        else
          computer = 1; //senza speranza
    }
    if (computer == 0) {  //non ancora definito (remaining > 11)
      //TODO
      if(human != 6 && human != 1 )
        computer = 1;
      else
        computer = 2;
    }
    progressive += computer;
    if (progressive == target) {
      System.out.println(getProgressRow() + "***** Ho vinto! *****");
      return true;
    }
    if (progressive > target) {
      System.out.println(getProgressRow() + "*** Vabbè: hai Vinto. ***");
      return true;
    }
    return false;
  }


  /***   M A I N   ***/
  static void main(String[] args) {
    System.out.println(INTRO);
    getTarget();
    int human = 0;
    int computer = 0;
    while (progressive < target) {
      if (getHumanMove())
        break;
      if (getComputerMove())
        break;
    }
  }

}
