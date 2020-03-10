package modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Query;

import lombok.extern.slf4j.Slf4j;
import modelo.BaseDatos;
import modelo.SocioXDeporte;

@Slf4j
public class SocioXDeporteDAO {

  List<SocioXDeporte> rtaNull;

  public SocioXDeporteDAO() {
    rtaNull = new ArrayList<SocioXDeporte>(1);
    SocioXDeporte sxd =
        SocioXDeporte.builder().id_socio("base de datos vacia").id_deporte(1).build();
    rtaNull.add(sxd);

  }

  /**
   * devuelve una lista de los deportes que hace tal socio segun id
   * 
   * @return
   */
  public List<SocioXDeporte> obtenerDeportesSegunIDSocio(String idSocio) {
    try (Connection con = BaseDatos.obtenerSql2o().open()) {
      return con.createQuery("SELECT * FROM SOCIOSXDEPORTE WHERE ID_SOCIO=:idSocio")
          .addParameter("idSocio", Integer.parseInt(idSocio)).executeAndFetch(SocioXDeporte.class);

    } catch (Exception e) {
      log.error("Error obtener socioXDeporte segun idSocio ", e);
    }
    return rtaNull;
  }

  public void eliminarDeportesQueTenga(String numerosocio) {

    try (Connection con = BaseDatos.obtenerSql2o().open()) {
      con.createQuery("DELETE FROM SOCIOSXDEPORTE WHERE ID_SOCIO=:id")
          .addParameter("id", Integer.parseInt(numerosocio)).executeUpdate();
    } catch (Exception e) {
      log.error("Error al eliminar deportes ", e);
    }
  }

  public void agregarDeporte(String numerosocio, int[] idDeportes) {

    if (idDeportes.length == 0) {
      return;
    }

    final String sql = "INSERT INTO SOCIOSXDEPORTE(ID_SOCIO, ID_DEPORTE) VALUES (:socio, :deporte)";

    final int idsocio = Integer.parseInt(numerosocio);

    try (Connection con = BaseDatos.obtenerSql2o().beginTransaction()) {
      Query query = con.createQuery(sql);

      for (int deporte : idDeportes) {
        query.addParameter("socio", idsocio).addParameter("deporte", deporte).addToBatch();
      }
      query.executeBatch();
      con.commit();
    } catch (Exception e) {
      log.error("No se pudo agregar deportes ", e);
    }
  }

}
