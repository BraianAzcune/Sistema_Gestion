package utilidades.paginacionTablas;



import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Contiene una Lista de objetos y dependiedno lo que tiene la lista, calcula la cantidad de filas y
 * modifica getValueAt, para que el que implemente reciba el objeto correspondietne a esa fila y la
 * columna, que tendra que saber a que atributo del objeto corresponde. COn setLista se cambia lo
 * que tiene la lista, que es lo que se deberia hacer para cambiar la pagina.
 * 
 * @author braian
 * @param <T>
 */
public abstract class ModeloTabla<T> extends AbstractTableModel {
  private List<T> lista = new ArrayList<T>();



  // METODOS SOBRESCRITOS
  @Override
  public int getRowCount() {
    return lista.size();
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    T t = lista.get(rowIndex);
    return getValueAt(t, columnIndex);
  }



  // METODOS ABSTRACTOS


  public abstract Object getValueAt(T generico, int columna);


  @Override
  public abstract String getColumnName(int columna);



  // GETTERS SETTERS
  public List<T> getLista() {
    return lista;
  }

  /**
   * Se cambia la lista, esto llama a fireTableDataChanged, para que se notifique a los oyentes el
   * cambio
   * 
   * @param lista
   */
  public void setLista(List<T> lista) {
    this.lista = lista;
    this.fireTableDataChanged();
  }


}
