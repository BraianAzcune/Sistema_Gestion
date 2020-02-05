package vista;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import lombok.extern.slf4j.Slf4j;
import modelo.BaseDatos;
import vista.añadirVista.AñadirSocio;

@Slf4j
public class Principal extends JFrame {

  private JPanel layergruop;
  private JTextField Nombre;
  private JTextField Apellido;
  private JTextField Direccion;
  private JTextField DNI;
  private JTextField Telefono;
  private JTextField NumerodeSocio;
  private JTextField Email;
  private JTextField FechadeAlta;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {

    CambiarEstilosGlobales();

    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          Principal frame = new Principal();
          frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
              BaseDatos.apagar();
              super.windowClosing(e);
            }
          });


          frame.setVisible(true);
        } catch (Exception e) {
          log.error("\nError en la ventana principal=", e);
          e.printStackTrace();
        }
      }
    });
  }

  private static void CambiarEstilosGlobales() {

    try {

      JFrame.setDefaultLookAndFeelDecorated(true);
      JDialog.setDefaultLookAndFeelDecorated(true);
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
      // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");//Este no funciona bien
      // UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//tampoco
      // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//mismo
      // UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");//same
      // UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");/same


    } catch (Exception e) {
      log.error("Error en cambiarEstilos el estilo no esta (probablemente)=", e);
    }
  }

  /**
   * Create the frame.
   */
  public Principal() {

    setTitle("Cultural Argentino");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(200, 200, 786, 686);
    setIconImage(new ImageIcon(getClass().getResource("/imagenes/cultu.png")).getImage());
    // Incorporo al JFRAME Principal un panel AñadirSocio
    JPanel panelAñadirSocio = new AñadirSocio();
    add(panelAñadirSocio);


  }
}
