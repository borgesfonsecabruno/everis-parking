import { PriceFactorService } from './services/price-factor.service';
import { RevenueByVehicleComponent } from './revenue-by-vehicle/revenue-by-vehicle.component';
import { RevenueAllComponent } from './revenue-all/revenue-all.component';
import { UpdateHourTaxComponent } from './update-hour-tax/update-hour-tax.component';
import { AppSettings } from './app-settings';
import { Component, OnInit } from '@angular/core';
import { ParkingsService } from './services/parking.service';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Estacionamento';
  carFactor = 0.0;
  motocycleFactor = 0.0;
  utilityFactor = 0.0;
  parkingFactor = 0.0;

  constructor(private dialog: MatDialog,
              private parkingService: ParkingsService,
              private priceFactorService: PriceFactorService) { }

  ngOnInit(): void {
    this.priceFactorService.getPriceFactorFor('CAR').subscribe(res => this.carFactor = res);
    this.priceFactorService.getPriceFactorFor('MOTOCYCLE').subscribe(res => this.motocycleFactor = res);
    this.priceFactorService.getPriceFactorFor('UTILITY').subscribe(res => this.utilityFactor = res);
    this.parkingService.getById(AppSettings.PARKING).subscribe(res => this.parkingFactor = res.hourValue);
  }

  updateHourTax(): void {

    const dialogConfig = this.getDialogConfig();

    this.parkingService.getById(AppSettings.PARKING).subscribe(
      parking => {
       dialogConfig.data = parking.hourValue;

       this.dialog.open(UpdateHourTaxComponent, dialogConfig);
    });
  }

  calculateAllRevenue(): void {
    const dialogConfig = this.getDialogConfig();

    this.dialog.open(RevenueAllComponent, dialogConfig);
  }

  calculateByVehicleRevenue(): void {
    const dialogConfig = this.getDialogConfig();

    this.dialog.open(RevenueByVehicleComponent, dialogConfig);
  }

  private getDialogConfig(): MatDialogConfig {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    return dialogConfig;
  }
}
