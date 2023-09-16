package med.voll.api.domain.paciente;

public record DatosListadoPaciente(Long id, String nombre, String rfc, String email) {

    public DatosListadoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getRfc(), paciente.getEmail());
    }
}


