package negocio;

import datos.NotaDatos;
import java.util.ArrayList;
import modelo.Nota;

public class NotaNegocio {

    private final NotaDatos datos = new NotaDatos();

    public String registrar(Nota n) {

        if (n.getNota() < 0 || n.getNota() > 10)
            return "La nota debe estar entre 0 y 10";

        if (datos.existeNota(n.getEstudiante(), n.getMateria(), n.getTipo(), n.getTitulo()))
            return "Esta asignación ya fue calificada";

        datos.registrar(n);
        return "OK";
    }

    public void editar(Nota n) {
        datos.editar(n);
    }

    public ArrayList<Nota> listar() {
        return datos.listar();
    }
    
    public boolean existeNotaPorTarea(int idTarea) {
        return datos.existeNotaPorTarea(idTarea);
    }

    // 🔹 PROMEDIO PONDERADO CORRECTO
    public double calcularPromedio(String estudiante) {
        double total = 0;

        for (Nota n : datos.listarPorEstudiante(estudiante)) {
            switch (n.getTipo()) {
                case "EXAMEN": total += n.getNota() * 0.40; break;
                case "LECCION": total += n.getNota() * 0.20; break;
                case "PROYECTO": total += n.getNota() * 0.30; break;
                case "FORO":
                case "TALLER":
                case "TAREA":
                case "FORO/TALLER/TAREA": total += n.getNota() * 0.10; break;
            }
        }
        return total;
    }
}
