package cl.dgac.planesvuelo.repository;

import cl.dgac.planesvuelo.model.PlanVuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlanVueloRepository extends JpaRepository<PlanVuelo, Long> {

    Optional<PlanVuelo> findByCodigoPlan(String codigoPlan);

    List<PlanVuelo> findByEstado(String estado);

    List<PlanVuelo> findByPilotoId(Long pilotoId);

    List<PlanVuelo> findByDroneId(Long droneId);

    @Query("SELECT p FROM PlanVuelo p WHERE LOWER(p.origen) LIKE LOWER(CONCAT('%', :origen, '%'))")
    List<PlanVuelo> buscarPorOrigen(String origen);
}