package br.com.everis.parking.controller;

import br.com.everis.parking.dto.entry.VehicleEntryDTO;
import br.com.everis.parking.dto.response.VehicleResponseDTO;
import br.com.everis.parking.model.Vehicle;
import br.com.everis.parking.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = "/{licensePlate}", method = RequestMethod.GET)
    public ResponseEntity<VehicleResponseDTO> findVehicleById(@PathVariable String licensePlate) {
        Vehicle vehicle = this.vehicleService.findById(licensePlate);

        VehicleResponseDTO responseDTO = new VehicleResponseDTO(vehicle);

        return ResponseEntity.ok(responseDTO);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<VehicleResponseDTO>> findAll() {
        List<Vehicle> vehicles= this.vehicleService.findAll();

        List<VehicleResponseDTO> vehicleResponseDTOS =
                vehicles.stream()
                        .map(vehicle -> new VehicleResponseDTO(vehicle))
                        .collect(Collectors.toList());

        return ResponseEntity.ok(vehicleResponseDTOS);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addVehicle(@Valid @RequestBody VehicleEntryDTO entry) {
        Vehicle vehicle = this.vehicleService.save(this.vehicleService.fromDto(entry));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{vehicle}")
                .buildAndExpand(vehicle.getLicensePlate())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
