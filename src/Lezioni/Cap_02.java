package Lezioni;

public class Cap_02 {

  static void ex2_3() {
    int raggio = 1024;
    double circo = 2 * raggio * Math.PI;
    double area = raggio * raggio * Math.PI;
    System.out.println("Circonferenza del cerchio di raggio " + raggio + " = " + circo);
    System.out.println("Area del cerchio di raggio " + raggio + " = " + area);
  }

  static void ex2_4() {
    int enne = (int) (Math.random() * Integer.MAX_VALUE);
    double res = (enne * (enne + 1)) / 2;
    System.out.println("(" + enne + " * (" + enne + " + 1 )) / 2 = " + res);
  }

  static void ex2_5a() {
    int enne = (int) (Math.random() * Integer.MAX_VALUE);
    int resto2 = enne % 2;
    System.out.println(enne + " è " + (char) ('P' + resto2));
  }

  static void ex2_5b() {
    int enne = (int) (Math.random() * Integer.MAX_VALUE);
    int resto2 = (enne % 2) * ('D' - 'P');
    System.out.println(enne + " è " + (char) ('P' + resto2));
  }


  public static void main(String[] args) {

  }

}
