package modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Cualquier agregacion o cambio que se le haga a esto tendra que mirarse el codigo de
 * ControladorFiltro.actualizarSQL que utiliza directamente la clase
 * 
 * (otras clases tambien hacen uso intensivo como AñadirSocio, ojo..)
 * 
 * @author braian
 *
 */
@Builder
@Getter
@Setter
@ToString
public class Socio {
  String nombre;
  String apellido;
  String email;
  String dni;
  String telefono;
  String direccion;
  String fecha_alta;
  String tipo_socio;// clave foranea
  String numerosocio;// clave primaria
}
