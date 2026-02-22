package negocio;

import java.util.ArrayList;
import modelo.Usuario;

public class UsuarioNegocio {
    // Datos quemados
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    static {
        usuarios.add(new Usuario("admin", "12345", "ADMIN"));
        usuarios.add(new Usuario("docente", "12345", "DOCENTE"));
        usuarios.add(new Usuario("estudiante", "12345", "ESTUDIANTE"));
    }

    public Usuario login(String user, String pass) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equalsIgnoreCase(user)
                && u.getContraseña().equals(pass)) {
                return u;
            }
        }
        return null;
    }

    public boolean registrar(String usuario, String pass, String rol) {
        // Validar si ya existe
        for (Usuario u : usuarios) {
            if (u.getUsuario().equalsIgnoreCase(usuario)) {
                return false; // Usuario ya existe
            }
        }

        usuarios.add(new Usuario(usuario, pass, rol));
        return true;
    }

    public ArrayList<Usuario> listar() {
        return usuarios;
    }
}