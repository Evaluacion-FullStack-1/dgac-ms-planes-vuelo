package cl.dgac.planesvuelo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PlanVueloRequestDTO {

    @NotNull(message = "El ID del drone es obligatorio")
    private Long droneId;

    @NotNull(message = "El ID del piloto es obligatorio")
    private Long pilotoId;

    @NotNull(message = "El ID de la empresa mandante es obligatorio")
    private Long empresaMandanteId;

    @NotBlank(message = "El origen es obligatorio")
    private String origen;

    @NotBlank(message = "El destino es obligatorio")
    private String destino;

    @NotNull(message = "La fecha de vuelo es obligatoria")
    private LocalDate fechaVuelo;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFin;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
}