import { HomeComponent } from './home/home.component';
import { AddVehiclesComponent } from './add-vehicles/add-vehicles.component';
import { ConsultVehiclesComponent } from './consult-vehicles/consult-vehicles.component';

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: 'home', component: HomeComponent },
  { path: 'consultar-veiculos', component: ConsultVehiclesComponent },
  { path: 'adicionar-veiculos', component: AddVehiclesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
