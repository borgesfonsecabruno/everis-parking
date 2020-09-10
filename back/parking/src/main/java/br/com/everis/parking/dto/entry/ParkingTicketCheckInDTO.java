package br.com.everis.parking.dto.entry;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ParkingTicketCheckInDTO {

    private LocalDateTime entryDateTime;

    @NotNull
    private String vehicleLicense;

    @NotNull
    private Long parkingId;

    public ParkingTicketCheckInDTO(LocalDateTime entryDateTime, String vehicleLicense, Long priceFactor_id, Long parkingId) {
        this.entryDateTime = entryDateTime;
        this.vehicleLicense = vehicleLicense;
        this.parkingId = parkingId;
    }

    public LocalDateTime getEntryDateTime() {
        return entryDateTime;
    }

    public void setEntryDateTime(LocalDateTime entryDateTime) {
        this.entryDateTime = entryDateTime;
    }

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }
}
