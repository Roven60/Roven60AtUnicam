package SimulazioneEsami.LibrettoVaccinale;

public class Test {

  static void main(String[] args) {
    //Persona badName = new Persona("Fa", 31, 3);
    Persona roberto = new Persona("Roberto", 65, Persona.MASCHILE);
    Persona rosa = new Persona("Rosa", 40, Persona.FEMMINILE);
    roberto.eseguiDoseVaccino(new VaccinoA(11,"Pfaizer", 15));
    roberto.eseguiDoseVaccino(new VaccinoB(12,"Piolla", 12, "Intramuscolo"));
    roberto.eseguiDoseVaccino(new VaccinoA(110,"Pfaizer", 15));
    System.out.println(roberto);

  }

}
