package modelo;

import java.io.Serializable;
import java.util.Date;


public class Nota implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int id;
    private int tareaId;
    private String estudiante;
    private String materia;
    private String titulo;
    private String tipo;
    private double nota;

    public Nota() {}

    public Nota(int id, int tareaId, String estudiante, String materia, String titulo, String tipo, double nota) {
        this.id = id;
        this.tareaId = tareaId;
        this.estudiante = estudiante;
        this.materia = materia;
        this.titulo = titulo;
        this.tipo = tipo;
        this.nota = nota;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getTareaId() { return tareaId; }
    public void setTareaId(int tareaId) { this.tareaId = tareaId; }

    public String getEstudiante() { return estudiante; }
    public void setEstudiante(String estudiante) { this.estudiante = estudiante; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }
}
