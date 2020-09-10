package br.com.everis.parking;

import br.com.everis.parking.exceptions.ObjectAlreadyExists;
import br.com.everis.parking.exceptions.ObjectNotFoundException;
import br.com.everis.parking.factory.VehicleFactory;
import br.com.everis.parking.model.Parking;
import br.com.everis.parking.model.ParkingTicket;
import br.com.everis.parking.model.PriceFactor;
import br.com.everis.parking.model.Vehicle;
import br.com.everis.parking.model.enums.VehicleType;
import br.com.everis.parking.repository.ParkingRepository;
import br.com.everis.parking.repository.ParkingTicketRepository;
import br.com.everis.parking.repository.PriceFactorRepository;
import br.com.everis.parking.repository.VehicleRepository;
import br.com.everis.parking.service.ParkingTicketService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
public class ParkingTicketServiceTest {

    @Autowired
    private ParkingTicketService parkingTicketService;

    @MockBean
    private ParkingTicketRepository parkingTicketRepository;

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private PriceFactorRepository priceFactorRepository;

    @MockBean
    private ParkingRepository parkingRepository;

    @Test
    public void whenIdNotInDatabase_thenObjectNotFoundException() {
        Mockito.when(this.parkingTicketRepository.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> this.parkingTicketService.findById(1L))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void whenSaveWithPreviousCheckInWithoutDeparture_thenObjectAlreadyExistsException() {
        Parking parking = new Parking();
        parking.setId(1L);

        Vehicle vehicleReturned = new VehicleFactory("123-ABC")
                .withType(VehicleType.CAR)
                .withParking(1L)
                .build();

        // Fator de preço para carro existe? Sim, é um PriceFactor para carro com valor 10.6
        Mockito.when(this.priceFactorRepository.findOneByVehicleTypeAndFinalDateIsNull(VehicleType.CAR))
                .thenReturn(Optional.of(new PriceFactor()));

        // Estacionamento 1 existe? Sim, é o estacionamento da variável parking
        Mockito.when(this.parkingRepository.findById(1L))
                .thenReturn(Optional.of(parking));

        // Veículo "123-ABC" do estacionamento 1 existe? Sim, é o veículo da variável vehicleReturn
        Mockito.when(this.vehicleRepository.findOneByLicensePlateAndParking("123-ABC", parking))
                .thenReturn(Optional.of(vehicleReturned));

        // Ticket para o veículo existe? Sim
        Mockito.when(this.parkingTicketRepository.findOneByVehicleAndDepartureDateTimeIsNull(vehicleReturned))
                .thenReturn(Optional.of(new ParkingTicket()));

        Assertions.assertThatThrownBy(() -> this.parkingTicketService.save(new ParkingTicket(vehicleReturned, null, null, parking)))
                .isInstanceOf(ObjectAlreadyExists.class);
    }

    @Test
    public void whenSaveAndPriceFactorNotInDatabase_thenObjectNotFoundException() {
        Mockito.when(this.priceFactorRepository.findOneByVehicleTypeAndFinalDateIsNull(VehicleType.CAR))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(
                () -> this.parkingTicketService.save(
                        new ParkingTicket(
                                new VehicleFactory().withType(VehicleType.CAR).build(),
                                null,
                                null,
                                null
                        )
                )
        ).isInstanceOf(ObjectNotFoundException.class);
    }
}
