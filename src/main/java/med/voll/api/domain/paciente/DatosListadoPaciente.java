package med.voll.api.domain.paciente.medico;

public record DatosListadoPaciente(Long id, String nombre, String especialidad, String documento, String email) {

    public DatosListadoPaciente(Paciente medico) {
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(), medico.getRfc(), medico.getEmail());
    }
}


