export class Parking {
  id: number;
  name: string;
  address: string;
  hourValue: number;

  constructor(data: any) {
    this.id = data.id;
    this.name = data.name;
    this.address = data.address;
    this.hourValue = data.hourValue;
  }
}
