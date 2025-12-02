package SimulazioneEsami.LibrettoVaccinale;

public class DoseNonSomministrabile extends RuntimeException {
  public DoseNonSomministrabile(String message) {
    super("Dose non somministrabile: " + message);
  }
}
