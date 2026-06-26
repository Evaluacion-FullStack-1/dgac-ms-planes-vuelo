package cl.dgac.planesvuelo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Schema(description = "Modelo de petición para la creación o actualización de un plan de vuelo")
public class PlanVueloRequestDTO {

    @Schema(description = "Identificador único del drone asignado al vuelo", example = "12")
    @NotNull(message = "El ID del drone es obligatorio")
    private Long droneId;

    @Schema(description = "Identificador único del piloto responsable del vuelo", example = "5")
    @NotNull(message = "El ID del piloto es obligatorio")
    private Long pilotoId;

    @Schema(description = "Identificador único de la empresa mandante u operadora", example = "3")
    @NotNull(message = "El ID de la empresa mandante es obligatorio")
    private Long empresaMandanteId;

    @Schema(description = "Ubicación, aeródromo o coordenadas de despegue", example = "Aeródromo Tobalaba")
    @NotBlank(message = "El origen es obligatorio")
    private String origen;

    @Schema(description = "Ubicación, aeródromo o coordenadas de aterrizaje", example = "Sector Cajón del Maipo")
    @NotBlank(message = "El destino es obligatorio")
    private String destino;

    @Schema(description = "Fecha programada para la operación de vuelo", example = "2026-07-15")
    @NotNull(message = "La fecha de vuelo es obligatoria")
    private LocalDate fechaVuelo;

    @Schema(description = "Hora programada de despegue (formato 24h)", example = "10:30:00")
    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @Schema(description = "Hora estimada de aterrizaje o fin de la operación (formato 24h)", example = "12:45:00")
    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFin;

    @Schema(description = "Propósito o descripción detallada de la misión de vuelo", example = "Levantamiento topográfico y fotogrametría del terreno")
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
}