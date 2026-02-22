package modelo;

import java.io.Serializable;
import java.util.Date;

public class Tarea implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int estudianteId;
    private String nombreMateria;
    private String nombreDocente;
    private String nombreEstudiante;
    private String curso;
    private String titulo;
    private String descripcion;
    private String tipo;
    private Date fechaEntrega;
    private String estado;

    public Tarea() {}

    public Tarea(int id, int estudianteId, String nombreMateria, String nombreDocente, String nombreEstudiante,
            String curso, String titulo, String descripcion, String tipo, Date fechaEntrega, String estado) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.nombreMateria = nombreMateria;
        this.nombreDocente = nombreDocente;
        this.nombreEstudiante = nombreEstudiante;
        this.curso = curso;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
    }

    // ================= GETTERS & SETTERS =================
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }
    
    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }
    
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return titulo;
    }
}