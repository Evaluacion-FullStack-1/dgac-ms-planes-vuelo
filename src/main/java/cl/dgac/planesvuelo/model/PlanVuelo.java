package cl.dgac.planesvuelo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "planes_vuelo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanVuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigoPlan;

    @Column(nullable = false)
    private Long droneId;

    @Column(nullable = false)
    private Long pilotoId;

    @Column(nullable = false)
    private Long empresaMandanteId;

    @Column(nullable = false)
    private String origen;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private LocalDate fechaVuelo;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String descripcion;
}