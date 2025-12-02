package SimulazioneEsami.LibrettoVaccinale;

public class DatiErrati extends IllegalArgumentException {
  public DatiErrati(String message) {
    super("Dati errati: " + message);
  }
}
