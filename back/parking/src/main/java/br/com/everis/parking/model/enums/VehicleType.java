package br.com.everis.parking.model.enums;

public enum VehicleType {
    MOTOCYCLE(Type.MOTOCYCLE), // passa pelo construtor quando cria os tipos.
    CAR(Type.CAR),
    UTILITY(Type.UTILITY);

    VehicleType(String type) {
        this.type = type;
    }

    private String type;

    public static class Type {
        public static final String MOTOCYCLE = "MOTOCYCLE";
        public static final String CAR = "CAR";
        public static final String UTILITY = "UTILITY";
    }
}
