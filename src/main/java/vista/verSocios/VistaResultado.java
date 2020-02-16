package vista.verSocios;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import controlador.ControladorFiltro;
import modelo.Socio;
import utilidades.paginacionTablas.ModeloTabla;
import utilidades.paginacionTablas.PaginadorTabla;
import utilidades.paginacionTablas.ProveedorDeDatosPaginacion;

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
    construirPanel();
  }

  private void construirPanel() {
    this.setLayout(new BorderLayout());

    // MODELO DE TABLA (define las columnas que tiene, y sus nombres)
    TableModel modelo = crearModeloTabla();
    // TABLA
    JTable tablaSocios = new JTable(modelo);
    tablaSocios.setFont(new Font("Tahoma", Font.PLAIN, 20));
    tablaSocios.setRowHeight(tablaSocios.getRowHeight() + 10);
    tablaSocios.setFillsViewportHeight(true);
    // Scroll encapsula a la tabla
    JScrollPane scroll = new JScrollPane(tablaSocios);
    // Se añade el scroll al panel
    this.add(scroll, BorderLayout.CENTER);

    // PAGINACION

    // Colocamos el proveedor por defecto, que mostrara el listado de todos los socios.
    ProveedorDeDatosPaginacion<Socio> proveedorDeDatosPaginacion =
        controladorFiltro.getProveedorDefault();
    // esto es PaginadorTabla tiene tanto lo visual como logico para la paginacion
    PaginadorTabla<Socio> paginador = new PaginadorTabla<Socio>(tablaSocios,
        proveedorDeDatosPaginacion, new int[] {10, 25, 50, 100}, 10);

    // creamos un panel vacio
    JPanel panelPaginacion = new JPanel();

    // Tenemos que decirle dentro de que panel se visualizara. esto debe ser un panel vacio
    paginador.setPanelPaginacion(panelPaginacion);

    // AGREGAMOS EL PANEL VACIO AL PANEL CENTRAL (osea this)
    this.add(panelPaginacion, BorderLayout.SOUTH);

  }

  /**
   * Genera el modelo de tabla, el modelo de tabla se encarga de gestionar unicamente la cantidad de
   * columnas que posee la tabla a la que se adhiere y el nombre de cada columna ademas de que hacer
   * para obtener el valor para cada valor de fila.
   * 
   * @return TableModel
   */
  private TableModel crearModeloTabla() {
    // Implementamos la clase abstracta ModeloTabla aqui mismo, sobrescribiendo los metodos
    // necesarios
    TableModel modelo = new ModeloTabla<Socio>() {

      @Override
      public int getColumnCount() {

        return 4;
      }

      @Override
      public Object getValueAt(Socio generico, int columna) {
        switch (columna) {
          case 0:
            return generico.getNumerosocio();
          case 1:
            return generico.getNombre();
          case 2:
            return generico.getApellido();
          case 3:
            return generico.getTelefono();
          default:
            return "Fuera De Indice";
        }
      }

      @Override
      public String getColumnName(int columna) {
        switch (columna) {
          case 0:
            return "ID";
          case 1:
            return "nombre";
          case 2:
            return "apellido";
          case 3:
            return "telefono";
          default:
            return "fuera de indice";
        }
      }

    };
    // retornamo la instancia
    return modelo;
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
