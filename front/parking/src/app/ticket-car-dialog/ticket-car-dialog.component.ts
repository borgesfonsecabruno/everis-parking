import { TicketService } from './../services/ticket.service';
import { ParkingsService } from './../services/parking.service';
import { Component, OnInit, Inject, ViewChild, HostListener } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MdbTableDirective } from 'angular-bootstrap-md';
import { Ticket } from '../models/ticket.model';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { AppSettings } from '../app-settings';

@Component({
  selector: 'app-ticket-car-dialog',
  templateUrl: './ticket-car-dialog.component.html',
  styleUrls: ['./ticket-car-dialog.component.css']
})
export class TicketCarDialogComponent implements OnInit {
  @ViewChild(MdbTableDirective, {static: true}) mdbTable: MdbTableDirective;
  searchText = '';
  previous: string;
  tickets: Ticket[] = [];
  status: string;
  licensePlate: string;
  checkOutForm: FormGroup;
  checkInForm: FormGroup;
  checkoutValue = 0.0;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              private fb: FormBuilder,
              public dialogRef: MatDialogRef<TicketCarDialogComponent>,
              private parkingService: ParkingsService,
              private ticketService: TicketService) { }

  ngOnInit(): void {
    this.tickets = this.data.tickets;
    this.status = this.data.status;
    this.licensePlate = this.data.licensePlate;
    this.mdbTable.setDataSource(this.tickets);
    this.previous = this.mdbTable.getDataSource();

    let ticketCheckOut: Ticket;
    this.data.tickets.forEach(ticket => {
      if (ticket.departure == null) {
        ticketCheckOut = ticket;
      }
    });

    const currentTime = new Date().toISOString().slice(0, 23);
    this.checkInForm = this.fb.group({
      entryDateTime: new FormControl(currentTime),
      vehicle_license: new FormControl(this.licensePlate),
      parking_id: new FormControl(AppSettings.PARKING)
    });

    this.checkOutForm = this.fb.group({
      departureDateTime: new FormControl(currentTime),
      ticket_id: new FormControl(ticketCheckOut ? ticketCheckOut.id : null)
    });

    if (ticketCheckOut) {
      this.setTicketTotal(ticketCheckOut.id, this.checkOutForm.get('departureDateTime').value);
    }
  }

  @HostListener('input') oninput(): void {
    this.searchItems();
  }

  close(): void {
    this.dialogRef.close();
  }

  searchItems(): void {
    const prev = this.mdbTable.getDataSource();
    if (!this.searchText) {
        this.mdbTable.setDataSource(this.previous);
        this.tickets = this.mdbTable.getDataSource();
    }
    if (this.searchText) {
        this.tickets = this.mdbTable.searchLocalDataBy(this.searchText);
        this.mdbTable.setDataSource(prev);
    }
  }

  submitForm(): void {
    if (this.status === 'out') {
      this.checkInForm.patchValue({
        license_plate: this.licensePlate
      });

      this.parkingService.checkIn(this.checkInForm).subscribe(
        res => {
          alert('Check in efetuado com sucesso!');
          location.reload();
        },
        err => {
          alert(err.error.message);
        }
      );
    } else {
      this.parkingService.checkOut(this.checkOutForm).subscribe(
        res => {
          alert('Check out efetuado com sucesso!');
          location.reload();
        },
        err => {
          alert(err.error.message);
        }
      );
    }
  }

  setTicketTotal(id: number, departureDateTime: Date): void {
    this.ticketService.getTotalParking(id, departureDateTime).subscribe(
      data => {
        this.checkoutValue = data;
      },
      err => {
        alert(err.error.message);
      }
    );
  }
}
