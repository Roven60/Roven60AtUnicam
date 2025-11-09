package SimulazioneEsami.Sistema_Solare;

public class Pianeta {

  String nome;
  double massa;
  double distanza;


  public Pianeta(String nome, double massa, double distanza) {
    if (nome == null || nome.equals("")) {
      throw new IllegalArgumentException("Nome nullo");
    }
    this.nome = nome;
    if (massa < 0 ) {
      throw new IllegalArgumentException("Massa nulla");
    }
    this.massa = massa;
    if (distanza < 0 ) {
      throw new IllegalArgumentException("Distanza nulla");
    }
    this.distanza = distanza;
  }

  public String getNome() {
    return nome;
  }

  public double getMassa() {
    return massa;
  }

  public double getDistanza() {
    return distanza;
  }

  public String toString() {
    return "Pianeta: " + nome + " massa: " + massa + " distanza: " + distanza +"\n";
  }

}
