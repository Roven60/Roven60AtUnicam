package SimulazioneEsami.Visite_Mediche;

public class Coda {

  private Visita[] coda;  //visite prenotate
  private int pazienti;
  private int prenotazioni = 0;//numero massimo pazienti

  public Coda(int max) {
    if (max <= 0) {
      throw new IllegalArgumentException("Numero non valido di visite gestite");
    }
    pazienti = max;
    coda = new Visita[max];
  }

  public boolean inserimento(Visita visita) {
    if (visita == null || prenotazioni >= pazienti) {
      return false;
    }
    coda[prenotazioni++] = visita;
    return true;
  }

  public Visita estrazione() {
    if (prenotazioni == 0) {
      return null;
    }
    Visita res = coda[0];
    for (int i = 1; i < prenotazioni; i++) {
      coda[i - 1] = coda[i];
    }
    prenotazioni--;
    return res;
  }

  public boolean cancellazione(String nome, String cognome) {
    for (int i = 0; i < prenotazioni; i++) {
      if (coda[i].getNome().equals(nome) && coda[i].getCognome().equals(cognome)) {
        for (int j = i; j < prenotazioni; j++) {
          coda[j] = coda[j + 1];
        }
        prenotazioni--;
        return true;
      }
    }
    return false;
  }


}