package br.com.everis.parking.model;

import br.com.everis.parking.exceptions.DepartureNotYetRegisteredException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class ParkingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime entryDateTime;

    private LocalDateTime departureDateTime;

    private BigDecimal totalParking;

    @NotNull
    @ManyToOne
    private Vehicle vehicle;

    @NotNull
    @ManyToOne
    private PriceFactor priceFactor;

    @NotNull
    @ManyToOne
    private Parking parking;

    public ParkingTicket() {
    }

    public ParkingTicket(Vehicle vehicle, LocalDateTime entry, PriceFactor priceFactor, Parking parking) {
        this.vehicle = vehicle;
        this.totalParking = BigDecimal.ZERO;
        this.entryDateTime = entry == null ? LocalDateTime.now() : entry;
        this.priceFactor = priceFactor;
        this.parking = parking;
    }

    public BigDecimal calculateTotalParking(BigDecimal hourPrice) {
        if(this.departureDateTime == null)
            throw new DepartureNotYetRegisteredException(this);

        BigDecimal totalHoursInPark = BigDecimal.valueOf(Duration.between(this.entryDateTime, this.departureDateTime).toHours());
        BigDecimal factor = this.priceFactor.getFactor();

        if(totalHoursInPark.equals(BigDecimal.ZERO))
            totalHoursInPark = BigDecimal.ONE;

        this.totalParking = totalHoursInPark
                .multiply(hourPrice)
                .multiply(factor);

        return this.totalParking;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getEntryDateTime() {
        return entryDateTime;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public BigDecimal getTotalParking() {
        return totalParking;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public PriceFactor getPriceFactor() {
        return priceFactor;
    }

    public void setPriceFactor(PriceFactor priceFactor) {
        this.priceFactor = priceFactor;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}
