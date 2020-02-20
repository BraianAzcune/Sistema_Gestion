package controlador;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import modelo.Socio;
import vista.verSocios.Filtro;

public class ControladorFiltroTest {

  private ControladorFiltro ct;
  private Map<String, String> map;



  @Before
  public void creraControlador() {
    ct = new ControladorFiltro(new Filtro(null));

    map = new HashMap<String, String>();
    map.put("numerosocio", "123123");
    map.put("nombre", "carlos");
    map.put("direccion", "12321");
  }


  @Test
  public void actualizarSQLTest() {


  }



  @Test
  public void crearSelectParteConsultaTest() {
    Socio s = Socio.builder().nombre("pepe").numerosocio("12323").apellido("aasda").build();



    try {
      Method m = ct.getClass().getDeclaredMethod("crearSelectParteConsulta", String.class);
      m.setAccessible(true);
      String r = (String) m.invoke(ct, s.toString());
      System.out.println(r);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void crearSelectConColumnasQueSeMostraranTest() {
    try {
      Method m = ct.getClass().getDeclaredMethod("crearSelectConColumnasQueSeMostraran", Map.class);
      m.setAccessible(true);
      String r = (String) m.invoke(ct, map);
      System.out.println(r);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void crearWhereConColumnasTest() {



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
