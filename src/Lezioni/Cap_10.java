package Lezioni;

public class Cap_10 {

  static int fattoriale(int n) {
    int res = 1;
    for (int i = 2; i <= n; i++) {
      res *= i;
    }
    return res;
  }

  static int fibonacci(int k) {
    int res = 0;
    int n = 1;
    while (k > 0) {
      n = n + res;
      res = n - res;
      k--;
    }
    return res;
  }

  static double potenzaR(double base, int esp) {
    return (esp == 0 ? 1 : base * potenzaR(base, esp - 1));
  }

  static double potenzaI(double base, int esp) {
    double res = 1;
    for (int i = 1; i <= esp; i++) {
      res *= base;
    }
    return res;
  }

  static void potenza() {
    double base = Math.random() * 1000;
    int esp = (int) (Math.random() * 100);
    long tStart;
    //
    tStart = System.currentTimeMillis();
    double resR = potenzaR(base, esp);
    long tR = System.currentTimeMillis() - tStart;
    StdOut.println(base + "^" + esp + " =R> " + resR + " (costo " + tR + ")");
    tStart = System.currentTimeMillis();
    double resI = potenzaI(base, esp);
    long tI = System.currentTimeMillis() - tStart;
    StdOut.println(base + "^" + esp + " =R> " + resI + " (costo " + tI + ")");
  }

  static String toBase2(int n1) {
    return (n1 >= 2 ? toBase2(n1 / 2) + (n1 % 2) : "" + n1);
  }

  static char toCharN(int n1) {
    return (char) (n1 < 10 ? '0' + n1 : n1 < 36 ? 'A' + n1 - 10 : '?');
  }

  static String toBaseN(int n1, int base) {
    if (n1 < 0)
      return "-" + toBaseN(-n1, base);
    if (n1 >= base)
      return toBaseN(n1 / base, base) + toCharN(n1 % base);
    if (n1 < 10)
      return "" + n1;
    return "" + toCharN(n1);
  }

  static void toBase() {
    int base = 2;
    for (int ii = 0; ii < 37; ii += 5) {
      System.out.print(ii + " => " + toBaseN(ii, base) + "   ");
      System.out.print(ii + 1 + " => " + toBaseN(ii + 1, base) + "   ");
      System.out.print(ii + 2 + " => " + toBaseN(ii + 2, base) + "   ");
      System.out.print(ii + 3 + " => " + toBaseN(ii + 3, base) + "   ");
      System.out.print(ii + 4 + " => " + toBaseN(ii + 4, base));
      System.out.println();
    }
    base = 8;
    for (int ii = 0; ii < 37; ii += 5) {
      System.out.print(ii + " => " + toBaseN(ii, base) + "   ");
      System.out.print(ii + 1 + " => " + toBaseN(ii + 1, base) + "   ");
      System.out.print(ii + 2 + " => " + toBaseN(ii + 2, base) + "   ");
      System.out.print(ii + 3 + " => " + toBaseN(ii + 3, base) + "   ");
      System.out.print(ii + 4 + " => " + toBaseN(ii + 4, base));
      System.out.println();
    }
    base = 16;
    for (int ii = 0; ii < 37; ii += 5) {
      System.out.print(ii + " => " + toBaseN(ii, base) + "   ");
      System.out.print(ii + 1 + " => " + toBaseN(ii + 1, base) + "   ");
      System.out.print(ii + 2 + " => " + toBaseN(ii + 2, base) + "   ");
      System.out.print(ii + 3 + " => " + toBaseN(ii + 3, base) + "   ");
      System.out.print(ii + 4 + " => " + toBaseN(ii + 4, base));
      System.out.println();
    }
  }

  /*static boolean hasDivider(int n, int k) {  //con n > k
    if (k < 2)
      return true;
    if (n % k == 0)
      return false;
    return hasDivider(n, k - 1);
    }*/

  static boolean hasDivider(int n, int a, int b) {
    if ((b < a) || (n < a)) return false;
    if (n % a == 0) return true;
    return hasDivider(n, a+1, n / a);
  }

  static boolean isPrime(int n1) {
    /*
    if (n1 <= 1)
      return false;
    return hasDivider(n1, (int) Math.sqrt(n1));
    */
    return !hasDivider(n1, 2, n1 - 1);

  }

  static void ex_10_4() {
    int n1 = (int) (Math.random() * 1000);
    System.out.println(n1 + " => " + (isPrime(n1) ? "" : "NON ") + "è numero primo");
  }

  public static void main(String[] args) {
    for (int i = 1; i < 100; i++) {
      System.out.println(i + (isPrime(i) ? " IS prime" : ""));
    }
  }


}
