package med.voll.api.domain.medico;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.paciente.DatosRegistroPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("deberia retornar nulo cuando el medico se en cuentre en consulta con otro paciente en ese horario")
    void seleccionarMedicoConEspecialidadEnFechaEsenario1()
    {
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico = registrarMedico("uriel","u@medvoll.com","AAAA010101",Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("Juan","j@mail.com","BBBB010101");
        registrarConsulta(paciente, medico, proximoLunes10H);

        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA,proximoLunes10H);

       //System.out.println(paciente);
       //System.out.println(medico);
       //System.out.println(proximoLunes10H);

        assertThat(medicoLibre).isNull();
    }

    @Test
    @DisplayName("deberia retornar un medico cuando relice la consulta en la base de datos para en ese horario")
    void seleccionarMedicoConEspecialidadEnFechaEsenario2()
    {
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico = registrarMedico("uriel","u@medvoll.com","AAAA010101",Especialidad.CARDIOLOGIA);

        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA,proximoLunes10H);

        //System.out.println(paciente);
        //System.out.println(medico);
        //System.out.println(proximoLunes10H);

        assertThat(medicoLibre).isEqualTo(medico);
    }

    private void registrarConsulta(Paciente paciente, Medico medico, LocalDateTime fecha) {
        em.persist(new Consulta(null, paciente, medico, fecha));
    }

    private Medico registrarMedico(String nombre, String email, String rfc, Especialidad especialidad) {
        var medico = new Medico(datosMedico(nombre, email, rfc, especialidad));
        em.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String rfc) {
        var paciente = new Paciente(datosPaciente(nombre, email, rfc));
        em.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedico datosMedico(String nombre, String email, String rfc, Especialidad especialidad) {
        return new DatosRegistroMedico(
                nombre,
                email,
                "61999999999",
                rfc,
                especialidad,
                datosDireccion()
        );
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String rfc) {
        return new DatosRegistroPaciente(
                nombre,
                email,
                "61999999999",
                rfc,
                datosDireccion()
        );
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                " loca",
                "azul",
                "acapulpo",
                "321",
                "12"
        );
    }
}