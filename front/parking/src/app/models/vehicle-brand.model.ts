export class VehicleBrand {
  id: number;
  brand: string;

  constructor(data: any) {
    this.id = data.id;
    this.brand = data.brand;
  }
}
