package vista.a�adirVista;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import controlador.ControladorA�adirSocio;
import utilidades.utilidadeVista.TextPrompt;
import vista.PanelSocio;



public class A�adirSocio extends PanelSocio {


  private ControladorA�adirSocio controladorA�adir;

  @Override
  protected Action accionBotonPrincipal() {
    Action btn = new AbstractAction("Guardar",
        new ImageIcon(getClass().getResource("/imagenes/comprobar.png"))) {



      @Override
      public void actionPerformed(ActionEvent e) {
        A�adirSocio.this.controladorA�adir.a�adirSocio();
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

        A�adirSocio.this.limpiar();
      }
    };
    return btn;
  }

  public A�adirSocio() {
    this.controladorA�adir = new ControladorA�adirSocio(this);
    super.crearPanel("A�adir Socio");


    // CREAMOS PLACEHOLDER PARA NUMERO SOCIO
    TextPrompt placeholder =
        new TextPrompt("dejar en blanco, para asignacion automatica", textFieldNumeroSocio);
    placeholder.changeAlpha(0.4f);
    placeholder.changeStyle(Font.ITALIC);
  }

  public void limpiar() {
    A�adirSocio.this.resetear();
    // Hacemos que el primer boton del grupo de socios, se seleccione, para evitar cosas raras
    A�adirSocio.this.grupoTipoSocio.getElements().nextElement().setSelected(true);
  }
}
