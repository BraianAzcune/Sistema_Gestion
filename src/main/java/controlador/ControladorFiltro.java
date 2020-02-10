package controlador;

import modelo.Socio;
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

  public ControladorFiltro(Filtro filtro) {
    this.panel = filtro;
  }


  /**
   * Otorga un proveedor de datos, segun lo que se especifique en el filtro. Si se cambia el filtro
   * debera llamarse esta clase de nuevo, para que cree un nuevo proveedor segun los nuevos ajustes
   * del filtro
   * 
   * @return
   */
  public ProveedorDeDatosPaginacion<Socio> generarProveedorDatosPaginacionSegunFiltro() {
    throw new java.lang.UnsupportedOperationException("Not supported yet.");

  }

}
