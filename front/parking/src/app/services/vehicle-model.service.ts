import { AppSettings } from './../app-settings';
import { VehicleModel } from './../models/vehicle-model.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class VehicleModelService {

  private readonly SERVER_URL = AppSettings.SERVER + '/vehicle-model';

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<VehicleModel[]> {
    return this.httpClient
      .get(this.SERVER_URL)
      .pipe(
        map((data: any[]) =>
          data.map(
            (item: any) =>
              new VehicleModel(item)
          )
        )
      );
  }
}
