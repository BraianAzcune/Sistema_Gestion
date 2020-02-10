package utilidades.paginacionTablas;

/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */


import java.util.List;

/**
 * Interfaz que tiene los metodos que hay que implementar para habilitar la paginacion de datos Si
 * vas a obtener datos en trozos, probablemetne deberias consultar la cantidad 1 sola vez y
 * almacenarla para sucesivas preguntas.
 * 
 * @author braian
 */
public interface ProveedorDeDatosPaginacion<T> {

  int getTotalRowsCount();

  // Si esto usara limit y offset seria por ejemplo si llegara 23, 40 -> limit 40-23 offset 23
  List<T> getRows(int startIndex, int endIndex);

}
