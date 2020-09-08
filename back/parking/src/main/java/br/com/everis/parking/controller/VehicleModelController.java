package br.com.everis.parking.controller;

import br.com.everis.parking.model.VehicleModel;
import br.com.everis.parking.service.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("vehicle-model")
public class VehicleModelController {

    @Autowired
    private VehicleModelService vehicleModelService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<VehicleModel>> findAll() {
        List<VehicleModel> vehicleModels = vehicleModelService.findAll();

        return ResponseEntity.ok(vehicleModels);
    }
}
