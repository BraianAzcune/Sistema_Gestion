package controlador;

import java.util.List;

import modelo.Deporte;
import modelo.dao.DeporteDAO;

/**
 * Controlador con metodos genericos estaticos para solicitar informacion comun
 * 
 * @author braian
 *
 */
public class Controlador {
  public static List<Deporte> ObtenerListaDeportes() {
    DeporteDAO dao = new DeporteDAO();
    return dao.obtenerDeportes();
  }
}
