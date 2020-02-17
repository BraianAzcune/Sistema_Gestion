package controlador;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import vista.verSocios.Filtro;

public class ControladorFiltroTest {

  @Test
  public void crearWhereConColumnasTest() {
    ControladorFiltro ct = new ControladorFiltro(new Filtro(null));



    Map<String, String> map = new HashMap<String, String>();
    map.put("numerosocio", "123123");
    map.put("nombre", "carlos");
    map.put("direccion", "12321");

    try {
      Method m = ct.getClass().getDeclaredMethod("crearWhereConColumnas", Map.class);
      m.setAccessible(true);
      String r = (String) m.invoke(ct, map);
      System.out.println(r);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  // SE NECESITA HACER TEST DE crearWhereConColumnas

}
