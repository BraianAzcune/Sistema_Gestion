package controlador;

import java.util.List;

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
public class ControladorFiltro {
  private Filtro panel;
  private String SQL;
  private SocioDAO socioDAO;

  public ControladorFiltro(Filtro filtro) {
    this.panel = filtro;

    socioDAO = new SocioDAO();

  }

  public void actualizarSQL() {

    Socio socio = panel.mapearSocio();

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
