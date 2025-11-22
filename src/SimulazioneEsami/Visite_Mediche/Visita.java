package SimulazioneEsami.Visite_Mediche;

import static SimulazioneEsami._lib.toTitleCase;

/**
 * @author Roberto Venturi aka Roven60
 */
public class Visita {
  String nome;
  String cognome;

  /**
   *
   * @param nome    nome del paziente richiedente visita
   * @param cognome conome del paziente richiedente visita
   */
  public Visita(String nome, String cognome) {
    if (nome == null || nome.length() < 2) {
      throw new IllegalArgumentException("Nome nullo o troppo corto");
    }
    if (cognome == null || cognome.length() < 2) {
      throw new IllegalArgumentException("Cognome nullo o troppo corto");
    }
    this.nome =  toTitleCase(nome);
    this.cognome = toTitleCase(cognome);
  }

  public String getNome() {
    return nome;
  }

  public String getCognome() {
    return cognome;
  }
}
