package br.com.everis.parking;

import br.com.everis.parking.repository.ParkingTicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ParkingTicketRepositoryTest {

    @Autowired
    private ParkingTicketRepository parkingTicketRepository;

    @Test
    public void whenFindOneByVehicleAndDepartureDateTimeIsNullReturnLastCheckIn() {

    }
}
