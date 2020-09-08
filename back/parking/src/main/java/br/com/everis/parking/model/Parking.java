package br.com.everis.parking.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Entity
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @NotNull
    private BigDecimal hourValue;

    @OneToMany(mappedBy = "parking")
    private List<ParkingTicket> parkingTickets;

    public Parking() {

    }

    public Parking(String address, BigDecimal hourValue) {
        this.address = address;
        this.hourValue = hourValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ParkingTicket> getParkingTickets() {
        return Collections.unmodifiableList(parkingTickets);
    }

    public void setParkingTickets(List<ParkingTicket> parkingTickets) {
        this.parkingTickets = parkingTickets;
    }

    public void setParkingTickets(ParkingTicket parkingTicket) {
        this.parkingTickets.add(parkingTicket);
    }

    public BigDecimal getHourValue() {
        return hourValue;
    }

    public void setHourValue(BigDecimal hourValue) {
        this.hourValue = hourValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
