package br.com.everis.parking.service;

import br.com.everis.parking.exceptions.ObjectNotFoundException;
import br.com.everis.parking.model.PriceFactor;
import br.com.everis.parking.model.enums.VehicleType;
import br.com.everis.parking.repository.PriceFactorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceFactorService {

    @Autowired
    private PriceFactorRepository priceFactorRepository;

    public PriceFactor findCurrentFactorFor(VehicleType type) {
        return priceFactorRepository
                .findOneByVehicleTypeAndFinalDateIsNull(type)
                .orElseThrow(() -> new ObjectNotFoundException(type.name(), "fator de preço"));
    }
}
