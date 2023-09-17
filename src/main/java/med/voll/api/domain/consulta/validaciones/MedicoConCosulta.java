package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConCosulta implements ValidadorDeConsultas
{
    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosAgendarConsulta datos)
    {
        if(datos.idMedico() == null)
        {
            return;
        }

        var medicoConConsulta =repository.existsByMedicoIdAndFecha(datos.idMedico(),datos.fecha());

        if(medicoConConsulta)
        {
            throw new ValidationException("No se puede programar una cita con un médico " +
                                "que ya tiene otra cita programada en la misma fecha/hora");
        }
    }
}
