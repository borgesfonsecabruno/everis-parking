package br.com.everis.parking.dto.entry;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ParkingTicketCheckInDTO {

    private LocalDateTime entryDateTime;

    @NotNull
    private String vehicle_license;

    @NotNull
    private Long parking_id;

    public ParkingTicketCheckInDTO(LocalDateTime entryDateTime, String vehicle_license, Long priceFactor_id, Long parking_id) {
        this.entryDateTime = entryDateTime;
        this.vehicle_license= vehicle_license;
        this.parking_id = parking_id;
    }

    public LocalDateTime getEntryDateTime() {
        return entryDateTime;
    }

    public void setEntryDateTime(LocalDateTime entryDateTime) {
        this.entryDateTime = entryDateTime;
    }

    public String getVehicle_license() {
        return vehicle_license;
    }

    public void setVehicle_license(String vehicle_license) {
        this.vehicle_license = vehicle_license;
    }

    public Long getParking_id() {
        return parking_id;
    }

    public void setParking_id(Long parking_id) {
        this.parking_id = parking_id;
    }
}
