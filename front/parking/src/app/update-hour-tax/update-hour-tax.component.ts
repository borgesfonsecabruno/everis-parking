import { ParkingsService } from './../services/parking.service';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit, Inject, Output, EventEmitter } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TicketCarDialogComponent } from '../ticket-car-dialog/ticket-car-dialog.component';

@Component({
  selector: 'app-update-hour-tax',
  templateUrl: './update-hour-tax.component.html',
  styleUrls: ['./update-hour-tax.component.css']
})
export class UpdateHourTaxComponent implements OnInit {
  @Output() updateHourTaxEvent = new EventEmitter<void>();

  currentHourValue = 0.0;
  form: FormGroup;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              private fb: FormBuilder,
              public dialogRef: MatDialogRef<TicketCarDialogComponent>,
              private parkingService: ParkingsService) { }

  ngOnInit(): void {
    this.currentHourValue = this.data;
    this.createForm();
  }

  createForm(): void {
    this.form = this.fb.group({
      hourValue: new FormControl(null, [Validators.required])
    });
  }

  close(): void {
    this.dialogRef.close();
  }

  invalidRequired(name): boolean {
    return this.form.controls[name].invalid
    && (this.form.controls[name].dirty || this.form.controls[name].touched)
    && this.form.controls[name].errors.required;
  }

  submit(form): void {
    this.parkingService.update(form).subscribe(
      res => {
        alert('Taxa atualizada com sucesso!');
        this.updateHourTaxEvent.emit();
        this.close();
      },
      err => {
        console.log(err.error.message);
      }
    );
  }
}
