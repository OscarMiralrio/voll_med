package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){
        var primerHorario = datosAgendarConsulta.fecha().withHour(7);
        var ultimoHorario = datosAgendarConsulta.fecha().withHour(19);

        var pacienteConConsulta = consultaRepository.existsByPacienteIdAndDataBetween(datosAgendarConsulta.idPaciente(), primerHorario,ultimoHorario);

        if (pacienteConConsulta){
            throw new ValidationException("El paciente ya tiene una consulta para ese dia.");
        }

    }

}
