package vista.aņadirVista;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import controlador.ControladorAņadirSocio;
import utilidades.utilidadeVista.TextPrompt;
import vista.PanelSocio;



public class AņadirSocio extends PanelSocio {


  private ControladorAņadirSocio controladorAņadir;

  @Override
  protected Action accionBotonPrincipal() {
    Action btn = new AbstractAction("Guardar",
        new ImageIcon(getClass().getResource("/imagenes/comprobar.png"))) {



      @Override
      public void actionPerformed(ActionEvent e) {
        AņadirSocio.this.controladorAņadir.aņadirSocio();
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

        AņadirSocio.this.limpiar();
      }
    };
    return btn;
  }

  public AņadirSocio() {
    this.controladorAņadir = new ControladorAņadirSocio(this);
    super.crearPanel("Aņadir Socio");


    // CREAMOS PLACEHOLDER PARA NUMERO SOCIO
    TextPrompt placeholder =
        new TextPrompt("dejar en blanco, para asignacion automatica", textFieldNumeroSocio);
    placeholder.changeAlpha(0.4f);
    placeholder.changeStyle(Font.ITALIC);
  }

  public void limpiar() {
    AņadirSocio.this.resetear();
    // Hacemos que el primer boton del grupo de socios, se seleccione, para evitar cosas raras
    AņadirSocio.this.grupoTipoSocio.getElements().nextElement().setSelected(true);
  }
}
