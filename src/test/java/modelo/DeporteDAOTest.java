package modelo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import modelo.dao.DeporteDAO;

public class DeporteDAOTest {

	
	
	@Test
	public void obtenerDeportesTest() {
		
		DeporteDAO dao= new DeporteDAO();
		List<Deporte> list= dao.obtenerDeportes();
		System.out.println(Arrays.toString(list.toArray()));
	}

}
