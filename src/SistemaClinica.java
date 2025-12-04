import java.util.ArrayList;
import java.util.HashMap;

public class SistemaClinica {
    private HashMap<String, Turno> turnos;
    // codigo turnos / turno.
    private HashMap<Integer, String> consultoriosOcupados;
    // numero consult /// codigo del turno
    static final int TOTAL_CONSULTORIOS = 20;

    public SistemaClinica() {
        this.turnos = new HashMap<>();
        this.consultoriosOcupados = new HashMap<>();
    }

    public void registrarTurno(Turno turno) throws IllegalStateException {
        int numConsul = turno.getNumeroConsultorio();
        if (consultoriosOcupados.containsKey(numConsul)) {
            throw new IllegalStateException("El consultorio" + numConsul + " ya esta ocupado");
        }
        turnos.put(turno.getCodigoTurno(), turno);
        consultoriosOcupados.put(numConsul, turno.getCodigoTurno());
    }

    public void confirmarTurno(String codigoTurno) throws TurnoNoEncontradoException {

        Turno t = turnos.get(codigoTurno);

        if (codigoTurno == null) {
            throw new TurnoNoEncontradoException("Turno " + codigoTurno + " no encontrado");
        }

        t.setEstado(EstadoTurno.CONFIRMADO);

    }

    public void registrarIngresoPaciente(String codigoTurno) throws TurnoNoEncontradoException{
        Turno t = turnos.get(codigoTurno);
        if (!turnos.containsKey(codigoTurno)) {
            throw new TurnoNoEncontradoException("El consultorio " + codigoTurno + " ya esta asignado a otro turno");
            
        }
        t.setEstado(EstadoTurno.EN_CURSO);
    }

    public void finalizarTurno(String codigoTurno) throws TurnoNoEncontradoException {

        if (!turnos.containsKey(codigoTurno)) {
            throw new TurnoNoEncontradoException("Turno no existente");
        }

        Turno t = turnos.get(codigoTurno);
        t.setEstado(EstadoTurno.FINALIZADO);
        consultoriosOcupados.remove(t.getNumeroConsultorio());
    }

    public void cancelarTurno(String codigoTurno) throws TurnoNoEncontradoException {
        if (!turnos.containsKey(codigoTurno)) {
            throw new TurnoNoEncontradoException("No existe el turno.");
        }
        Turno t = turnos.get(codigoTurno);
        t.setEstado(EstadoTurno.CANCELADO);
        consultoriosOcupados.remove(t.getNumeroConsultorio());
    }

    public Turno buscarTurnoPorCodigo(String codigoTurno) throws TurnoNoEncontradoException {
        if (codigoTurno == null) {
            throw new TurnoNoEncontradoException("Turno" + codigoTurno + " no encontrado");
        }
        Turno t = turnos.get(codigoTurno);
        return t;
    }

    public ArrayList<EstadoTurno> obtenerEstadoDeTurnos(EstadoTurno estado){
        ArrayList<EstadoTurno> arraydeturnos = new ArrayList<>();

        for(Turno t : turnos.values()){
            if ( t.getEstado() == estado) {
                arraydeturnos.add(estado);
            }
        }
        return arraydeturnos;
    }

    public String generarReporteOcupacion(){
        int  totalTurnos = turnos.size();
        int consultoriosOcup = consultoriosOcupados.size();
        double ocupacion = (consultoriosOcup / TOTAL_CONSULTORIOS) * 100;
        return "Total turnos: " + totalTurnos + "\n" +
            "Consultorios ocupados: " + consultoriosOcup + "\n" +
            "Ocupacion: " + ocupacion + "%";
    }
}
