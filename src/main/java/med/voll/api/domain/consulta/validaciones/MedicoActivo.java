package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ValidadorDeConsultas
{
    @Autowired
    private MedicoRepository medicoRepository;
    public void validar(DatosAgendarConsulta datos)
    {
        if(datos.idMedico() == null)
        {
            return;
        }

        var medicoActivo = medicoRepository.findByActivoById(datos.idMedico());

        if(!medicoActivo)
        {
            throw new ValidationException("No se puede programar citas con médicos inactivos en el sistema");
        }
    }
}