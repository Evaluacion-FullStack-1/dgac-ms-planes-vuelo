package cl.dgac.planesvuelo.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PlanVueloResponseDTO {

    private Long id;
    private String codigoPlan;
    private Long droneId;
    private Long pilotoId;
    private Long empresaMandanteId;
    private String origen;
    private String destino;
    private LocalDate fechaVuelo;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;
    private String descripcion;
}