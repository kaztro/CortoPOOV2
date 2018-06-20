package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Consulta extends JFrame {
    public JLabel lblNombre, lblDirector, lblPais, lblClasificacion, lblAño, lblEn_proyeccion;

    public JTextField codigo, descripcion, stock;
    public JComboBox clasificacion;

    ButtonGroup existencia = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblNombre);
        container.add("lblMarca");
        container.add("lblStock");
        container.add("lblExistencia");
        container.add("codigo");
        container.add("marca");
        container.add("stock");
        container.add("si");
        container.add("no");
        container.add("buscar");
        container.add("insertar");
        container.add("actualizar");
        container.add("eliminar");
        container.add("limpiar");
        container.add("table");
        setSize(600, 600);
        eventos();
    }

    public final void agregarLabels() {
        lblNombre = new JLabel("Nombre");
        lblDirector = new JLabel("Director");
        lblPais = new JLabel("País");
        lblEn_proyeccion = new JLabel("En Proyeccion");
        lblClasificacion = new JLabel("Clasificacion");
        lblAño = new JLabel("Año");
        lblCodigo.setBounds(10, 10, ANCHOC, ALTOC);
        lblMarca.setBounds(10, 60, ANCHOC, ALTOC);
        lblStock.setBounds(10, 100, ANCHOC, ALTOC);
        lblExistencia.setBounds(10, 140, ANCHOC, ALTOC);
    }

    public final void formulario() {
        codigo = new JTextField();
        clasificacion = new JComboBox();
        stock = new JTextField();
    }
}
