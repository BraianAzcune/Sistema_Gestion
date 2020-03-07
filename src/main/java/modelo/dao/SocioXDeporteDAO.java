package modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;

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

}
