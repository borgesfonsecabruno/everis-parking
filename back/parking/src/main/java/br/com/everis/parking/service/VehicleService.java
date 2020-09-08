package br.com.everis.parking.service;

import br.com.everis.parking.dto.entry.VehicleEntryDTO;
import br.com.everis.parking.exceptions.ObjectAlreadyExists;
import br.com.everis.parking.exceptions.ObjectNotFoundException;
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

    public Vehicle findById(String licensePlate) {
        Vehicle vehicle = vehicleRepository.findById(licensePlate)
                .orElseThrow(() -> new ObjectNotFoundException(licensePlate, "veículo"));

        return vehicle;
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle save(Vehicle vehicle) {
        Vehicle exists = this.vehicleRepository.findById(vehicle.getLicensePlate()).orElse(null);

        if(exists != null)
            throw new ObjectAlreadyExists(vehicle.getLicensePlate(), "veiculo");

        return this.vehicleRepository.save(vehicle);
    }

    public Vehicle fromDto(VehicleEntryDTO entry) {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setId(entry.getVehicleModel_id());

        return new Vehicle(entry.getLicensePlate(), vehicleModel);
    }
}
