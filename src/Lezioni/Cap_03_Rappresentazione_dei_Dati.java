package Lezioni;

public class Cap_03_Rappresentazione_dei_Dati {

  public class Cap_03 {

    static void ex3_3() {
      int x = (int) (Math.random() * 100) - 20;
      if (x < 0 || x > 64) {
        System.out.println(x + " è fuori scala");
      } else {
        int bits = (int) (Math.log(x) / Math.log(2) + 0.9999);
        System.out.println("per rappresentare " + x + " occorrono " + bits + " bits");
      }
    }

    static void ex3_4() {
      int x = (int) (Math.random() * 100) - 20;
      if (x < 0 || x > 64) {
        System.out.println(x + " è fuori scala");
      } else {
        int bit1 = 0;
        int x2 = x;
        while (x2 > 1) {
          bit1 += (x2 % 2);
          x2 /= 2;
        }
        if (x2 == 1)
          bit1++;
        System.out.println(Integer.toBinaryString(x));
        System.out.println("per rappresentare " + x + " occorrono " + bit1 + " bits a 1");
      }
    }

    static void ex3_5() {
      int n1 = (int) (Math.random() * 100);
      int n2 = (int) (Math.random() * 100);
      int n3 = (int) (Math.random() * 100);
      int inv = 0;
      if (n2 > n1)
        inv++;
      if (n3 > n2)
        inv++;
      if (n3 > n1)
        inv++;
      System.out.println("Input: " + n1 + ", " + n2 + ", " + n3 + " =>  " + inv + " inversioni");
    }


    public static void main(String[] args) {

    }

  }


    public static void main(String[] args) {
        double a = 3.14159;
        System.out.println("a=" + a);
        System.out.println("a=+1=" + ((a+1)));
        System.out.println("8/(int)a=" + (8/(int) a));
        System.out.println("8/a=" + (8/a));
        System.out.println("(int)8/a=" + ((int) (8/a)));
    }
}