package br.com.everis.parking.repository;

import br.com.everis.parking.model.PriceFactor;
import br.com.everis.parking.model.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceFactorRepository extends JpaRepository<PriceFactor, Long> {
    Optional<PriceFactor> findOneByVehicleTypeAndFinalDateIsNull(VehicleType vehicleType);
}
