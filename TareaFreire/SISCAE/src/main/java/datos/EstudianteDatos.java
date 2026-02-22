package datos;

import java.io.*;
import java.util.ArrayList;
import modelo.Estudiante;

public class EstudianteDatos {

    private static ArrayList<Estudiante> lista = new ArrayList<>();
    private static int idAuto = 1;

    private final String archivo = "estudiantes.dat";

    public EstudianteDatos() {
        lista = cargar(); // Primero intenta cargar archivo
    }

    // ---------- GUARDAR EN ARCHIVO ----------
    private void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        } catch (Exception e) {
            System.out.println("Error guardando archivo: " + e.getMessage());
        }
    }

    // ---------- CARGAR ARCHIVO ----------
    private ArrayList<Estudiante> cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (ArrayList<Estudiante>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No se pudo cargar archivo, se usarán datos quemados...");
            return new ArrayList<>();
        }
    }

    // ---------- REGISTRAR ----------
    public boolean registrar(Estudiante e) {
        e.setId(idAuto++);
        e.setEstado("Activo");
        lista.add(e);
        guardar();
        return true;
    }

    // ---------- EDITAR ----------
    public boolean editar(Estudiante e) {
        for (Estudiante est : lista) {
            if (est.getId() == e.getId()) {
                est.setNombres(e.getNombres());
                est.setApellidos(e.getApellidos());
                est.setCorreo(e.getCorreo());
                est.setTelefono(e.getTelefono());
                est.setMateriaId(e.getMateriaId());
                est.setNombreMateria(e.getNombreMateria());
                est.setDocenteId(e.getDocenteId());
                est.setNombreDocente(e.getNombreDocente());
                est.setCurso(e.getCurso());
                guardar();
                return true;
            }
        }
        return false;
    }

    // ---------- ELIMINAR ----------
    public boolean eliminar(int id) {
        for (Estudiante est : lista) {
            if (est.getId() == id) {
                est.setEstado("INACTIVO");
                guardar();
                return true;
            }
        }
        return false;
    }

    // ---------- LISTAR ----------
    public ArrayList<Estudiante> listar() {
        return lista;
    }

    // ---------- BUSCAR ----------
    public ArrayList<Estudiante> buscar(String filtro) {
        ArrayList<Estudiante> temporal = new ArrayList<>();
        filtro = filtro.toLowerCase();
        for (Estudiante e : lista) {
            if (e.getNombres().toLowerCase().contains(filtro) ||
                e.getApellidos().toLowerCase().contains(filtro) ||
                e.getNombreMateria().toLowerCase().contains(filtro)||
                e.getNombreDocente().toLowerCase().contains(filtro)||
                e.getEstado().toLowerCase().contains(filtro)) {
                temporal.add(e);
            }
        }
        return temporal;
    }
}
