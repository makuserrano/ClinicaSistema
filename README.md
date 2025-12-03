EJERCICIO – Sistema de Gestión de Turnos Médicos

1) Enum EstadoTurno

Crear un enum con los siguientes valores:

PENDIENTE   – Turno creado pero aún no confirmado
CONFIRMADO  – Turno confirmado
EN_CURSO    – Paciente ingresó al consultorio
FINALIZADO  – Atención finalizada
CANCELADO   – Turno cancelado


2) Clase Turno

Atributos privados:

- String codigoTurno
- String nombrePaciente
- String dniPaciente
- String especialidad
- int numeroConsultorio
- LocalDate fecha        (fecha del turno médico)
- String hora            (por ejemplo "15:30")
- EstadoTurno estado

Constructor:

Debe recibir: codigoTurno, nombrePaciente, dniPaciente, especialidad,
numeroConsultorio, fecha, hora.

Reglas:
- El estado inicial de todo turno debe ser PENDIENTE.

Métodos obligatorios:
- Getters para todos los atributos.
- Setter SOLO para el atributo estado.
- Método toString() que devuelva EXACTAMENTE el siguiente formato:

  "Código: [codigo] | Paciente: [nombre] | DNI: [dni] | Especialidad: [especialidad] | Consultorio: [consultorio] | Fecha: [fecha] | Hora: [hora] | Estado: [estado]"


3) Excepción TurnoNoEncontradoException

Requisitos:
- Debe ser una excepción verificada (checked), es decir, extender de Exception.
- Debe tener un constructor que reciba un String mensaje y lo pase a super.


4) Clase SistemaClinica

Atributos privados:

- HashMap<String, Turno> turnos
    Clave: código de turno (String)
    Valor: objeto Turno

- HashMap<Integer, String> consultoriosOcupados
    Clave: número de consultorio (int)
    Valor: código del turno que está usando ese consultorio (String)

Constante:
- static final int TOTAL_CONSULTORIOS = 20;

Constructor:
- Debe inicializar ambos HashMaps vacíos.


5) Métodos a implementar en SistemaClinica


5.1 registrarTurno(Turno turno)

Reglas:
- Obtener el número de consultorio desde el objeto Turno (turno.getNumeroConsultorio()).
- Verificar si ese consultorio YA está en consultoriosOcupados.
  - Si ya está ocupado, lanzar:
    IllegalStateException("El consultorio " + numeroConsultorio + " ya está ocupado")
- Si el consultorio está libre:
  - Agregar el turno al HashMap turnos usando como clave el código del turno.
  - Agregar en consultoriosOcupados una entrada:
    clave = numeroConsultorio, valor = codigoTurno.

-----------------------------------------
5.2 confirmarTurno(String codigoTurno) throws TurnoNoEncontradoException

Reglas:
- Buscar el turno en el HashMap turnos usando el código.
- Si el turno no existe (es null), lanzar:
  new TurnoNoEncontradoException("Turno " + codigoTurno + " no encontrado")
- Si existe, cambiar su estado a CONFIRMADO.

------------------------------------------
5.3 registrarIngresoPaciente(String codigoTurno) throws TurnoNoEncontradoException

Reglas:
- Buscar el turno en el HashMap.
- Si no existe, lanzar TurnoNoEncontradoException.
- Si existe, cambiar su estado a EN_CURSO.
- El consultorio permanece ocupado (NO se modifica consultoriosOcupados).

-------------------------------------------
5.4 finalizarTurno(String codigoTurno) throws TurnoNoEncontradoException

Reglas:
- Buscar el turno en el HashMap.
- Si no existe, lanzar TurnoNoEncontradoException.
- Si existe:
  - Cambiar su estado a FINALIZADO.
  - Obtener el número de consultorio desde el turno.
  - Liberar el consultorio removiéndolo del HashMap consultoriosOcupados
    (consultoriosOcupados.remove(numeroConsultorio)).

--------------------------------------------
5.5 cancelarTurno(String codigoTurno) throws TurnoNoEncontradoException

Reglas:
- Buscar el turno en el HashMap.
- Si no existe, lanzar TurnoNoEncontradoException.
- Si existe:
  - Cambiar su estado a CANCELADO.
  - Obtener el número de consultorio desde el turno.
  - Liberar el consultorio removiéndolo de consultoriosOcupados (si estaba ocupado).

-------------------------------------------
5.6 buscarTurnoPorCodigo(String codigoTurno) throws TurnoNoEncontradoException

Reglas:
- Buscar el turno en el HashMap turnos.
- Si el turno no existe, lanzar:
  new TurnoNoEncontradoException("Turno " + codigoTurno + " no encontrado")
- Si existe, retornarlo.

--------------------------------------------
5.7 obtenerEstadosDeTurnos(EstadoTurno estado)

Este método NO debe devolver una lista de turnos, sino un arreglo de enums.

Firma sugerida:
- public EstadoTurno[] obtenerEstadosDeTurnos(EstadoTurno estado)

Reglas:
- Recorrer todos los turnos almacenados en el HashMap turnos.
- Seleccionar únicamente aquellos cuya propiedad estado sea igual al estado recibido por parámetro.
- Por cada coincidencia, agregar el valor de estado a una colección temporal.
- Al final, devolver un arreglo de EstadoTurno (EstadoTurno[]) con todos los estados encontrados.
- Si no hay turnos con ese estado, devolver un arreglo vacío (de longitud 0).

(No se pide devolver los turnos, solo los estados. El objetivo es practicar el manejo de enums y conversión a arreglos.)

-------------------------------------------
5.8 generarReporteOcupacion()

Reglas:
- Debe devolver un String con el siguiente formato EXACTO:

  "Total turnos: X
  Consultorios ocupados: Y
  Ocupación: Z%"

  donde:
  - X = cantidad total de turnos en el sistema (turnos.size())
  - Y = cantidad de consultorios ocupados (consultoriosOcupados.size())
  - Z = (Y / (double) TOTAL_CONSULTORIOS) * 100  (como double)


6) Clase Main – Programa de prueba

Escribir una clase Main que:

- Cree una instancia de SistemaClinica.
- Registre al menos dos turnos con distintos consultorios.
- Intente registrar un tercer turno en un consultorio ya ocupado y capture la IllegalStateException, mostrando un mensaje de error.
- Confirme un turno.
- Registre el ingreso de ese turno (EN_CURSO).
- Muestre por consola los datos de ese turno usando buscarTurnoPorCodigo.
- Finalice ese turno y libere el consultorio.
- Cancele el otro turno y libere su consultorio.
- Muestre el reporte de ocupación final usando generarReporteOcupacion().
- Llame a obtenerEstadosDeTurnos(FINALIZADO) y muestre cuántos estados FINALIZADO se obtuvieron.

(No es necesario que el Main tenga un output EXACTO, pero sí debe demostrar el uso de todos los métodos y el cambio de estados.)
