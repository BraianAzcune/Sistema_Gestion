package vista.añadirVista;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.sun.glass.events.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AñadirSocio extends JPanel implements ActionListener{
	
	//Mensajes que se crean en los Dialog
	private static final String msgAyuda="<ul><li>Utiliza Tab para navegar entre los campos</li><li>Shift+Tab para retroceder</li><li>Espacio para clic</li></ul>";
	

	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDireccion;
	private JTextField textFieldDNI;
	private JTextField textFieldTelefono;
	private JTextField textFieldNumeroSocio;
	private JTextField textFieldEmail;

	/**
	 * Create the panel.
	 */
	public AñadirSocio() {
		setFocusable(false);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BorderLayout(0, 0));
		
		
		JLabel lblTitulo = new JLabel("A\u00F1adir Socio");
		lblTitulo.setFocusable(false);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setIcon(new ImageIcon(getClass().getResource("/identificacion2.png")));
		this.add(lblTitulo, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setFocusable(false);
		this.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JButton btnAyuda = new JButton("Ayuda");
		btnAyuda.setActionCommand("Ayuda");
		btnAyuda.addActionListener(this);
		btnAyuda.setFocusable(false);
		btnAyuda.setIcon(new ImageIcon(AñadirSocio.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));
		btnAyuda.setFont(new Font("Tahoma", Font.PLAIN, 20));
		//panel.add(btnAyuda);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConfirmar.setIcon(new ImageIcon(getClass().getResource("/comprobar.png")));
		//panel.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancelar.setIcon(new ImageIcon(getClass().getResource("/cancelar.png")));
		//panel.add(btnCancelar);
		
		//Creamos el layout de abajo
		//la caja crea las restricciones añadimos los componentes que estaran dentro y luego la caja se añade al panel.
		Box box = Box.createHorizontalBox();
		box.add(btnAyuda);
		box.add(Box.createHorizontalGlue());
		box.add(btnConfirmar);
		box.add(Box.createHorizontalStrut(5));
		box.add(btnCancelar);
		
		panel.add(box);
		
		JPanel panel_1 = new JPanel();
		panel_1.setFocusable(false);
		this.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{82, 101, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFocusable(false);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		panel_1.add(lblNombre, gbc_lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 0;
		panel_1.add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JButton btnBorrarNombre = new JButton("");
		btnBorrarNombre.setFocusable(false);
		btnBorrarNombre.setToolTipText("Borrar campo");
		btnBorrarNombre.setIcon(new ImageIcon(getClass().getResource("/eliminar.png")));
		GridBagConstraints gbc_btnBorrarNombre = new GridBagConstraints();
		gbc_btnBorrarNombre.insets = new Insets(0, 0, 5, 0);
		gbc_btnBorrarNombre.gridx = 2;
		gbc_btnBorrarNombre.gridy = 0;
		panel_1.add(btnBorrarNombre, gbc_btnBorrarNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFocusable(false);
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.WEST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 0;
		gbc_lblApellido.gridy = 1;
		panel_1.add(lblApellido, gbc_lblApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellido.gridx = 1;
		gbc_textFieldApellido.gridy = 1;
		panel_1.add(textFieldApellido, gbc_textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JButton btnBorrarApellido = new JButton("");
		btnBorrarApellido.setFocusable(false);
		btnBorrarApellido.setIcon(new ImageIcon(getClass().getResource("/eliminar.png")));
		btnBorrarApellido.setToolTipText("Borrar campo");
		GridBagConstraints gbc_btnBorrarApellido = new GridBagConstraints();
		gbc_btnBorrarApellido.insets = new Insets(0, 0, 5, 0);
		gbc_btnBorrarApellido.gridx = 2;
		gbc_btnBorrarApellido.gridy = 1;
		panel_1.add(btnBorrarApellido, gbc_btnBorrarApellido);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setFocusable(false);
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
		gbc_lblDireccion.anchor = GridBagConstraints.WEST;
		gbc_lblDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDireccion.gridx = 0;
		gbc_lblDireccion.gridy = 2;
		panel_1.add(lblDireccion, gbc_lblDireccion);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_textFieldDireccion = new GridBagConstraints();
		gbc_textFieldDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDireccion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDireccion.gridx = 1;
		gbc_textFieldDireccion.gridy = 2;
		panel_1.add(textFieldDireccion, gbc_textFieldDireccion);
		textFieldDireccion.setColumns(10);
		
		JButton btnBorrarDireccion = new JButton("");
		btnBorrarDireccion.setFocusable(false);
		btnBorrarDireccion.setIcon(new ImageIcon(getClass().getResource("/eliminar.png")));
		btnBorrarDireccion.setToolTipText("Borrar campo");
		GridBagConstraints gbc_btnBorrarDireccion = new GridBagConstraints();
		gbc_btnBorrarDireccion.insets = new Insets(0, 0, 5, 0);
		gbc_btnBorrarDireccion.gridx = 2;
		gbc_btnBorrarDireccion.gridy = 2;
		panel_1.add(btnBorrarDireccion, gbc_btnBorrarDireccion);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setFocusable(false);
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.anchor = GridBagConstraints.WEST;
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.gridx = 0;
		gbc_lblDni.gridy = 3;
		panel_1.add(lblDni, gbc_lblDni);
		
		textFieldDNI = new JTextField();
		textFieldDNI.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_textFieldDNI = new GridBagConstraints();
		gbc_textFieldDNI.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDNI.gridx = 1;
		gbc_textFieldDNI.gridy = 3;
		panel_1.add(textFieldDNI, gbc_textFieldDNI);
		textFieldDNI.setColumns(10);
		
		JButton btnBorrarDNI = new JButton("");
		btnBorrarDNI.setFocusable(false);
		btnBorrarDNI.setIcon(new ImageIcon(getClass().getResource("/eliminar.png")));
		btnBorrarDNI.setToolTipText("Borrar campo");
		GridBagConstraints gbc_btnBorrarDNI = new GridBagConstraints();
		gbc_btnBorrarDNI.insets = new Insets(0, 0, 5, 0);
		gbc_btnBorrarDNI.gridx = 2;
		gbc_btnBorrarDNI.gridy = 3;
		panel_1.add(btnBorrarDNI, gbc_btnBorrarDNI);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFocusable(false);
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.anchor = GridBagConstraints.WEST;
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefono.gridx = 0;
		gbc_lblTelefono.gridy = 4;
		panel_1.add(lblTelefono, gbc_lblTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_textFieldTelefono = new GridBagConstraints();
		gbc_textFieldTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTelefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTelefono.gridx = 1;
		gbc_textFieldTelefono.gridy = 4;
		panel_1.add(textFieldTelefono, gbc_textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		JButton btnBorrarTelefono = new JButton("");
		btnBorrarTelefono.setFocusable(false);
		btnBorrarTelefono.setIcon(new ImageIcon(getClass().getResource("/eliminar.png")));
		btnBorrarTelefono.setToolTipText("Borrar campo");
		GridBagConstraints gbc_btnBorrarTelefono = new GridBagConstraints();
		gbc_btnBorrarTelefono.insets = new Insets(0, 0, 5, 0);
		gbc_btnBorrarTelefono.gridx = 2;
		gbc_btnBorrarTelefono.gridy = 4;
		panel_1.add(btnBorrarTelefono, gbc_btnBorrarTelefono);
		
		JLabel lblNumeroSocio = new JLabel("Numero Socio");
		lblNumeroSocio.setFocusable(false);
		lblNumeroSocio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNumeroSocio = new GridBagConstraints();
		gbc_lblNumeroSocio.anchor = GridBagConstraints.WEST;
		gbc_lblNumeroSocio.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumeroSocio.gridx = 0;
		gbc_lblNumeroSocio.gridy = 5;
		panel_1.add(lblNumeroSocio, gbc_lblNumeroSocio);
		
		textFieldNumeroSocio = new JTextField();
		textFieldNumeroSocio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_textFieldNumeroSocio = new GridBagConstraints();
		gbc_textFieldNumeroSocio.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNumeroSocio.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNumeroSocio.gridx = 1;
		gbc_textFieldNumeroSocio.gridy = 5;
		panel_1.add(textFieldNumeroSocio, gbc_textFieldNumeroSocio);
		textFieldNumeroSocio.setColumns(10);
		
		JButton btnBorrarNumeroSocio = new JButton("");
		btnBorrarNumeroSocio.setFocusable(false);
		btnBorrarNumeroSocio.setIcon(new ImageIcon(getClass().getResource("/eliminar.png")));
		btnBorrarNumeroSocio.setToolTipText("Borrar campo");
		GridBagConstraints gbc_btnBorrarNumeroSocio = new GridBagConstraints();
		gbc_btnBorrarNumeroSocio.insets = new Insets(0, 0, 5, 0);
		gbc_btnBorrarNumeroSocio.gridx = 2;
		gbc_btnBorrarNumeroSocio.gridy = 5;
		panel_1.add(btnBorrarNumeroSocio, gbc_btnBorrarNumeroSocio);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFocusable(false);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 6;
		panel_1.add(lblEmail, gbc_lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 6;
		panel_1.add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JButton btnBorrarEmail = new JButton("");
		btnBorrarEmail.setFocusable(false);
		btnBorrarEmail.setIcon(new ImageIcon(getClass().getResource("/eliminar.png")));
		btnBorrarEmail.setToolTipText("Borrar campo");
		GridBagConstraints gbc_btnBorrarEmail = new GridBagConstraints();
		gbc_btnBorrarEmail.insets = new Insets(0, 0, 5, 0);
		gbc_btnBorrarEmail.gridx = 2;
		gbc_btnBorrarEmail.gridy = 6;
		panel_1.add(btnBorrarEmail, gbc_btnBorrarEmail);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.ipady = 2;
		gbc_separator.gridwidth = 3;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 7;
		panel_1.add(separator, gbc_separator);
		
		JLabel lblDeporte = new JLabel("Deporte");
		lblDeporte.setFocusable(false);
		lblDeporte.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblDeporte = new GridBagConstraints();
		gbc_lblDeporte.anchor = GridBagConstraints.WEST;
		gbc_lblDeporte.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeporte.gridx = 0;
		gbc_lblDeporte.gridy = 8;
		panel_1.add(lblDeporte, gbc_lblDeporte);
		
		JPanel panelDeportes = new JPanel();
		GridBagConstraints gbc_panelDeportes = new GridBagConstraints();
		gbc_panelDeportes.insets = new Insets(0, 0, 5, 5);
		gbc_panelDeportes.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelDeportes.gridx = 1;
		gbc_panelDeportes.gridy = 8;
		panel_1.add(panelDeportes, gbc_panelDeportes);
		
		JCheckBox chckbxBsquet = new JCheckBox("B\u00E1squet");
		chckbxBsquet.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelDeportes.add(chckbxBsquet);
		
		JCheckBox chckbxFtbol = new JCheckBox("F\u00FAtbol");
		chckbxFtbol.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelDeportes.add(chckbxFtbol);
		
		JCheckBox chckbxVoley = new JCheckBox("Voley");
		chckbxVoley.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelDeportes.add(chckbxVoley);
		
		JCheckBox chckbxCestoball = new JCheckBox("Cestoball");
		chckbxCestoball.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelDeportes.add(chckbxCestoball);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.ipady = 2;
		gbc_separator_1.gridwidth = 3;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 9;
		panel_1.add(separator_1, gbc_separator_1);
		
		JLabel lblTipoSocio = new JLabel("Tipo Socio");
		lblTipoSocio.setFocusable(false);
		lblTipoSocio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblTipoSocio = new GridBagConstraints();
		gbc_lblTipoSocio.fill = GridBagConstraints.VERTICAL;
		gbc_lblTipoSocio.anchor = GridBagConstraints.WEST;
		gbc_lblTipoSocio.insets = new Insets(0, 0, 0, 5);
		gbc_lblTipoSocio.gridx = 0;
		gbc_lblTipoSocio.gridy = 10;
		panel_1.add(lblTipoSocio, gbc_lblTipoSocio);
		
		JPanel panelTipoSocio = new JPanel();
		GridBagConstraints gbc_panelTipoSocio = new GridBagConstraints();
		gbc_panelTipoSocio.insets = new Insets(0, 0, 0, 5);
		gbc_panelTipoSocio.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelTipoSocio.gridx = 1;
		gbc_panelTipoSocio.gridy = 10;
		panel_1.add(panelTipoSocio, gbc_panelTipoSocio);
		
		
		JRadioButton rdbtnDeportista = new JRadioButton("Deportista");
		rdbtnDeportista.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelTipoSocio.add(rdbtnDeportista);
		
		JRadioButton rdbtnProtector = new JRadioButton("Protector");
		rdbtnProtector.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelTipoSocio.add(rdbtnProtector);
		
		JRadioButton rdbtnVitalicio = new JRadioButton("Vitalicio");
		rdbtnVitalicio.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelTipoSocio.add(rdbtnVitalicio);
		
		
		//Creo el GroupButton para que las opcines sean excluyentes
		ButtonGroup grupoTipoSocio = new ButtonGroup();
		//agrego los radiobutton
		grupoTipoSocio.add(rdbtnDeportista);
		grupoTipoSocio.add(rdbtnProtector);
		grupoTipoSocio.add(rdbtnVitalicio);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Ayuda")){
			//Creamos el JDialog
			new InformacionDialog(this,msgAyuda);
		}
		
	}

}
