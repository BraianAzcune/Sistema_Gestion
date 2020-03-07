package controlador;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import modelo.Socio;
import modelo.SocioXDeporte;
import modelo.dao.SocioDAO;
import modelo.dao.SocioXDeporteDAO;
import vista.verSocios.EditarSocio;

@Slf4j
public class ControladorEditarSocio {

  SocioDAO socioDAO = new SocioDAO();
  SocioXDeporteDAO socioXDeporte = new SocioXDeporteDAO();
  private EditarSocio panel;


  public ControladorEditarSocio(EditarSocio panel) {
    this.panel = panel;
  }

  /**
   * Recupera los datos SOCIO y DEPORTE segun ID_SOCIO y pone los datos en el panel.
   * 
   * @param iD_SOCIO
   */
  public void inicializarPanel(String ID_SOCIO) {


    Socio socio = socioDAO.getSocio(ID_SOCIO);

    panel.putSocio(socio);


    List<SocioXDeporte> sxd = socioXDeporte.obtenerDeportesSegunIDSocio(ID_SOCIO);

    ArrayList<Integer> idDeportes = new ArrayList<Integer>();

    for (SocioXDeporte s : sxd) {
      idDeportes.add(s.getId_deporte());
    }

    panel.putDeportes(idDeportes.stream().mapToInt(i -> i).toArray());

  }

}
