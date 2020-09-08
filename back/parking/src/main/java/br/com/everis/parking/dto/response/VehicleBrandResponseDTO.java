package br.com.everis.parking.dto.response;

import br.com.everis.parking.model.VehicleBrand;

public class VehicleBrandResponseDTO {

    private Long id;

    private String brand;

    public VehicleBrandResponseDTO(VehicleBrand brand) {
        this.id = brand.getId();
        this.brand = brand.getBrand();
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }
}
