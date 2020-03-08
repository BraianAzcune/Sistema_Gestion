package vista.verSocios;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ref.WeakReference;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.Test;

public class EditarSocioTest {

  WeakReference<EditarSocio> refDebil;

  @Test
  public void test() {
    JFrame marco = new JFrame();
    marco.setLayout(new FlowLayout());



    JButton btn = new JButton("verificar si esta");
    btn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        System.gc();
        if (refDebil != null) {
          System.out.println((refDebil.get() == null) ? "se libero" : "esta");
        } else {
          System.out.println("refDebil no instanciada");
        }

      }
    });
    marco.add(btn);

    JButton btn2 = new JButton("Instanciar");
    btn2.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        EditarSocio panel = new EditarSocio("729");

        refDebil = new WeakReference<EditarSocio>(panel);
      }
    });

    marco.add(btn2);

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


