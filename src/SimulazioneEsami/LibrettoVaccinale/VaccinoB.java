package SimulazioneEsami.LibrettoVaccinale;

public class VaccinoB extends Vaccino {
  String somministrazione;  //metodo di somministrazione

  public VaccinoB(int id, String azienda, int intervallo, String somministrazione) {
    super(id, azienda, intervallo);
    if (somministrazione == null || (somministrazione.trim()).isEmpty()) {
      throw new DatiErrati("somministrazione non valorizzata");
    }

    this.somministrazione = somministrazione.trim();
  }

  public String toString() {
    return "VaccinoB " + super.toString() + ", somministrazione: " + this.somministrazione;
  }


}
