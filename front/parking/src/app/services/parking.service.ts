import { AppSettings } from './../app-settings';
import { Observable } from 'rxjs';
import { Routes, RouterModule, Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ParkingsService {

  private readonly SERVER_URL = AppSettings.SERVER + '/parking';

  constructor(private httpClient: HttpClient, private router: Router) { }

  checkIn(data: FormGroup): Observable<any> {
    const formObj = data.getRawValue();
    const serializedForm = JSON.stringify(formObj);

    return this.httpClient.post(this.SERVER_URL + '/checkin',
      serializedForm,
      {headers : new HttpHeaders({ 'Content-Type': 'application/json' })});
  }

  checkOut(data: FormGroup): Observable<any> {
    const formObj = data.getRawValue();
    const serializedForm = JSON.stringify(formObj);

    return this.httpClient.put(this.SERVER_URL + '/checkout',
    serializedForm,
    {headers : new HttpHeaders({ 'Content-Type': 'application/json' })});
  }

  getById(id): Observable<any> {
    return this.httpClient.get(this.SERVER_URL + '/' + id);
  }

  update(data: FormGroup): Observable<any> {
    const formObj = data.getRawValue();
    const serializedForm = JSON.stringify(formObj);

    return this.httpClient.put(this.SERVER_URL + '/' + AppSettings.PARKING,
    serializedForm,
    {headers : new HttpHeaders({ 'Content-Type': 'application/json' })});
  }

  calculateAllRevenue(): Observable<any> {
    return this.httpClient.get(this.SERVER_URL + '/' + AppSettings.PARKING + '/revenue');
  }

  calculateByVehicleRevenue(vehicleType): Observable<any> {
    return this.httpClient.get(this.SERVER_URL + '/' + AppSettings.PARKING + '/revenue?vehicleType=' + vehicleType);
  }
}
