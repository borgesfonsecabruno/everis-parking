package br.com.everis.parking.dto.response;

import br.com.everis.parking.model.PriceFactor;
import br.com.everis.parking.model.enums.VehicleType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PriceFactorResponseDTO {

    private Long id;

    private VehicleType vehicleType;

    private BigDecimal factor;

    private LocalDate initDate;

    private LocalDate finalDate;

    public PriceFactorResponseDTO(PriceFactor priceFactor) {
        this.id = priceFactor.getId();
        this.vehicleType = priceFactor.getVehicleType();
        this.factor = priceFactor.getFactor();
        this.initDate = priceFactor.getInitDate();
        this.finalDate = priceFactor.getFinalDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }

    public LocalDate getInitDate() {
        return initDate;
    }

    public void setInitDate(LocalDate initDate) {
        this.initDate = initDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }
}
