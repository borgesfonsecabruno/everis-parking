export class PriceFactor {
  id: number;
  vehicleType: string;
  factor: number;
  initDate: Date;
  finalDate: Date;

  constructor(data: any) {
    this.id = data.id;
    this.vehicleType = data.vehicleType;
    this.factor = data.factor;
    this.initDate = data.initDate;
    this.finalDate = data.finalDate;
  }
}
