package utilidades.utilidadeVista;

import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 * Esta clase es un panel con un borde y un titulo para agrupar radiobutton que se generan
 * dinamicamente pasandoles por el constructor un string con un metodo para saber si tal radiobutton
 * esta seleccionado
 * 
 * @author braian
 *
 */
public class GrupoRadios extends JPanel {

  private String titulo;

  private ButtonGroup grupo;

  /**
   * 
   * @param nombres lista de Strings con los nombres de cada radio
   * @param titulo titulo que tendra el panel
   */
  public GrupoRadios(String[] nombres, String titulo) {
    this.titulo = titulo;

    grupo = new ButtonGroup();

    for (String nombre : nombres) {
      JRadioButton r = new JRadioButton(nombre);
      grupo.add(r);
      this.add(r);

    }

    crearBorde();
  }

  private void crearBorde() {
    TitledBorder ti = BorderFactory.createTitledBorder(titulo);
    this.setBorder(ti);
  }

  /**
   * Comprueba si el JRadioButton que tiene el texto pasado por parametro esta seleccionado
   * 
   * @param nombre
   * @return
   */
  public boolean isSelected(String nombre) {

    Enumeration<AbstractButton> enu = grupo.getElements();
    while (enu.hasMoreElements()) {
      AbstractButton rd = enu.nextElement();
      if (rd.getText().equals(nombre)) {
        return rd.isSelected();
      }
    }
    return false;
  }


  /**
   * Selecciona el radioButton con el nombre pasado
   * 
   * @param nombre nombre que se le puso
   * @return true si se logro asingar, falso sino existe el radio nombrado.
   */
  public boolean setSelected(String nombre) {

    Enumeration<AbstractButton> enu = grupo.getElements();
    while (enu.hasMoreElements()) {
      AbstractButton rd = enu.nextElement();
      if (rd.getText().equals(nombre)) {
        rd.setSelected(true);
        return true;
      }
    }
    return false;
  }

  /***
   * 
   * @return String del radio seleccionado o null si ninguno esta seleccionado.
   */
  public String getSelectedRadio() {
    Enumeration<AbstractButton> enu = grupo.getElements();
    while (enu.hasMoreElements()) {
      AbstractButton rd = enu.nextElement();
      if (rd.isSelected()) {
        return rd.getText();
      }
    }
    return null;
  }

  /**
   * Deselecciona todos los radioButton
   */
  public void limpiar() {
    grupo.clearSelection();
  }

  /**
   * Añadir un oyente para cuando cualquier radio button sea seleccionado.
   */
  public void addActionListenerALLRadios(ActionListener oyente) {
    Enumeration<AbstractButton> enu = grupo.getElements();
    while (enu.hasMoreElements()) {
      AbstractButton rd = enu.nextElement();
      rd.addActionListener(oyente);
    }
  }

}
