package cl.dgac.planesvuelo.service;

import cl.dgac.planesvuelo.dto.PlanVueloRequestDTO;
import cl.dgac.planesvuelo.dto.PlanVueloResponseDTO;
import cl.dgac.planesvuelo.exception.ResourceNotFoundException;
import cl.dgac.planesvuelo.mapper.PlanVueloMapper;
import cl.dgac.planesvuelo.model.PlanVuelo;
import cl.dgac.planesvuelo.repository.PlanVueloRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanVueloService {

    private final PlanVueloRepository planVueloRepository;
    private final PlanVueloMapper planVueloMapper;
    private final WebClient.Builder webClientBuilder;

    public PlanVueloService(PlanVueloRepository planVueloRepository,
                            PlanVueloMapper planVueloMapper,
                            WebClient.Builder webClientBuilder) {
        this.planVueloRepository = planVueloRepository;
        this.planVueloMapper = planVueloMapper;
        this.webClientBuilder = webClientBuilder;
    }

    public List<PlanVueloResponseDTO> listarPlanes() {
        return planVueloRepository.findAll()
                .stream()
                .map(planVueloMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PlanVueloResponseDTO buscarPorId(Long id) {
        PlanVuelo plan = planVueloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan de vuelo no encontrado con ID: " + id));

        return planVueloMapper.toDTO(plan);
    }

    public PlanVueloResponseDTO crearPlan(PlanVueloRequestDTO dto) {
        PlanVuelo plan = planVueloMapper.toEntity(dto);

        plan.setCodigoPlan("PV-" + System.currentTimeMillis());
        plan.setEstado("PENDIENTE");

        PlanVuelo planGuardado = planVueloRepository.save(plan);

        return planVueloMapper.toDTO(planGuardado);
    }

    public PlanVueloResponseDTO actualizarPlan(Long id, PlanVueloRequestDTO dto) {
        PlanVuelo plan = planVueloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan de vuelo no encontrado con ID: " + id));

        planVueloMapper.updateEntity(plan, dto);
        PlanVuelo planActualizado = planVueloRepository.save(plan);

        return planVueloMapper.toDTO(planActualizado);
    }

    public void eliminarPlan(Long id) {
        PlanVuelo plan = planVueloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan de vuelo no encontrado con ID: " + id));

        planVueloRepository.delete(plan);
    }

    public PlanVueloResponseDTO buscarPorCodigoPlan(String codigoPlan) {
        PlanVuelo plan = planVueloRepository.findByCodigoPlan(codigoPlan)
                .orElseThrow(() -> new ResourceNotFoundException("Plan de vuelo no encontrado con código: " + codigoPlan));

        return planVueloMapper.toDTO(plan);
    }

    public List<PlanVueloResponseDTO> listarPorEstado(String estado) {
        return planVueloRepository.findByEstado(estado)
                .stream()
                .map(planVueloMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PlanVueloResponseDTO> listarPorPiloto(Long pilotoId) {
        return planVueloRepository.findByPilotoId(pilotoId)
                .stream()
                .map(planVueloMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PlanVueloResponseDTO> listarPorDrone(Long droneId) {
        return planVueloRepository.findByDroneId(droneId)
                .stream()
                .map(planVueloMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PlanVueloResponseDTO> buscarPorOrigen(String origen) {
        return planVueloRepository.buscarPorOrigen(origen)
                .stream()
                .map(planVueloMapper::toDTO)
                .collect(Collectors.toList());
    }

    public String consultarMicroservicioRestriccionesAereas() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8087/api/restricciones-aereas")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}