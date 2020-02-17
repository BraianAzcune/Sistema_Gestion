package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SocioTest {


  @Test
  public void ControladdorFiltro_crearColumnasQueSeMostraran() {



    Socio socio = Socio.builder().nombre("pedro").dni("213213").numerosocio("123213").build();

    String[] columnasDefault = {"numerosocio", "nombre", "apellido", "telefono"};

    String rta = socio.toString();
    System.out.println(rta);
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



    ArrayList<String> columnasQueSeMostraran =
        new ArrayList<String>(Arrays.asList(columnasDefault));

    myMap.forEach((k, v) -> {

      boolean esta = false;
      // verifica si la clave esta dentro del array.
      for (String s : columnasDefault) {
        if (k.equals(s)) {
          esta = true;
        }
      }
      // sino esta entonces lo añadimos, porque se mostrara como un dato extra.
      if (!esta) {
        columnasQueSeMostraran.add(k);
      }

    });

    String rtaFinal = columnasQueSeMostraran.toString();

    System.out.println(rtaFinal.substring(1, rtaFinal.length() - 1));
  }

}
