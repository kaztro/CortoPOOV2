package Modelo;

public class Movie {
    private int id;
    private String nombre;
    private String director;
    private String pais;
    private String clasificacion;
    private int año;
    private int en_proyeccion;
    private boolean proyeccion;

    public Movie() {

    }

    public Movie(int id, String nombre, String director, String pais, String clasificacion, int año, int en_proyeccion, boolean proyeccion) {
        this.id = id;
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.año = año;
        this.en_proyeccion = en_proyeccion;
        this.proyeccion = proyeccion;
    }

    public Movie(String nombre, String director, String pais, String clasificacion, int año, int en_proyeccion, boolean proyeccion) {
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.año = año;
        this.en_proyeccion = en_proyeccion;
        this.proyeccion = proyeccion;
    }

    public Movie(String director, String pais, String clasificacion, int año, boolean proyeccion) {
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.año = año;
        this.proyeccion = proyeccion;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getEn_proyeccion() {
        return en_proyeccion;
    }

    public void setEn_proyeccion(int en_proyeccion) {
        this.en_proyeccion = en_proyeccion;
    }

    public boolean isProyeccion() {
        return proyeccion;
    }

    public void setProyeccion(boolean proyeccion) {
        this.proyeccion = proyeccion;
    }
    
    public boolean getProyeccion() {
        return proyeccion;
    }



    
}
