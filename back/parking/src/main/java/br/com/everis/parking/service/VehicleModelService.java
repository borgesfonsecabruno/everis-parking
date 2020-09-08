package br.com.everis.parking.service;

import br.com.everis.parking.model.VehicleModel;
import br.com.everis.parking.repository.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleModelService {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    public List<VehicleModel> findAll() {
        return this.vehicleModelRepository.findAll();
    }
}
