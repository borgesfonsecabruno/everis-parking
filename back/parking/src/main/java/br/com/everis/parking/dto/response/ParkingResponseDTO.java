package br.com.everis.parking.dto.response;

import br.com.everis.parking.model.Parking;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ParkingResponseDTO {

    private Long id;

    private String name;

    private String address;

    private BigDecimal hourValue;

    public ParkingResponseDTO(Parking parking) {
        this.id = parking.getId();
        this.name = parking.getName();;
        this.address = parking.getAddress();
        this.hourValue = parking.getHourValue();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getHourValue() {
        return hourValue;
    }
}
