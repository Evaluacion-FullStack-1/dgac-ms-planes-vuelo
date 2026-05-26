package cl.dgac.planesvuelo.controller;

import cl.dgac.planesvuelo.dto.PlanVueloRequestDTO;
import cl.dgac.planesvuelo.dto.PlanVueloResponseDTO;
import cl.dgac.planesvuelo.service.PlanVueloService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes-vuelo")
public class PlanVueloController {

    private final PlanVueloService planVueloService;

    public PlanVueloController(PlanVueloService planVueloService) {
        this.planVueloService = planVueloService;
    }

    @GetMapping
    public ResponseEntity<List<PlanVueloResponseDTO>> listarPlanes() {
        return ResponseEntity.ok(planVueloService.listarPlanes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanVueloResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(planVueloService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PlanVueloResponseDTO> crearPlan(
            @Valid @RequestBody PlanVueloRequestDTO dto) {

        PlanVueloResponseDTO planCreado = planVueloService.crearPlan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(planCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanVueloResponseDTO> actualizarPlan(
            @PathVariable Long id,
            @Valid @RequestBody PlanVueloRequestDTO dto) {

        return ResponseEntity.ok(planVueloService.actualizarPlan(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPlan(@PathVariable Long id) {
        planVueloService.eliminarPlan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-codigo")
    public ResponseEntity<PlanVueloResponseDTO> buscarPorCodigoPlan(
            @RequestParam String codigoPlan) {

        return ResponseEntity.ok(planVueloService.buscarPorCodigoPlan(codigoPlan));
    }

    @GetMapping("/estado")
    public ResponseEntity<List<PlanVueloResponseDTO>> listarPorEstado(
            @RequestParam String estado) {

        return ResponseEntity.ok(planVueloService.listarPorEstado(estado));
    }

    @GetMapping("/piloto/{pilotoId}")
    public ResponseEntity<List<PlanVueloResponseDTO>> listarPorPiloto(
            @PathVariable Long pilotoId) {

        return ResponseEntity.ok(planVueloService.listarPorPiloto(pilotoId));
    }

    @GetMapping("/drone/{droneId}")
    public ResponseEntity<List<PlanVueloResponseDTO>> listarPorDrone(
            @PathVariable Long droneId) {

        return ResponseEntity.ok(planVueloService.listarPorDrone(droneId));
    }

    @GetMapping("/origen")
    public ResponseEntity<List<PlanVueloResponseDTO>> buscarPorOrigen(
            @RequestParam String origen) {

        return ResponseEntity.ok(planVueloService.buscarPorOrigen(origen));
    }

    @GetMapping("/restricciones-aereas")
    public ResponseEntity<String> consultarRestriccionesAereas() {
        return ResponseEntity.ok(planVueloService.consultarMicroservicioRestriccionesAereas());
    }
}