package Lezioni;

import java.util.Arrays;

public class Cap_11 {

  static void inverti_11_1a(int[] a) {
    for (int i = 0; i < a.length / 2; i++) {
      int buf = a[i];
      a[i] = a[a.length - i - 1];
      a[a.length - i - 1] = buf;
    }
  }

  static int[] inverti_11_1b(int[] a) {
    int[] b = new int[a.length];
    for (int i = 0; i < a.length; i++)
      b[a.length - i - 1] = a[i];
    return b;
  }

  static int min_11_2(int[] a) {
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < a.length; i++) {
      if (a[i] < min)
        min = a[i];
    }
    return min;
  }

  static int max_11_2(int[] a) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < a.length; i++) {
      if (a[i] > max)
        max = a[i];
    }
    return max;
  }

  // Restituisce l'array con l'elemento k inserito in poszione i //
  static int[] inserisci(int k, int[] a, int i) {
    if (i > a.length)
      return null;
    int[] a2 = new int[a.length + 1];
    for (int j = 0; j < i; j++)
      a2[j] = a[j];
    a2[i] = k;
    for (int j = i; j < a.length; j++)
      a2[j + 1] = a[j];
    return a2;
  }

  static void ex_11() {
    int[] a = new int[]{8, 2, 5, 4, 9, 10, 7, 6, 1, 3};
    //System.out.println(Arrays.toString(a));
    /*
    inverti_11_1a(a);
    System.out.println();

    System.out.println(Arrays.toString(a));
    System.out.println(Arrays.toString(inverti_11_1b(a)));
    */
    //System.out.println(" min is " + min_11_2(a) + " max is " + max_11_2(a));
  }

  static int[] merge(int [] a, int []b) {
    int [] res = new int[a.length + b.length];
    int ia = 0;
    int ib = 0;
    for (int i = 0; i < res.length; i++) {
      if (ia < a.length && ib < b.length) {
        if (a[ia] < b[ib]) {
          res[i] = a[ia];
          ia++;
        } else {
          res[i] = a[ia];
          ia++;

        }
        //if () {}
      }
    }
    return res;
  }

  public static void main(String[] args) {
    int[] uno = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    System.out.println(Arrays.toString(uno));
    inverti_11_1a(uno);
    System.out.println(Arrays.toString(uno));
  }



}
