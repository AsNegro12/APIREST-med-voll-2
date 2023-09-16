package med.voll.api.domain.paciente.medico;

import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRespuestaPaciente(
        Long id,
        String nombre,
        String email,
        String telefono,
        String rfc,
        DatosDireccion direccion) {
}
