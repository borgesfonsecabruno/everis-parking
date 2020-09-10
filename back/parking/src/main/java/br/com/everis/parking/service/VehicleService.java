package br.com.everis.parking.service;

import br.com.everis.parking.dto.entry.VehicleEntryDTO;
import br.com.everis.parking.exceptions.ObjectAlreadyExists;
import br.com.everis.parking.exceptions.ObjectNotFoundException;
import br.com.everis.parking.model.Parking;
import br.com.everis.parking.model.Vehicle;
import br.com.everis.parking.model.VehicleModel;
import br.com.everis.parking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingService parkingService;

    public Vehicle findByLicensePlateAndParking(String licensePlate, Long parkingId) {
        Parking parking = parkingService.findById(parkingId);

        Vehicle vehicle = vehicleRepository.findOneByLicensePlateAndParking(licensePlate, parking)
                .orElseThrow(() -> new ObjectNotFoundException(licensePlate, "veículo"));

        return vehicle;
    }

    public List<Vehicle> findAllByParking(Long parkingId) {
        Parking parking = parkingService.findById(parkingId);

        return vehicleRepository.findAllByParking(parking);
    }

    public Vehicle save(Vehicle vehicle) {
        Parking parking = this.parkingService.findById(vehicle.getParking().getId());

        Vehicle exists = this.vehicleRepository.findOneByLicensePlateAndParking(
                vehicle.getLicensePlate(),
                parking).orElse(null);

        if(exists != null)
            throw new ObjectAlreadyExists(vehicle.getLicensePlate(), "veiculo");

        return this.vehicleRepository.save(vehicle);
    }

    public Vehicle fromDto(VehicleEntryDTO entry) {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setId(entry.getVehicleModelId());

        Parking parking = new Parking();
        parking.setId(entry.getParkingId());

        return new Vehicle(entry.getLicensePlate(), vehicleModel, parking);
    }
}
