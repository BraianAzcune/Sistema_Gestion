package controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Query;

import lombok.extern.slf4j.Slf4j;
import modelo.BaseDatos;
import modelo.Deporte;
import modelo.Socio;
import modelo.SocioXDeporte;
import modelo.SocioXDeporte.SocioXDeporteBuilder;
import modelo.dao.DeporteDAO;
import vista.añadirVista.AñadirSocio;

/**
 * Controlador que gestiona el panel AñadirSocio y los sub paneles
 * @author braian
 *
 */
public class ControladorAñadirSocio {
	
	private AñadirSocio panel;
	
	
	public ControladorAñadirSocio(AñadirSocio panel) {
		this.panel=panel;
		
		
	}
	
	public List<Deporte> ObtenerListaDeportes() {
		DeporteDAO dao= new DeporteDAO();
		return dao.obtenerDeportes(); 
	}

	
	/**
	 * Usa los datos que tenga panel para añadir el socio
	 */
	public void añadirSocio() {
		
		
		WorkerAñadirSocio worker= new WorkerAñadirSocio(mapearSocio(),mapearDeportes());
		
		worker.execute();
	}
	/**
	 * Mapeamos lo que tiene la ventana a lo que tiene la clase Socio
	 * 
	 * @return Socio->numerosocio es -1 si no hay asignado nada
	 */
	private Socio mapearSocio() {
		//mapeamos los datos que seguro van
		Socio s= Socio.builder().nombre(panel.textFieldNombre.getText())
				.apellido(panel.textFieldApellido.getText())
				.email(panel.textFieldEmail.getText())
				.dni(panel.textFieldDNI.getText())
				.telefono(panel.textFieldTelefono.getText())
				.Direccion(panel.textFieldDireccion.getText())
				.tipo_socio(panel.queTipoSocioEs())
				.build();
		
		if(panel.textFieldNumeroSocio.getText().equals("")) {
			s.setNumerosocio(-1);
		}else {
			s.setNumerosocio(Integer.parseInt(panel.textFieldNumeroSocio.getText()));
		}
		
		return s;
	}
	
	/**
	 * Dado un id_socio y un array de deportes, devuelve un array de SocioXDeporte.
	 * @return SocioXDeporte
	 */
	private static synchronized SocioXDeporte[] mapearSocioXDeporte(int id_socio,int[] id_deportes) {
		
		SocioXDeporte[] array= new SocioXDeporte[id_deportes.length];
		
		for(int i=0;i<id_deportes.length;i++) {
			array[i]= SocioXDeporte.builder().id_socio(id_socio).id_deporte(id_deportes[i]).build();
		}
		
		return array;
	}
	
	/**
	 * Toma los datos de la ventana AñadirSocio y mapea los id de deportes seleccionados
	 * 
	 * @return retorna lista de enteros que representa los id de deportes seleccionados
	 */
	private int[] mapearDeportes(){
		int[] array = new int[panel.arrayCheckBoxDeportes.size()];
		
		int i=0;
		for(JCheckBox valor: panel.arrayCheckBoxDeportes) {
			if(valor.isSelected()) {
				array[i]= Integer.parseInt(valor.getActionCommand());
				i++;
			}
		}
		return Arrays.copyOf(array, i);
 	}
	
	/**
	 * Esta clase interna utiliza las variables de la clase externa para su operatoria.
	 * @author braian
	 *
	 */
	class WorkerAñadirSocio extends SwingWorker<Void,Void>{
		
		
		private Logger log=  LoggerFactory.getLogger(WorkerAñadirSocio.class);
		
		private Connection con;
		
		private Socio socio;
		//contiene los id de los deportes que hara el nuevo socio
		private int[] arrayDeportesID;

		public WorkerAñadirSocio(Socio mapearSocio, int[] mapearDeportes) {
			this.socio = mapearSocio;
			this.arrayDeportesID = mapearDeportes;
		}

		@Override
		protected Void doInBackground() throws Exception {
			
			try{
				con = BaseDatos.obtenerSql2o().open();
				if(verificarValidezID()) {
					insertarSocio();
					SocioXDeporte[] socioxdeporte= mapearSocioXDeporte(socio.getNumerosocio(), arrayDeportesID);
					insertarSocioXDeporte(socioxdeporte);
				}else {//avisar al usuario que debe ingresar otro id
					throw new java.lang.UnsupportedOperationException("No implementado llamar a que el no esta "
							+ "el id disponible");
				}
			} catch (Exception e) {
				log.error("el worker no se pudo conectar a la db",e);
			}
			return null;
		}
		
		@Override
		protected void done() {
			//ESTO DEBERIA SER CAMBIADO
			JOptionPane.showMessageDialog(null,"Socio "+socio.getNombre()+" agregado","Añadir Socios",JOptionPane.INFORMATION_MESSAGE);
			super.done();
		}
		
		
		private void insertarSocioXDeporte(SocioXDeporte[] socioxdeporte) {
			String sql= "INSERT INTO PUBLIC.PUBLIC.SOCIOSXDEPORTE (ID_SOCIO, ID_DEPORTE) VALUES(:id_socio, :id_deporte);";
			
			try (Connection conn= BaseDatos.obtenerSql2o().beginTransaction()){
				  Query query = conn.createQuery(sql);
				  
				  for(SocioXDeporte sd: socioxdeporte) {
					  query.bind(sd).addToBatch();
				  }
				  query.executeBatch();
				  conn.commit();//si falla se aborta automaticamente, y hace rollback
			} catch (Exception e) {
				log.error("insertar deportes fallo ",e);
			}
		}
		
		/**
		 * Realiza una consulta preguntado por el id que tiene el socio, si es que tiene un id, sino es verdad automatica.
		 * @return
		 */
		private Boolean verificarValidezID() {
			if(!"".equals(socio.getNumerosocio())) {
				String sql= "SELECT COUNT(*) FROM PUBLIC.PUBLIC.SOCIOS WHERE NUMEROSOCIO=:numero;";
				
			    
				int rta=con.createQuery(sql)
						.addParameter("numero", socio.getNumerosocio())
						.executeScalar(Integer.class);
				//si es 0 esta disponible, sino no.
			    return (rta==0)?true:false;
			}
			return true;
		}
		/**
		 * Inserta el socio, y le ingresa el numero de id que se le asigno si es que no tenia uno antes valido.
		 */
		private void insertarSocio() {
			String sql;
			if(socio.getNumerosocio()==-1){
				sql="INSERT INTO PUBLIC.PUBLIC.SOCIOS\r\n" + 
					"(NOMBRE, APELLIDO, EMAIL, DNI, TELEFONO, DIRECCION, FECHA_ALTA, FK_TIPO_SOCIO)\r\n" + 
					"VALUES(:nombre, :apellido, :email, :dni, :telefono, :Direccion, CURRENT_DATE, :tipo_socio);";
			}else {
				sql="INSERT INTO PUBLIC.PUBLIC.SOCIOS\r\n" + 
						"(NOMBRE, APELLIDO, EMAIL, DNI, TELEFONO, DIRECCION, FECHA_ALTA, FK_TIPO_SOCIO,NUMEROSOCIO)\r\n" + 
						"VALUES(:nombre, :apellido, :email, :dni, :telefono, :Direccion, CURRENT_DATE, :tipo_socio,:numerosocio);";
			}
			
			con.createQuery(sql).bind(socio).executeUpdate();
			
			if(socio.getNumerosocio()==-1) {
				sql="SELECT NUMEROSOCIO FROM SOCIOS WHERE NOMBRE=:nombre  AND APELLIDO=:apellido AND DNI=:dni;";
				socio.setNumerosocio(con.createQuery(sql).executeScalar(Integer.class));
			}
		}
		
	}
	
}
