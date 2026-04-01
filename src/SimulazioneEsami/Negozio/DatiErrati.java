package SimulazioneEsami.Negozio;

public class DatiErrati extends RuntimeException {
  public DatiErrati(String message) {
    super("Dati errati: " + message);
  }
}
