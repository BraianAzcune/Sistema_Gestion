package vista.añadirVista;

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

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
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

import controlador.ControladorAñadirSocio;
import modelo.Deporte;
import vista.PanelSocio;
import vista.utilidades.InformacionDialog;
import vista.utilidades.JTextFieldLimit;
import vista.utilidades.TextPrompt;
import vista.utilidades.Verficador;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;



public class AñadirSocio extends PanelSocio{
	
	
	private ControladorAñadirSocio controladorAñadir;

	@Override
	protected Action accionBotonPrincipal() {
		Action btn= new AbstractAction("Confirmar",	new ImageIcon(getClass().getResource("/imagenes/comprobar.png"))) {
			
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AñadirSocio.this.controladorAñadir.añadirSocio();
			}
		};
		return btn;
	}

	@Override
	protected Action accionBotonSecundario() {
		Action btn= new AbstractAction("Cancelar",new ImageIcon(getClass().getResource("/imagenes/cancelar.png"))) {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AñadirSocio.this.resetear();
				//Hacemos que el primer boton del grupo de socios, se seleccione, para evitar cosas raras
				((AbstractButton)AñadirSocio.this.grupoTipoSocio.getElements().nextElement()).setSelected(true);
			}
		};
		return btn;
	}
	
	public AñadirSocio() {
		this.controladorAñadir= new ControladorAñadirSocio(this);
		super.crearPanel("Añadir Socio");
	}
}
