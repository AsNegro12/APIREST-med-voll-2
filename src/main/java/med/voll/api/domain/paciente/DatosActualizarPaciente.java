package med.voll.api.domain.paciente.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosActualizarPaciente(@NotNull Long id, String nombre, String rfc, DatosDireccion direccion) {
}
