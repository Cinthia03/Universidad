package negocio;

import datos.NotaDatos;
import datos.TareaDatos;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import modelo.Tarea;

public class TareaNegocio {

    private final TareaDatos datos = new TareaDatos();
    private final NotaDatos notaDatos = new NotaDatos();

    // ================= REGISTRAR =================
    public String registrar(Tarea t) {

        if (t.getTitulo().isEmpty())
            return "El título es obligatorio";

        if (t.getDescripcion().isEmpty())
            return "La descripción es obligatoria";

        if (t.getFechaEntrega() == null)
            return "Debe seleccionar fecha de entrega";
        // 🔴 NORMALIZAR FECHAS (QUITAR HORA)
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.HOUR_OF_DAY, 0);
        hoy.set(Calendar.MINUTE, 0);
        hoy.set(Calendar.SECOND, 0);
        hoy.set(Calendar.MILLISECOND, 0);

        Calendar entrega = Calendar.getInstance();
        entrega.setTime(t.getFechaEntrega());
        entrega.set(Calendar.HOUR_OF_DAY, 0);
        entrega.set(Calendar.MINUTE, 0);
        entrega.set(Calendar.SECOND, 0);
        entrega.set(Calendar.MILLISECOND, 0);

        if (entrega.before(hoy))
            return "La fecha no puede ser anterior a hoy";

        datos.registrar(t);
        return "OK";
    }

    // ================= EDITAR =================
    public String editar(Tarea t) {
        boolean ok = datos.editar(t);
        return ok ? "OK" : "Error al editar";
    }

    // ================= ELIMINAR =================
    public String eliminar(int idTarea) {
        if (notaDatos.existeNotaPorTarea(idTarea)) {
            return "NO_SE_PUEDE_ELIMINAR";
        }
        return datos.eliminar(idTarea) ? "OK" : "ERROR";
    }
    
    // ================= LISTAR =================
    public ArrayList<Tarea> listar() {
        return datos.listar();
    }

    // ================= BUSCAR =================
    public ArrayList<Tarea> buscar(String filtro) {
        return datos.buscar(filtro);
    }
}
