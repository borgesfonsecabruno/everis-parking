import { Vehicle } from './vehicle.model';
import { Parking } from './parking.model';
import { PriceFactor } from './price-factor.model';


export class Ticket {
  id: number;
  entry: Date;
  departure: Date;
  totalParking: number;
  vehicle: Vehicle;
  priceFactor: PriceFactor;
  parking: Parking;

  constructor(data: any) {
    this.id = data.id;
    this.entry = data.entryDateTime;
    this.departure = data.departureDateTime;
    this.totalParking = data.totalParking;
    this.vehicle = new Vehicle(data.vehicle);
    this.priceFactor = new PriceFactor(data.priceFactor);
    this.parking = new Parking(data.parking);
  }
}
