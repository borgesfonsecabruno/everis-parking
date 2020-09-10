import { AppSettings } from './../app-settings';
import { Ticket } from './../models/ticket.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private readonly SERVER_URL = AppSettings.SERVER + '/ticket';

  constructor(private httpClient: HttpClient) { }

  getStatusVehicle(licensePlate: string): Observable<string> {

    return this.httpClient
      .get(this.SERVER_URL + '/status?licensePlate=' + licensePlate + '&parkingId=' + AppSettings.PARKING, {responseType: 'text'})
      .pipe(
        map(
          (res: string) => {
            return res;
          }
        )
      );
  }

  getAllByVehicle(licensePlate: string): Observable<Ticket[]> {
    return this.httpClient
      .get(this.SERVER_URL + '?licensePlate=' + licensePlate + '&parkingId=' + AppSettings.PARKING)
      .pipe(
        map((data: any[]) =>
          data.map(
            (item: any) =>
              new Ticket(item)
          )
        )
      );
  }

  getTotalParking(ticketId: number, departureDateTime: Date): Observable<any> {
    return this.httpClient.get(this.SERVER_URL + '/' + ticketId + '/totalParking?departureDateTime=' + departureDateTime);
  }

  getAllByParking(): Observable<any> {
    return this.httpClient.get(this.SERVER_URL + '?parkingId=' + AppSettings.PARKING);
  }
}
