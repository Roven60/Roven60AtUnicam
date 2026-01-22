package SimulazioneEsami.Biblioteca;

public class Libro {
  String titolo;
  String autore;

  public Libro(String titolo, String autore) {
    if (titolo == null || titolo.length() < 1) {
      throw new IllegalArgumentException("Titolo mancante o non valido");
    }
    if (autore == null || autore.length() < 1) {
      throw new IllegalArgumentException("Autore mancante o non valido");
    }
    this.titolo = titolo;
    this.autore = autore;
  }

  public String getTitolo() {
    return titolo;
  }

  public String getAutore() {
    return autore;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass())
      return false;
    Libro libro = (Libro) o;
    return (titolo == libro.titolo && autore == libro.autore);
  }

}
