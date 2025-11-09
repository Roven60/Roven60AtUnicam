package Progetti_Personali;

import java.util.Scanner;

public class _RvLib {

  public static int intInput(String[] args, int idx) {
    String s1;
    int num = 0;
    if (args.length > idx) {
      s1 = args[idx];
    } else {
      Scanner myObj = new Scanner(System.in);  // Create a Scanner object
      System.out.print("Please enter an integer number ");
      s1 = myObj.nextLine();  // Read user input
    }
    try {
      num = Integer.parseInt(s1);
    } catch (NumberFormatException e) {
      System.out.printf("Invalid input [%s]: need an integer number!", s1);
      System.out.println();
    }
    return num;
  }

  public static long longInput(String[] args, int idx) {
    String s1;
    long num = 0;
    if (args.length > idx) {
      s1 = args[idx];
    } else {
      Scanner myObj = new Scanner(System.in);  // Create a Scanner object
      System.out.print("Please enter an integer number ");
      s1 = myObj.nextLine();  // Read user input
    }
    try {
      num = Long.parseLong(s1);
    } catch (NumberFormatException e) {
      System.out.printf("Invalid input [%s]: need an integer number!", s1);
      System.out.println();
    }
    return num;
  }

  public static double doubleInput(String[] args, int idx) {
    String s1;
    double num = 0;
    if (args.length > idx) {
      s1 = args[idx];
    } else {
      Scanner myObj = new Scanner(System.in);  // Create a Scanner object
      System.out.print("Please enter a number ");
      s1 = myObj.nextLine();  // Read user input
    }
    try {
      num = Double.parseDouble(s1);
    } catch (NumberFormatException e) {
      System.out.printf("Invalid input [%s]: need a number!", s1);
      System.out.println();
    }
    return num;
  }

  public static String stringInput(String[] args, int idx) {
    String s1;
    if (args.length > idx) {
      s1 = args[idx];
    } else {
      Scanner myObj = new Scanner(System.in);  // Create a Scanner object
      System.out.print("Please enter a String ");
      s1 = myObj.nextLine();  // Read user input
    }
    return s1;
  }

  private static void hBorderPrint(int len, boolean numbered) {
    if (len > 10)
      numbered = false;
    if (numbered) {
      System.out.print(" ");
    }
    System.out.print('+');
    for (int ii = len; ii > 0; ii--) {
      System.out.print('-');
    }
    System.out.println('+');
  }

  public static void booleanArrayPrint(boolean[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i])
        System.out.print('*');
      else
        System.out.print(' ');
    }
  }

  public static void booleanMatrixPrint(boolean[][] arr) {
    booleanMatrixPrint(arr, false);
  }

  public static void booleanMatrixPrint(boolean[][] arr, boolean numbered) {
    if (arr[0].length > 10)
      numbered = false;
    if (numbered) {
      System.out.print("  ");
      for (int j = 0; j < arr[0].length; j++) {
        System.out.print(j);
      }
      System.out.println();
    }
    hBorderPrint(arr[0].length, numbered);
    for (int ii = 0; ii < arr.length; ii++) {
      if (numbered) {
        System.out.print(ii);
      }
      System.out.print('|');
      booleanArrayPrint(arr[ii]);
      System.out.println('|');
    }
    hBorderPrint(arr[0].length, numbered);
  }

  public static void intArrayPrint(int[] arr) {
    for (int ii = 0; ii < arr.length; ii++) {
      System.out.print(arr[ii] + "  ");
    }
  }

  public static void intBubbleSort(int[] nums) {    /* Bubble Sort  without library */
    int last = nums.length - 1;
    while (last > 0) {
      for (int ii = 1; ii <= last; ii++) {
        if (nums[ii - 1] > nums[ii]) {
          int n0 = nums[ii];
          nums[ii] = nums[ii - 1];
          nums[ii - 1] = n0;
        }
      }
      last--;
      //PrintArray(nums);
    }
  }

  public static void stringArrayPrint(String[] arr) {
    //----- Calculate max string length -----//
    int len = 0;
    for (int ii = 0; ii < arr.length; ii++) {
      if (arr[ii].length() > len) {
        len = arr[ii].length();
      }
    }
    stringArrayPrint(arr, len);
  }

  public static void stringArrayPrint(String[] arr, int len) {
    String fmt = "%" + len + "s ";
    for (int i = 0; i < arr.length; i++) {
      System.out.printf(fmt, arr[i]);
    }
  }

  public static void stringMatrixPrint(String[][] texts) {
    //----- Calculate max string length -----//
    int maxlen = 0;
    for (int row = 0; row < texts.length; row++) {
      for (int col = 0; col < texts[row].length; col++) {
        if (texts[row][col].length() > maxlen) {
          maxlen = texts[row][col].length();
        }
      }
    }
    //----- Prints rows of columns -----//
    for (int row = 0; row < texts.length; row++) {
      stringArrayPrint(texts[row], maxlen);
      System.out.println();
    }
  }

  public static void stringBubbleSort(String[] text) {    /* Bubble Sort  without library */
    int last = text.length - 1;
    while (last > 0) {
      for (int ii = 1; ii <= last; ii++) {
        if (text[ii - 1].compareTo(text[ii]) > 0) {
          String s0 = text[ii];
          text[ii] = text[ii - 1];
          text[ii - 1] = s0;
        }
      }
      last--;
      //PrintArray(nums);
    }
  }


}
