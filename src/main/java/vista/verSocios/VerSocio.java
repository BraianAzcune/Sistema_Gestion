package vista.verSocios;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import controlador.ControladorFiltro;
import lombok.extern.slf4j.Slf4j;

/**
 * Panel principal que administra la opcion verSocio
 * 
 * se encarga de inyectar las dependencias que se deban inyectar a cada clase de abajo.
 * 
 * @author braian
 *
 */
@Slf4j
public class VerSocio extends JXTaskPaneContainer implements PropertyChangeListener {

  private ControladorFiltro controladorFiltro;

  private Filtro filtro;
  private VistaResultado vistaResultado;

  /**
   * Se utilizan para controlar el deslizamiento de los paneles para que haya solo uno visible a la
   * vez.
   */
  private JXTaskPane panelFiltro;
  private JXTaskPane panelVistaResultado;


  private int fuenteTitulosPanelesDeslizables = 26;

  public VerSocio() {

    // CONSTRUIMOS PANELES DESLIZABLES, y los paneles que estaran dentro
    crearFiltro();
    crearVistaResultado();
    unirFiltroYResultado();


  }

  /**
   * !TODO probablemente se pueda hacer mejor, y esto incurra a complicar mucho las cosas. Les
   * inyecta a cada uno el otro, para que se puedan comunicar.
   */
  private void unirFiltroYResultado() {

    this.filtro.setVistaResultado(vistaResultado);
    this.vistaResultado.setFiltro(filtro);

  }

  private void crearVistaResultado() {

    this.vistaResultado = new VistaResultado(this.controladorFiltro);

    // configuramos el panel deslizable donde estara la vistaResultado
    this.panelVistaResultado = new JXTaskPane();
    this.panelVistaResultado.setTitle("Resultados");
    this.panelVistaResultado.setCollapsed(true);

    // añadimos vistaResultado dentro del panel deslizable
    this.panelVistaResultado.add(this.vistaResultado);

    // lo añadimos al contenedor
    this.add(this.panelVistaResultado);

    // nos añadimos para escuchar eventos de deslizamiento del panel deslizable
    this.panelVistaResultado.addPropertyChangeListener(this);

    // le cambiamos la fuente al titulo.
    this.panelVistaResultado
        .setFont(new Font("Tahoma", Font.PLAIN, fuenteTitulosPanelesDeslizables));

    log.debug("Puede requerir mas trabajo");
  }

  /**
   * Aqui se inicializa ControladorFiltro
   */
  private void crearFiltro() {
    // TODO Auto-generated method stub
    this.filtro = new Filtro(this);
    this.controladorFiltro = this.filtro.getControladorFiltro();


    // configuramos el panel deslizable donde estara filtro
    this.panelFiltro = new JXTaskPane();
    this.panelFiltro.setTitle("Filtro");
    // por defecto este estara desplegado
    this.panelFiltro.setCollapsed(false);
    // añadimos el panel del filtro al panel deslizable
    this.panelFiltro.add(filtro);

    // añadimos el panel deslizable a este contenedor
    this.add(this.panelFiltro);

    // nos añadimos para escuchar eventos de deslizamiento del panel deslizable
    this.panelFiltro.addPropertyChangeListener(this);

    // le cambiamos la fuente al titulo.
    this.panelFiltro.setFont(new Font("Tahoma", Font.PLAIN, fuenteTitulosPanelesDeslizables));

    log.debug("Puede requerir mas trabajo");
  }

  /**
   * Cuando ocurre un cambio con proveedorDeDatos que se muestren los resultados.
   */
  public void showPanelVistaResultado() {
    this.panelVistaResultado.setCollapsed(false);
  }

  /**
   * Implementa la logica de hacer que este 1 solo panel deslizante abierto a la vez.
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    // TODO Auto-generated method stub
    // log.debug("entre a propertychange");
    if (evt.getPropertyName().equals("collapsed")) {
      // log.debug("el evento es collapse");
      if (evt.getSource().equals(this.panelFiltro)) {
        // log.debug("lo genero panelfiltro");
        // true significa que se va a cerrar. por lo tanto false signfica que se abra.
        if (evt.getNewValue().equals(false)) {
          // log.debug("fue falso");
          this.panelVistaResultado.setCollapsed(true);
        }
      } else {
        // log.debug("lo genero panelVistaResutado");
        if (evt.getNewValue().equals(false)) {
          // log.debug("fue falso");
          this.panelFiltro.setCollapsed(true);
        }
      }
    }
  }


}
