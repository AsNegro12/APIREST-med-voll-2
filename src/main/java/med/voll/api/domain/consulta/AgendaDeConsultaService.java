package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultaService
{
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    List<ValidadorDeConsultas> validadores;

    public DatosDetalleConsulta agendar(DatosAgendarConsulta datos)
    {
        if(!pacienteRepository.findById(datos.idPaciente()).isPresent())
        {
            throw new ValidacionDeIntegridadException(" este id para el pasiente no fue encontrado.");
        }

        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico()))
        {
            throw new ValidacionDeIntegridadException(" este id para el medico no fue encontrado.");
        }

        validadores.forEach(v->v.validar(datos));

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = escogerMedico(datos);

        if(medico == null)
        {
            throw new ValidacionDeIntegridadException(" No hay medicos especialistas disponible para este horario");
        }

        var consulta = new Consulta(null, paciente, medico, datos.fecha());
        consultaRepository.save(consulta);

        return new DatosDetalleConsulta(consulta);
    }

    private Medico escogerMedico(DatosAgendarConsulta datos)
    {
        if(datos.idMedico() != null)
        {
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad() == null)
        {
            throw new ValidacionDeIntegridadException("Debe seleccionarse una especialidad para el medico");
        }

        return  medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(),datos.fecha());
    }
}
