package br.com.everis.parking.dto.response;

import br.com.everis.parking.model.Vehicle;

public class VehicleResponseDTO {

    private String licensePlate;

    private VehicleModelResponseDTO model;

    public VehicleResponseDTO(Vehicle vehicle) {
        this.licensePlate = vehicle.getLicensePlate();
        this.model = new VehicleModelResponseDTO(vehicle.getModel());
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleModelResponseDTO getModel() {
        return model;
    }
}
