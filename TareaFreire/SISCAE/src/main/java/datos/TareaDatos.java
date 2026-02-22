package datos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import modelo.Tarea;



public class TareaDatos {

    private final String ARCHIVO = "tareas.dat";
    //private ArrayList<Tarea> lista;
    private ArrayList<Tarea> lista = new ArrayList<>();
    private static int idAuto = 1;

    public TareaDatos() {
        lista = cargar();
    }

    // =========================
    // GUARDAR ARCHIVO
    // =========================
    private void guardarArchivo() {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // CARGAR ARCHIVO
    // =========================
    private ArrayList<Tarea> cargar() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (ArrayList<Tarea>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // =========================
    // REGISTRAR
    // =========================
    public boolean registrar(Tarea t) {
        t.setId(generarId());
        t.setEstado("ACTIVA");
        lista.add(t);
        guardarArchivo();
        return true;
    }

    // =========================
    // EDITAR
    // =========================
    public boolean editar(Tarea t) {
        for (Tarea aux : lista) {
            if (aux.getId() == t.getId()) {
                aux.setEstudianteId(t.getEstudianteId());
                aux.setNombreMateria(t.getNombreMateria());
                aux.setNombreDocente(t.getNombreDocente());
                aux.setNombreEstudiante(t.getNombreEstudiante());
                aux.setCurso(t.getCurso());
                aux.setTitulo(t.getTitulo());
                aux.setDescripcion(t.getDescripcion());
                aux.setTipo(t.getTipo());
                aux.setFechaEntrega(t.getFechaEntrega());
                guardarArchivo();
                return true;
            }
        }
        return false;
    }

    // =========================
    // ELIMINAR (CORREGIDO)
    // =========================
    public boolean eliminar(int id) {
        boolean eliminado = lista.removeIf(t -> t.getId() == id);
        if (eliminado) {
            guardarArchivo(); // ✅ CORRECTO
        }
        return eliminado;
    }

    // =========================
    // LISTAR
    // =========================
    public ArrayList<Tarea> listar() {
        return new ArrayList<>(lista); // buena práctica
    }

    // =========================
    // BUSCAR
    // =========================
    public ArrayList<Tarea> buscar(String filtro) {
        ArrayList<Tarea> temp = new ArrayList<>();
        filtro = filtro.toLowerCase();

        for (Tarea t : lista) {
            if (t.getTitulo().toLowerCase().contains(filtro)
                    || t.getNombreMateria().toLowerCase().contains(filtro)
                    || t.getNombreDocente().toLowerCase().contains(filtro)
                    || t.getTipo().toLowerCase().contains(filtro)
                    || t.getEstado().toLowerCase().contains(filtro)) {
                temp.add(t);
            }
        }
        return temp;
    }

    // =========================
    // ID AUTOMÁTICO
    // =========================
    private int generarId() {
        int max = 0;
        for (Tarea t : lista) {
            if (t.getId() > max) {
                max = t.getId();
            }
        }
        return max + 1;
    }
}