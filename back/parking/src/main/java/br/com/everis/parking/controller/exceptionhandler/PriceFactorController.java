package br.com.everis.parking.controller.exceptionhandler;

import br.com.everis.parking.model.enums.VehicleType;
import br.com.everis.parking.service.PriceFactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/price-factor")
public class PriceFactorController {

    @Autowired
    PriceFactorService priceFactorService;

    @RequestMapping(method = RequestMethod.GET)
    public BigDecimal factorByVehicle(
            @RequestParam(value = "vehicleType", required = false) VehicleType vehicleType) {
        return this.priceFactorService.findCurrentFactorFor(vehicleType).getFactor();
    }
}
