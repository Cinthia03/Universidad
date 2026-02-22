package negocio;

import datos.DocenteDatos;
import java.util.ArrayList;
import modelo.Docente;


public class DocenteNegocio {
    
    DocenteDatos datos = new DocenteDatos();
    
    // REGISTRO – Validaciones RF-1
    public String registrar(Docente e) {

        if (!e.getNombres().matches("[a-zA-Z ]{1,100}"))
            return "Nombres inválidos";

        if (!e.getApellidos().matches("[a-zA-Z ]{1,100}"))
            return "Apellidos inválidos";

        if (!e.getCedula().matches("\\d{10}"))
            return "Cédula inválida (10 dígitos)";

        if (!e.getTelefono().matches("\\d{10}"))
            return "Teléfono inválido (10 dígitos)";

        if (!e.getCorreo().endsWith("@instituto.edu.ec"))
            return "Correo institucional inválido: Ejemplo@instituto.edu.ec";

        datos.registrar(e);
        return "OK";
    }

    // EDICIÓN – RF-2
    public String editar(Docente e) {

        if (!e.getCorreo().endsWith("@instituto.edu.ec"))
            return "Correo institucional inválido: Ejemplo@instituto.edu.ec";

        if (!e.getTelefono().matches("\\d{10}"))
            return "Teléfono inválido";

        boolean ok = datos.editar(e);
        return ok ? "OK" : "Error al editar";
    }

    // ELIMINACIÓN – RF-3
    public boolean eliminar(int id) {
        return datos.eliminar(id);
    }

    // LISTAR – RF-4
    public ArrayList<Docente> listar() {
        return datos.listar();
    }

    public ArrayList<Docente> buscar(String filtro) {
        return datos.buscar(filtro);
    }
}