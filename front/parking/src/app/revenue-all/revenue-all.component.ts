import { AppSettings } from './../app-settings';
import { ParkingsService } from './../services/parking.service';
import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TicketCarDialogComponent } from '../ticket-car-dialog/ticket-car-dialog.component';

@Component({
  selector: 'app-revenue-all',
  templateUrl: './revenue-all.component.html',
  styleUrls: ['./revenue-all.component.css']
})
export class RevenueAllComponent implements OnInit {
  total = 0.0;
  parking = AppSettings.PARKING;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<TicketCarDialogComponent>,
              private parkingService: ParkingsService) { }

  ngOnInit(): void {
  }

  calculate(): void {
    this.parkingService.calculateAllRevenue().subscribe(
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
