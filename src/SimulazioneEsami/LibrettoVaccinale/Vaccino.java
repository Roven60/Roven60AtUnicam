package SimulazioneEsami.LibrettoVaccinale;

public abstract class Vaccino {
  int identificativo;
  String azienda;     //azienda farmaceutica
  int intervallo;     //giorni di intervallo prima del richiamo o altro vaccino
  // 10 < intervallo

  public Vaccino(int identificativo, String azienda, int intervallo) {
    this.identificativo = identificativo;
    if (azienda == null || (azienda.trim()).isEmpty()) {
      throw new DatiErrati("azienda non valorizzata");
    }
    this.azienda = azienda.trim();
    if (intervallo <= 10) {
      throw new DatiErrati("intervallo non valido (minimo 11 gg.)");
    }
    this.intervallo = intervallo;
  }

  public String toString() {
    String res = "";
    res += "identificativo: " + this.identificativo;
    res += ", azienda: " + this.azienda;
    res += ", intervallo: " + this.intervallo;
    return res;
  }

}
