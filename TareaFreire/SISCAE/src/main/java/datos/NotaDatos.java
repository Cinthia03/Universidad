package datos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelo.Nota;


public class NotaDatos {

    private ArrayList<Nota> lista;
    private static int idAuto = 1;
    private final String archivo = "notas.dat";

    public NotaDatos() {
        lista = cargar();
        if (!lista.isEmpty()) {
            idAuto = lista.get(lista.size() - 1).getId() + 1;
        }
    }

    private void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        } catch (Exception e) {
            System.out.println("Error guardando notas");
        }
    }

    private ArrayList<Nota> cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (ArrayList<Nota>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void registrar(Nota n) {
        n.setId(idAuto++);
        lista.add(n);
        guardar();
    }

    public void editar(Nota n) {
        for (Nota x : lista) {
            if (x.getId() == n.getId()) {
                x.setEstudiante(n.getEstudiante());
                x.setMateria(n.getMateria());
                x.setTipo(n.getTipo());
                x.setTitulo(n.getTitulo());
                x.setNota(n.getNota());

                guardar();
                return;
            }
        }
    }

    public ArrayList<Nota> listar() {
        return lista;
    }
    
    public boolean existeNotaPorTarea(int tareaId) {
        for (Nota n : lista) {
            if (n.getTareaId() == tareaId) {
                return true;
            }
        }
        return false;
    }

    public boolean existeNota(String est, String mat, String tipo, String titulo) {
        return lista.stream().anyMatch(n ->
            n.getEstudiante().equals(est) &&
            n.getMateria().equals(mat) &&
            n.getTipo().equals(tipo) &&
            n.getTitulo().equals(titulo)
        );
    }

    public ArrayList<Nota> listarPorEstudiante(String estudiante) {
        ArrayList<Nota> temp = new ArrayList<>();
        for (Nota n : lista) {
            if (n.getEstudiante().equals(estudiante)) {
                temp.add(n);
            }
        }
        return temp;
    }
}