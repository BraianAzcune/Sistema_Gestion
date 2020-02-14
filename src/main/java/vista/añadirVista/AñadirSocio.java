package vista.añadirVista;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import controlador.ControladorAñadirSocio;
import utilidades.utilidadeVista.TextPrompt;
import vista.PanelSocio;



public class AñadirSocio extends PanelSocio {


  private ControladorAñadirSocio controladorAñadir;

  @Override
  protected Action accionBotonPrincipal() {
    Action btn = new AbstractAction("Guardar",
        new ImageIcon(getClass().getResource("/imagenes/comprobar.png"))) {



      @Override
      public void actionPerformed(ActionEvent e) {
        AñadirSocio.this.controladorAñadir.añadirSocio();
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

        AñadirSocio.this.limpiar();
      }
    };
    return btn;
  }

  public AñadirSocio() {
    this.controladorAñadir = new ControladorAñadirSocio(this);
    super.crearPanel("Añadir Socio");


    // CREAMOS PLACEHOLDER PARA NUMERO SOCIO
    TextPrompt placeholder =
        new TextPrompt("dejar en blanco, para asignacion automatica", textFieldNumeroSocio);
    placeholder.changeAlpha(0.4f);
    placeholder.changeStyle(Font.ITALIC);
  }

  public void limpiar() {
    AñadirSocio.this.resetear();
    // Hacemos que el primer boton del grupo de socios, se seleccione, para evitar cosas raras
    AñadirSocio.this.grupoTipoSocio.getElements().nextElement().setSelected(true);
  }
}
