package modelo.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import modelo.Socio;

@Slf4j
public class SocioDAOTest {

  private final int CANTIDADDESOCIOSTOTALES = 2;

  private SocioDAO sociodao;


  @Before
  public void iniciarSocioDAO() {
    sociodao = new SocioDAO();
  }

  @Test
  public void getTotalRowTest() {

    // SOLO HAY 2 SOCIOS.
    // assertEquals(CANTIDADDESOCIOSTOTALES, sociodao.getTotalRow());
  }

  @Test
  public void getSociosTest() {

    List<Socio> lista;

    lista = sociodao.getSocios(0, 100);

    int cantidadSocio = lista.size();

    log.debug("CANTIDAD SOCIO= " + cantidadSocio);

    // for (Socio s : lista) {
    // log.debug(s.toString());
    // }



    // assertEquals(cantidadSocio, CANTIDADDESOCIOSTOTALES);

  }

}
