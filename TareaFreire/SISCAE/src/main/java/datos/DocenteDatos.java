package datos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelo.Docente;

public class DocenteDatos {
    
    private static ArrayList<Docente> lista = new ArrayList<>();
    private static int idAuto = 1;

    private final String archivo = "Docente.dat";

    public DocenteDatos() {
        lista = cargar(); // Primero intenta cargar archivo

        if (lista.isEmpty()) {
            // Datos quemados solo si el archivo no existe
            //lista.add(new Docente(idAuto++, "Juan", "Mendoza", "0123456789", "juan.mendoza@instituto.edu.ec", "0987654321", "1er año EGB", "ACTIVO"));
            guardar();
        } else {
            idAuto = lista.get(lista.size() - 1).getId() + 1;
        }
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
    private ArrayList<Docente> cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (ArrayList<Docente>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No se pudo cargar archivo, se usarán datos quemados...");
            return new ArrayList<>();
        }
    }

    // ---------- REGISTRAR ----------
    public boolean registrar(Docente e) {
        e.setId(idAuto++);
        e.setEstado("Activo");
        lista.add(e);
        guardar();
        return true;
    }

    // ---------- EDITAR ----------
    public boolean editar(Docente e) {
        for (Docente est : lista) {
            if (est.getId() == e.getId()) {
                est.setNombres(e.getNombres());
                est.setApellidos(e.getApellidos());
                est.setCorreo(e.getCorreo());
                est.setTelefono(e.getTelefono());
                est.setEspecialidad(e.getEspecialidad());
                guardar();
                return true;
            }
        }
        return false;
    }

    // ---------- ELIMINAR ----------
    public boolean eliminar(int id) {
        for (Docente est : lista) {
            if (est.getId() == id) {
                est.setEstado("INACTIVO");
                guardar();
                return true;
            }
        }
        return false;
    }

    // ---------- LISTAR ----------
    public ArrayList<Docente> listar() {
        return lista;
    }

    // ---------- BUSCAR ----------
    public ArrayList<Docente> buscar(String filtro) {
        ArrayList<Docente> temporal = new ArrayList<>();
        filtro = filtro.toLowerCase();
        for (Docente e : lista) {
            if (e.getNombres().toLowerCase().contains(filtro) ||
                e.getApellidos().toLowerCase().contains(filtro) ||
                e.getEspecialidad().toLowerCase().contains(filtro) ||
                e.getEstado().toLowerCase().contains(filtro)) {
                temporal.add(e);
            }
        }
        return temporal;
    }
}
