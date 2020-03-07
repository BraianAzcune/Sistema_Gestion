package vista.verSocios;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.junit.Test;

public class EditarSocioTest {

  @Test
  public void test() {
    JFrame marco = new JFrame();

    EditarSocio panel = new EditarSocio("729");

    JScrollPane scroll = new JScrollPane(panel);
    marco.add(scroll);

    marco.setBounds(400, 500, 600, 500);
    marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    marco.setVisible(true);
    try {
      Thread.sleep((long) 3.6e+6);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}


