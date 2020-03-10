package vista.verSocios;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import controlador.ControladorFiltro;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class VistaResultado extends JPanel implements MouseListener, KeyListener {

  private Filtro filtro;

  // Se utiliza para obtener el proveedor de datos.
  private ControladorFiltro controladorFiltro;

  private JTable tablaSocios;

  private PaginadorTabla<Socio> paginador;

  public VistaResultado(ControladorFiltro controladorFiltro) {
    this.controladorFiltro = controladorFiltro;
    construirPanel();
  }

  /**
   * Se debe llamar este metodo para actulizar los resultados
   * 
   * @param m
   * @param p
   * 
   */
  public void actualizarResultadosAMostrar(ModeloTabla<Socio> m,
      ProveedorDeDatosPaginacion<Socio> p) {
    tablaSocios.setModel(m);
    paginador.actualizarModeloYPaginacion(m, p);
  }

  private void construirPanel() {
    this.setLayout(new BorderLayout());

    // MODELO DE TABLA (define las columnas que tiene, y sus nombres)
    TableModel modelo = crearModeloTabla();
    tablaSocios = new JTable(modelo);
    tablaSocios.addMouseListener(this);
    tablaSocios.addKeyListener(this);
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
    paginador = new PaginadorTabla<Socio>(tablaSocios, proveedorDeDatosPaginacion,
        new int[] {10, 25, 50, 100}, 10);

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


  private void crearVentanaEditarSocio() {
    String idSocio = (String) this.tablaSocios.getValueAt(this.tablaSocios.getSelectedRow(), 0);
    EditarSocio editar = new EditarSocio(idSocio);
    editar.getVentana().addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        paginador.paginar();
      }
    });
  }

  // EVENTOS MOUSE.

  // Hacemos que con doble click se habra EditarSocio, y cuando se cierre se actualiza la tabla.
  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getClickCount() == 2) {
      crearVentanaEditarSocio();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      crearVentanaEditarSocio();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  @Override
  public void keyTyped(KeyEvent e) {}
}
