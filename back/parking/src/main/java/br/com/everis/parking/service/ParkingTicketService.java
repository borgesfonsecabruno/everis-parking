package br.com.everis.parking.service;

import br.com.everis.parking.dto.entry.ParkingTicketCheckInDTO;
import br.com.everis.parking.dto.entry.ParkingTicketCheckOutDTO;
import br.com.everis.parking.exceptions.ObjectAlreadyExists;
import br.com.everis.parking.exceptions.ObjectNotFoundException;
import br.com.everis.parking.model.Parking;
import br.com.everis.parking.model.ParkingTicket;
import br.com.everis.parking.model.PriceFactor;
import br.com.everis.parking.model.Vehicle;
import br.com.everis.parking.model.enums.VehicleType;
import br.com.everis.parking.repository.ParkingTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingTicketService {

    @Autowired
    private ParkingTicketRepository parkingTicketRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private PriceFactorService priceFactorService;

    @Autowired
    private ParkingService parkingService;

    public ParkingTicket findById(Long id) {
        return this.parkingTicketRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        String.valueOf(id),
                        "ticket de estacionamento"));
    }

    public List<ParkingTicket> findAllByVehicle(String licensePlate, Long parkingId) {
        Vehicle vehicle = this.vehicleService.findByLicensePlateAndParking(licensePlate, parkingId);

        return this.parkingTicketRepository.findAllByVehicle(vehicle);
    }

    public ParkingTicket findByVehicleAndDepartureTimeIsNull(String licensePlate, Long parkingId) {
        Vehicle vehicle = this.vehicleService.findByLicensePlateAndParking(licensePlate, parkingId);

        return this.parkingTicketRepository
                .findOneByVehicleAndDepartureDateTimeIsNull(vehicle).orElse(null);
    }

    public List<ParkingTicket> findAllByParking(Long parkingId) {
        Parking parking = this.parkingService.findById(parkingId);

        return this.parkingTicketRepository.findAllByParkingOrderByEntryDateTimeDesc(parking);
    }

    public void save(ParkingTicket parkingTicket) {
        VehicleType vehicleType;

        if(parkingTicket != null
                && parkingTicket.getVehicle() != null
                && parkingTicket.getVehicle().getModel() != null
                && parkingTicket.getVehicle().getModel().getType() != null)
            vehicleType = parkingTicket.getVehicle().getModel().getType();
        else
            throw new NullPointerException("Falta dados no ticket");

        PriceFactor factor = this.priceFactorService
                .findCurrentFactorFor(vehicleType);

        if(factor == null) {
            throw new ObjectNotFoundException(String.valueOf(vehicleType), "fator de preço");
        }

        ParkingTicket exists = this.findByVehicleAndDepartureTimeIsNull(
                parkingTicket.getVehicle().getLicensePlate(),
                parkingTicket.getParking().getId());

        if(exists != null) {
            throw new ObjectAlreadyExists(exists);
        }

        parkingTicket.setPriceFactor(factor);
        parkingTicket.setId(null);

        this.parkingTicketRepository.save(parkingTicket);
    }

    public void update(ParkingTicket newParkingTicket) {
        ParkingTicket existentParkingTicket = this.findById(newParkingTicket.getId());

        if(newParkingTicket.getDepartureDateTime() != null)
            existentParkingTicket.setDepartureDateTime(newParkingTicket.getDepartureDateTime());

        this.parkingTicketRepository.save(existentParkingTicket);
    }

    public BigDecimal getTotalParkingByIdAndDeparture(Long ticketId, LocalDateTime departureDateTime) {
        ParkingTicket ticket = this.findById(ticketId);
        ticket.setDepartureDateTime(departureDateTime);
        Parking parking = ticket.getParking();

        return ticket.calculateTotalParking(parking.getHourValue());
    }

    public ParkingTicket fromDTO(ParkingTicketCheckInDTO entryTicket) {
        Vehicle vehicle = this.vehicleService.findByLicensePlateAndParking(entryTicket.getVehicleLicense(), entryTicket.getParkingId());
        Parking parking = this.parkingService.findById(entryTicket.getParkingId());

        return new ParkingTicket(vehicle,
                entryTicket.getEntryDateTime(),
                null,
                parking);
    }

    public ParkingTicket fromDTO(ParkingTicketCheckOutDTO entryTicket) {

        ParkingTicket fromDto = new ParkingTicket(null, null, null, null);
        fromDto.setId(entryTicket.getTicketId());
        fromDto.setDepartureDateTime(entryTicket.getDepartureDateTime());

        return fromDto;
    }
}
