package modelo;

import java.io.Serializable;

public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String nombres;
    private String apellidos;
    private String cedula;
    private String correo;
    private String telefono;
    private int materiaId;
    private int docenteId;
    private String nombreMateria;
    private String nombreDocente;
    private String curso;
    private String estado;

    public Estudiante() {}

    public Estudiante(int id, String nombres, String apellidos, String cedula, 
                      String correo, String telefono, int materiaId, int docenteId, 
                      String nombreMateria, String nombreDocente, String curso, String estado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
        this.materiaId = materiaId;
        this.docenteId = docenteId;
        this.nombreMateria = nombreMateria;
        this.nombreDocente = nombreDocente;
        this.curso = curso;
        this.estado = estado;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getMateriaId() {
        return materiaId;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public String getCurso() {
        return curso;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return nombres; // 🔑 esto es clave para el ComboBox
    }
    
}