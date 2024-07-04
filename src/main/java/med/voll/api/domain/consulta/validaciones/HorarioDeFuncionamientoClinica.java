package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.infra.errores.ValidacionDeIntegracion;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsulta{

    public void validar(DatosAgendarConsulta datosAgendarConsulta){
        var domingo = DayOfWeek.SUNDAY.equals(datosAgendarConsulta.fecha().getDayOfWeek());
        var antesDeApertura = datosAgendarConsulta.fecha().getHour() < 7;
        var despuesDeCierre = datosAgendarConsulta.fecha().getHour() > 19;
        if (domingo || antesDeApertura || despuesDeCierre){
            throw new ValidationException("El horario de atención de la clínica es de lunes a sábado de 7:00 a 19:00 horas.");
        }
    }

}
