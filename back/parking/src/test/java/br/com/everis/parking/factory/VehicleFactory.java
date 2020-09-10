package br.com.everis.parking.factory;

import br.com.everis.parking.model.Parking;
import br.com.everis.parking.model.Vehicle;
import br.com.everis.parking.model.VehicleBrand;
import br.com.everis.parking.model.VehicleModel;
import br.com.everis.parking.model.enums.VehicleType;

public class VehicleFactory {

    private Vehicle vehicle;

    public VehicleFactory() {
        this.init();
    }

    public VehicleFactory(String licensePlate) {
        this.init();
        this.vehicle.setLicensePlate(licensePlate);
    }

    public VehicleFactory withType(VehicleType type) {
        this.vehicle.getModel().setType(type);
        return this;
    }

    public VehicleFactory withParking(Long parkingId) {
        this.vehicle.getParking().setId(parkingId);

        return this;
    }

    public Vehicle build() {
        return this.vehicle;
    }

    public void init() {
        this.vehicle = new Vehicle();

        VehicleModel model = new VehicleModel();
        model.setBrand(new VehicleBrand());

        this.vehicle.setModel(model);
        this.vehicle.setParking(new Parking());
    }
}
