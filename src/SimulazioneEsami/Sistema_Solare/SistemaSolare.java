package SimulazioneEsami.Sistema_Solare;

public class SistemaSolare {

  Pianeta[] pianeti;
  int pianetiPresenti;

  public SistemaSolare(int numPianeti) {
    if (numPianeti < 1) {
      throw new IllegalArgumentException("Numero di pianeti nullo o negativo");
    }
    pianeti = new Pianeta[numPianeti];
    pianetiPresenti = 0;
  }

  public boolean nuovo(Pianeta p) {
    if (p == null || pianetiPresenti == pianeti.length) {
      return false;
    }
    //Cerchiamo la posizione ove inserire il nuovo pianeta
    int idx = 0;
    for (; idx < pianetiPresenti; idx++) {
      if (pianeti[idx].getDistanza() > p.getDistanza()) {
        //Se esiste almeno un pianeta più lontano, li sposto tutti
        for (int j = pianetiPresenti; j > idx; j--) {
          pianeti[j] = pianeti[j - 1];
        }
        break;
        // ed ho il posto per il nuovo pianeta
      }
      //se tutti i pianeti inseriti sono più vicini, idx=pianetiPresenti
    } // se non vi sono pianeti, idx=0
    pianeti[idx] = p;
    pianetiPresenti++;
    return true;
  }

  public int getPianetiPresenti() {
    return pianetiPresenti;
  }

  public int getNumero() {
    return pianetiPresenti;
  }

  public Pianeta getPianeta(int num) {
    if (num < 0 || num > pianetiPresenti) {
      throw new IllegalArgumentException("Numero di pianeta negativo");
    }
    if (num >= pianetiPresenti) {
      throw new IllegalArgumentException("Numero di pianeta maggiore del numero pianeti presenti");
    }
    return pianeti[num];
  }

  public Pianeta getMaxPianeta() {
    if (pianetiPresenti == 0) {
      return null;
    }
    int idx = 0;
    for (int i = 1; i < pianetiPresenti; i++) {
      if (pianeti[i].getMassa() > pianeti[idx].getMassa()) {
        idx = i;
      }
    }
    return pianeti[idx];
  }


  @Override
  public String toString() {
    String res = "";

    return res;
  }

}
