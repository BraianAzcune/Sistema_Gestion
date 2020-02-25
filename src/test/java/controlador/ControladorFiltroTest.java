package controlador;


import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

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
  public void TESTMultipleMatches() {


    String consulta = "blabla FROM perro gato alfiler FROM sapo pepe FROM jose";

    Pattern p = Pattern.compile("(?i)FROM.*FROM");

    java.util.regex.Matcher m = p.matcher(consulta);

    String subSQL = "";
    // Si encontro una coincidencia. entonces tenemos que quitar el Where.
    if (m.find()) {
      // Nos quedamos con la parte de adentro entre el SELECT, y FROM
      subSQL = consulta.substring(m.start());
      System.out.println(m.group(0));
      System.out.println(consulta.charAt(m.start()));
    }

    System.out.println(subSQL);
  }


  @Test
  public void getColumnasSQL() {

    String consulta = "SELECT    pepe, jose,daniel,  maria   FROM";

    Pattern p = Pattern.compile("(?i)SELECT .*FROM");

    java.util.regex.Matcher m = p.matcher(consulta);

    String subSQL = "";
    // Si encontro una coincidencia. entonces tenemos que quitar el Where.
    if (m.find()) {
      // Nos quedamos con la parte de adentro entre el SELECT, y FROM
      subSQL = consulta.substring(m.start() + 6, m.end() - 4);
    }

    // removemos cualquier tipo de espacio en blanco
    subSQL = subSQL.replaceAll("\\s+", "");

    // retornamos el array con el nombre de columnas

    System.out.println(Arrays.toString(subSQL.split(",")));
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
