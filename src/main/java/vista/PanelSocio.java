package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Deporte;
import modelo.Socio;
import utilidades.utilidadeVista.InformacionDialog;
import utilidades.utilidadeVista.JTextFieldLimit;
import utilidades.utilidadeVista.Verficador;

/**
 * Panel generico de socio
 * 
 * @author braian
 *
 */
public abstract class PanelSocio extends JPanel {

  /**
   * Se debe implementar esto, que dira que hace, como luce, que texto tiene, el boton principal
   * 
   * @return Action con su texto, icono, y accion
   */
  protected abstract Action accionBotonPrincipal();

  /**
   * Se debera implemetnar esto, que dira que hace, como luce, que texto tiene, el boton secundario.
   * 
   * @return
   */
  protected abstract Action accionBotonSecundario();


  // Esto es lo que hay que cargar en la base de datos
  public JTextField textFieldNombre;
  public JTextField textFieldApellido;
  public JTextField textFieldDireccion;
  public JTextField textFieldDNI;
  public JTextField textFieldTelefono;
  public JTextField textFieldNumeroSocio;
  public JTextField textFieldEmail;


  // mensajes que se muestran si escribe algo erroneo
  private JTextField txtNombreIncorrecto;
  private JTextField txtApellidoIncorrecto;
  private JTextField txtEmailIncorrecto;
  // Mensajes que se crean en los Dialog
  private static final String msgAyuda =
      "<ul><li>Utiliza Tab para navegar entre los campos</li><li>Shift+Tab para retroceder</li><li>Espacio para clic</li></ul>";



  private JPanel panel1;


  /**
   * Si agregas alguna opcion aparte de lo que se trae desde la base de datos, agregalo a este panel
   * con respecto a deportes. acordate de agregarlo al arrayCheckBoxDeportes
   */
  protected JPanel panelDeportes;
  public List<JCheckBox> arrayCheckBoxDeportes = new ArrayList<JCheckBox>();

  /**
   * Si queres agregar algun jradio extra o boton debes hacerlo en este panel, para el lugar de
   * socios, si es un radiobutton agregalo a grupoTipoSocio tambien
   */
  protected JPanel panelTipoSocio;
  /**
   * Esto esta estatico, no es lo mejor ya que si se cambia la db haya que cambiar el codigo pero no
   * tiene pinta que se vaya a agregar otra categoria.
   * 
   * por lo tanto los valores de a que grupo de socio pertenece esta dado en como se agregaron al
   * grupo de botones, asi que que no se cambie el orden en que estan los radiobutton, sino se jode
   * todo.
   */
  protected ButtonGroup grupoTipoSocio;


  /**
   * ESTE metodo debe ser llamado despues de asegurar que esta inicializado todo aquello que use los
   * metodos abstractos
   * 
   * @param Titulo
   */
  protected void crearPanel(String Titulo) {

    setFocusable(false);
    this.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.setLayout(new BorderLayout(0, 0));


    JLabel lblTitulo = new JLabel(Titulo);
    lblTitulo.setFocusable(false);
    lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 22));
    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    lblTitulo.setIcon(new ImageIcon(getClass().getResource("/imagenes/identificacion2.png")));
    this.add(lblTitulo, BorderLayout.NORTH);

    JPanel panel = new JPanel();
    panel.setFocusable(false);
    this.add(panel, BorderLayout.SOUTH);
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

    JButton btnAyuda = new JButton("Ayuda");
    btnAyuda.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        new InformacionDialog(PanelSocio.this, msgAyuda);
      }
    });
    btnAyuda.setFocusable(false);
    btnAyuda.setIcon(new ImageIcon(
        PanelSocio.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));
    btnAyuda.setFont(new Font("Tahoma", Font.PLAIN, 20));


    JButton btnPrincipal = new JButton();
    btnPrincipal.setAlignmentX(Component.RIGHT_ALIGNMENT);
    btnPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 20));
    btnPrincipal.setAction(accionBotonPrincipal());

    JButton btnSecundario = new JButton();
    btnSecundario.setAlignmentX(Component.RIGHT_ALIGNMENT);
    btnSecundario.setFont(new Font("Tahoma", Font.PLAIN, 20));
    btnSecundario.setAction(accionBotonSecundario());


    // Creamos el layout de abajo
    // la caja crea las restricciones añadimos los componentes que estaran dentro y luego la caja se
    // añade al panel.
    Box box = Box.createHorizontalBox();
    box.add(btnAyuda);
    box.add(Box.createHorizontalGlue());
    box.add(btnPrincipal);
    box.add(Box.createHorizontalStrut(5));
    box.add(btnSecundario);

    panel.add(box);

    panel1 = new JPanel();
    panel1.setFocusable(false);
    this.add(panel1, BorderLayout.CENTER);
    GridBagLayout gbl_panel1 = new GridBagLayout();
    gbl_panel1.columnWidths = new int[] {82, 101, 0, 0};
    gbl_panel1.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    gbl_panel1.columnWeights = new double[] {0.0, 1.0, 0.0, Double.MIN_VALUE};
    gbl_panel1.rowWeights = new double[] {1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0,
        0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
    panel1.setLayout(gbl_panel1);

    JLabel lblNombre = new JLabel("Nombre");
    lblNombre.setFocusable(false);
    lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_lblNombre = new GridBagConstraints();
    gbc_lblNombre.anchor = GridBagConstraints.WEST;
    gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
    gbc_lblNombre.gridx = 0;
    gbc_lblNombre.gridy = 0;
    panel1.add(lblNombre, gbc_lblNombre);

    textFieldNombre = new JTextField();
    textFieldNombre.setDocument(new JTextFieldLimit(18));
    textFieldNombre.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent e) {
        if ((textFieldNombre.getText() != null)
            && (textFieldNombre.getText().matches("^[a-zA-Z ]*$"))) {
          txtNombreIncorrecto.setVisible(false);
          panel1.revalidate();
          panel1.repaint();


        } else {
          textFieldNombre.requestFocusInWindow();
          txtNombreIncorrecto.setVisible(true);
        } ;
        panel1.revalidate();
        panel1.repaint();

      }

    });
    textFieldNombre.setInputVerifier(new Verficador());
    textFieldNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
    gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
    gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
    gbc_textFieldNombre.gridx = 1;
    gbc_textFieldNombre.gridy = 0;
    panel1.add(textFieldNombre, gbc_textFieldNombre);
    textFieldNombre.setColumns(10);

    JButton btnBorrarNombre = new JButton("");
    btnBorrarNombre.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        textFieldNombre.setText("");
      }
    });
    btnBorrarNombre.setFocusable(false);
    btnBorrarNombre.setToolTipText("Borrar campo");
    btnBorrarNombre.setIcon(new ImageIcon(getClass().getResource("/imagenes/eliminar.png")));
    GridBagConstraints gbc_btnBorrarNombre = new GridBagConstraints();
    gbc_btnBorrarNombre.insets = new Insets(0, 0, 5, 0);
    gbc_btnBorrarNombre.gridx = 2;
    gbc_btnBorrarNombre.gridy = 0;
    panel1.add(btnBorrarNombre, gbc_btnBorrarNombre);

    txtNombreIncorrecto = new JTextField();
    txtNombreIncorrecto.setBackground(Color.RED);
    txtNombreIncorrecto.setVisible(false);
    txtNombreIncorrecto.setText("Solo se permiten letras");
    GridBagConstraints gbc_txtNombreIncorrecto = new GridBagConstraints();
    gbc_txtNombreIncorrecto.insets = new Insets(0, 0, 5, 5);
    gbc_txtNombreIncorrecto.fill = GridBagConstraints.HORIZONTAL;
    gbc_txtNombreIncorrecto.gridx = 1;
    gbc_txtNombreIncorrecto.gridy = 1;
    panel1.add(txtNombreIncorrecto, gbc_txtNombreIncorrecto);
    txtNombreIncorrecto.setColumns(10);

    JLabel lblApellido = new JLabel("Apellido");
    lblApellido.setFocusable(false);
    lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_lblApellido = new GridBagConstraints();
    gbc_lblApellido.anchor = GridBagConstraints.WEST;
    gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
    gbc_lblApellido.gridx = 0;
    gbc_lblApellido.gridy = 2;
    panel1.add(lblApellido, gbc_lblApellido);

    textFieldApellido = new JTextField();
    textFieldApellido.setDocument(new JTextFieldLimit(18));
    textFieldApellido.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent e) {
        if ((textFieldApellido.getText() != null)
            && (textFieldApellido.getText().matches("^[a-zA-Z ]*$"))) {
          txtApellidoIncorrecto.setVisible(false);
          panel1.revalidate();
          panel1.repaint();


        } else {
          textFieldApellido.requestFocusInWindow();
          txtApellidoIncorrecto.setVisible(true);
        } ;
        panel1.revalidate();
        panel1.repaint();

      }

    });
    textFieldApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
    gbc_textFieldApellido.insets = new Insets(0, 0, 5, 5);
    gbc_textFieldApellido.fill = GridBagConstraints.HORIZONTAL;
    gbc_textFieldApellido.gridx = 1;
    gbc_textFieldApellido.gridy = 2;
    panel1.add(textFieldApellido, gbc_textFieldApellido);
    textFieldApellido.setColumns(10);

    JButton btnBorrarApellido = new JButton("");
    btnBorrarApellido.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        textFieldApellido.setText("");
      }
    });
    btnBorrarApellido.setFocusable(false);
    btnBorrarApellido.setIcon(new ImageIcon(getClass().getResource("/imagenes/eliminar.png")));
    btnBorrarApellido.setToolTipText("Borrar campo");
    GridBagConstraints gbc_btnBorrarApellido = new GridBagConstraints();
    gbc_btnBorrarApellido.insets = new Insets(0, 0, 5, 0);
    gbc_btnBorrarApellido.gridx = 2;
    gbc_btnBorrarApellido.gridy = 2;
    panel1.add(btnBorrarApellido, gbc_btnBorrarApellido);

    txtApellidoIncorrecto = new JTextField();
    txtApellidoIncorrecto.setVisible(false);
    txtApellidoIncorrecto.setBackground(Color.RED);
    txtApellidoIncorrecto.setText("Solo se permiten Letras");
    GridBagConstraints gbc_txtApellidoIncorrecto = new GridBagConstraints();
    gbc_txtApellidoIncorrecto.insets = new Insets(0, 0, 5, 5);
    gbc_txtApellidoIncorrecto.fill = GridBagConstraints.HORIZONTAL;
    gbc_txtApellidoIncorrecto.gridx = 1;
    gbc_txtApellidoIncorrecto.gridy = 3;
    panel1.add(txtApellidoIncorrecto, gbc_txtApellidoIncorrecto);
    txtApellidoIncorrecto.setColumns(10);

    JLabel lblDireccion = new JLabel("Direccion");
    lblDireccion.setFocusable(false);
    lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
    gbc_lblDireccion.anchor = GridBagConstraints.WEST;
    gbc_lblDireccion.insets = new Insets(0, 0, 5, 5);
    gbc_lblDireccion.gridx = 0;
    gbc_lblDireccion.gridy = 4;
    panel1.add(lblDireccion, gbc_lblDireccion);

    textFieldDireccion = new JTextField();
    textFieldDireccion.setDocument(new JTextFieldLimit(36));
    textFieldDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_textFieldDireccion = new GridBagConstraints();
    gbc_textFieldDireccion.insets = new Insets(0, 0, 5, 5);
    gbc_textFieldDireccion.fill = GridBagConstraints.HORIZONTAL;
    gbc_textFieldDireccion.gridx = 1;
    gbc_textFieldDireccion.gridy = 4;
    panel1.add(textFieldDireccion, gbc_textFieldDireccion);
    textFieldDireccion.setColumns(10);

    JButton btnBorrarDireccion = new JButton("");
    btnBorrarDireccion.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        textFieldDireccion.setText("");
      }

    });
    btnBorrarDireccion.setFocusable(false);
    btnBorrarDireccion.setIcon(new ImageIcon(getClass().getResource("/imagenes/eliminar.png")));
    btnBorrarDireccion.setToolTipText("Borrar campo");
    GridBagConstraints gbc_btnBorrarDireccion = new GridBagConstraints();
    gbc_btnBorrarDireccion.insets = new Insets(0, 0, 5, 0);
    gbc_btnBorrarDireccion.gridx = 2;
    gbc_btnBorrarDireccion.gridy = 4;
    panel1.add(btnBorrarDireccion, gbc_btnBorrarDireccion);

    JLabel lblDni = new JLabel("DNI");
    lblDni.setFocusable(false);
    lblDni.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_lblDni = new GridBagConstraints();
    gbc_lblDni.anchor = GridBagConstraints.WEST;
    gbc_lblDni.insets = new Insets(0, 0, 5, 5);
    gbc_lblDni.gridx = 0;
    gbc_lblDni.gridy = 5;
    panel1.add(lblDni, gbc_lblDni);

    textFieldDNI = new JTextField();
    textFieldDNI.setDocument(new JTextFieldLimit(9));


    textFieldDNI.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent ke) {
        String value = textFieldDNI.getText();
        char c = ke.getKeyChar();

        if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || (c == KeyEvent.VK_BACK_SPACE)
            || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER)) {
          textFieldDNI.setEditable(true);

        } else {
          textFieldDNI.setEditable(false);
        }



      };
    });

    textFieldDNI.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_textFieldDNI = new GridBagConstraints();
    gbc_textFieldDNI.insets = new Insets(0, 0, 5, 5);
    gbc_textFieldDNI.fill = GridBagConstraints.HORIZONTAL;
    gbc_textFieldDNI.gridx = 1;
    gbc_textFieldDNI.gridy = 5;
    panel1.add(textFieldDNI, gbc_textFieldDNI);
    textFieldDNI.setColumns(10);

    JButton btnBorrarDNI = new JButton("");
    btnBorrarDNI.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        textFieldDNI.setText("");
      }
    });
    btnBorrarDNI.setFocusable(false);
    btnBorrarDNI.setIcon(new ImageIcon(getClass().getResource("/imagenes/eliminar.png")));
    btnBorrarDNI.setToolTipText("Borrar campo");
    GridBagConstraints gbc_btnBorrarDNI = new GridBagConstraints();
    gbc_btnBorrarDNI.insets = new Insets(0, 0, 5, 0);
    gbc_btnBorrarDNI.gridx = 2;
    gbc_btnBorrarDNI.gridy = 5;
    panel1.add(btnBorrarDNI, gbc_btnBorrarDNI);

    JLabel lblTelefono = new JLabel("Telefono");
    lblTelefono.setFocusable(false);
    lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
    gbc_lblTelefono.anchor = GridBagConstraints.WEST;
    gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
    gbc_lblTelefono.gridx = 0;
    gbc_lblTelefono.gridy = 7;
    panel1.add(lblTelefono, gbc_lblTelefono);

    textFieldTelefono = new JTextField();


    // con la clases JTextFieldLimit LIMITAMOS los text field en este caso a 12 solo cambiar el
    // numero para cambiar el limite
    // no toque la clase JTextFieldLimit solo la implemente
    textFieldTelefono.setDocument(new JTextFieldLimit(12));



    // Control de teclas, solo permite numeros y el de borrar y enter
    textFieldTelefono.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent ke) {
        String value = textFieldTelefono.getText();
        char c = ke.getKeyChar();

        if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || (c == KeyEvent.VK_BACK_SPACE)
            || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER)) {
          textFieldTelefono.setEditable(true);

        } else {
          textFieldTelefono.setEditable(false);

        }
      }
    });
    textFieldTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_textFieldTelefono = new GridBagConstraints();
    gbc_textFieldTelefono.insets = new Insets(0, 0, 5, 5);
    gbc_textFieldTelefono.fill = GridBagConstraints.HORIZONTAL;
    gbc_textFieldTelefono.gridx = 1;
    gbc_textFieldTelefono.gridy = 7;
    panel1.add(textFieldTelefono, gbc_textFieldTelefono);
    textFieldTelefono.setColumns(10);

    JButton btnBorrarTelefono = new JButton("");
    btnBorrarTelefono.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        textFieldTelefono.setText("");
      }
    });
    btnBorrarTelefono.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {}
    });
    btnBorrarTelefono.setFocusable(false);
    btnBorrarTelefono.setIcon(new ImageIcon(getClass().getResource("/imagenes/eliminar.png")));
    btnBorrarTelefono.setToolTipText("Borrar campo");
    GridBagConstraints gbc_btnBorrarTelefono = new GridBagConstraints();
    gbc_btnBorrarTelefono.insets = new Insets(0, 0, 5, 0);
    gbc_btnBorrarTelefono.gridx = 2;
    gbc_btnBorrarTelefono.gridy = 7;
    panel1.add(btnBorrarTelefono, gbc_btnBorrarTelefono);

    JLabel lblNumeroSocio = new JLabel("Numero Socio");
    lblNumeroSocio.setFocusable(false);
    lblNumeroSocio.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_lblNumeroSocio = new GridBagConstraints();
    gbc_lblNumeroSocio.anchor = GridBagConstraints.WEST;
    gbc_lblNumeroSocio.insets = new Insets(0, 0, 5, 5);
    gbc_lblNumeroSocio.gridx = 0;
    gbc_lblNumeroSocio.gridy = 8;
    panel1.add(lblNumeroSocio, gbc_lblNumeroSocio);

    textFieldNumeroSocio = new JTextField();
    textFieldNumeroSocio.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_textFieldNumeroSocio = new GridBagConstraints();
    gbc_textFieldNumeroSocio.insets = new Insets(0, 0, 5, 5);
    gbc_textFieldNumeroSocio.fill = GridBagConstraints.HORIZONTAL;
    gbc_textFieldNumeroSocio.gridx = 1;
    gbc_textFieldNumeroSocio.gridy = 8;
    panel1.add(textFieldNumeroSocio, gbc_textFieldNumeroSocio);
    textFieldNumeroSocio.setColumns(10);



    textFieldNumeroSocio.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent ke) {
        String value = textFieldNumeroSocio.getText();
        char c = ke.getKeyChar();

        if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || (c == KeyEvent.VK_BACK_SPACE)
            || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER)) {
          textFieldNumeroSocio.setEditable(true);

        } else {
          textFieldNumeroSocio.setEditable(false);
        }



      };
    });

    JButton btnBorrarNumeroSocio = new JButton("");
    btnBorrarNumeroSocio.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        textFieldNumeroSocio.setText("");
      }
    });
    btnBorrarNumeroSocio.setFocusable(false);
    btnBorrarNumeroSocio.setIcon(new ImageIcon(getClass().getResource("/imagenes/eliminar.png")));
    btnBorrarNumeroSocio.setToolTipText("Borrar campo");
    GridBagConstraints gbc_btnBorrarNumeroSocio = new GridBagConstraints();
    gbc_btnBorrarNumeroSocio.insets = new Insets(0, 0, 5, 0);
    gbc_btnBorrarNumeroSocio.gridx = 2;
    gbc_btnBorrarNumeroSocio.gridy = 8;
    panel1.add(btnBorrarNumeroSocio, gbc_btnBorrarNumeroSocio);

    JLabel lblEmail = new JLabel("Email");
    lblEmail.setFocusable(false);
    lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_lblEmail = new GridBagConstraints();
    gbc_lblEmail.anchor = GridBagConstraints.WEST;
    gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
    gbc_lblEmail.gridx = 0;
    gbc_lblEmail.gridy = 9;
    panel1.add(lblEmail, gbc_lblEmail);

    textFieldEmail = new JTextField();
    textFieldEmail.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent e) {
        if (((textFieldEmail.getText() != null) && (textFieldEmail.getText()
            .matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")))
            || textFieldEmail.getText().equals("")) {
          txtEmailIncorrecto.setVisible(false);
          panel1.revalidate();
          panel1.repaint();


        } else {
          textFieldEmail.requestFocusInWindow();
          txtEmailIncorrecto.setVisible(true);
        } ;
        panel1.revalidate();
        panel1.repaint();

      }

    });
    textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
    gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
    gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
    gbc_textFieldEmail.gridx = 1;
    gbc_textFieldEmail.gridy = 9;
    panel1.add(textFieldEmail, gbc_textFieldEmail);
    textFieldEmail.setColumns(10);

    JButton btnBorrarEmail = new JButton("");
    btnBorrarEmail.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        textFieldEmail.setText("");
      }
    });
    btnBorrarEmail.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {}
    });
    btnBorrarEmail.setFocusable(false);
    btnBorrarEmail.setIcon(new ImageIcon(getClass().getResource("/imagenes/eliminar.png")));
    btnBorrarEmail.setToolTipText("Borrar campo");
    GridBagConstraints gbc_btnBorrarEmail = new GridBagConstraints();
    gbc_btnBorrarEmail.insets = new Insets(0, 0, 5, 0);
    gbc_btnBorrarEmail.gridx = 2;
    gbc_btnBorrarEmail.gridy = 9;
    panel1.add(btnBorrarEmail, gbc_btnBorrarEmail);

    txtEmailIncorrecto = new JTextField();
    txtEmailIncorrecto.setVisible(false);
    txtEmailIncorrecto.setBackground(Color.RED);
    txtEmailIncorrecto.setText("El formato deberia ser algo como: jsmith@example.com");
    GridBagConstraints gbc_txtEmailIncorrecto = new GridBagConstraints();
    gbc_txtEmailIncorrecto.insets = new Insets(0, 0, 5, 5);
    gbc_txtEmailIncorrecto.fill = GridBagConstraints.HORIZONTAL;
    gbc_txtEmailIncorrecto.gridx = 1;
    gbc_txtEmailIncorrecto.gridy = 10;
    panel1.add(txtEmailIncorrecto, gbc_txtEmailIncorrecto);
    txtEmailIncorrecto.setColumns(10);

    JSeparator separator = new JSeparator();
    GridBagConstraints gbc_separator = new GridBagConstraints();
    gbc_separator.ipady = 2;
    gbc_separator.gridwidth = 3;
    gbc_separator.fill = GridBagConstraints.HORIZONTAL;
    gbc_separator.insets = new Insets(0, 0, 5, 0);
    gbc_separator.gridx = 0;
    gbc_separator.gridy = 11;
    panel1.add(separator, gbc_separator);

    JLabel lblDeporte = new JLabel("Deporte");
    lblDeporte.setFocusable(false);
    lblDeporte.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_lblDeporte = new GridBagConstraints();
    gbc_lblDeporte.anchor = GridBagConstraints.WEST;
    gbc_lblDeporte.insets = new Insets(0, 0, 5, 5);
    gbc_lblDeporte.gridx = 0;
    gbc_lblDeporte.gridy = 12;
    panel1.add(lblDeporte, gbc_lblDeporte);

    panelDeportes = new JPanel();
    GridBagConstraints gbc_panelDeportes = new GridBagConstraints();
    gbc_panelDeportes.insets = new Insets(0, 0, 5, 5);
    gbc_panelDeportes.fill = GridBagConstraints.HORIZONTAL;
    gbc_panelDeportes.gridx = 1;
    gbc_panelDeportes.gridy = 12;
    panel1.add(panelDeportes, gbc_panelDeportes);

    this.AgregarOpcionesDeporte(panelDeportes);

    JSeparator separator_1 = new JSeparator();
    GridBagConstraints gbc_separator_1 = new GridBagConstraints();
    gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
    gbc_separator_1.ipady = 2;
    gbc_separator_1.gridwidth = 3;
    gbc_separator_1.insets = new Insets(0, 0, 5, 0);
    gbc_separator_1.gridx = 0;
    gbc_separator_1.gridy = 13;
    panel1.add(separator_1, gbc_separator_1);

    JLabel lblTipoSocio = new JLabel("Tipo Socio");
    lblTipoSocio.setFocusable(false);
    lblTipoSocio.setFont(new Font("Tahoma", Font.PLAIN, 18));
    GridBagConstraints gbc_lblTipoSocio = new GridBagConstraints();
    gbc_lblTipoSocio.fill = GridBagConstraints.VERTICAL;
    gbc_lblTipoSocio.anchor = GridBagConstraints.WEST;
    gbc_lblTipoSocio.insets = new Insets(0, 0, 0, 5);
    gbc_lblTipoSocio.gridx = 0;
    gbc_lblTipoSocio.gridy = 14;
    panel1.add(lblTipoSocio, gbc_lblTipoSocio);

    panelTipoSocio = new JPanel();
    GridBagConstraints gbc_panelTipoSocio = new GridBagConstraints();
    gbc_panelTipoSocio.insets = new Insets(0, 0, 0, 5);
    gbc_panelTipoSocio.fill = GridBagConstraints.HORIZONTAL;
    gbc_panelTipoSocio.gridx = 1;
    gbc_panelTipoSocio.gridy = 14;
    panel1.add(panelTipoSocio, gbc_panelTipoSocio);

    // OPCIONES DE SOCIO
    JRadioButton rdbtnDeportista = new JRadioButton("Deportista");
    rdbtnDeportista.setFont(new Font("Tahoma", Font.PLAIN, 22));
    rdbtnDeportista.setSelected(true);
    rdbtnDeportista.setActionCommand("0");

    panelTipoSocio.add(rdbtnDeportista);

    JRadioButton rdbtnProtector = new JRadioButton("Protector");
    rdbtnProtector.setFont(new Font("Tahoma", Font.PLAIN, 22));
    rdbtnProtector.setActionCommand("1");
    panelTipoSocio.add(rdbtnProtector);

    JRadioButton rdbtnVitalicio = new JRadioButton("Vitalicio");
    rdbtnVitalicio.setFont(new Font("Tahoma", Font.PLAIN, 22));
    rdbtnVitalicio.setActionCommand("2");
    panelTipoSocio.add(rdbtnVitalicio);


    grupoTipoSocio = new ButtonGroup();
    // agrego los radiobutton
    grupoTipoSocio.add(rdbtnDeportista);
    grupoTipoSocio.add(rdbtnProtector);
    grupoTipoSocio.add(rdbtnVitalicio);

  }


  private void AgregarOpcionesDeporte(JPanel panel) {

    List<Deporte> deportes = Controlador.ObtenerListaDeportes();

    for (Deporte deporte : deportes) {

      JCheckBox check = new JCheckBox(deporte.getDeporte());
      check.setFont(new Font("Tahoma", Font.PLAIN, 22));
      check.setActionCommand(String.valueOf(deporte.getId()));
      panel.add(check);
      this.arrayCheckBoxDeportes.add(check);

    }
  }


  /**
   * Mapeamos lo que tiene la ventana a lo que tiene la clase Socio
   * 
   * @return Socio->numerosocio es -1 si no hay asignado nada
   */
  public Socio mapearSocio() {
    // mapeamos los datos que seguro van
    Socio s = Socio.builder().nombre(this.textFieldNombre.getText())
        .apellido(this.textFieldApellido.getText()).email(this.textFieldEmail.getText())
        .dni(this.textFieldDNI.getText()).telefono(this.textFieldTelefono.getText())
        .Direccion(this.textFieldDireccion.getText()).tipo_socio(this.queTipoSocioEs()).build();

    if (this.textFieldNumeroSocio.getText().equals("")) {
      s.setNumerosocio(-1);
    } else {
      s.setNumerosocio(Integer.parseInt(this.textFieldNumeroSocio.getText()));
    }

    return s;
  }

  /**
   * Toma los datos de la del Panel y mapea los id de deportes seleccionados
   * 
   * @return retorna lista de enteros que representa los id de deportes seleccionados
   */
  public int[] mapearDeportes() {
    int[] array = new int[this.arrayCheckBoxDeportes.size()];

    int i = 0;
    for (JCheckBox valor : this.arrayCheckBoxDeportes) {
      if (valor.isSelected()) {
        array[i] = Integer.parseInt(valor.getActionCommand());
        i++;
      }
    }
    return Arrays.copyOf(array, i);
  }


  /**
   * Esto esta hecho casero, si se llega a agregar una nueva categoria en la base de datos se rompe
   * todo esto o si se llega a cambiar los valores, no esta hecho de forma generica porque no lo
   * amerita
   * 
   * @return entero que representa en la db que tipo de socio es (-1,0,1,2)->
   *         (cualquiera,depor,protec,vita)
   */
  public int queTipoSocioEs() {
    int rta = -1;
    if (grupoTipoSocio.getSelection() != null) {
      rta = Integer.parseInt(grupoTipoSocio.getSelection().getActionCommand());
    }
    return rta;
  };


  /**
   * Limpia toda la ventana, es llamado cuando se da a cancelar, de momento.
   */
  protected void resetear() {

    // limpiamos texto
    textFieldNombre.setText("");
    textFieldApellido.setText("");
    textFieldDireccion.setText("");
    textFieldDNI.setText("");
    textFieldTelefono.setText("");
    textFieldNumeroSocio.setText("");
    textFieldEmail.setText("");

    // quitamos seleccion de deportes
    for (JCheckBox check : arrayCheckBoxDeportes) {
      check.setSelected(false);
    }

    // Ponemos el tipo de socio a deporte
    grupoTipoSocio.clearSelection();
  }

}
