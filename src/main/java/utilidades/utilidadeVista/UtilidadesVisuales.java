package utilidades.utilidadeVista;

import java.awt.Component;

import javax.swing.JPanel;

public class UtilidadesVisuales {


  /**
   * Lllame recursivamente para habilitar o desabilitar todos los componentes.
   * 
   * @param panel
   * @param isEnabled
   */
  public static void setPanelEnabled(JPanel panel, Boolean isEnabled) {
    panel.setEnabled(isEnabled);

    Component[] components = panel.getComponents();

    for (int i = 0; i < components.length; i++) {
      if (components[i].getClass().getName() == "javax.swing.JPanel") {
        setPanelEnabled((JPanel) components[i], isEnabled);
      }

      components[i].setEnabled(isEnabled);
    }
  }

}
