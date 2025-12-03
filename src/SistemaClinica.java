import java.util.HashMap;

public class SistemaClinica {
    private HashMap<String, Turno> turnos;
    // codigo turnos / turno.
    private HashMap<Integer, String> consultoriosOcupados;
    // numero consult /// codigo del turno
    static final int TOTAL_CONSULTORIOS = 20;
    public SistemaClinica(){
        this.turnos = new HashMap<>();
        this.consultoriosOcupados = new HashMap<>();
    }
    public void registrarTurno(Turno turno) throws IllegalStateException {
        int numConsul = turno.getNumeroConsultorio();
        if (consultoriosOcupados.containsKey(numConsul)) {
            throw new IllegalStateException("El consultorio" + numConsul + " ya esta ocupado" );
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
        if (turnos.containsKey(codigoTurno)) {
            throw new TurnoNoEncontradoException("El consultorio " + codigoTurno + " ya esta asignado a otro turno")
            
        }
        t.setEstado(EstadoTurno.EN_CURSO);
    }
}
