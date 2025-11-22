package Lezioni;

public class Cap_13_ClassiOggetti {

  public static class Orologio {
    int ore = 0;
    int minuti = 0;

    public Orologio(int ore, int minuti) {
      if (ore > 0 && ore < 23) {
        this.ore = ore;
      }
      if (minuti > 0 && minuti < 59) {
        this.minuti = minuti;
      }
    }

    public int get_ora() {
      return ore;
    }

    public int get_minuti() {
      return minuti;
    }

    public void tick() {
      minuti++;
      if (minuti > 59) {
        ore++;
        minuti = 0;
      }
      if (ore > 23) {
        ore = 0;
      }
    }

  } //Orologio end

  public static class Recipiente {
    private int volume;     // 0 <= volume private int contenuto;
    private int contenuto;  // 0 <= contenuto <= volume ...
    private boolean aperto;

    public Recipiente(int quantita) { // quantita >= 0
      volume = quantita;
      contenuto = 0;
      aperto = false;
    }

    public int get_volume() {
      return volume;
    }

    public int get_contenuto() {
      return contenuto;
    }

    public int capacita() {
      return volume - contenuto;
    }

    public void apri() {
      aperto = true;
    }

    public void chiudi() {
      aperto = false;
    }

    public void aggiungi(int quantita) { // 0 <= quantita
      if (aperto)
        contenuto = Math.min(contenuto + quantita, volume);
    }

    public void rimuovi(int quantita) { // 0 <= quantita
      if (aperto)
        contenuto = Math.max(contenuto - quantita, 0);
    }
  }  //Recipiente End

  public static class ContoCorrente {
    private int saldo;
    private int saldoMax;

    public ContoCorrente(int saldo) {
      this.saldo = saldo;
      this.saldoMax = saldo;
    }

    public int get_saldo() {
      return saldo;
    }

    public int get_saldoMax() {
      return saldoMax;
    }

    public void deposita(int cifra) {
      this.saldo += cifra;
      if (saldo > saldoMax)
        this.saldoMax = saldo;
    }

    public int preleva(int cifra) {
      int disponibilita = Math.min(saldo, cifra);
      this.saldo -= disponibilita;
      return disponibilita;
    }
  } //ContoCorrente End

  public static class Primes {
    private int current;

    public Primes() {
      current = 1;
    }

    public int next() {
      for (int i = current + 1; i < Integer.MAX_VALUE; i++) {
        if (this.isPrime(i)) {
          current = i;
        }  //else try next integer
      }
      //all primes < MAX_VALUE returned: what to do?
      return current;
    }

    private boolean isPrime(int number) {
      int i;
      for (i = 2; i <= number / i; i++)
        if (number % i == 0) break;
      return (i > number / i);
    }

  } //Primes End


  static void main(String[] args) {
    StdOut.println("===== OROLOGIO =====");
    Orologio o1 = new Orologio(20, 58);
    StdOut.println("Ore " + o1.get_ora() + ":" + o1.get_minuti());
    o1.tick();
    StdOut.println("Ore " + o1.get_ora() + ":" + o1.get_minuti());
    o1.tick();
    StdOut.println("Ore " + o1.get_ora() + ":" + o1.get_minuti());
    StdOut.println("===== RECIPIENTE =====");
    Recipiente r1 = new Recipiente(10);
    StdOut.println("Capacità " + r1.capacita());
    r1.aggiungi(4);
    StdOut.println("Capacità " + r1.capacita());
    r1.apri();
    r1.aggiungi(4);
    StdOut.println("Capacità " + r1.capacita());
    StdOut.println("===== CONTO CORRENTE =====");
    ContoCorrente cc1 = new ContoCorrente(20);
    StdOut.println("Saldo " + cc1.get_saldo() + " (max " + cc1.get_saldoMax() +")");
    cc1.deposita(2);
    StdOut.println("Saldo " + cc1.get_saldo() + " (max " + cc1.get_saldoMax() +")");
    cc1.deposita(8);
    StdOut.println("Saldo " + cc1.get_saldo() + " (max " + cc1.get_saldoMax() +")");
    int contante = cc1.preleva(18);
    StdOut.println("Saldo " + cc1.get_saldo() + " (max " + cc1.get_saldoMax() +") contante " + contante);
    contante = cc1.preleva(18);
    StdOut.println("Saldo " + cc1.get_saldo() + " (max " + cc1.get_saldoMax() +") contante " + contante);
    StdOut.println("===== PRIMES =====");
    Primes p1 = new Primes();
    for (int i = 0; i < 10; i++) {
      StdOut.println("Next prime is " + p1.next());
    }
  }

}
