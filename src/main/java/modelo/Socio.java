package modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
