package SimulazioneEsami.LibrettoVaccinale;

public class VaccinoA extends Vaccino {

  public VaccinoA(int id, String azienda, int intervallo) {
    super(id, azienda, intervallo);
  }

  public String toString() {
    return "VaccinoA " +  super.toString();
  }

}
