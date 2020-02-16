package vista.verSocios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import controlador.ControladorFiltro;
import eu.hansolo.custom.SteelCheckBox;
import eu.hansolo.tools.ColorDef;
import lombok.extern.slf4j.Slf4j;
import utilidades.utilidadeVista.GrupoRadios;
import vista.PanelSocio;


/**
 * Panel que estara contenida dentro de un panel collapsable, y que se encargara de mostrar la vista
 * para filtrar la informacion de socios.
 * 
 * @author braian
 *
 */
@Slf4j
public class Filtro extends PanelSocio {

  private ControladorFiltro controladorFiltro;
  private VistaResultado vistaResultado;

  public ControladorFiltro getControladorFiltro() {
    return controladorFiltro;
  }

  private SteelCheckBox btnTodosDeporte;
  private SteelCheckBox btnTodosTipoSocio;
  private VerSocio padre;


  /**
   * Panel que se inserta en el panel deporte, y controla los radioButtons que tienen las opciones
   * de filtrado por deporte "Y" "O"
   */
  private GrupoRadios radiosOpcionDeporte;


  @Override
  protected Action accionBotonPrincipal() {

    Action btn = new AbstractAction("Filtrar",
        new ImageIcon(getClass().getResource("/imagenes/buscar.png"))) {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        // !TODO esto debe llamar al controladorFiltro, probablemente en un swingWorker. y ademas
        // decirle a verSocio
        // que acaba de ejecutar esta opcion, para que cierre el panel desplegable, asi se muestra
        // el otro.
        Filtro.this.controladorFiltro.actualizarSQL();
        Filtro.this.padre.showPanelVistaResultado();
        // Filtro.this.log.debug("is Y opcion= " + Filtro.this.isYoptionDeporte());
        Filtro.this.log.error("no implementado");
      }
    };
    return btn;
  }

  @Override
  protected Action accionBotonSecundario() {

    Action btn = new AbstractAction("Limpiar",
        new ImageIcon(getClass().getResource("/imagenes/limpiar.png"))) {

      @Override
      public void actionPerformed(ActionEvent e) {

        Filtro.this.resetear();
      }
    };
    return btn;
  }

  public Filtro(VerSocio padre) {
    this.padre = padre;
    this.controladorFiltro = new ControladorFiltro(this);

    super.crearPanel("Filtro Socios");

    modificarPanelDeporte();

    modificarTipoSocio();

    añadirEscucharCheckBoxDeporte();
    añadirEscucharCheckBoxTipoSocio();

  }

  /**
   * Hace que los radioButtons del tipo socio, al ser apretados, pongan en false el boton Todos
   */
  private void añadirEscucharCheckBoxTipoSocio() {
    Enumeration<AbstractButton> e = this.grupoTipoSocio.getElements();
    while (e.hasMoreElements()) {
      e.nextElement().addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          btnTodosTipoSocio.setSelected(false);
        }
      });
    }
  }

  /**
   * Ponemos a escuchar los eventos de checkBox si alguno es seleccionado el boton Todos se
   * desactiva
   */
  private void añadirEscucharCheckBoxDeporte() {

    for (JCheckBox check : this.arrayCheckBoxDeportes) {
      check.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

          btnTodosDeporte.setSelected(false);
        }
      });
    }

  }

  /**
   * crea un boton Todos, que esta activado en verdad por defecto.
   */
  private void modificarTipoSocio() {

    btnTodosTipoSocio = new SteelCheckBox();
    btnTodosTipoSocio.setText("Todos");
    btnTodosTipoSocio.setColored(true);
    btnTodosTipoSocio.setSelectedColor(ColorDef.RED);
    btnTodosTipoSocio.setSelected(true);

    // evento del boton
    btnTodosTipoSocio.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent event) {
        grupoTipoSocio.clearSelection();
      }
    });

    this.grupoTipoSocio.clearSelection();

    this.panelTipoSocio.add(btnTodosTipoSocio);
  }

  /**
   * crea un boton opcion "Y" por defecto (activado) y "O" (desactivado), para definir como se busca
   * los deportes.
   * 
   * ej: que tenga Basket y futbol. VS que tenga basket o futbol.
   * 
   * crea un boton de estado "Todos", cuando es seleccionado se deseleccionan todos los checkbox.
   */
  private void modificarPanelDeporte() {

    crearBotonesOpcionFiltradoDeporte(this.panelDeportes);



    // crear boton de estado "Todos"
    btnTodosDeporte = new SteelCheckBox();
    btnTodosDeporte.setText("Todos");
    btnTodosDeporte.setColored(true);
    btnTodosDeporte.setSelectedColor(ColorDef.RED);
    btnTodosDeporte.setSelected(true);

    // Si se apreta el boton de TODOS se desactivan las otras opciones.
    btnTodosDeporte.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        for (JCheckBox check : Filtro.this.arrayCheckBoxDeportes) {
          check.setSelected(false);
          Filtro.this.radiosOpcionDeporte.limpiar();
        }
      }
    });

    this.panelDeportes.add(btnTodosDeporte);

  }

  /**
   * añade la opcion de filtrado, esto sera tenido en cuenta para el filtro
   */
  private void crearBotonesOpcionFiltradoDeporte(JPanel padre) {
    radiosOpcionDeporte = new GrupoRadios(new String[] {"Y", "O"}, "opciones");

    // Si se apreta alguna opcion se desactiva la opcion "TODOS"
    radiosOpcionDeporte.addActionListenerALLRadios(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Filtro.this.btnTodosDeporte.setSelected(false);
      }
    });
    this.panelDeportes.add(radiosOpcionDeporte);
  }


  /**
   * Retorna true si la opcion de filtrado del deporte esta en Y.
   * 
   * @return
   */
  public boolean isYoptionDeporte() {
    return radiosOpcionDeporte.isSelected("Y");
  }


  @Override
  protected void resetear() {

    // resetea todos los campos comunes
    super.resetear();
    // se ponen los botones de Todos en activo por defecto
    this.btnTodosDeporte.setSelected(true);
    this.radiosOpcionDeporte.limpiar();
    this.btnTodosTipoSocio.setSelected(true);

  }

  @Override
  public String queTipoSocioEs() {
    // TODO Auto-generated method stub
    log.debug("NO IMPLEMENTADO");
    throw new java.lang.UnsupportedOperationException("Not supported yet.");
  }

  /**
   * ControladorFiltro deberia llamar a este metodo, para que filtro le avise a verResultado, que
   * hay un nuevo proveedor.
   */
  public void fireProveedorNuevoDisponible() {
    this.vistaResultado.nuevoProveedor();
  }

  public VistaResultado getVistaResultado() {
    return vistaResultado;
  }

  public void setVistaResultado(VistaResultado vistaResultado) {
    this.vistaResultado = vistaResultado;
  }
}
