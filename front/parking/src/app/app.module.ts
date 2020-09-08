import { AddVehiclesComponent } from './add-vehicles/add-vehicles.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { TicketCarDialogComponent } from './ticket-car-dialog/ticket-car-dialog.component';
import { FinderComponent } from './finder/finder.component';
import { ConsultVehiclesComponent } from './consult-vehicles/consult-vehicles.component';
import { UpdateHourTaxComponent } from './update-hour-tax/update-hour-tax.component';
import { HomeComponent } from './home/home.component';
import { RevenueAllComponent } from './revenue-all/revenue-all.component';
import { RevenueByVehicleComponent } from './revenue-by-vehicle/revenue-by-vehicle.component';

@NgModule({
  declarations: [
    AppComponent,
    ConsultVehiclesComponent,
    TicketCarDialogComponent,
    AddVehiclesComponent,
    FinderComponent,
    UpdateHourTaxComponent,
    HomeComponent,
    RevenueAllComponent,
    RevenueByVehicleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MDBBootstrapModule.forRoot(),
    FormsModule,
    HttpClientModule,
    MatDialogModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [
    TicketCarDialogComponent,
    FinderComponent,
    UpdateHourTaxComponent,
    RevenueAllComponent,
    RevenueByVehicleComponent
  ]
})
export class AppModule { }
