package br.com.everis.parking.dto.update;

import java.math.BigDecimal;

public class ParkingUpdateDTO {

    private BigDecimal hourValue;

    private String address;

    private String name;

    public ParkingUpdateDTO(BigDecimal hourValue, String name, String address) {
        this.hourValue = hourValue;
        this.address = address;
        this.name = name;
    }

    public BigDecimal getHourValue() {
        return hourValue;
    }

    public void setHourValue(BigDecimal hourValue) {
        this.hourValue = hourValue;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
