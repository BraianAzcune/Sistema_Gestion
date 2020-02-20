package controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.slf4j.Slf4j;
import modelo.Socio;
import modelo.dao.SocioDAO;
import utilidades.paginacionTablas.ProveedorDeDatosPaginacion;
import vista.verSocios.Filtro;

/**
 * Nos devuelve un proveedorDeDatosPaginacion segun lo que especifica el filtro.
 * 
 * @author braian
 *
 */
@Slf4j
public class ControladorFiltro {
  private Filtro panel;
  private String SQL;
  private SocioDAO socioDAO;

  public ControladorFiltro(Filtro filtro) {
    this.panel = filtro;

    socioDAO = new SocioDAO();

  }

  /**
   * Crea una nueva consulta sql atendiendo a los criterios que se especificaron en el filtro
   */
  public void actualizarSQL() {

    Socio socio = panel.mapearSocio();

    log.info("AQUIII " + socio.toString());

    StringBuilder consulta = new StringBuilder();

    // primera parte Select columnas from tabla where y campos que esten con datos..
    String primeraParte = crearSelectParteConsulta(socio.toString());
    consulta.append(primeraParte);

    boolean deboAgregarAND = primeraParte.contains("AND");
    // segunda parte si esta especificado el tipo de socio se añade.
    if (!panel.isTipoSocioTodosActivo()) {
      if (deboAgregarAND) {
        consulta.append(" AND ");
      } else {
        deboAgregarAND = true;
      }
      consulta.append("FK_TIPO_SOCIO=");
      consulta.append(panel.queTipoSocioEs());
    }


    // tercera parte (deportes)
    if (!panel.isDeportesTodosActivo()) {
      if (deboAgregarAND) {
        consulta.append(" AND ");
      }
      consulta.append(crearDeporteParteConsulta(panel.mapearDeportes(),
          panel.isDeportesTodosActivo(), panel.isYoptionDeporte()));
    }

    // !TODO cuarta parte order by, y implementacion para pedir en trozos los datos

  }

  /**
   * 
   * @param idDeportes se consigue de mapearDeporte de PanelSocio
   * @param todosDeportesActivo se consigue de isDeportesTodosActivo de Filtro
   * @param yDeportesActivo se consigue de isYOptionDeporte de Filtro
   * @return retorna la consulta sql pertinente
   */
  private String crearDeporteParteConsulta(int[] idDeportes, boolean todosDeportesActivo,
      boolean yDeportesActivo) {
    if (todosDeportesActivo) {
      // si esta todos los deportes activo, no hay que tener criterio alguno para buscar.
      return "";
    } else {
      if (yDeportesActivo) {
        // se debe buscar que tenga todos si o si
      } else {
        // se debe buscar que tenga alguno de los nombrados
      }
    }

  }

  /**
   * Crea la primera parte de la consulta, que corresponde al select junto con las columnas que se
   * mostraran mas algunas adicionales si se rellenaron en el filtro, mas el from, where y las
   * condiciones de cada una.
   * 
   * @param valores Se espera socio.toString(); = ej
   *        "Socio(nombre=pepe,apellido=null,dni=123123,...)"
   * @return String SQL, tendra la parte SELECT
   *         columnasPorDefecto+columnasConCamposPuestosEnElFiltro FROM SOCIOS WHERE
   *         columnaConCamposPuestosEnElFiltro=:nombreColumna AND ...
   */
  private String crearSelectParteConsulta(String valores) {



    // ponemos los valores dentro de un mapa, para hacer un manejo mas facil. y se quitan los nulos
    valores = valores.substring(6, valores.toString().length() - 1);



    Map<String, String> myMap = new HashMap<String, String>();

    // quitamos cualquier espacio en blanco que haya.
    valores = valores.replaceAll("\\s", "");
    log.debug("Valores socio.tostring=" + valores);
    // ponemos el String en formato clave, valor en un Map
    String[] pairs = valores.split(",");
    for (int i = 0; i < pairs.length; i++) {
      String pair = pairs[i];
      String[] keyValue = pair.split("=");
      if (!keyValue[1].equals("null")) {
        myMap.put(keyValue[0], keyValue[1]);
      }
    }

    log.debug("MAP= " + myMap.toString());
    // instanciamos un constructor de Strings con 100 Char reservados
    StringBuilder rta = new StringBuilder(100);

    log.debug("TAMAÑO " + rta.capacity());

    rta.append("SELECT ");

    String columnasAMostrar = crearSelectConColumnasQueSeMostraran(myMap);

    rta.append(columnasAMostrar);
    rta.append(" FROM SOCIOS WHERE ");

    String condicionColumnas = crearWhereConColumnas(myMap);

    rta.append(condicionColumnas);

    log.debug("TAMAÑO " + rta.capacity());

    return rta.toString();
  }

  /**
   * Devuelve la parte que corresponde al where y tiene en cuenta la naturaleza integer de
   * numerosocio si llega a ser usado.
   * 
   * NOTA= numerosocio busca coicidir exactamente | no distingue mayusculas de minusculas
   * 
   * @param myMap
   * @return PARTE SQL WHERE = ej "NOMBRE LIKE 'pepe%' AND DNI LIKE '2323%'" SI VIENE VACIO DEVUELVE
   *         STRING "".
   */
  private String crearWhereConColumnas(Map<String, String> myMap) {

    StringBuilder str = new StringBuilder();


    Iterator<Map.Entry<String, String>> it = myMap.entrySet().iterator();

    while (it.hasNext()) {
      Entry<String, String> pair = it.next();
      // caso especial.
      if (pair.getKey().equals("numerosocio")) {
        str.append("numerosocio=");
        str.append(pair.getValue());
      } else {
        str.append("lower(");
        str.append(pair.getKey());
        str.append(") LIKE '");
        str.append(pair.getValue().toLowerCase());
        str.append("%'");
      }
      // agregamos AND si hay mas..
      if (it.hasNext()) {
        str.append(" AND ");
      }
    }

    String rta = str.toString();

    return rta;
  }

  /**
   * En principio si todas las columnas estan en blanco por defecto se mostraran
   * NUMEROSOCIO,nombre,apellido,telefono
   * 
   * pero si hay alguna de estas que no esta, se agregara. Como por ejemplo DNI. si se pusiera un
   * criterio para este entonces apareceria ej retorno:
   * 
   * @param myMap
   * 
   * @param valores ej: "nombre:pepe,dni:123213"
   * @return String ej: numerosocio,nombre,apellido,telefono, dni
   * 
   */
  private String crearSelectConColumnasQueSeMostraran(Map<String, String> myMap) {

    final String[] columnasDefault = {"numerosocio", "nombre", "apellido", "telefono"};

    ArrayList<String> columnasQueSeMostraran =
        new ArrayList<String>(Arrays.asList(columnasDefault));

    myMap.forEach((k, v) -> {

      boolean esta = false;
      // verifica si la clave esta dentro del array.
      for (String s : columnasDefault) {
        if (k.equals(s)) {
          esta = true;
          break;
        }
      }
      // sino esta entonces lo añadimos, porque se mostrara como un dato extra.
      if (!esta) {
        columnasQueSeMostraran.add(k);
      }

    });

    String rtaFinal = columnasQueSeMostraran.toString();

    return rtaFinal.substring(1, rtaFinal.length() - 1);
  }

  /**
   * Retorna un proveedor de datos, construido segun la consulta sql, que corresponderia a lo que
   * esta especificado en el panel Filtro. Esta consulta sql se actualiza cuando se llama al metodo
   * 
   * @return
   */
  public ProveedorDeDatosPaginacion<Socio> generarProveedorDatosPaginacionSegunFiltro() {
    throw new java.lang.UnsupportedOperationException("Not supported yet.");

  }

  /**
   * Este es el proveedor por defecto, simplemente muestra todo.
   * 
   * @return
   */
  public ProveedorDeDatosPaginacion<Socio> getProveedorDefault() {

    return new ProveedorDeDatosPaginacion<Socio>() {

      @Override
      public int getTotalRowsCount() {
        // !TODO esto esta llamando vaya a saber cuantas veces...
        return socioDAO.getTotalRow();
      }

      @Override
      public List<Socio> getRows(int startIndex, int endIndex) {
        return socioDAO.getSocios(startIndex, endIndex);
      }
    };
  }

}
