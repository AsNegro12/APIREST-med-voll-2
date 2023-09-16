package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultaService
{
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DatosAgendarConsulta datosAgendarConsulta)
    {
        var paciente = pacienteRepository.findById(datosAgendarConsulta.idPaciente()).get();
        var medico = medicoRepository.findById(datosAgendarConsulta.idMedico()).get();

        var consulta = new Consulta(null, paciente, medico, datosAgendarConsulta.fecha());
        consultaRepository.save(consulta);
    }
}
