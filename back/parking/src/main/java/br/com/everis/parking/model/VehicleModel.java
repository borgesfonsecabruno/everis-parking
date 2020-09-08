package br.com.everis.parking.model;

import br.com.everis.parking.model.enums.VehicleType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    private Long year;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @NotNull
    @ManyToOne
    private VehicleBrand brand;

    public VehicleModel(){
    }

    public VehicleModel(String model, Long year, VehicleBrand brand) {
        this.model = model;
        this.year = year;
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleModel that = (VehicleModel) o;
        return Objects.equals(model, that.model) &&
                Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, year);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public VehicleBrand getBrand() {
        return brand;
    }

    public void setBrand(VehicleBrand brand) {
        this.brand = brand;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }
}
