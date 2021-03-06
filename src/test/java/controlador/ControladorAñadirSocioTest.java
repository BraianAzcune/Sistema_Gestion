package controlador;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.swing.JCheckBox;

import org.junit.Before;
import org.junit.Test;

import vista.aņadirVista.AņadirSocio;

public class ControladorAņadirSocioTest {

  private AņadirSocio panel;

  @Before
  public void before() {
    panel = new AņadirSocio();
  }

  @Test
  public void Test() {

  }

  /**
   * Prueba que el placeholder no deja ningun contenido en el jtextfield
   */
  @Test
  public void probarPlaceHolders() {

    assertEquals("", panel.textFieldNumeroSocio.getText());
  }

  @Test
  public void mapearDeportesTest() {
    int cantidadChecks = 5;
    int[] seleccionados = {0, 1, 1, 0, 1};



    panel.arrayCheckBoxDeportes = new ArrayList<>();

    ArrayList<Integer> esperado = new ArrayList<>();

    for (int i = 0; i < cantidadChecks; i++) {
      JCheckBox c = new JCheckBox("check " + i);
      c.setActionCommand(String.valueOf(i));
      panel.arrayCheckBoxDeportes.add(c);

      if (seleccionados[i] == 1) {
        c.setSelected(true);
        esperado.add(i);
      }
    }

    ControladorAņadirSocio controlador = new ControladorAņadirSocio(panel);

    /*
     * int[] rta= controlador.mapearDeportes();
     * 
     * 
     * Assert.assertArrayEquals(esperado.stream().mapToInt(i->i).toArray(), rta);
     * 
     * System.out.println("array= "+Arrays.toString(rta));
     */
  }

}
