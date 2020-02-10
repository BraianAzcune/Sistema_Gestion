package vista.verSocios;

import javax.swing.JPanel;

import controlador.ControladorFiltro;

/**
 * Esta clase se encarga de visualizar la tabla de resultado de socios, con los socios que encajan
 * segun lo que dijo filtro.
 * 
 * Contiene un panel, que tiene un titulo, la tabla, y los elementos de paginacion.
 * 
 * @author braian
 *
 */
public class VistaResultado extends JPanel {

  private Filtro filtro;

  // Se utiliza para obtener el proveedor de datos.
  private ControladorFiltro controladorFiltro;

  public VistaResultado(ControladorFiltro controladorFiltro) {
    this.controladorFiltro = controladorFiltro;
    // TODO Auto-generated constructor stub
  }

  public Filtro getFiltro() {
    return filtro;
  }

  public void setFiltro(Filtro filtro) {
    this.filtro = filtro;
  }

  /**
   * Cuando hay un nuevo proveedor, vistaResultado le preguntara al ControladorFiltro
   */
  public void nuevoProveedor() {

    // !TODO aqui se debe llamar a controladorFiltro.generarProveedorDatosPaginacionSegunFiltro
    throw new java.lang.UnsupportedOperationException("Not supported yet.");
  }


}
