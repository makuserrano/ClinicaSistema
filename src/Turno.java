import java.time.LocalDate;

public class Turno {
    private String codigoTurno;
    private String nombrePaciente;
    private String dniPaciente;
    private String especialidad;
    private LocalDate fecha;
    private String hora;
    private EstadoTurno estado;
    private int numeroConsultorio;


    public Turno(String codigoTurno, String nombrePaciente, String dniPaciente,int numeroConsultorio ,String especialidad, LocalDate fecha,
            String hora) {
        this.codigoTurno = codigoTurno;
        this.nombrePaciente = nombrePaciente;
        this.dniPaciente = dniPaciente;
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = EstadoTurno.PENDIENTE;
        this.numeroConsultorio = numeroConsultorio;
    }

    public String getCodigoTurno() {
        return codigoTurno;
    }

    public void setCodigoTurno(String codigoTurno) {
        this.codigoTurno = codigoTurno;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public int getNumeroConsultorio() {
        return numeroConsultorio;
    }

    public void setNumeroConsultorio(int numeroConsultorio) {
        this.numeroConsultorio = numeroConsultorio;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "codigoTurno='" + codigoTurno + '\'' +
                ", nombrePaciente='" + nombrePaciente + '\'' +
                ", dniPaciente='" + dniPaciente + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", estado=" + estado +
                '}';
    }

}
