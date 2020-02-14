package vista.verSocios;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VerSocioTest {

  @Test
  public void test() {

    JFrame marco = new JFrame();

    VerSocio panel = new VerSocio();


    log.debug("altura forzada" + panel.getScrollableTracksViewportHeight());
    log.debug("ancho forzado" + panel.getScrollableTracksViewportWidth());

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
