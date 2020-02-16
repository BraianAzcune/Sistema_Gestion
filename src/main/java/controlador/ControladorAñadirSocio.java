package controlador;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Query;

import modelo.BaseDatos;
import modelo.Socio;
import modelo.SocioXDeporte;
import vista.añadirVista.AñadirSocio;

/**
 * Controlador que gestiona el panel AñadirSocio y los sub paneles
 * 
 * @author braian
 *
 */
public class ControladorAñadirSocio {

  private AñadirSocio panel;


  public ControladorAñadirSocio(AñadirSocio panel) {
    this.panel = panel;


  }



  /**
   * Usa los datos que tenga panel para añadir el socio
   */
  public void añadirSocio() {


    WorkerAñadirSocio worker = new WorkerAñadirSocio(panel.mapearSocio(), panel.mapearDeportes());

    worker.execute();
  }


  /**
   * Dado un id_socio y un array de deportes, devuelve un array de SocioXDeporte.
   * 
   * @return SocioXDeporte
   */
  private static SocioXDeporte[] mapearSocioXDeporte(String id_socio, int[] id_deportes) {

    SocioXDeporte[] array = new SocioXDeporte[id_deportes.length];

    for (int i = 0; i < id_deportes.length; i++) {
      array[i] = SocioXDeporte.builder().id_socio(id_socio).id_deporte(id_deportes[i]).build();
    }

    return array;
  }



  /**
   * Esta clase interna utiliza las variables de la clase externa para su operatoria.
   * 
   * @author braian
   *
   */
  class WorkerAñadirSocio extends SwingWorker<Void, Void> {

    private Boolean exito = false;

    private Logger log = LoggerFactory.getLogger(WorkerAñadirSocio.class);

    private Connection con;

    private Socio socio;
    // contiene los id de los deportes que hara el nuevo socio
    private int[] arrayDeportesID;

    public WorkerAñadirSocio(Socio mapearSocio, int[] mapearDeportes) {
      this.socio = mapearSocio;
      this.arrayDeportesID = mapearDeportes;
    }

    @Override
    protected Void doInBackground() throws Exception {

      try {
        con = BaseDatos.obtenerSql2o().open();
        if (verificarValidezID()) {
          insertarSocio();
          SocioXDeporte[] socioxdeporte =
              mapearSocioXDeporte(socio.getNumerosocio(), arrayDeportesID);
          insertarSocioXDeporte(socioxdeporte);
          exito = true;
        } else {// avisar al usuario que debe ingresar otro id

          JOptionPane.showMessageDialog(null, "El numero de socio esta ocupado, elija otro",
              "Añadir Socios", JOptionPane.WARNING_MESSAGE);
        }
      } catch (Exception e) {
        log.error("el worker no se pudo conectar a la db", e);
      }
      return null;
    }

    @Override
    protected void done() {
      // ESTO DEBERIA SER CAMBIADO
      if (exito) {
        JOptionPane.showMessageDialog(null, "Socio " + socio.getNombre() + " agregado",
            "Añadir Socios", JOptionPane.INFORMATION_MESSAGE);
        panel.limpiar();
        super.done();
      }

    }


    private void insertarSocioXDeporte(SocioXDeporte[] socioxdeporte) {
      String sql =
          "INSERT INTO PUBLIC.PUBLIC.SOCIOSXDEPORTE (ID_SOCIO, ID_DEPORTE) VALUES(:id_socio, :id_deporte);";
      if (socioxdeporte.length != 0) {

        try (Connection conn = BaseDatos.obtenerSql2o().beginTransaction()) {
          Query query = conn.createQuery(sql);

          for (SocioXDeporte sd : socioxdeporte) {
            query.bind(sd).addToBatch();
          }
          query.executeBatch();
          conn.commit();// si falla se aborta automaticamente, y hace rollback
        } catch (Exception e) {
          log.error("insertar deportes fallo ", e);
        }
      } else {
        log.debug("no habia deportes seleccionados");
      }
    }

    /**
     * Realiza una consulta preguntado por el id que tiene el socio, si es que tiene un id, sino es
     * verdad automatica.
     * 
     * @return
     */
    private Boolean verificarValidezID() {
      if (socio.getNumerosocio() != null) {
        log.debug("se ingreso un numero de socio");
        String sql = "SELECT COUNT(*) FROM PUBLIC.PUBLIC.SOCIOS WHERE NUMEROSOCIO=:numero;";


        int rta = con.createQuery(sql).addParameter("numero", socio.getNumerosocio())
            .executeScalar(Integer.class);
        // si es 0 esta disponible, sino no.
        return (rta == 0) ? true : false;
      }
      return true;
    }

    /**
     * Inserta el socio, y le ingresa el numero de id que se le asigno si es que no tenia uno antes
     * valido.
     */
    private void insertarSocio() {
      String sql;
      if (socio.getNumerosocio() == null) {
        sql = "INSERT INTO PUBLIC.PUBLIC.SOCIOS\r\n"
            + "(NOMBRE, APELLIDO, EMAIL, DNI, TELEFONO, DIRECCION, FECHA_ALTA, FK_TIPO_SOCIO)\r\n"
            + "VALUES(:nombre, :apellido, :email, :dni, :telefono, :Direccion, CURRENT_DATE, :tipo_socio);";
      } else {
        sql = "INSERT INTO PUBLIC.PUBLIC.SOCIOS\r\n"
            + "(NOMBRE, APELLIDO, EMAIL, DNI, TELEFONO, DIRECCION, FECHA_ALTA, FK_TIPO_SOCIO,NUMEROSOCIO)\r\n"
            + "VALUES(:nombre, :apellido, :email, :dni, :telefono, :Direccion, CURRENT_DATE, :tipo_socio,:numerosocio);";
      }

      con.createQuery(sql).bind(socio).executeUpdate();

      if (socio.getNumerosocio() == null) {
        sql =
            "SELECT NUMEROSOCIO FROM SOCIOS WHERE NOMBRE=:nombre  AND APELLIDO=:apellido AND DNI=:dni;";
        socio.setNumerosocio(con.createQuery(sql).addParameter("nombre", socio.getNombre())
            .addParameter("apellido", socio.getApellido()).addParameter("dni", socio.getDni())
            .executeScalar(String.class));
      }
    }


  }

}
