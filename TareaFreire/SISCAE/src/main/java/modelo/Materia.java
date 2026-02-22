package modelo;

import java.io.Serializable;

public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombreMateria;
    private String descripcion;
    private int docenteId;
    private String docenteInfo; // 👈 SOLO PARA MOSTRAR
    private String curso;
    private String estado;

    public Materia() {
    }

    public Materia(int id, String nombreMateria, String descripcion,
                   int docenteId, String docenteInfo, String curso, String estado) {
        this.id = id;
        this.nombreMateria = nombreMateria;
        this.descripcion = descripcion;
        this.docenteId = docenteId;
        this.docenteInfo = docenteInfo;
        this.curso = curso;
        this.estado = estado;
    }

    // GETTERS Y SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreMateria() { return nombreMateria; }
    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDocenteId() { return docenteId; }
    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }

    public String getDocenteInfo() { return docenteInfo; }
    public void setDocenteInfo(String docenteInfo) {
        this.docenteInfo = docenteInfo;
    }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

