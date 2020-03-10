package controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import modelo.Socio;
import modelo.dao.SocioDAO;
import utilidades.paginacionTablas.ModeloTabla;
import utilidades.paginacionTablas.ProveedorDeDatosPaginacion;
import vista.verSocios.Filtro;
import vista.verSocios.VistaResultado;

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
   * LLamado por el filtro cuando hay una nueva busqueda
   * 
   * crea la nueva consulta y genera el Modelo y Proveedor correspondientes para que funcione el
   * JTable
   * 
   * @param vistaResultado
   */
  public void actualizarModeloTabla(VistaResultado vistaResultado) {

    String consulta = actualizarSQL();
    ProveedorDeDatosPaginacion<Socio> proveedorNuevo =
        generarProveedorDatosPaginacionSegunFiltro(consulta);
    ModeloTabla<Socio> modeloNuevo = generarModeloNuevoSegunFiltro(consulta);

    vistaResultado.actualizarResultadosAMostrar(modeloNuevo, proveedorNuevo);
  }



  /**
   * Dada una consulta sql, entrega un array de String con el nombre de las columnas
   * 
   * @param consulta
   * @return {"columnName1","columnName2",...}
   */
  private String[] getColumnasSQL(String consulta) {

    String subSQL = consulta.substring(consulta.toUpperCase().indexOf("SELECT") + 6,
        consulta.toUpperCase().indexOf("FROM") - 1);

    // removemos cualquier tipo de espacio en blanco
    subSQL = subSQL.replaceAll("\\s+", "");

    // lo ponemos en minusculas por si acaso....
    subSQL = subSQL.toLowerCase();

    // retornamos el array con el nombre de columnas
    return subSQL.split(",");
  }


  private ModeloTabla<Socio> generarModeloNuevoSegunFiltro(String consulta) {

    // Procedemos a extraer la parte de que columnas se mostraran, en un arrayList.
    String[] columnas = getColumnasSQL(consulta);

    log.debug("TEST nombre columnas= " + Arrays.toString(columnas));

    String fueraIndice = "FUERA DE INDICE= ";
    ModeloTabla<Socio> modelo = new ModeloTabla<Socio>() {

      @Override
      public int getColumnCount() {
        return columnas.length;
      }

      @Override
      public Object getValueAt(Socio generico, int columna) {
        String nombreColumna = getColumnName(columna);
        String rta;

        // alta paja... probabilidades de que falle un caracter 75%, probababilidades
        // de que se descubra el error 23%
        switch (nombreColumna) {
          case "nombre":
            rta = generico.getNombre();
            break;
          case "apellido":
            rta = generico.getApellido();
            break;
          case "email":
            rta = generico.getEmail();
            break;
          case "dni":
            rta = generico.getDni();
            break;
          case "telefono":
            rta = generico.getTelefono();
            break;
          case "direccion":
            rta = generico.getDireccion();
            break;
          case "fecha_alta":
            rta = generico.getFecha_alta();
            break;
          case "numerosocio":
            rta = generico.getNumerosocio();
            break;
          default:
            // lo concatenamos con la columna que tendria que ir... asi las probabilidades
            // aumentan al 66%
            rta = fueraIndice + nombreColumna;
            break;
        }

        return rta;
      }

      @Override
      public String getColumnName(int columna) {
        if (columnas.length > columna) {
          return columnas[columna];
        } else {
          return fueraIndice;
        }
      }

    };
    return modelo;
  }

  /**
   * Crea una nueva consulta sql atendiendo a los criterios que se especificaron en el filtro
   * 
   * Esto trae dos campos que se deben llenar con sql2o que son
   * 
   * *LIMIT:cantidad
   *
   * *OFFSET:desplazamiento
   */
  private String actualizarSQL() {

    Socio socio = panel.mapearSocio();

    // log.info("AQUIII " + socio.toString());

    StringBuilder consulta = new StringBuilder();

    // primera parte Select columnas from tabla where y campos que esten con datos..
    String primeraParte = crearSelectParteConsulta(socio.toString());
    consulta.append(primeraParte);

    boolean deboAgregarAND =
        primeraParte.contains("AND") || primeraParte.contains("=") || primeraParte.contains("LIKE");
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
      int[] idDeporte = panel.mapearDeportes();
      if (idDeporte.length > 0) {

        consulta.append(crearDeporteParteConsulta(idDeporte, panel.isDeportesTodosActivo(),
            panel.isYoptionDeporte()));
      } else {
        consulta.append(
            "NOT EXISTS ( SELECT ID_SOCIO FROM SOCIOSXDEPORTE WHERE SOCIOS.NUMEROSOCIO=SOCIOSXDEPORTE.ID_SOCIO) ");
      }
    }

    // cuarta parte order by, y implementacion para pedir en trozos los datos
    consulta.append(" ORDER BY NUMEROSOCIO LIMIT :cantidad OFFSET :desplazamiento;");

    String respuesta = consulta.toString();



    // Comprobaciones (si esta WHERE ... ORDER entonces hay que quitar le where, lo que ocurrio es
    // que no
    // hubo ningun campo escrito

    // (?i)-> insensible a mayusculas y minusculas
    // se pone el doble de \ para las indicaciones especiales por que se confunden con caracteres
    // especiales.
    Pattern p = Pattern.compile("(?i)WHERE\\s+ORDER");

    java.util.regex.Matcher m = p.matcher(respuesta);

    // Si encontro una coincidencia. entonces tenemos que quitar el Where.
    if (m.find()) {
      respuesta = respuesta.substring(0, m.start())
          + respuesta.substring(respuesta.toUpperCase().indexOf("ORDER"));;
    }

    log.debug("CONSULTA= " + respuesta);

    return respuesta;
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
      // creamos la parte de la consulta para obtener los deportes que es analoga para la opcion "Y"
      // y "O"
      StringBuilder deporteConsulta = new StringBuilder(
          "numerosocio IN (SELECT ID_SOCIO FROM SOCIOSXDEPORTE WHERE ID_DEPORTE IN (");

      for (int i = 0; i < idDeportes.length; i++) {
        deporteConsulta.append(String.valueOf(idDeportes[i]));
        // mientras quede otra vuelta
        if (i + 1 < idDeportes.length) {
          deporteConsulta.append(",");
        } else {
          deporteConsulta.append(")");
        }
      }

      deporteConsulta.append(" GROUP BY ID_SOCIO ");

      // si es "y" opcion debe llevar esto, sino lo lleva se comporta como la opcion "O"
      if (yDeportesActivo) {
        // se debe buscar que tenga todos si o si
        deporteConsulta.append("HAVING COUNT(*)=");
        deporteConsulta.append(String.valueOf(idDeportes.length));
      }

      deporteConsulta.append(") ");

      return deporteConsulta.toString();
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

        // EXCEPCIONES QUE NO DEBEN SER CONTADAS, EJ= TIPO_SOCIO
        if (!keyValue[0].equals("tipo_socio")) {
          myMap.put(keyValue[0], keyValue[1]);
        }
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
   * Dada una consulta sql, devuelve su version COUNT
   * 
   * elimina los nombres de campos por COUNT(*) y elimina Todo lo que quede despues de la palabra
   * ORDER, inclusive esa. (ya que no debe llevar eso, el count)
   * 
   * @param consulta
   * @return
   */
  private String getCountConsulta(String consulta) {


    Pattern p = Pattern.compile("(?i)FROM");

    java.util.regex.Matcher m = p.matcher(consulta);

    String subSQL = "";



    if (m.find()) {
      // Nos quedamos con la parte despues del primer FROM
      subSQL = "SELECT COUNT(*) " + consulta.substring(m.start());
    }

    // quitamos la parte de limit
    subSQL = subSQL.substring(0, subSQL.indexOf("ORDER"));


    return subSQL;

  }


  /**
   * Retorna un proveedor de datos, construido segun la consulta sql, que corresponderia a lo que
   * esta especificado en el panel Filtro. Esta consulta sql se actualiza cuando se llama al metodo
   * 
   * @param consulta
   * 
   * @return
   */
  private ProveedorDeDatosPaginacion<Socio> generarProveedorDatosPaginacionSegunFiltro(
      String consulta) {

    String consultaCount = getCountConsulta(consulta);

    log.debug("TEST= CONSULTACOUNT= " + consultaCount);
    log.debug("TEST consulta= " + consulta);
    SocioDAO dao = new SocioDAO();

    ProveedorDeDatosPaginacion<Socio> proveedor = new ProveedorDeDatosPaginacion<Socio>() {

      @Override
      public int getTotalRowsCount() {
        return dao.getTotalRow(consultaCount);
      }

      @Override
      public List<Socio> getRows(int startIndex, int endIndex) {
        return dao.getSocio(consulta, startIndex, endIndex);
      }

    };

    return proveedor;
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
