package modelo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SocioXDeporte {
  String id_socio;
  int id_deporte;
}
