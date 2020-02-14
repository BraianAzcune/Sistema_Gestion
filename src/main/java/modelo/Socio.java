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
  String Direccion;
  String fecha_alta;
  int tipo_socio;// clave foranea
  int numerosocio;// clave primaria
}
