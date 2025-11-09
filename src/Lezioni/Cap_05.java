package Lezioni;

public class Cap_05 {

  static void ex5_5() {
    int a = (int) (Math.random() * 100);
    int b = (int) Math.sqrt(a);
    if (b * b == a) {
      System.out.println(a + " = " + b + "^2");
    } else {
      System.out.println(a + " non è un quadrato perfetto");
    }
  }

  static void ex5_6() {
    int a = (int) (Math.random() * 1000);
    int b = (int) (Math.random() * (1000 - a)) + a;
    int c = (int) (Math.random() * 1000);
    int d = (int) (Math.random() * (1000 - c)) + c;
    System.out.println("Inputs: " + a + "..." + b + " U " + c + "..." + d);
    /* Casi possibili:
    //A B c d   -   A c B d   -   A c d B
    //c d A B   -   c A d B   -   c A B d
     */
    if (a <= c) { //A ? ? ?
      if (b < c) { //A B   C D
        StdOut.println("Non è un intervallo");
      } else { //A BC D o A C B D
        if (b < d) {  //A C B D
          StdOut.println("Intervallo (ACBD): " + a + ", " + d);
        } else {  //A C D B
          StdOut.println("Intervallo (ACDB): " + a + ", " + b);
        }
      }
    } else {  //C ? ? ?
      if (d < a) { //C D   A B
        StdOut.println("Non è un intervallo");
      } else {  //C AD B o C A D B
        if (d < b) {  //C A D B
          StdOut.println("Intervallo (CADB): " + c + ", " + b);
        } else {  //C A B D
          StdOut.println("Intervallo (CABD): " + c + ", " + d);
        }
      }
    }
  }


  public static void main(String[] args) {
  }


}
