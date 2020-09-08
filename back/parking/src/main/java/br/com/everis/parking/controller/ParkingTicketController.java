package br.com.everis.parking.controller;

import br.com.everis.parking.dto.response.ParkingTicketResponseDTO;
import br.com.everis.parking.model.ParkingTicket;
import br.com.everis.parking.model.Vehicle;
import br.com.everis.parking.service.ParkingTicketService;
import br.com.everis.parking.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ticket")
public class ParkingTicketController {

    @Autowired
    private ParkingTicketService parkingTicketService;

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ParkingTicketResponseDTO> findTicketById(@PathVariable Long id) {
        ParkingTicket parkingTicket = this.parkingTicketService.findById(id);

        ParkingTicketResponseDTO responseDTO = new ParkingTicketResponseDTO(parkingTicket);

        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<List<ParkingTicketResponseDTO>> findAll() {
        List<ParkingTicket> parkingTickets = this.parkingTicketService.findAll();
        List<ParkingTicketResponseDTO> parkingTicketResponseDTOS = new ArrayList<>();

        parkingTickets.forEach(
                parkingTicket ->
                        parkingTicketResponseDTOS.add(new ParkingTicketResponseDTO(parkingTicket))
        );

        return ResponseEntity.ok(parkingTicketResponseDTOS);
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ResponseEntity<String> findSituationByCarId(
            @RequestParam(value = "byCar", required = true) String vehicleLicense) {
        Vehicle vehicle_entity = this.vehicleService.findById(vehicleLicense);
        ParkingTicket ticket = this.parkingTicketService.findByVehicleAndDepartureTimeIsNull(vehicle_entity);

        String result = "";
        if(ticket != null)
            result = "in";
        else
            result = "out";

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}/totalParking", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> getTotalParking(@PathVariable Long id,
                                                      @RequestParam(value = "departureDateTime", required = true)
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                              LocalDateTime departureDateTime) {

        return ResponseEntity.ok(this.parkingTicketService.getTotalParking(id, departureDateTime));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ParkingTicketResponseDTO>> findAll(
            @RequestParam(value = "byCar", required = false) String vehicleLicense) {

        List<ParkingTicket> parkingTickets;
        if(vehicleLicense == null)
            parkingTickets = this.parkingTicketService.findAll();
        else {
            Vehicle vehicle_entity = this.vehicleService.findById(vehicleLicense);
            parkingTickets = this.parkingTicketService.findAllByCar(vehicle_entity);
        }

        List<ParkingTicketResponseDTO> parkingTicketResponseDTOS =
                parkingTickets.stream()
                        .map(ticket -> new ParkingTicketResponseDTO(ticket))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(parkingTicketResponseDTOS);
    }

}
