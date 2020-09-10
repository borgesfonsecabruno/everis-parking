package br.com.everis.parking.controller;

import br.com.everis.parking.dto.response.ParkingTicketResponseDTO;
import br.com.everis.parking.model.ParkingTicket;
import br.com.everis.parking.service.ParkingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ticket")
public class ParkingTicketController {

    @Autowired
    private ParkingTicketService parkingTicketService;

    @RequestMapping(value = "/{ticketId}", method = RequestMethod.GET)
    public ResponseEntity<ParkingTicketResponseDTO> findTicketById(@PathVariable Long ticketId) {
        ParkingTicket parkingTicket = this.parkingTicketService.findById(ticketId);

        ParkingTicketResponseDTO responseDTO = new ParkingTicketResponseDTO(parkingTicket);

        return ResponseEntity.ok(responseDTO);
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ResponseEntity<String> findStatusByCarId(
            @RequestParam(value = "licensePlate", required = true) String licensePlate,
            @RequestParam(value = "parkingId", required = true) Long parkingId) {
        ParkingTicket ticket = this.parkingTicketService.findByVehicleAndDepartureTimeIsNull(licensePlate, parkingId);

        String result = "";
        if(ticket != null)
            result = "in";
        else
            result = "out";

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{ticketId}/totalParking", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> getTicketTotalParking(@PathVariable Long ticketId,
                                                            @RequestParam(value = "departureDateTime", required = true)
                                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                    LocalDateTime departureDateTime) {
        return ResponseEntity.ok(this.parkingTicketService.getTotalParkingByIdAndDeparture(ticketId, departureDateTime));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ParkingTicketResponseDTO>> findAllByParking(
            @RequestParam(value = "licensePlate", required = false) String licensePlate,
            @RequestParam(value = "parkingId", required = true) Long parkingId) {
        List<ParkingTicket> parkingTickets;

        if(licensePlate == null)
            parkingTickets = this.parkingTicketService.findAllByParking(parkingId);
        else {
            parkingTickets = this.parkingTicketService.findAllByVehicle(licensePlate, parkingId);
        }

        List<ParkingTicketResponseDTO> parkingTicketResponseDTOS =
                parkingTickets.stream()
                        .map(ticket -> new ParkingTicketResponseDTO(ticket))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(parkingTicketResponseDTOS);
    }

}
