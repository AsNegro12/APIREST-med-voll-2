package med.voll.api.domain.paciente.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroPaciente(

        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Size(min = 10,max = 10)
        String rfc,
        @NotNull
        Especialidad especialidad,
        @NotNull
        @Valid
        DatosDireccion direccion) {
}
