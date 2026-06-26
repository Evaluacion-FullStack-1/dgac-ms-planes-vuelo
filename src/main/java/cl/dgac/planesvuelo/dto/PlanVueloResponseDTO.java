package cl.dgac.planesvuelo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Schema(description = "Modelo de respuesta con la información detallada del plan de vuelo")
public class PlanVueloResponseDTO {

    @Schema(description = "Identificador único del plan de vuelo en la base de datos", example = "1")
    private Long id;

    @Schema(description = "Código único generado para el plan de vuelo", example = "PV-2026-001")
    private String codigoPlan;

    @Schema(description = "Identificador del drone asignado al vuelo", example = "12")
    private Long droneId;

    @Schema(description = "Identificador del piloto responsable del vuelo", example = "5")
    private Long pilotoId;

    @Schema(description = "Identificador de la empresa mandante u operadora", example = "3")
    private Long empresaMandanteId;

    @Schema(description = "Ubicación, aeródromo o coordenadas de despegue", example = "Aeródromo Tobalaba")
    private String origen;

    @Schema(description = "Ubicación, aeródromo o coordenadas de aterrizaje", example = "Sector Cajón del Maipo")
    private String destino;

    @Schema(description = "Fecha programada para la operación", example = "2026-07-15")
    private LocalDate fechaVuelo;

    @Schema(description = "Hora de despegue", example = "10:30:00")
    private LocalTime horaInicio;

    @Schema(description = "Hora de aterrizaje", example = "12:45:00")
    private LocalTime horaFin;

    @Schema(description = "Estado actual del plan de vuelo (ej. PENDIENTE, APROBADO, RECHAZADO)", example = "APROBADO")
    private String estado;

    @Schema(description = "Propósito o descripción detallada de la misión", example = "Levantamiento topográfico y fotogrametría del terreno")
    private String descripcion;
}