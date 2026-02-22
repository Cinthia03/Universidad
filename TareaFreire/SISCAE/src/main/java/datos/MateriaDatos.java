package datos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelo.Materia;

public class MateriaDatos {

    private static ArrayList<Materia> lista = new ArrayList<>();
    private static int idAuto = 1;

    private final String archivo = "materias.dat";

    public MateriaDatos() {
        lista = cargar();

        if (!lista.isEmpty()) {
            idAuto = lista.get(lista.size() - 1).getId() + 1;
        } else {
            guardar();
        }
    }

    // =========================
    // GUARDAR EN ARCHIVO
    // =========================
    private void guardar() {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        } catch (Exception e) {
            System.out.println("Error guardando materias: " + e.getMessage());
        }
    }

    // =========================
    // CARGAR ARCHIVO
    // =========================
    private ArrayList<Materia> cargar() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(archivo))) {
            return (ArrayList<Materia>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No se pudo cargar archivo de materias...");
            return new ArrayList<>();
        }
    }

    // =========================
    // REGISTRAR
    // =========================
    public boolean registrar(Materia m) {
        m.setId(idAuto++);
        m.setEstado("ACTIVA");
        lista.add(m);
        guardar();
        return true;
    }

    // =========================
    // EDITAR
    // =========================
    public boolean editar(Materia m) {
        for (Materia mat : lista) {
            if (mat.getId() == m.getId()) {
                mat.setNombreMateria(m.getNombreMateria());
                mat.setDescripcion(m.getDescripcion());
                mat.setDocenteId(m.getDocenteId());
                mat.setDocenteInfo(m.getDocenteInfo());
                mat.setCurso(m.getCurso());
                guardar();
                return true;
            }
        }
        return false;
    }

    // =========================
    // LISTAR
    // =========================
    public ArrayList<Materia> listar() {
        return lista;
    }

    // =========================
    // BUSCAR
    // =========================
    public ArrayList<Materia> buscar(String filtro) {
        filtro = filtro.toLowerCase();
        ArrayList<Materia> temp = new ArrayList<>();

        for (Materia m : lista) {
            if (m.getNombreMateria().toLowerCase().contains(filtro)
                    || m.getCurso().toLowerCase().contains(filtro)
                    || m.getEstado().toLowerCase().contains(filtro)) {
                temp.add(m);
            }
        }
        return temp;
    }

    // =========================
    // ELIMINAR DEFINITIVO ✅
    // =========================
    public void eliminarDefinitivo(int id) {
        lista.removeIf(m -> m.getId() == id);
        guardar();
    }
}