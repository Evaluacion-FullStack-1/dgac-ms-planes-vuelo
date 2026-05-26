package cl.dgac.planesvuelo.mapper;

import cl.dgac.planesvuelo.dto.PlanVueloRequestDTO;
import cl.dgac.planesvuelo.dto.PlanVueloResponseDTO;
import cl.dgac.planesvuelo.model.PlanVuelo;
import org.springframework.stereotype.Component;

@Component
public class PlanVueloMapper {

    public PlanVuelo toEntity(PlanVueloRequestDTO dto) {
        PlanVuelo plan = new PlanVuelo();

        plan.setCodigoPlan(dto.getCodigoPlan());
        plan.setDroneId(dto.getDroneId());
        plan.setPilotoId(dto.getPilotoId());
        plan.setEmpresaMandanteId(dto.getEmpresaMandanteId());
        plan.setOrigen(dto.getOrigen());
        plan.setDestino(dto.getDestino());
        plan.setFechaVuelo(dto.getFechaVuelo());
        plan.setHoraInicio(dto.getHoraInicio());
        plan.setHoraFin(dto.getHoraFin());
        plan.setEstado(dto.getEstado());
        plan.setDescripcion(dto.getDescripcion());

        return plan;
    }

    public PlanVueloResponseDTO toDTO(PlanVuelo plan) {
        PlanVueloResponseDTO dto = new PlanVueloResponseDTO();

        dto.setId(plan.getId());
        dto.setCodigoPlan(plan.getCodigoPlan());
        dto.setDroneId(plan.getDroneId());
        dto.setPilotoId(plan.getPilotoId());
        dto.setEmpresaMandanteId(plan.getEmpresaMandanteId());
        dto.setOrigen(plan.getOrigen());
        dto.setDestino(plan.getDestino());
        dto.setFechaVuelo(plan.getFechaVuelo());
        dto.setHoraInicio(plan.getHoraInicio());
        dto.setHoraFin(plan.getHoraFin());
        dto.setEstado(plan.getEstado());
        dto.setDescripcion(plan.getDescripcion());

        return dto;
    }

    public void updateEntity(PlanVuelo plan, PlanVueloRequestDTO dto) {
        plan.setCodigoPlan(dto.getCodigoPlan());
        plan.setDroneId(dto.getDroneId());
        plan.setPilotoId(dto.getPilotoId());
        plan.setEmpresaMandanteId(dto.getEmpresaMandanteId());
        plan.setOrigen(dto.getOrigen());
        plan.setDestino(dto.getDestino());
        plan.setFechaVuelo(dto.getFechaVuelo());
        plan.setHoraInicio(dto.getHoraInicio());
        plan.setHoraFin(dto.getHoraFin());
        plan.setEstado(dto.getEstado());
        plan.setDescripcion(dto.getDescripcion());
    }
}