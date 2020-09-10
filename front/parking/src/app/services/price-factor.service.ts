import { AppSettings } from './../app-settings';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PriceFactorService {

  private readonly SERVER_URL = AppSettings.SERVER + '/price-factor';

  constructor(private httpClient: HttpClient) { }


  getPriceFactorFor(vehicleType): Observable<any> {
    return this.httpClient.get(this.SERVER_URL + '?vehicleType=' + vehicleType);
  }
}
