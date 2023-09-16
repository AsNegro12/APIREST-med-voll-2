package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(
        Long id,
        Long paciente_id,
        Long medico_id,
        LocalDateTime fecha
)
{}
