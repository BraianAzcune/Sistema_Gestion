package controlador;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import vista.añadirVista.AñadirSocio;

public class ControladorAñadirSocioTest {
	
	private AñadirSocio panel;
	@Before
	public void before() {
		panel= new AñadirSocio();
	}
	
	@Test
	public void Test() {
		
	}
	
	/**
	 * Prueba que el placeholder no deja ningun contenido en el jtextfield
	 */
	@Test
	public void probarPlaceHolders() {
		
		assertEquals("", panel.textFieldNumeroSocio.getText());
	}
	
	@Test
	public void mapearDeportesTest() {
		int cantidadChecks=5;
		int[] seleccionados= {0,1,1,0,1};
		
		
		
		panel.arrayCheckBoxDeportes= new ArrayList<>();
		
		ArrayList<Integer> esperado= new ArrayList<>();
		
		for(int i=0;i<cantidadChecks;i++) {
			JCheckBox c= new JCheckBox("check "+i);
			c.setActionCommand(String.valueOf(i));
			panel.arrayCheckBoxDeportes.add(c);
			
			if(seleccionados[i]==1) {
				c.setSelected(true);
				esperado.add(i);
			}
		}
		
		ControladorAñadirSocio controlador= new ControladorAñadirSocio(panel);
		
		/*
		int[] rta= controlador.mapearDeportes();
		
		
		Assert.assertArrayEquals(esperado.stream().mapToInt(i->i).toArray(), rta);
		
		System.out.println("array= "+Arrays.toString(rta));
		*/
	}

}
