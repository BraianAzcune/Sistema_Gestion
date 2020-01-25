package modelo.dao;

import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;

import lombok.extern.slf4j.Slf4j;
import modelo.BaseDatos;
import modelo.Deporte;;
@Slf4j
public class DeporteDAO {
	
	
	
	public List<Deporte> obtenerDeportes(){
		
		
		
		String sql= "SELECT ID, DEPORTE FROM PUBLIC.PUBLIC.DEPORTE;";
		
		try (Connection con= BaseDatos.obtenerSql2o().open()){
			return con.createQuery(sql).executeAndFetch(Deporte.class);
		} catch (Exception e) {
			log.error("error obtenerDeportes en DAO ",e);
			e.printStackTrace();
		}
		return null;
		
	}
}
