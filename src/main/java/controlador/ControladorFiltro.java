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

  /**
   * Crea una nueva consulta sql atendiendo a los criterios que se especificaron en el filtro
   */
  public void actualizarSQL() {

    Socio socio = panel.mapearSocio();
    crearColumnasQueSeMostraran(socio.toString());
  }

  /**
   * En principio si todas las columnas estan en blanco por defecto se mostraran
   * NUMEROSOCIO,nombre,apellido,telefono
   * 
   * pero si hay alguna de estas que no esta, se agregara. Como por ejemplo DNI. si se pusiera un
   * criterio para este entonces apareceria NUMEROSOCIO,nombre,apellido,telefono, DNI.
   */
  private void crearColumnasQueSeMostraran(String valores) {

    String[] columnasDefault = {" NUMEROSOCIO", "nombre", "apellido", "telefono"};

    /*
     * Pasar socio a string, separar a lista, luego extrar los que no sean nulos, y para los que no
     * esten en columnaDefault agregarlo al final.
     */

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
