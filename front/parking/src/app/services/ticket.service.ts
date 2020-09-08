import { Ticket } from './../models/ticket.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private readonly SERVER_URL = 'http://localhost:8080/ticket';

  constructor(private httpClient: HttpClient) { }

  getStatusByCar(vehicleLicense: string): Observable<string> {

    return this.httpClient
      .get(this.SERVER_URL + '/status?byCar=' + vehicleLicense, {responseType: 'text'})
      .pipe(
        map(
          (res: string) => {
            return res;
          }
        )
      );
  }

  getAllByCar(vehicleLicense: string): Observable<Ticket[]> {
    return this.httpClient
      .get(this.SERVER_URL + '?byCar=' + vehicleLicense)
      .pipe(
        map((data: any[]) =>
          data.map(
            (item: any) =>
              new Ticket(item)
          )
        )
      );
  }

  getTotalParking(id: number, departureDateTime: Date): Observable<any> {
    return this.httpClient.get(this.SERVER_URL + '/' + id + '/totalParking?departureDateTime=' + departureDateTime);
  }

  getAll(): Observable<any> {
    return this.httpClient.get(this.SERVER_URL);
  }
}
