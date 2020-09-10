import { Ticket } from '../models/ticket.model';
import { TicketCarDialogComponent } from '../ticket-car-dialog/ticket-car-dialog.component';
import { TicketService } from '../services/ticket.service';
import { VehicleService } from '../services/vehicle.service';
import { Vehicle } from '../models/vehicle.model';
import { Component, OnInit, ViewChild, HostListener } from '@angular/core';
import { MdbTableDirective } from 'angular-bootstrap-md';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

@Component({
  selector: 'app-consult-cars',
  templateUrl: './consult-vehicles.component.html',
  styleUrls: ['./consult-vehicles.component.css']
})
export class ConsultVehiclesComponent implements OnInit {
  @ViewChild(MdbTableDirective, {static: true}) mdbTable: MdbTableDirective;
  searchText: '';
  previous: string;
  vehicles: Vehicle[] = [];

  constructor(private dialog: MatDialog, private vehicleService: VehicleService, private ticketService: TicketService) {
  }

  @HostListener('input') oninput(): void {
    this.searchItems();
  }

  ngOnInit(): void {
    this.vehicleService.getAllVehiclesByParking().subscribe(vehicles => {
      vehicles.forEach(element => {
        this.ticketService.getStatusVehicle(element.licensePlate).subscribe(status => {
          element.parkingStatus = status;
        });
        this.vehicles.push(element);
      });
    });

    this.mdbTable.setDataSource(this.vehicles);
    this.previous = this.mdbTable.getDataSource();
  }

  searchItems(): void {
    const prev = this.mdbTable.getDataSource();
    if (!this.searchText) {
        this.mdbTable.setDataSource(this.previous);
        this.vehicles = this.mdbTable.getDataSource();
    }
    if (this.searchText) {
        this.vehicles = this.mdbTable.searchLocalDataBy(this.searchText);
        this.mdbTable.setDataSource(prev);
    }
  }

  async openDialog(vehicle: Vehicle): Promise<void> {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const ticketsData: Ticket[] = [];

    await this.ticketService.getAllByVehicle(vehicle.licensePlate).subscribe(
      data => {
        data.forEach(ticket => {
          ticketsData.push(ticket);
        });
        dialogConfig.data = {tickets: ticketsData, status: vehicle.parkingStatus, licensePlate: vehicle.licensePlate};

        this.dialog.open(TicketCarDialogComponent, dialogConfig);
    });
  }
}
