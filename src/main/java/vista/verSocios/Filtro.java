package vista.verSocios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import controlador.ControladorFiltro;
import eu.hansolo.custom.SteelCheckBox;
import eu.hansolo.tools.ColorDef;
import lombok.extern.slf4j.Slf4j;
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

  private SteelCheckBox btnCualquieraDeporte;
  private SteelCheckBox btnCualquieraTipoSocio;
  private VerSocio padre;

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
        log.error("no implementado");
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
   * Hace que los radioButtons del tipo socio, al ser apretados, pongan en false el boton cualquiera
   */
  private void añadirEscucharCheckBoxTipoSocio() {
    Enumeration<AbstractButton> e = this.grupoTipoSocio.getElements();
    while (e.hasMoreElements()) {
      e.nextElement().addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          btnCualquieraTipoSocio.setSelected(false);
        }
      });
    }
  }

  /**
   * Ponemos a escuchar los eventos de checkBox si alguno es seleccionado el boton cualquiera se
   * desactiva
   */
  private void añadirEscucharCheckBoxDeporte() {

    for (JCheckBox check : this.arrayCheckBoxDeportes) {
      check.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

          btnCualquieraDeporte.setSelected(false);
        }
      });
    }

  }

  /**
   * crea un boton cualquiera, que esta activado en verdad por defecto.
   */
  private void modificarTipoSocio() {

    btnCualquieraTipoSocio = new SteelCheckBox();
    btnCualquieraTipoSocio.setText("Cualquiera");
    btnCualquieraTipoSocio.setColored(true);
    btnCualquieraTipoSocio.setSelectedColor(ColorDef.RED);
    btnCualquieraTipoSocio.setSelected(true);

    // evento del boton
    btnCualquieraTipoSocio.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent event) {
        grupoTipoSocio.clearSelection();
      }
    });

    this.grupoTipoSocio.clearSelection();

    this.panelTipoSocio.add(btnCualquieraTipoSocio);
  }

  /**
   * Agrega un boton de estado "cualquiera", cuando es seleccionado se deseleccionan todos los
   * checkbox.
   */
  private void modificarPanelDeporte() {

    btnCualquieraDeporte = new SteelCheckBox();
    btnCualquieraDeporte.setText("Cualquiera");
    btnCualquieraDeporte.setColored(true);
    btnCualquieraDeporte.setSelectedColor(ColorDef.RED);
    btnCualquieraDeporte.setSelected(true);

    btnCualquieraDeporte.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        for (JCheckBox check : Filtro.this.arrayCheckBoxDeportes) {
          check.setSelected(false);
        }
      }
    });

    this.panelDeportes.add(btnCualquieraDeporte);

  }

  @Override
  protected void resetear() {

    super.resetear();
    this.btnCualquieraDeporte.setSelected(true);
    this.btnCualquieraTipoSocio.setSelected(true);
  }

  @Override
  public int queTipoSocioEs() {
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
