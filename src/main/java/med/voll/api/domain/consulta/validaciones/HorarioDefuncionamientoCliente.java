package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDefuncionamientoCliente implements ValidadorDeConsultas
{
    public void validar(DatosAgendarConsulta datos)
    {
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var horarioAntesDeApertura = datos.fecha().getHour()<7;
        var horarioDespuesDeApertura = datos.fecha().getHour()>19;
        if(domingo || horarioAntesDeApertura || horarioDespuesDeApertura)
        {
            throw new ValidationException("El horario de atención de la clínica " +
                                    "es de lunes a sábado, de 07:00 a 19:00 horas");
        }
    }
}
