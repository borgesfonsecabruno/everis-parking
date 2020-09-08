package br.com.everis.parking;

import br.com.everis.parking.model.Vehicle;
import br.com.everis.parking.repository.ParkingTicketRepository;
import br.com.everis.parking.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class, args);
    }
}
