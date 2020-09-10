package br.com.everis.parking.repository;

import br.com.everis.parking.model.Parking;
import br.com.everis.parking.model.ParkingTicket;
import br.com.everis.parking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
    Optional<ParkingTicket> findOneByVehicleAndDepartureDateTimeIsNull(Vehicle vehicle);
    List<ParkingTicket> findAllByVehicle(Vehicle vehicle);
    List<ParkingTicket> findAllByParkingOrderByEntryDateTimeDesc(Parking parking);
}
