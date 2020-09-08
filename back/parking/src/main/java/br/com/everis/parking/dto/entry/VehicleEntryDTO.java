package br.com.everis.parking.dto.entry;

import br.com.everis.parking.model.VehicleModel;

import javax.validation.constraints.NotNull;

public class VehicleEntryDTO {

    @NotNull
    private String licensePlate;

    @NotNull
    private Long vehicleModel_id;

    public VehicleEntryDTO(String licensePlate, Long model) {
        this.licensePlate = licensePlate;
        this.vehicleModel_id = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getVehicleModel_id() {
        return vehicleModel_id;
    }

    public void setVehicleModel_id(Long vehicleModel_id) {
        this.vehicleModel_id = vehicleModel_id;
    }
}
