package modelo.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class Socio {
	String nombre;
	String apellido;
	String email;
	String dni;
	String telefono;
	String Direccion;
	String fecha_alta;
	int tipo_socio;//clave foranea
	int numerosocio;//clave primaria
}
