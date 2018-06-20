package dao;

import Conexion.Conexion;
import Interfaces.metodos;
import Modelo.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieDao implements metodos<Movie> {
    private static final String SQL_INSERT = "INSERT INTO movie(nombre, director, pais, clasificacion, anio, en_proyeccion) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE movie SET director = ?, pais = ?, clasificacion = ?, anio = ?, en_proyeccion = ? WHERE nombre = ?";
    private static final String SQL_DELETE = "DELETE FROM movie WHERE nombre = ?";
    private static final String SQL_READ = "SELECT * FROM movie WHERE nombre = ?";
    private static final String SQL_READALL = "SELECT * FROM movie";

    private static final Conexion con = Conexion.conectar();

    @Override
    public boolean create(Movie g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNombre());
            ps.setString(2, g.getDirector());
            ps.setString(3, g.getPais());
            ps.setString(3, g.getClasificacion());
            ps.setDate(5, g.getAño());
            ps.setInt(6, g.getEn_proyeccion());
            
            if(ps.executeUpdate() > 0) {
                return true;
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
       PreparedStatement ps;
       try {
           ps = con.getCnx().prepareStatement(SQL_DELETE);
           ps.setString(1, key.toString());
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { con.cerrarConexion(); }
        return false;
    }

    @Override
    public boolean update(Movie c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getNombre());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getDirector());
            ps.setString(2, c.getPais());
            ps.setString(3, c.getClasificacion());
            ps.setDate(4, c.getAño());
            ps.setInt(5, c.getEn_proyeccion());
            if(ps.executeUpdate() > 0) { return true; }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { con.cerrarConexion(); }
        return false;
    }

    @Override
    public Movie read(Object key) {
        Movie m = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
          
            while(rs.next()) { m = new Movie (rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getInt(6), rs.getBoolean(7)); }
            rs.close();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally { con.cerrarConexion(); }
        return m;
    }

    @Override
    public ArrayList<Movie> readAll() {
ArrayList<Movie> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL); 
             while(rs.next()) {all.add(new Movie(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getInt(7), rs.getBoolean(8)));}
        }catch (SQLException ex) {
            Logger.getLogger(MovieDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }   
}
