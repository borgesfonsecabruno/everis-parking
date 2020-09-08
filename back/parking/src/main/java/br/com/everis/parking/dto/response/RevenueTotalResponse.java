package br.com.everis.parking.dto.response;

import java.math.BigDecimal;

public class RevenueTotalResponse {

    private BigDecimal totalRevenue;

    public RevenueTotalResponse(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
}
