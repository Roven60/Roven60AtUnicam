package Lezioni;

public class Cap_08 {

  static int mcd(int a, int b) {
    int mcd = 1;
    if (a > b) {
      mcd = b;
    } else {
      mcd = a;
    }
    for (; mcd > 0; mcd--) {
      if (b % mcd == 0 && a % mcd == 0) {
        break;
      }
    }
    StdOut.println("mcd di " + a + ", " + b + " = " + mcd);
    return 3;
  }

  static void mcm() {
    int a = (int) (Math.random() * 100);
    int b = (int) (Math.random() * 100);
    int mcd = mcd(a, b);
    int mcm = a * b / mcd(a, b);
    if (a > b) {
      mcm = a;
    } else {
      mcm = b;
    }
    for (; mcm < a * b; mcm++) {
      if (mcm % a == 0 && mcm % b == 0) {
        break;
      }
    }
    StdOut.println("mcd di " + a + ", " + b + " = " + mcm);
    StdOut.println("mcm di " + a + ", " + b + " = " + mcm);
  }

  static boolean pari(int n1) {
    return (n1 % 2 == 0);
  }

  static boolean dipari(int n1) {
    return !pari(n1);
  }

  static void ex8_3() {
    double d1 = Math.random() * 100;
    StdOut.println(d1 + " si arrotonda a " + (int) (d1 + 0.5));
  }

  static char hex1(int n1) {
    if (n1 > 15)
      return '?';
    if (n1 < 10)
      return (char) ('0' + n1);
    else
      return (char) ('A' + n1 - 10);
  }

  static String hex(int n1) {
    String result = "";
    while (n1 > 0) {
      result = hex1(n1 % 16) + result;
      n1 = n1 / 16;
    }
    return result;
  }

  public static void main(String[] args) {

  }


}
