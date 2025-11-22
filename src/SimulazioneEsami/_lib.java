package SimulazioneEsami;

/**
 * @author Roberto Venturi aka Roven60
 */
public class _lib {

  /**
   *
   * @param name  a String containing a name or a surname or both
   * @return      the capitalized version of name
   */
  public static String toTitleCase(String name) {
    if (name == null) {
      return "";
    }
    name = name.trim();
    if (name.isEmpty()) {
      return "";
    }
    StringBuilder res = new StringBuilder();
    res.append(Character.toUpperCase(name.charAt(0)));
    name = name.substring(1).toLowerCase().trim();
    int idx = name.indexOf(" ");
    while (idx > 0) {
      res.append(name, 0, idx + 1);
      name = name.substring(idx + 1).trim();
      res.append(Character.toUpperCase(name.charAt(0)));
      name = name.substring(1).trim();
      idx = name.indexOf(" ");
    }
    res.append(name);
    return res.toString();
  }

}
