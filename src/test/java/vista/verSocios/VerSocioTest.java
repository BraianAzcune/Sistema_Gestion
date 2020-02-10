package vista.verSocios;

import javax.swing.JFrame;

import org.junit.Test;

public class VerSocioTest {

  @Test
  public void test() {

    JFrame marco = new JFrame();

    VerSocio panel = new VerSocio();

    marco.add(panel);



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
