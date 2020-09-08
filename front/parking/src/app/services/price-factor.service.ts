import { Ticket } from './../models/ticket.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PriceFactorService {

  private readonly SERVER_URL = 'http://localhost:8080/price-factor';

  constructor(private httpClient: HttpClient) { }


  getPriceFactorFor(vehicleType): Observable<any> {
    return this.httpClient.get(this.SERVER_URL + '?vehicleType=' + vehicleType);
  }
}
