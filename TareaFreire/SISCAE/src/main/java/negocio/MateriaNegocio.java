package negocio;

import datos.MateriaDatos;
import java.util.ArrayList;
import modelo.Materia;

public class MateriaNegocio {

    MateriaDatos datos = new MateriaDatos();

    public String registrar(Materia m) {

        if (m.getNombreMateria().length() < 3)
            return "Nombre de materia demasiado corto";

        if (m.getCurso().isEmpty())
            return "Debe seleccionar un curso";

        if (m.getDocenteId() == 0)
            return "Debe seleccionar un docente";

        datos.registrar(m);
        return "OK";
    }

    public String editar(Materia m) {

        if (m.getDocenteId() == 0)
            return "Debe seleccionar un docente";

        boolean ok = datos.editar(m);
        return ok ? "OK" : "Error al editar";
    }

    public void eliminarDefinitivo(int id) {
        datos.eliminarDefinitivo(id);
    }

    public ArrayList<Materia> listar() {
        return datos.listar();
    }

    public ArrayList<Materia> buscar(String f) {
        return datos.buscar(f);
    }
}
