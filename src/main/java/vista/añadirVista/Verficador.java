package vista.a�adirVista;
import javax.swing.InputVerifier;

import javax.swing.JComponent;

import javax.swing.JOptionPane;

import javax.swing.JTextField;





public class Verficador extends InputVerifier {



	@Override

	public boolean verify(JComponent editor) {
                // suponemos que JComponent ser� un JTextField

		if (editor instanceof JTextField)

		{

			String texto = ((JTextField)editor).getText();

			try

			{
                                // Si se puede convertir en entero, est� bien
				if((texto != null) 
		                && (!texto.equals("")) 
		                && (texto.matches("^[a-zA-Z]*$")))
				{
					return true;
				};



			}

			catch (Exception e)

			{
                                // Si no se ha podido convertir a entero, mostramos
                                // una ventana de error y devolvemos false
				editor.setVisible(true);
				JOptionPane.showMessageDialog(editor, "No es un n�mero");

				return false;

			}

		}

		return true;

	}



}