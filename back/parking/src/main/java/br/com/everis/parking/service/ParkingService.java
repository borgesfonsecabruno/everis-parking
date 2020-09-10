package br.com.everis.parking.service;

import br.com.everis.parking.dto.update.ParkingUpdateDTO;
import br.com.everis.parking.exceptions.ObjectNotFoundException;
import br.com.everis.parking.exceptions.message.DepartureHasAlreadyBeenRegisteredException;
import br.com.everis.parking.model.Parking;
import br.com.everis.parking.model.ParkingTicket;
import br.com.everis.parking.model.PriceFactor;
import br.com.everis.parking.model.enums.VehicleType;
import br.com.everis.parking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private final String entity = "Parking";

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private PriceFactorService priceFactorService;

    @Autowired
    private ParkingTicketService parkingTicketService;

    public Parking findById(Long id) {
        Parking parking = this.parkingRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.valueOf(id), this.entity));

        return parking;
    }

    public void update(Long parkingId, Parking toUpdateParking) {
        toUpdateParking.setId(parkingId);
        Parking existentParking = this.findById(toUpdateParking.getId());

        if(toUpdateParking.getAddress() != null)
            existentParking.setAddress(toUpdateParking.getAddress());

        if(toUpdateParking.getHourValue() != null)
            existentParking.setHourValue(toUpdateParking.getHourValue());

        this.parkingRepository.save(existentParking);
    }

    public void checkIn(ParkingTicket parkingTicket) {
        parkingTicket.setId(null);

        PriceFactor priceFactor = this.priceFactorService
                .findCurrentFactorFor(parkingTicket.getVehicle().getModel().getType());

        parkingTicket.setPriceFactor(priceFactor);

        this.parkingTicketService.save(parkingTicket);
    }

    public void checkOut(ParkingTicket toUpdate) {
        ParkingTicket existentParkingTicket = this.parkingTicketService.findById(toUpdate.getId());

        if(existentParkingTicket.getDepartureDateTime() != null)
            throw new DepartureHasAlreadyBeenRegisteredException(existentParkingTicket);

        LocalDateTime departureTime =
                toUpdate.getDepartureDateTime() == null ? LocalDateTime.now() : toUpdate.getDepartureDateTime();

        if(departureTime.isBefore(existentParkingTicket.getEntryDateTime()))
            throw new IllegalArgumentException("Não é possível adicionar uma data de saída " +
                    departureTime + " menor que a data de entrada " +
                    existentParkingTicket.getEntryDateTime());

        existentParkingTicket.setDepartureDateTime(departureTime);
        existentParkingTicket.calculateTotalParking(existentParkingTicket.getParking().getHourValue());

        this.parkingTicketService.update(existentParkingTicket);
    }

    public BigDecimal calculateRevenues(Long parking_id, VehicleType vehicleType) {
        List<ParkingTicket> parkingTickets = this.parkingTicketService.findAllByParking(parking_id);

        if(vehicleType != null)
            parkingTickets = parkingTickets.stream()
                    .filter(ticket -> ticket.getVehicle() != null
                            && ticket.getVehicle().getModel() != null
                            &&ticket.getVehicle().getModel().getType() != null
                            && ticket.getVehicle().getModel().getType().equals(vehicleType))
                    .collect(Collectors.toList());

        BigDecimal totalRevenue = BigDecimal.ZERO;
        for(ParkingTicket ticket : parkingTickets) {
            totalRevenue = ticket.getTotalParking() != null ?
                    totalRevenue.add(ticket.getTotalParking()) : totalRevenue;
        }

        return totalRevenue;
    }

    public Parking fromDTO(ParkingUpdateDTO parkingUpdate) {
        return new Parking(parkingUpdate.getAddress(), parkingUpdate.getHourValue());
    }
}
