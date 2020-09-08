package br.com.everis.parking.repository;

import br.com.everis.parking.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {

}
