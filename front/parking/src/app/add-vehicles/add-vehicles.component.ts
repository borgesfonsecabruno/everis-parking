import { AppSettings } from './../app-settings';
import { VehicleService } from './../services/vehicle.service';
import { FinderComponent } from './../finder/finder.component';
import { VehicleModelService } from './../services/vehicle-model.service';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { VehicleModel } from '../models/vehicle-model.model';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-add-cars',
  templateUrl: './add-vehicles.component.html',
  styleUrls: ['./add-vehicles.component.css']
})
export class AddVehiclesComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder,
              private vehicleModelService: VehicleModelService,
              private vehicleService: VehicleService,
              private dialog: MatDialog) { }

  ngOnInit(): void {
    this.createForm();
  }

  async openDialog(): Promise<void> {

    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const vehicleModelData: VehicleModel[] = [];

    this.vehicleModelService.getAll().subscribe(
      data => {
        data.forEach(vehicle => {
          vehicleModelData.push(vehicle);
        });
        dialogConfig.data = vehicleModelData;

        const dialog = this.dialog.open(FinderComponent, dialogConfig);
        const subscribe = dialog.componentInstance.newItemEvent.subscribe((value) => {
          this.form.patchValue({vehicleModelId: value});
        });
    });
  }

  submit(form): void {
    this.vehicleService.addCar(form).subscribe(
      res => {
        alert('Veículo inserido com sucesso');
        this.createForm();
      },
      err => {
        alert(err.error.message);
      }
    );
  }

  createForm(): void {
    this.form = this.fb.group({
      licensePlate: new FormControl('', [Validators.required]),
      vehicleModelId: new FormControl(null, [Validators.required]),
      parkingId: new FormControl(AppSettings.PARKING, [Validators.required])
    });
  }

  invalidRequired(name): boolean {
    return this.form.controls[name].invalid
    && (this.form.controls[name].dirty || this.form.controls[name].touched)
    && this.form.controls[name].errors.required;
  }
}
