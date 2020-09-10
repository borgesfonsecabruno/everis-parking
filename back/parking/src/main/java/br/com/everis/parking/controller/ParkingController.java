package br.com.everis.parking.controller;

import br.com.everis.parking.dto.entry.ParkingTicketCheckInDTO;
import br.com.everis.parking.dto.entry.ParkingTicketCheckOutDTO;
import br.com.everis.parking.dto.response.ParkingResponseDTO;
import br.com.everis.parking.dto.response.RevenueTotalResponse;
import br.com.everis.parking.dto.update.ParkingUpdateDTO;
import br.com.everis.parking.model.Parking;
import br.com.everis.parking.model.ParkingTicket;
import br.com.everis.parking.model.enums.VehicleType;
import br.com.everis.parking.service.ParkingService;
import br.com.everis.parking.service.ParkingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ParkingTicketService parkingTicketService;

    @RequestMapping(value = "/{parkingId}", method = RequestMethod.GET)
    public ResponseEntity<ParkingResponseDTO> getParkingById(@PathVariable Long parkingId) {
        ParkingResponseDTO parking = new ParkingResponseDTO(this.parkingService.findById(parkingId));

        return ResponseEntity.ok(parking);
    }

    @RequestMapping(value = "/{parkingId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long parkingId,
                                       @Valid @RequestBody ParkingUpdateDTO parking) {
        Parking toUpdateParking = this.parkingService.fromDTO(parking);

        this.parkingService.update(parkingId, toUpdateParking);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/checkin", method = RequestMethod.POST)
    public ResponseEntity<Void> checkIn(
            @Valid @RequestBody ParkingTicketCheckInDTO entryTicket) {
        ParkingTicket fromDTO = this.parkingTicketService.fromDTO(entryTicket);

        this.parkingService.checkIn(fromDTO);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.PUT)
    public ResponseEntity<Void> checkOut(
            @Valid @RequestBody ParkingTicketCheckOutDTO entryTicket) {
        ParkingTicket fromDto = this.parkingTicketService.fromDTO(entryTicket);
        this.parkingService.checkOut(fromDto);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{parking}/revenue", method = RequestMethod.GET)
    public ResponseEntity<RevenueTotalResponse> getRevenue(
            @PathVariable Long parking,
            @RequestParam(value = "vehicleType", required = false) VehicleType vehicleType) {
        BigDecimal revenueTotal = this.parkingService.calculateRevenues(parking, vehicleType);
        RevenueTotalResponse response = new RevenueTotalResponse(revenueTotal);

        return ResponseEntity.ok(response);
    }
}
