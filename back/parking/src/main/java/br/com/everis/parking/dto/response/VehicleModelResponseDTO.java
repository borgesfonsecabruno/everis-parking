package br.com.everis.parking.dto.response;

import br.com.everis.parking.model.VehicleModel;
import br.com.everis.parking.model.enums.VehicleType;

public class VehicleModelResponseDTO {

    private Long id;

    private String model;

    private Long year;

    private VehicleBrandResponseDTO brand;

    private VehicleType type;

    public VehicleModelResponseDTO(VehicleModel vehicleModel) {
        this.id = vehicleModel.getId();
        this.model = vehicleModel.getModel();
        this.year = vehicleModel.getYear();
        this.brand = new VehicleBrandResponseDTO(vehicleModel.getBrand());
        this.type = vehicleModel.getType();
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public Long getYear() {
        return year;
    }

    public VehicleBrandResponseDTO getBrand() {
        return brand;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }
}
