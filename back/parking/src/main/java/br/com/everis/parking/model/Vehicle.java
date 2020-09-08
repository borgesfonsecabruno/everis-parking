package br.com.everis.parking.model;

import br.com.everis.parking.model.enums.VehicleType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Vehicle {

    @Id
    private String licensePlate;

    @ManyToOne
    private VehicleModel model;

    public Vehicle() {
    }

    public Vehicle(String licensePlate, VehicleModel model) {
        this.licensePlate = licensePlate;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(licensePlate, vehicle.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate);
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleModel getModel() {
        return model;
    }

    public void setModel(VehicleModel model) {
        this.model = model;
    }
}
