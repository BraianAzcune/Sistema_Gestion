package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Cultural Argentino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 786, 686);
		layergruop = new JPanel();
		layergruop.setForeground(Color.GREEN);
		layergruop.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(layergruop);
		
		JLabel lblNewLabel = new JLabel("Nuevo Socio");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\santy\\Documents\\Sistema_Gestion\\src\\resources\\identificacion (2).png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSeparator separator = new JSeparator();
		
		JLabel NombreLabel = new JLabel("Nombre");
		NombreLabel.setForeground(Color.BLACK);
		NombreLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		
		Nombre = new JTextField();
		Nombre.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		Nombre.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
			      Apellido.requestFocusInWindow();    
			    }
			}); 
		Nombre.setColumns(10);
		
		JLabel ApellidoLabel = new JLabel("Apellido");
		ApellidoLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		
		Apellido = new JTextField();
		Apellido.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		Apellido.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
			      Direccion.requestFocusInWindow();    
			    }
			}); 
		Apellido.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\santy\\Documents\\Sistema_Gestion\\src\\resources\\comprobar.png"));
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\santy\\Documents\\Sistema_Gestion\\src\\resources\\cancelar.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton BorrarTodo = new JButton("Borrar Campos");
		BorrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BorrarTodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					Nombre.setText("");
					Apellido.setText("");
					Direccion.setText("");
					DNI.setText("");
					Telefono.setText("");
					NumerodeSocio.setText("");
					Email.setText("");
					FechadeAlta.setText("");
				
			}
		});
		
		JLabel DirecionLabel = new JLabel("Direccion");
		DirecionLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		
		Direccion = new JTextField();
		Direccion.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		Direccion.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
			      DNI.requestFocusInWindow();    
			    }
			}); 
		Direccion.setColumns(10);
		
		JButton BorrarNombre = new JButton("");
		BorrarNombre.setPreferredSize(new Dimension(18, 18));
		BorrarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BorrarNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Nombre.setText("");
			}
		});
		BorrarNombre.setIcon(new ImageIcon("C:\\Users\\santy\\Documents\\Sistema_Gestion\\src\\resources\\eliminar.png"));
		
		JButton BorrarApellido = new JButton("");
		BorrarApellido.setIcon(new ImageIcon("C:\\Users\\santy\\Documents\\Sistema_Gestion\\src\\resources\\eliminar.png"));
		BorrarApellido.setPreferredSize(new Dimension(18, 18));
		BorrarApellido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BorrarApellido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Apellido.setText("");
			}
		});
		
		JButton BorrarDireccion = new JButton("");
		BorrarDireccion.setIcon(new ImageIcon("C:\\Users\\santy\\Documents\\Sistema_Gestion\\src\\resources\\eliminar.png"));
		BorrarDireccion.setPreferredSize(new Dimension(18, 18));
		BorrarDireccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BorrarDireccion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Direccion.setText("");
			}
		});
		
		JLabel DNILabel = new JLabel("DNI");
		DNILabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		
		DNI = new JTextField();
		DNI.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		DNI.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
			      Telefono.requestFocusInWindow();    
			    }
			   });
	
		DNI.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            String value = DNI.getText();
	            char c = ke.getKeyChar();
	           
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || (c == KeyEvent.VK_BACK_SPACE) ||
	            	     (c == KeyEvent.VK_DELETE)||(c == KeyEvent.VK_ENTER)) {
	               DNI.setEditable(true);
	             
	            } else {
	               DNI.setEditable(false);
	            
	              	            }
	         }
	      });
		DNI.setColumns(10);
		
		JButton BorrarDNI = new JButton("");
		BorrarDNI.setIcon(new ImageIcon("C:\\Users\\santy\\Documents\\Sistema_Gestion\\src\\resources\\eliminar.png"));
		BorrarDNI.setPreferredSize(new Dimension(18, 18));
		BorrarDNI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DNI.setText("");
			}
		});
		
		JLabel TelefonoLabel = new JLabel("Telefono");
		TelefonoLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		
		Telefono = new JTextField();
		
		Telefono.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		
		Telefono.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   NumerodeSocio.requestFocusInWindow();    
			    }
			   });
			
	
		Telefono.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            String value = DNI.getText();
	            char c = ke.getKeyChar();
	           
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || (c == KeyEvent.VK_BACK_SPACE) ||
	            	     (c == KeyEvent.VK_DELETE)||(c == KeyEvent.VK_ENTER)) {
	               Telefono.setEditable(true);
	             
	            } else {
	               Telefono.setEditable(false);
	              	            }
	         }
	      });
		Telefono.setColumns(10);
		
		JButton BorrarTelefono = new JButton("");
		BorrarTelefono.setIcon(new ImageIcon("C:\\Users\\santy\\Documents\\Sistema_Gestion\\src\\resources\\eliminar.png"));
		BorrarTelefono.setPreferredSize(new Dimension(18,18));
		BorrarTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BorrarTelefono.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Telefono.setText("");
			}
		});
		
		JLabel NumeroSocioLabel = new JLabel("Numero de Socio");
		NumeroSocioLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		
		NumerodeSocio = new JTextField();
		NumerodeSocio.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   Email.requestFocusInWindow();    
			    }
			   });
		
		NumerodeSocio.addKeyListener(new KeyAdapter() {
	         public void keyPressed(KeyEvent ke) {
	            String value = DNI.getText();
	            char c = ke.getKeyChar();
	           
	            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || (c == KeyEvent.VK_BACK_SPACE) ||
	            	     (c == KeyEvent.VK_DELETE)||(c == KeyEvent.VK_ENTER)) {
	            	NumerodeSocio.setEditable(true);
	             
	            } else {
	            	NumerodeSocio.setEditable(false);
	              	            }
	         }
	      });
		
		
		
		NumerodeSocio.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		NumerodeSocio.setColumns(10);
		
		JButton BorrarNumerodeSocio = new JButton("");
		BorrarNumerodeSocio.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		BorrarNumerodeSocio.setIcon(new ImageIcon("C:\\Users\\santy\\Documents\\Sistema_Gestion\\src\\resources\\eliminar.png"));
		BorrarNumerodeSocio.setPreferredSize(new Dimension(18,18));
		BorrarNumerodeSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BorrarNumerodeSocio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NumerodeSocio.setText("");
			}
		});
		
		JLabel EmailLabel = new JLabel("Email");
		EmailLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		
		//Para el control del patron del mail
		final String EMAIL_PATTERN = 
			    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		Email = new JTextField();
		Email.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   FechadeAlta.requestFocusInWindow();    
			    }
			   });
		
		
		Email.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		Email.setColumns(10);
		
		JButton BorrarEmail = new JButton("");
		BorrarEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			Email.setText("");
			}
		});
		BorrarEmail.setIcon(new ImageIcon("C:\\Users\\santy\\Documents\\Sistema_Gestion\\src\\resources\\eliminar.png"));
		BorrarEmail.setPreferredSize(new Dimension(18,18));
		
		BorrarEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BorrarEmail.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		
		JLabel lblTipoDeSocio = new JLabel("Tipo de Socio");
		lblTipoDeSocio.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		
		JPanel panel = new JPanel();
		
		JLabel FechaDeAltaLabel = new JLabel("Fecha de Alta");
		FechaDeAltaLabel.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		
		FechadeAlta = new JTextField();
		FechadeAlta.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		FechadeAlta.setColumns(10);
		
		JButton BorrarFechadeAlta = new JButton("");
		BorrarFechadeAlta.setIcon(new ImageIcon("C:\\Users\\santy\\Documents\\Sistema_Gestion\\src\\resources\\eliminar.png"));
		BorrarFechadeAlta.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		BorrarFechadeAlta.setPreferredSize(new Dimension(18,18));
		BorrarFechadeAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BorrarFechadeAlta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FechadeAlta.setText("");
			}
		});
		
		JLabel lblDeporte = new JLabel("Deporte");
		lblDeporte.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 11));
		lblDeporte.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_layergruop = new GroupLayout(layergruop);
		gl_layergruop.setHorizontalGroup(
			gl_layergruop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layergruop.createSequentialGroup()
					.addGap(168)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 660, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_layergruop.createSequentialGroup()
					.addGap(10)
					.addComponent(NombreLabel)
					.addGap(71)
					.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(BorrarNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_layergruop.createSequentialGroup()
					.addGap(10)
					.addComponent(ApellidoLabel)
					.addGap(71)
					.addComponent(Apellido, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(BorrarApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_layergruop.createSequentialGroup()
					.addGap(10)
					.addComponent(DirecionLabel)
					.addGap(64)
					.addComponent(Direccion, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(BorrarDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_layergruop.createSequentialGroup()
					.addGap(10)
					.addComponent(DNILabel)
					.addGap(96)
					.addComponent(DNI, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(BorrarDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_layergruop.createSequentialGroup()
					.addGap(10)
					.addComponent(TelefonoLabel)
					.addGap(68)
					.addComponent(Telefono, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(BorrarTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_layergruop.createSequentialGroup()
					.addGap(301)
					.addComponent(BorrarTodo)
					.addGap(18)
					.addComponent(btnNewButton_1)
					.addGap(18)
					.addComponent(btnNewButton))
				.addGroup(gl_layergruop.createSequentialGroup()
					.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layergruop.createSequentialGroup()
							.addGap(10)
							.addComponent(NumeroSocioLabel))
						.addGroup(gl_layergruop.createSequentialGroup()
							.addContainerGap()
							.addComponent(EmailLabel))
						.addGroup(gl_layergruop.createSequentialGroup()
							.addContainerGap()
							.addComponent(FechaDeAltaLabel))
						.addGroup(gl_layergruop.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTipoDeSocio))
						.addGroup(gl_layergruop.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDeporte)))
					.addGap(23)
					.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
						.addComponent(NumerodeSocio, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_layergruop.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(FechadeAlta, Alignment.LEADING)
							.addComponent(Email, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layergruop.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
								.addComponent(BorrarEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(BorrarNumerodeSocio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_layergruop.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(BorrarFechadeAlta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(1))
		);
		gl_layergruop.setVerticalGroup(
			gl_layergruop.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layergruop.createSequentialGroup()
					.addGap(11)
					.addComponent(lblNewLabel)
					.addGap(11)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layergruop.createSequentialGroup()
							.addGap(3)
							.addComponent(NombreLabel))
						.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_layergruop.createSequentialGroup()
							.addGap(6)
							.addComponent(BorrarNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layergruop.createSequentialGroup()
							.addGap(3)
							.addComponent(ApellidoLabel))
						.addComponent(Apellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(BorrarApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layergruop.createSequentialGroup()
							.addGap(3)
							.addComponent(DirecionLabel))
						.addComponent(Direccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(BorrarDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layergruop.createSequentialGroup()
							.addGap(3)
							.addComponent(DNILabel))
						.addComponent(DNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(BorrarDNI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layergruop.createSequentialGroup()
							.addGap(3)
							.addComponent(TelefonoLabel))
						.addComponent(Telefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(BorrarTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layergruop.createSequentialGroup()
							.addGap(3)
							.addComponent(NumeroSocioLabel))
						.addComponent(NumerodeSocio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(BorrarNumerodeSocio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
						.addComponent(Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(EmailLabel)
						.addComponent(BorrarEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layergruop.createSequentialGroup()
							.addGroup(gl_layergruop.createParallelGroup(Alignment.BASELINE)
								.addComponent(FechaDeAltaLabel)
								.addComponent(FechadeAlta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDeporte)))
						.addComponent(BorrarFechadeAlta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layergruop.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(54)
							.addGroup(gl_layergruop.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_layergruop.createSequentialGroup()
									.addGap(5)
									.addComponent(BorrarTodo))
								.addComponent(btnNewButton_1)
								.addComponent(btnNewButton)))
						.addComponent(lblTipoDeSocio)))
		);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Basquet");
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnFutbol = new JRadioButton("Futbol");
		panel_1.add(rdbtnFutbol);
		
		JRadioButton rdbtnVoley = new JRadioButton("Voley");
		panel_1.add(rdbtnVoley);
		
		JRadioButton rdbtnCestoball = new JRadioButton("Cestoball");
		panel_1.add(rdbtnCestoball);
		
		JRadioButton Deportista = new JRadioButton("Deportista");
		panel.add(Deportista);
		
		JRadioButton Protector = new JRadioButton("Protector");
		panel.add(Protector);
		
		JRadioButton Vitalicio = new JRadioButton("Vitalicio");
		panel.add(Vitalicio);
		layergruop.setLayout(gl_layergruop);
	}
}
