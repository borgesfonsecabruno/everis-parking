import { VehicleModel } from './vehicle-model.model';
export class Vehicle {
  licensePlate: string;
  model: VehicleModel;
  parkingStatus: string;

  constructor(data: any) {
    this.licensePlate = data.licensePlate;
    this.model = new VehicleModel(data.model);
  }
}
