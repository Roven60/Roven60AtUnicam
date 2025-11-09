package SimulazioneEsami.Visite_Mediche;

public class Visita {
  String nome;
  String cognome;

  public Visita(String nome, String cognome) {
    if (nome == null || nome.length() < 2) {
      throw new IllegalArgumentException("Nome nullo o troppo corto");
    }
    if (cognome == null || cognome.length() < 2) {
      throw new IllegalArgumentException("Cognome nullo o troppo corto");
    }
    this.nome = nome;
    this.cognome = cognome;
  }

  public String getNome() {
    return nome;
  }

  public String getCognome() {
    return cognome;
  }
}
