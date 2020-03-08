package modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;

import lombok.extern.slf4j.Slf4j;
import modelo.BaseDatos;
import modelo.Socio;

@Slf4j
public class SocioDAO {

  private final String totalFilas = "SELECT COUNT(*) FROM SOCIOS;";
  private final String obtenerTodosSociosIndice =
      "SELECT NUMEROSOCIO,nombre,apellido,telefono FROM SOCIOS ORDER BY NUMEROSOCIO LIMIT :cantidad OFFSET :desplazamiento;";

  private List<Socio> rtaNull;
  /**
   * !TODO TEST DESPUES BORRAR
   */
  private int cantidadDeVecesLLamado = 0;


  /**
   * Constructor con consulta personalizada...
   * 
   * @param consulta
   */
  public SocioDAO(String consulta) {

  }

  public SocioDAO() {
    rtaNull = new ArrayList<Socio>(1);
    Socio s = Socio.builder().nombre("base de datos vacia").apellido("o un error ocurrio").build();
    rtaNull.add(s);
  }


  /**
   * Debe ser una consulta count..
   * 
   * @param consultaCount
   * @return
   */
  public int getTotalRow(String consultaCount) {

    try (Connection con = BaseDatos.obtenerSql2o().open()) {

      return con.createQuery(consultaCount).executeScalar(Integer.class);
    } catch (Exception e) {
      log.error("error obtener total de filas en Socio", e);

    }
    return 0;
  }

  /**
   * Devuevle la cantidad total de filas que tiene la tabla SOCIO
   * 
   * @return
   */
  public int getTotalRow() {



    try (Connection con = BaseDatos.obtenerSql2o().open()) {

      return con.createQuery(totalFilas).executeScalar(Integer.class);
    } catch (Exception e) {
      log.error("error obtener total de filas en Socio", e);

    }
    return 0;
  }



  public List<Socio> getSocio(String consulta, int inicio, int fin) {
    try (Connection con = BaseDatos.obtenerSql2o().open()) {
      return con.createQuery(consulta).addParameter("cantidad", fin - inicio)
          .addParameter("desplazamiento", inicio).executeAndFetch(Socio.class);
    } catch (Exception e) {
      log.error("Error obtener todos los socios por indice ", e);
    }

    // !TODO esto puede generar muchas excepciones...
    return rtaNull;
  }


  /**
   * Utiliza la sentencia sql obtenerTodosSociosIndice
   * 
   * @param inicio
   * @param fin
   * @return
   */
  public List<Socio> getSocios(int inicio, int fin) {



    try (Connection con = BaseDatos.obtenerSql2o().open()) {
      return con.createQuery(obtenerTodosSociosIndice).addParameter("cantidad", fin - inicio)
          .addParameter("desplazamiento", inicio).executeAndFetch(Socio.class);
    } catch (Exception e) {
      log.error("Error obtener todos los socios por indice ", e);
    }

    // !TODO esto puede generar muchas excepciones...
    return rtaNull;
  }

  /**
   * 
   * @param idSocio
   * @return socio que coincida con el id
   */
  public Socio getSocio(String idSocio) {
    try (Connection con = BaseDatos.obtenerSql2o().open()) {
      List<Socio> rta = con.createQuery("SELECT * FROM SOCIOS WHERE NUMEROSOCIO=:idSocio")
          .addParameter("idSocio", Integer.parseInt(idSocio))
          .addColumnMapping("FK_TIPO_SOCIO", "tipo_socio").executeAndFetch(Socio.class);
      if (rta.get(0) != null) {
        return rta.get(0);
      }
    } catch (Exception e) {
      log.error("Error obtener socio segun id ", e);
    }
    return rtaNull.get(0);
  }

  /**
   * Actualiza los datos del socio.
   * 
   * @param socio
   */
  public void actualizarSocio(Socio socio) {
    try (Connection con = BaseDatos.obtenerSql2o().open()) {

      con.createQuery("UPDATE SOCIOS SET nombre=:nombre, apellido=:apellido, email=:email, "
          + "dni=:dni, telefono=:telefono, direccion=:direccion, FK_TIPO_SOCIO=:tipo_socio "
          + " WHERE NUMEROSOCIO=:numerosocio").bind(socio).executeUpdate();


    } catch (Exception e) {
      log.error("Error actualizar datos del socio ", e);
    }
  }


}
