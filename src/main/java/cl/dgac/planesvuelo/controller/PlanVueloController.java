package cl.dgac.planesvuelo.controller;

import cl.dgac.planesvuelo.dto.PlanVueloRequestDTO;
import cl.dgac.planesvuelo.dto.PlanVueloResponseDTO;
import cl.dgac.planesvuelo.service.PlanVueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes-vuelo")
@Tag(name = "Planes de Vuelo", description = "Operaciones relacionadas con la gestión y autorización de planes de vuelo en la DGAC")
public class PlanVueloController {

    private final PlanVueloService planVueloService;

    public PlanVueloController(PlanVueloService planVueloService) {
        this.planVueloService = planVueloService;
    }

    @Operation(summary = "Listar todos los planes de vuelo", description = "Obtiene una lista completa de todos los planes de vuelo registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de planes de vuelo obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<PlanVueloResponseDTO>> listarPlanes() {
        return ResponseEntity.ok(planVueloService.listarPlanes());
    }

    @Operation(summary = "Buscar plan de vuelo por ID", description = "Obtiene los detalles de un plan de vuelo específico mediante su identificador único interno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan de vuelo encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Plan de vuelo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PlanVueloResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(planVueloService.buscarPorId(id));
    }

    @Operation(
            summary = "Crear nuevo plan de vuelo", 
            description = "Registra y somete a evaluación un nuevo plan de vuelo en el sistema.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos para registrar un nuevo plan de vuelo",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de Registro",
                                    value = "{\n  \"codigoPlan\": \"PV-2026-001\",\n  \"pilotoId\": 105,\n  \"droneId\": 42,\n  \"origen\": \"Aeródromo Los Cerrillos\",\n  \"destino\": \"Plaza de Armas, Santiago\",\n  \"fechaHoraSalida\": \"2026-07-15T10:00:00\",\n  \"fechaHoraLlegada\": \"2026-07-15T12:30:00\",\n  \"altitudMaximaMetros\": 120,\n  \"estado\": \"PENDIENTE\"\n}"
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plan de vuelo creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<PlanVueloResponseDTO> crearPlan(
            @Valid @RequestBody PlanVueloRequestDTO dto) {

        PlanVueloResponseDTO planCreado = planVueloService.crearPlan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(planCreado);
    }

    @Operation(
            summary = "Actualizar plan de vuelo", 
            description = "Modifica los datos o el estado de un plan de vuelo existente.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nuevos datos del plan de vuelo (ej. cambio de estado a APROBADO)",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de Actualización",
                                    value = "{\n  \"codigoPlan\": \"PV-2026-001\",\n  \"pilotoId\": 105,\n  \"droneId\": 42,\n  \"origen\": \"Aeródromo Los Cerrillos\",\n  \"destino\": \"Plaza de Armas, Santiago\",\n  \"fechaHoraSalida\": \"2026-07-15T10:00:00\",\n  \"fechaHoraLlegada\": \"2026-07-15T12:30:00\",\n  \"altitudMaximaMetros\": 120,\n  \"estado\": \"APROBADO\"\n}"
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan de vuelo actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Plan de vuelo no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PlanVueloResponseDTO> actualizarPlan(
            @PathVariable Long id,
            @Valid @RequestBody PlanVueloRequestDTO dto) {

        return ResponseEntity.ok(planVueloService.actualizarPlan(id, dto));
    }

    @Operation(summary = "Eliminar plan de vuelo", description = "Elimina un plan de vuelo del sistema mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Plan de vuelo eliminado exitosamente (Sin contenido)"),
            @ApiResponse(responseCode = "404", description = "Plan de vuelo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlan(@PathVariable Long id) {
        planVueloService.eliminarPlan(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar plan de vuelo por código", description = "Busca un plan exacto utilizando su código de vuelo único (ej. DGAC-2023-001).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan de vuelo encontrado"),
            @ApiResponse(responseCode = "404", description = "Código de plan no registrado")
    })
    @GetMapping("/buscar-codigo")
    public ResponseEntity<PlanVueloResponseDTO> buscarPorCodigoPlan(
            @RequestParam String codigoPlan) {

        return ResponseEntity.ok(planVueloService.buscarPorCodigoPlan(codigoPlan));
    }

    @Operation(summary = "Filtrar planes de vuelo por estado", description = "Obtiene una lista de planes filtrados según su estado (ej. APROBADO, PENDIENTE, RECHAZADO).")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/estado")
    public ResponseEntity<List<PlanVueloResponseDTO>> listarPorEstado(
            @RequestParam String estado) {

        return ResponseEntity.ok(planVueloService.listarPorEstado(estado));
    }

    @Operation(summary = "Buscar planes de vuelo por Piloto", description = "Obtiene el historial de planes de vuelo asignados a un ID de piloto en específico.")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/piloto/{pilotoId}")
    public ResponseEntity<List<PlanVueloResponseDTO>> listarPorPiloto(
            @PathVariable Long pilotoId) {

        return ResponseEntity.ok(planVueloService.listarPorPiloto(pilotoId));
    }

    @Operation(summary = "Buscar planes de vuelo por Drone", description = "Obtiene el historial de planes de vuelo asociados a un ID de drone en específico.")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/drone/{droneId}")
    public ResponseEntity<List<PlanVueloResponseDTO>> listarPorDrone(
            @PathVariable Long droneId) {

        return ResponseEntity.ok(planVueloService.listarPorDrone(droneId));
    }

    @Operation(summary = "Buscar planes de vuelo por Origen", description = "Busca planes de vuelo que salgan desde una ubicación o aeródromo específico.")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/origen")
    public ResponseEntity<List<PlanVueloResponseDTO>> buscarPorOrigen(
            @RequestParam String origen) {

        return ResponseEntity.ok(planVueloService.buscarPorOrigen(origen));
    }

    @Operation(summary = "Consultar Restricciones Aéreas (WebClient)", description = "Endpoint de integración que se comunica con el microservicio de Restricciones Aéreas para validar zonas de exclusión.")
    @ApiResponse(responseCode = "200", description = "Comunicación exitosa con el microservicio de Restricciones Aéreas")
    @GetMapping("/restricciones-aereas")
    public ResponseEntity<String> consultarRestriccionesAereas() {
        return ResponseEntity.ok(planVueloService.consultarMicroservicioRestriccionesAereas());
    }
}