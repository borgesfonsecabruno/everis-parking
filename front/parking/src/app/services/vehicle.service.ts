import { AppSettings } from './../app-settings';
import { Vehicle } from './../models/vehicle.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private readonly SERVER_URL = AppSettings.SERVER + '/vehicle';

  constructor(private httpClient: HttpClient) { }

  getAllVehiclesByParking(): Observable<Vehicle[]> {

    return this.httpClient
      .get(this.SERVER_URL + '?parkingId=' + AppSettings.PARKING)
      .pipe(
        map((data: any[]) =>
          data.map(
            (item: any) =>
              new Vehicle(item)
          )
        )
      );
  }

  addCar(data: FormGroup): Observable<any> {
    const formObj = data.getRawValue();
    const serializedForm = JSON.stringify(formObj);

    return this.httpClient.post(this.SERVER_URL,
      serializedForm,
      {headers : new HttpHeaders({ 'Content-Type': 'application/json' })});
  }
}
