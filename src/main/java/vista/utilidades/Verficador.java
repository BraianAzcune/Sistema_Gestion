package vista.utilidades;
import javax.swing.InputVerifier;

import javax.swing.JComponent;

import javax.swing.JOptionPane;

import javax.swing.JTextField;





public class Verficador extends InputVerifier {



	@Override

	public boolean verify(JComponent editor) {
                // suponemos que JComponent será un JTextField

		if (editor instanceof JTextField)

		{

			String texto = ((JTextField)editor).getText();

			try

			{
                               
				if((texto != null) 
		                && (!texto.equals("")) 
		                && (texto.matches("^[a-zA-Z]*$")))
				{
					return true;
				};



			}

			catch (Exception e)

			{
                                
                                // una ventana de error y devolvemos false
				editor.setVisible(true);
				JOptionPane.showMessageDialog(editor, "No es un número");

				return false;

			}

		}

		return true;

	}



}