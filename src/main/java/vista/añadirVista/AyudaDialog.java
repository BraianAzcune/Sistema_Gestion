package vista.aņadirVista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;

public class AyudaDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();



	/**
	 * Create the dialog.
	 */
	public AyudaDialog(JPanel Padre) {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 463, 198);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			//UTILIZA HTML
			JTextPane textPane = new JTextPane();
			textPane.setBorder(null);
			textPane.setOpaque(false);
			textPane.setContentType("text/html");
			textPane.setFocusable(false);
			textPane.setText("<html bgcolor=\"#f0f0f0\"><font face=\"arial\" size='5' color=rgb(1,1,1)>"
					+ "<ul>\r\n" + 
					"  <li>Utiliza Tab para navegar entre los campos</li>\r\n" + 
					"  <li>Shift+Tab para retroceder</li>\r\n" + 
					"  <li>Espacio para clic</li>\r\n" + 
					"</ul></font></html>");
			textPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textPane.setBackground(SystemColor.control);
			textPane.setEditable(false);
			
			
			contentPanel.add(textPane, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(this);
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		
		this.setVisible(true);
		this.pack();
		setLocationRelativeTo(Padre);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("OK")) {
			this.dispose();
		}
		
	}
}
