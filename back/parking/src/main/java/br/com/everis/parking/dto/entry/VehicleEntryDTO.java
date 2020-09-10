package br.com.everis.parking.dto.entry;

import javax.validation.constraints.NotNull;

public class VehicleEntryDTO {

    @NotNull
    private String licensePlate;

    @NotNull
    private Long vehicleModelId;

    @NotNull
    private Long parkingId;

    public VehicleEntryDTO(String licensePlate, Long model, Long parkingId) {
        this.licensePlate = licensePlate;
        this.vehicleModelId = model;
        this.parkingId = parkingId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(Long vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }
}
