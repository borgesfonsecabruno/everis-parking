import { VehicleBrand } from './vehicle-brand.model';

export class VehicleModel {
  id: number;
  model: string;
  year: number;
  brand: VehicleBrand;
  type: string;

  constructor(data: any) {
    this.id = data.id;
    this.model = data.model;
    this.year = data.year;
    this.brand = new VehicleBrand(data.brand);
    this.type = data.type;
  }
}
