package Progetti_Personali.Mastermind;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;

public class Mastermind_Singola_Classe {
  static final int NUMHOLES = 4; //number of holes (at moment only 4 is considered valid)
  static final char[] COLORS = {'A', 'B', 'C', 'D', 'E', 'F'};  // (between 2 and 8) N.B. array must be sorted!
  static final int COLORSNUM = COLORS.length;
  static final int ROUNDS = 20; //max number of round to guess the code

  static boolean DEBUGMODE = false;
  static boolean DEBUGPCLOGIC = false; //if true focus is on computer logic: human answer are simulated

  static final String VICTORYOUTCOME = "**********".substring(0, NUMHOLES);
  static final String USRPROMPT = "Codice (" + NUMHOLES + " caratteri " + Arrays.toString(COLORS) + "): ";

  static final String INTRO =
      "E' il classico gioco del Mastermind https://it.wikipedia.org/wiki/Mastermind.\n"
          + "Io genero un codice di " + NUMHOLES + " caratteri che tenterai di indovinare\n"
          + "I valori possibili sono " + Arrays.toString(COLORS) + "."
          + "Ogni volta ti dirò quante cifre hai indovinato al posto giusto (ma non quali ne dove)\n"
          + "e quante cifre hai indovinato NON al posto giusto (anche qui senza quali ne dove).\n"
          + "* = 1 cifra giusta al posto giusto, + = 1 cifra giusta al posto sbagliato,\n"
          + "RIPETO: la posizione dei * e dei + NON fa riferimento alla posizione\n\n";

  static String myCode = "";

  //static String[] allTries = new String[(int) Math.pow(COLORSNUM, NUMHOLES)]; // all possible combinations
  static String[] allTries;
  static String[] myTries = new String[ROUNDS]; //maximum number of tries
  static String[] myOutcomes = new String[ROUNDS]; //outcomes of every try


  //*************************//
  //*** TERMINAL ROUTINES ***//
  //*************************//
  static void print(String msg) {
    System.out.print(msg);
  }

  static void println(String msg) {
    System.out.println(msg);
  }

  static void println() {
    System.out.println();
  }

  static String getUserInput() {
    String res = "";
    if (DEBUGPCLOGIC) {
      // generate a random input
      for (int i = 0; i < NUMHOLES; i++) {
        char c = COLORS[(int) (Math.random() * COLORSNUM)];
        res += c;
      }
    } else {
      Scanner myObj = new Scanner(System.in);
      while (res.length() < NUMHOLES) {
        while (res.length() < NUMHOLES) {
          print(USRPROMPT);
          res = myObj.nextLine().toUpperCase();
        }
        for (int i = 0; i < NUMHOLES; i++) {
          if (Arrays.binarySearch(COLORS, res.charAt(i)) < 0) {
            res = "";
            break;
          }
        }
      }
    }
    return res;
  }

  static String getUserOutcome(String myTry) {
    String res = null;
    if (DEBUGPCLOGIC) {
      String oc = codeTest(myCode, myTry);
      println(myTry + " Risultato (* o +): " + oc);
      return oc;
    } else {
      Scanner myObj = new Scanner(System.in);
      while (res == null) {
        print(myTry + " Risultato (* o +): ");
        res = myObj.nextLine().toUpperCase();
        for (int i = 0; i < res.length(); i++) {
          if (res.charAt(i) != '*' && res.charAt(i) != '+') {
            res = null;
            break;
          }
        }
      }
    }
    return res;
  }

  //****************//
  //*** ROUTINES ***//
  //****************//

  static void myCodeGeneration() {
    myCode = "";
    for (int i = 0; i < NUMHOLES; i++) {
      myCode += COLORS[(int) (Math.random() * COLORSNUM)];
    }
  }

  static String[] add1Color(String[] sa) {
    String[] res;
    if (sa.length == 0) {
      res = new String[COLORSNUM];
      for (int i = 0; i < COLORSNUM; i++) {
        res[i] = "" + COLORS[i];
      }
    } else {
      res = new String[sa.length * COLORSNUM];
      for (int i = 0; i < COLORSNUM; i++) {
        for (int j = 0; j < sa.length; j++) {
          res[i * sa.length + j] = COLORS[i] + sa[j];
        }
      }
    }
    return res;
  }

  static void allTriesGeneration() {
    allTries = new String[0];
    for (int i = 0; i < NUMHOLES; i++) {
      allTries = add1Color(allTries);
    }
  }

  static void myTriesInit() {
    for (int i = 0; i < ROUNDS; i++) {
      myTries[i] = null;
    }
  }

  static void myStrategyInit() {
    if (NUMHOLES != 4 || COLORSNUM != 6)
      return;
    //only for standar game we try a strategy
    int type = (int) (Math.random() * 4);  //type of initial strategy
    int i1 = (int) (Math.random() * COLORSNUM);
    int i2 = i1;
    while (i2 == i1)
      i2 = (int) (Math.random() * COLORSNUM);
    int i3 = i1;
    while (i3 == i1 || i3 == i2)
      i3 = (int) (Math.random() * COLORSNUM);
    int i4 = i1;
    while (i4 == i1 || i4 == i2 || i4 == i3)
      i4 = (int) (Math.random() * COLORSNUM);
    // now we have 4 different pegs to try
    switch (type) {
      case 0:
        myTries[0] = "" + COLORS[i1] + COLORS[i1] + COLORS[i2] + COLORS[i2];
        myTries[1] = "" + COLORS[i3] + COLORS[i4] + COLORS[i4] + COLORS[i3];
        break;
      case 1:
        myTries[0] = "" + COLORS[i1] + COLORS[i2] + COLORS[i2] + COLORS[i1];
        myTries[1] = "" + COLORS[i3] + COLORS[i3] + COLORS[i4] + COLORS[i4];
        break;
      case 2:
        myTries[0] = "" + COLORS[i1] + COLORS[i2] + COLORS[i1] + COLORS[i2];
        myTries[1] = "" + COLORS[i3] + COLORS[i4] + COLORS[i4] + COLORS[i3];
        break;
      case 3:
        myTries[0] = "" + COLORS[i1] + COLORS[i2] + COLORS[i1] + COLORS[i2];
        myTries[1] = "" + COLORS[i3] + COLORS[i3] + COLORS[i4] + COLORS[i4];
        break;
    }
  }

  static void setUp() {
    if (NUMHOLES < 2 || NUMHOLES > 5) {
      throw new IllegalArgumentException("NUMHOLES deve essere tra 2 e 5 compresi!");
    }
    if (COLORSNUM < 2 || COLORSNUM > 8) {
      throw new IllegalArgumentException("Il numero dei PEGS deve essere tra 2 e 8 compresi!");
    }
    println(INTRO);
    myCodeGeneration();
    if (DEBUGMODE) println("-DBG- " + myCode);
    allTriesGeneration();
    myTriesInit();
    if (NUMHOLES == 4 && COLORSNUM == 6) {
      myStrategyInit();
    }
  }

  static String codeTest(String code, String test) {
    //if (DEBUGMODE) println("Codetest(" + code + ", " + test + ")  ");
    char[] ctCode = new char[NUMHOLES];
    char[] ctTest = new char[NUMHOLES];
    for (int i = 0; i < NUMHOLES; i++) {
      ctCode[i] = code.charAt(i);
      ctTest[i] = test.charAt(i);
    }
    String res = "";
    for (int i = 0; i < NUMHOLES; i++) {
      if (ctCode[i] == ctTest[i]) {
        res += "*";
        ctCode[i] = '?';  //any not numeric char (myCode different from myTest)
        ctTest[i] = '!';  //any not numeric char (myCode different from myTest)
        //if (DEBUGMODE) println("i:" + i + " res:" + res + " " + Arrays.toString(ctCode) + " " + Arrays.toString(ctTest));
      }
    }
    for (int i = 0; i < NUMHOLES; i++) {
      for (int j = 0; j < NUMHOLES; j++) {
        if (ctCode[i] == ctTest[j]) {
          res += "+";
          ctCode[i] = '?';
          ctTest[j] = '!';
          //if (DEBUGMODE) println("i:" + i + " j:" + j + " res:" + res + " " + Arrays.toString(ctCode) + " " + Arrays.toString(ctTest));
        }
      }
    }
    return res;
  }

  static String getRandomTry() {
    if (DEBUGPCLOGIC) System.out.print("allTries.length=" + allTries.length +"   ");
    if (allTries.length < 1) {
      println();
      println("************************************");
      println("*                                  *");
      println("*   Credo che tu mi abbia dato     *");
      println("*   una o più risposte sbagliate   *");
      println("*                                  *");
      println("*   Dev'essere stato un errore:    *");
      println("*   non voglio neppure pensare     *");
      println("*                                  *");
      println("*       CHE TU ABBIA BARATO        *");
      println("*                                  *");
      println("************************************");
      exit(-1);
    }
    int i = (int) (Math.random() * allTries.length);
    return allTries[i];
  }

  static void removeInvalidTries(String myTry, String outcome) {
    // for every try in allTries test my actual try and relative outcome
    // first pass we set null all invalid tries
    int validTries = allTries.length;
    for (int i = 0; i < allTries.length; i++) {
      String oc = codeTest(allTries[i], myTry);
      if (!oc.equals(outcome)) {
        allTries[i] = null;
        validTries--;
      }
    }
    if (validTries <1) {
      validTries = 0;
    }
    // second pass collect only valid tries
    String[] reminingTries = new String[validTries];
    int idx = 0;
    for (int i = 0; i < allTries.length; i++) {
      if (allTries[i] != null) {
        reminingTries[idx] = allTries[i];
        idx++;
      }
    }
    allTries = reminingTries;
  }

  static String getMyTry(int tryNum) {
    switch (tryNum) {
      case 0:
        if (myTries[0] != null)
          return myTries[0];
        //else
        return getRandomTry();
      case 1:
        if (myOutcomes[0].length() < 4) {
          if (myTries[1] != null)
            return myTries[1];
          // else
          return getRandomTry();
        } else {
          println("Abbiamo i colori ma non in giusta posizione");
          return getRandomTry();
        }
      default:
        return getRandomTry();
    }
  }

  static void partita() {
    setUp();
    String usrSpacer = "                    ".substring(0, NUMHOLES * 2 + 2);
    String mySpacer = "                                                        ".substring(0, USRPROMPT.length() + NUMHOLES + 4);
    int round = 0;
    while (round < ROUNDS) {
      // human play
      print(usrSpacer);
      String usrTry = getUserInput();
      String usrOutcome = codeTest(myCode, usrTry);
      print(usrTry + " " + usrOutcome + "          ".substring(0, NUMHOLES - usrOutcome.length()) + " ");
      //computer play
      print(mySpacer);
      myTries[round] = getMyTry(round);
      myOutcomes[round] = getUserOutcome(myTries[round]);
      removeInvalidTries(myTries[round], myOutcomes[round]);
      //--- Is game ended?
      if (usrOutcome.equals(VICTORYOUTCOME) && myOutcomes[round].equals(VICTORYOUTCOME)) {
        println("Abbiamo indovintato entrambi in " + (round + 1) + " tentativi");
        break;
      }
      if (usrOutcome.equals(VICTORYOUTCOME)) {
        println("Hai indovintato in " + (round + 1) + " tentativi");
        break;
      }
      if (myOutcomes[round].equals(VICTORYOUTCOME)) {
        println("Ho indovintato in " + (round + 1) + " tentativi");
        break;
      }
      // No: let's go on
      round++;
    }
    println("===========================");
  }

  /*
      T E S T   S E C T I O N
   */
  private static int t = 0;

  static <T> void test(T x, T y) {
    System.out.println("test " + (t++) + " " + (x.equals(y) ? "OK" : "NO"));
  }

  static void testCodeTest() {
    String guess = "4223";
    test(codeTest(guess, "0402"), "++  ");
    test(codeTest(guess, "0200"), "*   ");
    test(codeTest(guess, "0203"), "**  ");
    test(codeTest(guess, "2222"), "**  ");
    test(codeTest(guess, "2002"), "++  ");
    test(codeTest(guess, "4222"), "*** ");
    test(codeTest(guess, "4223"), "****");

  }

  static void main(String[] args) {
//    testCodeTest();

//    setUp();
//    println(Math.pow(COLORSNUM, NUMHOLES) + " " + Arrays.toString(allTries));
//    println(allTries.length + " " + Arrays.toString(allTries));

    partita();
  }


}
