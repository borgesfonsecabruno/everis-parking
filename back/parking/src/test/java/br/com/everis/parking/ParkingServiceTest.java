package br.com.everis.parking;

import br.com.everis.parking.exceptions.ObjectNotFoundException;
import br.com.everis.parking.exceptions.message.DepartureHasAlreadyBeenRegisteredException;
import br.com.everis.parking.model.Parking;
import br.com.everis.parking.model.ParkingTicket;
import br.com.everis.parking.model.enums.VehicleType;
import br.com.everis.parking.repository.ParkingRepository;
import br.com.everis.parking.service.ParkingService;
import br.com.everis.parking.service.ParkingTicketService;
import br.com.everis.parking.factory.VehicleFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
public class ParkingServiceTest {

    @Autowired
    private ParkingService parkingService;

    @MockBean
    private ParkingTicketService parkingTicketService;

    @MockBean
    private ParkingRepository parkingRepository;

    @Test
    public void whenFindByIdAndIdNotInDatabase_thenThrowObjectNotFoundException() {
        Mockito.when(this.parkingRepository.findById(555L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> this.parkingService.findById(555L))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void whenFindByIdAndIdInDatabase_thenReturnObject() {
        Parking parking = new Parking("Teste", BigDecimal.valueOf(10.1), "Teste");
        parking.setId(1L);

        Mockito.when(this.parkingRepository.findById(1L))
                .thenReturn(Optional.of(parking));

        Assertions.assertThat(this.parkingService.findById(1L))
                .isEqualTo(parking);
    }

    @Test
    public void whenNotCheckInAndTryCheckOut_thenThrowDepartureHasAlreadyBeenRegisteredException() {
        ParkingTicket existentTicket = new ParkingTicket();
        existentTicket.setId(1L);
        existentTicket.setDepartureDateTime(LocalDateTime.parse("2020-09-10T12:00:00"));

        Mockito.when(this.parkingTicketService.findById(1L))
                .thenReturn(existentTicket);

        ParkingTicket toUpdateTicket = new ParkingTicket();
        toUpdateTicket.setId(1L);
        toUpdateTicket.setDepartureDateTime(LocalDateTime.parse("2020-09-10T15:00:00"));

        Assertions.assertThatThrownBy(() -> this.parkingService.checkOut(toUpdateTicket))
                .isInstanceOf(DepartureHasAlreadyBeenRegisteredException.class);
    }

    @Test
    public void whenCheckInAndTryCheckoutWithDepartureLesserThanArrival_thenIllegalArgumentException() {
        ParkingTicket existentTicket = new ParkingTicket(
                null,
                LocalDateTime.parse("2020-09-10T11:00:00"),
                null,
                null);
        existentTicket.setId(1L);

        Mockito.when(this.parkingTicketService.findById(1L))
                .thenReturn(existentTicket);

        ParkingTicket toUpdateTicket = new ParkingTicket();
        toUpdateTicket.setId(1L);
        toUpdateTicket.setDepartureDateTime(LocalDateTime.parse("2020-09-10T10:00:00"));

        Assertions.assertThatThrownBy(() -> this.parkingService.checkOut(toUpdateTicket))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenCalculateAllRevenues_thenReturnCorrectValue() {
        ParkingTicket parkingTicket1 = new ParkingTicket();
        parkingTicket1.setTotalParking(BigDecimal.valueOf(100));

        ParkingTicket parkingTicket2 = new ParkingTicket();
        parkingTicket2.setTotalParking(BigDecimal.valueOf(10));

        ParkingTicket parkingTicket3 = new ParkingTicket();
        parkingTicket3.setTotalParking(BigDecimal.valueOf(34));

        Mockito.when(this.parkingTicketService.findAllByParking(1L))
                .thenReturn(Arrays.asList(parkingTicket1, parkingTicket2, parkingTicket3));

        Assertions.assertThat(this.parkingService.calculateRevenues(1L, null))
                .isEqualTo(BigDecimal.valueOf(144));
    }

    @Test
    public void whenCalculateRevenueByVehicle_thenReturnCorrectValue() {
        ParkingTicket parkingTicket1 = new ParkingTicket();
        parkingTicket1.setVehicle(new VehicleFactory().withType(VehicleType.CAR).build());
        parkingTicket1.setTotalParking(BigDecimal.valueOf(100));

        ParkingTicket parkingTicket2 = new ParkingTicket();
        parkingTicket2.setVehicle(new VehicleFactory().withType(VehicleType.MOTOCYCLE).build());
        parkingTicket2.setTotalParking(BigDecimal.valueOf(10));

        ParkingTicket parkingTicket3 = new ParkingTicket();
        parkingTicket3.setVehicle(new VehicleFactory().withType(VehicleType.CAR).build());
        parkingTicket3.setTotalParking(BigDecimal.valueOf(30));

        Mockito.when(this.parkingTicketService.findAllByParking(1L))
                .thenReturn(Arrays.asList(parkingTicket1, parkingTicket2, parkingTicket3));

        Assertions.assertThat(this.parkingService.calculateRevenues(1L, VehicleType.CAR))
                .isEqualTo(BigDecimal.valueOf(130));
    }

    @Test
    public void whenCalculateRevenueWithoutTicket_thenReturnZero() {
        Mockito.when(this.parkingTicketService.findAllByParking(1L))
                .thenReturn(new ArrayList<>());

        Assertions.assertThat(this.parkingService.calculateRevenues(1L, null))
                .isEqualTo(BigDecimal.ZERO);
    }
}
