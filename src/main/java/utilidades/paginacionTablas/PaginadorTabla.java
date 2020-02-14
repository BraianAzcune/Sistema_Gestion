package utilidades.paginacionTablas;

/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import lombok.extern.slf4j.Slf4j;

/**
 * Recibe un ModeloTabla a traves de el JTable que solicita, y le inicializa todo el contenido, y
 * prepara para llamarlo cuando ocurren eventos de botones, o cambia la cantidad de filas que se
 * deben mostrar
 * 
 * tambien crea, y actualiza la fila de botones de paginas y el JcomboBox que tiene la cantidad de
 * filas de opciones valida.
 * 
 * llama al modelo y al proveedor para solicitar los datos que corresponden al nuevo cambio de
 * botones o del combobox y le dice al jtable que se reactualice
 * 
 * @author braian
 */
@Slf4j
public class PaginadorTabla<T> implements ActionListener, TableModelListener {
  // ASPECTO TECNICO
  private JTable tabla;
  private ProveedorDeDatosPaginacion<T> proveedorDeDatosPaginacion;
  private int[] arrayFilasPermitidas;
  /**
   * Esto representa la cantidad de filas que se mostraran, se corresponde al valor que tiene el
   * jcomboboxfilaspermitidas
   */
  private int filaPermitida;
  /**
   * Representa el numero de pagina en la que se esta actualmente 1,2,3.. en cada pagina habra una
   * cantidad "filaPermitida" de filas
   */
  private int paginaActual = 1;

  private ModeloTabla<T> modeloTabla;

  /**
   * CONSTRUCTOR
   * 
   * @param tabla ya debe tener cargado el ModeloTabla, porque lo solicita, para operar con el
   *        internamente y actualizar.
   * @param proveedorDatos
   * @param arrayFilasPermitidas un array con las diferentes opciones de valores de cantidad de
   *        filas que se pueden mostrar.
   * @param filaPermitida cantidad de filas que se van a ver por defecto
   */
  public PaginadorTabla(JTable tabla, ProveedorDeDatosPaginacion<T> proveedorDatos,
      int[] arrayFilasPermitidas, int filaPermitida) {
    this.arrayFilasPermitidas = arrayFilasPermitidas;
    this.filaPermitida = filaPermitida;
    this.proveedorDeDatosPaginacion = proveedorDatos;
    this.tabla = tabla;
    init();
  }

  private void init() {
    inicializarModeloTabla();
    paginar();

  }

  private void inicializarModeloTabla() {

    try {
      modeloTabla = (ModeloTabla<T>) tabla.getModel();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void paginar() {
    int inicio = (paginaActual - 1) * filaPermitida;// porque el default es 1 //caso base= (1-1)*10
    int finalizacion = inicio + filaPermitida;
    if (finalizacion > proveedorDeDatosPaginacion.getTotalRowsCount()) {
      finalizacion = proveedorDeDatosPaginacion.getTotalRowsCount();
    }
    List<T> lista = proveedorDeDatosPaginacion.getRows(inicio, finalizacion);
    modeloTabla.setLista(lista);
  }

  // ASPECTO VISUAL
  private JComboBox<Integer> comboBoxFilasPermitidas;
  private JPanel panelPaginacionBotones;
  private int limiteBotonesPaginador = 9;

  /**
   * construye el panel que tendra todo sobre la paginacion de dicha tabla.
   * 
   * @param panelPaginador se espera el panel vacio que pondra los botones de paginacion y la opcion
   *        de cuantas filas ver a vez
   */
  public void setPanelPaginacion(JPanel panelPaginador) {


    this.panelPaginacionBotones = new JPanel(new GridLayout(1, limiteBotonesPaginador, 3, 3));

    panelPaginador.add(this.panelPaginacionBotones);

    if (arrayFilasPermitidas != null) {

      Integer[] array = Arrays.stream(arrayFilasPermitidas).boxed().toArray(Integer[]::new);

      comboBoxFilasPermitidas = new JComboBox<>(array);
      comboBoxFilasPermitidas.setSelectedItem(String.valueOf(filaPermitida));

      panelPaginador.add(Box.createHorizontalStrut(15));

      panelPaginador.add(new JLabel("Numero de filas"));

      panelPaginador.add(comboBoxFilasPermitidas);
    }

    // SE AÑADEN ESCUCHADORES DE EVENTOS
    comboBoxFilasPermitidas.addActionListener(this);
    modeloTabla.addTableModelListener(this);
    actualizarBotonesPaginacion();
  }



  /**
   * Se deberia llamar cuando se seleccione un nuevo valor de cuantas filas se mostraran a la vez en
   * la tabla. lo que hace este metodo es encargarse de que si estabas por ejemplo en la pagina 3,
   * viendo hasta 50 filas y tu primera fila era la numero 150. y ahora queres ver de 100 en 100
   * filas. entonces ahora se cambiara a decir que estas en la pagina numero 2.
   * 
   * @param pageComboBox
   */
  public void eventJComboBox(JComboBox<Integer> pageComboBox) {
    int filaInicialPagina = ((paginaActual - 1) * filaPermitida) + 1;
    filaPermitida = (Integer) pageComboBox.getSelectedItem();
    paginaActual = ((filaInicialPagina - 1) / filaPermitida) + 1;
    paginar();
  }

  // GETTERS AND SETTERS COMBOBOX
  public JComboBox<Integer> getComboBoxFilasPermitidas() {
    return comboBoxFilasPermitidas;
  }

  public void setComboBoxFilasPermitidas(JComboBox<Integer> comboBoxFilasPermitidas) {
    this.comboBoxFilasPermitidas = comboBoxFilasPermitidas;
  }


  // PARTE DE LOS BOTONES DEL PAGINADOR

  /**
   * Agrega un solo boton al panel panelPaginacionBotones
   * 
   * @param panelPadre es la instancia panelPaginacionBotones, que tiene este, ni idea, parece un
   *        sin sentido recibirlo como parametro
   * @param numeroPagina
   */
  private void agregarBotonesPaginacion(JPanel panelPadre, int numeroPagina) {

    JToggleButton toggleButton = new JToggleButton(String.valueOf(numeroPagina));

    toggleButton.setMargin(new Insets(1, 3, 1, 3));

    panelPadre.add(toggleButton);
    // Si coincide debe mostrarse seleccionado
    if (numeroPagina == paginaActual) {
      toggleButton.setSelected(true);
    }

    toggleButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        paginaActual = numeroPagina;
        paginar();
      }
    });

  }

  /**
   * Agrega un rango de botones al panel panelPaginacionBotones
   */
  private void agregarRangoBotonesPaginacion(JPanel panelPadre, int inicio, int finalizacion) {

    for (int i = inicio; i < finalizacion; i++) {
      agregarBotonesPaginacion(panelPadre, i);
    }

  }

  /**
   * Son las botones que aparecen entre medio para mostrar que hay mas paginas para visitar.
   * 
   * @return
   */
  private JLabel crearElipses() {
    return new JLabel("...", SwingConstants.CENTER);
  }

  /**
   * Se llama cada vez que ocurre un cambio con el modelo.
   */
  public void actualizarBotonesPaginacion() {

    log.debug("Entre a actualizar botones");

    panelPaginacionBotones.removeAll();

    int totalFilas = proveedorDeDatosPaginacion.getTotalRowsCount();

    int paginas = (int) Math.ceil((double) totalFilas / filaPermitida);

    // se tiene que agregar los ... si es mayor
    if (paginas > limiteBotonesPaginador) {

      // El primero siempre esta visible.
      agregarBotonesPaginacion(panelPaginacionBotones, 1);

      if (paginaActual <= (limiteBotonesPaginador + 1) / 2) {

        agregarRangoBotonesPaginacion(panelPaginacionBotones, 2, limiteBotonesPaginador - 2);
        // SE AGREGA "..."
        panelPaginacionBotones.add(crearElipses());
        // SE AGREGA LA ULTIMA
        agregarBotonesPaginacion(panelPaginacionBotones, paginas);
      } else if (paginaActual > (paginas - (limiteBotonesPaginador + 1) / 2)) {
        // SE AGREGA "..."
        panelPaginacionBotones.add(crearElipses());
        agregarRangoBotonesPaginacion(panelPaginacionBotones, paginas - limiteBotonesPaginador + 3,
            paginas);

      } else {
        // ESTE ES EL CASO DONDE ESTAN LAS 2 ETIQUETAS
        panelPaginacionBotones.add(crearElipses());

        // botones intermedios
        int inicio = paginaActual - (limiteBotonesPaginador - 4) / 2;
        int finalizacion = inicio + limiteBotonesPaginador - 5;

        agregarRangoBotonesPaginacion(panelPaginacionBotones, inicio, finalizacion);
        // SE AGREGA "..."
        panelPaginacionBotones.add(crearElipses());
        // ultimo boton
        agregarBotonesPaginacion(panelPaginacionBotones, paginas);
      }

    } else {
      log.debug("agrege un solo boton");
      agregarRangoBotonesPaginacion(panelPaginacionBotones, 1, paginas);
    }


    panelPaginacionBotones.getParent().validate();
    panelPaginacionBotones.getParent().repaint();
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    // SI ES UN EVENTO DEL COMBO BOX
    if (e.getSource().equals(comboBoxFilasPermitidas)) {
      // SE RELIZA EL EVENTO DEL COMBO BOX, QUE LLAMA A LA TABLA A MOSTRAR LOS DATOS DE NUEVO CON LA
      // NUEVA OPCION.
      eventJComboBox(comboBoxFilasPermitidas);
    }
  }

  /**
   * Cada vez que se actualice la tabla es necesario re ajustar los botones de paginacion.
   * 
   * @param e
   */
  @Override
  public void tableChanged(TableModelEvent e) {
    actualizarBotonesPaginacion();
  }

  /**
   * Para cambiar el proveedor de paginacion, pone todo en 0 de nuevo. se resetea a la pagina
   * inicial y se reconstruyen los botones, y se pagina la nueva informacion.
   */
  public void setProveedorPaginacion() {

    paginaActual = 1;
    actualizarBotonesPaginacion();
    paginar();
  }
}
