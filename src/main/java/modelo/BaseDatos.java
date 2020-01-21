package modelo;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

/**
 * Encapsula la inicializacion del objeto sql2o que sera estatico, ya que se reusara en toda la aplicacion
 * 
 * NOTAS=Se debe pedir para cada consulta a sql2o que te entregue una connection con sql2o.open()
 * *Se debe siempre apagar la base de datos como ultimo comando
 * @author braian
 *
 */
public class BaseDatos {
	
	/**
	 * Datos Sobre la base de datos
	 */
	private static final String rutaDB= "db/clubdb";
	private static final String user="fantastica";
	private static final String pass="1548";
	
	
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseDatos.class);
	
	private  static Sql2o sql2o;
	
	
	private BaseDatos() {};//Constructor privado esta clase no se instancia.
	
	
	
	/**
	 * Otorga el objeto sql2o, para crear conexiones especiales como transacciones
	 * @return
	 */
	public static Sql2o obtenerSql2o() {
		if(Objects.isNull(sql2o)) {
			sql2o= new Sql2o("jdbc:hsqldb:file:"+rutaDB+";shutdown=true", user, pass);
		}
		return sql2o;
	}
	
	
	/**
	 * Envia la orden a la base de datos embebida de que ejecute cualquier tarea que tenia pendiente y se cierre
	 * ademas se cierra la conexion
	 */
	public static void apagar() {
		LOG.debug("Intentado apagar base datos");
		
		if(Objects.nonNull(sql2o)) {
			
			try {
				Connection con= sql2o.open();
				
				con.getJdbcConnection().createStatement().execute("SHUTDOWN");
				
				con.close();
				LOG.debug("apagada db --ok");
			} catch (Exception e) {
				LOG.error("La orden de cerrar la base de datos fallo= ",e);
			}
		}else {
			LOG.debug("sql2o no inicializado, no se uso la base de datos");
		}
	}
}
