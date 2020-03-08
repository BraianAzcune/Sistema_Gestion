package modelo.dao;

import org.junit.Test;

public class SocioXDeporteDAOTest extends SocioXDeporteDAO {

  @Test
  public void eliminarDeportesQueTengaTest() {
    SocioXDeporteDAO dao = new SocioXDeporteDAO();

    dao.eliminarDeportesQueTenga("2");
  }


  @Test
  public void agregarDeporteTest() {
    SocioXDeporteDAO dao = new SocioXDeporteDAO();
    dao.agregarDeporte("2", new int[] {1, 2, 3});
  }
}
