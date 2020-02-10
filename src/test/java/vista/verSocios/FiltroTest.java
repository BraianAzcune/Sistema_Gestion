package vista.verSocios;

import javax.swing.JFrame;

import org.junit.Test;

public class FiltroTest {

  @Test
  public void mostrarFiltro() {
    JFrame marco = new JFrame();
    // marco.add(new Filtro());

    marco.setBounds(400, 400, 900, 500);
    marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    marco.setVisible(true);
    try {
      Thread.sleep((long) 3.6e+6);// duerme durante 1 hora
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
