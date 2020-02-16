package modelo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SocioTest {


  @Test
  public void test() {
    Socio socio = Socio.builder().nombre("pedro").dni("213213").build();

    String rta = socio.toString();
    rta = rta.substring(6, rta.toString().length() - 1);

    Map<String, String> myMap = new HashMap<String, String>();

    String[] pairs = rta.split(",");
    for (int i = 0; i < pairs.length; i++) {
      String pair = pairs[i];
      String[] keyValue = pair.split("=");
      if (!keyValue[1].equals("null")) {
        myMap.put(keyValue[0], keyValue[1]);
      }

    }

    if (socio.getNumerosocio() == null) {
      System.out.println("yes");
    }

    System.out.println(myMap);
  }

}
