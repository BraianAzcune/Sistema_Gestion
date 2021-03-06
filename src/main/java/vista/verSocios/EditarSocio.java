package vista.verSocios;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

import controlador.ControladorEditarSocio;
import lombok.extern.slf4j.Slf4j;
import modelo.Socio;
import vista.PanelSocio;

/**
 * Esta clase se muetra en un JDialog al instanciarla. (tipo popup)
 * 
 * Esta clase necesita un ID_SOCIO, para recuperar la informacion relacionada a ese socio, y sus
 * deportes. aqui se puede editar un socio, y sus deportes, Ademas de suspenderlo.
 * 
 * @author braian
 *
 */
@Slf4j
public class EditarSocio extends PanelSocio {


  private ControladorEditarSocio controlador;
  private JDialog ventana;

  /**
   * Consulta a db, los datos del socio y sus deportes.
   * 
   * @param ID_SOCIO
   */
  public EditarSocio(String ID_SOCIO) {
    super();
    super.crearPanel("Editar Socio");
    controlador = new ControladorEditarSocio(this);
    controlador.inicializarPanel(ID_SOCIO);

    cambiarNumeroSocioTextfield();

    crearDialog();
  }

  /**
   * 
   */
  private void crearDialog() {
    ventana = new JDialog(Frame.getFrames()[0], "Editar Socio");

    JScrollPane scroll = new JScrollPane(this);
    getVentana().add(scroll);

    getVentana().pack();
    getVentana().setLocationRelativeTo(Frame.getFrames()[0]);
    getVentana().setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    getVentana().setVisible(true);
  }


  private void cambiarNumeroSocioTextfield() {
    this.textFieldNumeroSocio.setEditable(false);
    super.eliminarBotonBorrarNUMEROSOCIO();
  }


  public void putSocio(Socio s) {
    this.textFieldApellido.setText(s.getApellido());
    this.textFieldDireccion.setText(s.getDireccion());
    this.textFieldDNI.setText(s.getDni());
    this.textFieldEmail.setText(s.getEmail());
    this.textFieldNombre.setText(s.getNombre());
    this.textFieldNumeroSocio.setText(s.getNumerosocio());
    this.textFieldTelefono.setText(s.getTelefono());

    // MARCAMOS EL TIPO DE SOCIO QUE ES
    putTipoSocio(s.getTipo_socio());
  }

  private void putTipoSocio(String tipo_socio) {

    Enumeration<AbstractButton> elements = this.grupoTipoSocio.getElements();

    while (elements.hasMoreElements()) {
      AbstractButton nextElement = elements.nextElement();
      if (nextElement.getActionCommand().equals(tipo_socio)) {
        nextElement.setSelected(true);
        break;
      }
    }
  }

  public void putDeportes(int[] idDeportes) {

    Arrays.sort(idDeportes);


    for (JCheckBox check : this.arrayCheckBoxDeportes) {

      if (0 <= Arrays.binarySearch(idDeportes, Integer.parseInt(check.getActionCommand()))) {
        check.setSelected(true);
      }

    }
  }


  @Override
  protected Action accionBotonPrincipal() {
    Action btn = new AbstractAction("Guardar",
        new ImageIcon(getClass().getResource("/imagenes/comprobar.png"))) {

      @Override
      public void actionPerformed(ActionEvent e) {

        EditarSocio.this.controlador.actualizarDatos();

        EditarSocio.this.getVentana().dispose();
      }
    };
    return btn;
  }

  @Override
  protected Action accionBotonSecundario() {
    Action btn = new AbstractAction("Cancelar",
        new ImageIcon(getClass().getResource("/imagenes/cancelar.png"))) {

      @Override
      public void actionPerformed(ActionEvent e) {
        EditarSocio.this.getVentana().dispose();
      }
    };
    return btn;
  }

  public JDialog getVentana() {
    return ventana;
  }

}
