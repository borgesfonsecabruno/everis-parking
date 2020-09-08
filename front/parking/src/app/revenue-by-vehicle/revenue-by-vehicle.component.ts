import { Component, OnInit, Inject } from '@angular/core';
import { AppSettings } from '../app-settings';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TicketCarDialogComponent } from '../ticket-car-dialog/ticket-car-dialog.component';
import { ParkingsService } from '../services/parking.service';

@Component({
  selector: 'app-revenue-by-vehicle',
  templateUrl: './revenue-by-vehicle.component.html',
  styleUrls: ['./revenue-by-vehicle.component.css']
})
export class RevenueByVehicleComponent implements OnInit {
  total = 0.0;
  parking = AppSettings.PARKING;
  vehicleType = '';

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<TicketCarDialogComponent>,
              private parkingService: ParkingsService) { }

  ngOnInit(): void {
  }

  calculate(): void {
    this.parkingService.calculateByVehicleRevenue(this.vehicleType).subscribe(
      res => {
        this.total = res.totalRevenue;
      },
      err => {
        alert(err.error.message);
      }
    );
  }

  close(): void {
    this.dialogRef.close();
  }
}
