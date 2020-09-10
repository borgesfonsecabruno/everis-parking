package br.com.everis.parking.repository;

import br.com.everis.parking.model.Parking;
import br.com.everis.parking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Optional<Vehicle> findOneByLicensePlateAndParking(String licensePlate, Parking parking);
    List<Vehicle> findAllByParking(Parking parking);
}
