package Lezioni;

import java.util.Arrays;

public class Cap_12_Permutazioni {
  /*
    Esercizio per il 03/11/2025
   */

  // Restituisce l'array fornito, con l'elemento k inserito in poszione i //
  static int[] inserisci(int k, int[] a, int i) {
    if (i > a.length) return null;
    int[] a2 = new int[a.length + 1];
    for (int j = 0; j < i; j++) a2[j] = a[j];
    a2[i] = k;
    for (int j = i; j < a.length; j++) a2[j + 1] = a[j];
    return a2;
  }

  static int[][] permutazioni(int n) {
    if (n == 1) {
      return new int[][]{{1}};
    }
    int[][] sub = permutazioni(n - 1);
    int[][] res = new int[sub.length * n][];
    for (int ri = 0; ri < n; ri++) {
      for (int si = 0; si < sub.length; si++) {
        res[n * si + ri] = inserisci(n, sub[si], ri);

      }
    }
    return res;
  }

  static void main(String[] args) {
    int[][] res = permutazioni(4);
    for (int i = 0; i < res.length; i++) {
      System.out.println(Arrays.toString(res[i]));
    }
  }


}
