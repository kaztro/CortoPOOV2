package Vista;

import Modelo.Movie;
import dao.MovieDao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class Consulta extends JFrame {
    public JLabel lblNombre, lblDirector, lblPais, lblClasificacion, lblAño, lblEn_proyeccion;

    public JTextField nombre, director, pais, año, enProyec;
    public JComboBox clasificacion;

    ButtonGroup en_proyeccion = new ButtonGroup();
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
        container.add(lblDirector);
        container.add(lblPais);
        container.add(lblClasificacion);
        container.add(lblAño);
        container.add(lblEn_proyeccion);
        container.add(nombre);
        container.add(clasificacion);
        container.add(director);
        container.add(pais);
        container.add(año);
        container.add(enProyec);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
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
        lblNombre.setBounds(10, 10, ANCHOC, ALTOC);
        lblDirector.setBounds(10, 60, ANCHOC, ALTOC);
        lblPais.setBounds(10, 100, ANCHOC, ALTOC);
        lblEn_proyeccion.setBounds(10, 140, ANCHOC, ALTOC);
        lblClasificacion.setBounds(10, 30, ANCHOC, ALTOC);
        lblAño.setBounds(10, 38, ANCHOC, ALTOC);
    }

    public final void formulario() {
        clasificacion = new JComboBox();
        director = new JTextField();
        nombre = new JTextField();
        pais = new JTextField();
        año = new JTextField();
        enProyec = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar ");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("limpiar");
        
        table = new JPanel();
        
        clasificacion.addItem("PG-13");
        clasificacion.addItem("R");
        clasificacion.addItem("G");
        
        en_proyeccion = new ButtonGroup();
        en_proyeccion.add(si);
        en_proyeccion.add(no);
        
        director.setBounds(140, 10, ANCHOC, ALTOC);
        nombre.setBounds(140, 12, ANCHOC, ALTOC);
        pais.setBounds(140, 14, ANCHOC, ALTOC);
        año.setBounds(140, 16, ANCHOC, ALTOC);
        enProyec.setBounds(140, 18, ANCHOC, ALTOC);
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(210, 140, 50, ALTOC);
        
        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        resultados = new JTable();
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
    }
    
    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch(column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Nombre");
        tm.addColumn("Director");
        tm.addColumn("País");
        tm.addColumn("Clasificación");
        tm.addColumn("Año");
        tm.addColumn("En proyección");
        
        MovieDao md = new MovieDao();
        ArrayList<Movie> movies = md.readAll();
        
        for (Movie mo : movies) {
            tm.addRow(new Object[]{mo.getNombre(),mo.getDirector(),mo.getPais(),mo.getClasificacion(),mo.getAño(),mo.getEn_proyeccion()});
        }
        resultados.setModel(tm);
    }
    
    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieDao md = new MovieDao();
                Movie m = new Movie(nombre.getText(), director.getText(), pais.getText(), clasificacion.getSelectedItem().toString(), Integer.parseInt(año.getText()), Integer.parseInt(enProyec.getText()), true);
                
                if (no.isSelected()) {
                    m.setProyeccion(false);
                }
                if (md.create(m)) {
                    JOptionPane.showMessageDialog(null, "Pelicula registrada con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "La regaste :v");
                }
            }   
        }); 
        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieDao md = new MovieDao();
                Movie m = new Movie(nombre.getText(), director.getText(), pais.getText(), clasificacion.getSelectedItem().toString(), Integer.parseInt(año.getText()), Integer.parseInt(enProyec.getText()), true);
                
                if (no.isSelected()) {
                    m.setProyeccion(false);
                }
                if (md.update(m)) {
                    JOptionPane.showMessageDialog(null, "Pelicula registrada con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "La regaste :v");
                }
            }   
        }); 
        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieDao md = new MovieDao();
                if (md.delete(nombre.getText())) {
                    JOptionPane.showMessageDialog(null, "Se elimino");
                    limpiarCampos();
                    llenarTabla();
                }
                else {
                    JOptionPane.showMessageDialog(null, "La regaste :v");
                }
            }   
        }); 
       buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieDao md = new MovieDao();
                Movie m = md.read(nombre.getText());
                
                if (m == null) {
                    JOptionPane.showMessageDialog(null, "No esta weon");
                } else {
                    nombre.setText(m.getNombre());
                    director.setText(m.getDirector());
                    pais.setText(m.getPais());
                    clasificacion.setSelectedItem(m.getClasificacion());
                    año.setText(Integer.toString(m.getAño()));
                    if (m.getProyeccion()) {
                    si.setSelected(true);
                    }else {
                    no.setSelected(false);
                    }
                }                
            }   
        });
    }
    public void limpiarCampos() {
        nombre.setText("");
        director.setText("");
        pais.setText("");
        año.setText("");
        clasificacion.setSelectedItem("");
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable(){
        @Override
        public void run() {
            new Consulta().setVisible(true);
        }
    });
    }
}