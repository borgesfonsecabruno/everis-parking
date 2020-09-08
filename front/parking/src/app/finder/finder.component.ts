import { VehicleModel } from './../models/vehicle-model.model';
import { element } from 'protractor';
import { Component, OnInit, ViewChild, HostListener, Inject, Output, EventEmitter } from '@angular/core';
import { MdbTableDirective } from 'angular-bootstrap-md';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-finder',
  templateUrl: './finder.component.html',
  styleUrls: ['./finder.component.css']
})
export class FinderComponent implements OnInit {
  @ViewChild(MdbTableDirective, {static: true}) mdbTable: MdbTableDirective;
  @Output() newItemEvent = new EventEmitter<number>();
  elements: any = [];
  searchText = '';
  previous: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialogRef: MatDialogRef<FinderComponent>) {
  }

  @HostListener('input') oninput(): void {
      this.searchItems();
  }

  ngOnInit(): void {
      this.elements = this.data;
      this.mdbTable.setDataSource(this.elements);
      this.previous = this.mdbTable.getDataSource();
  }

  searchItems(): void {
      const prev = this.mdbTable.getDataSource();
      if (!this.searchText) {
          this.mdbTable.setDataSource(this.previous);
          this.elements = this.mdbTable.getDataSource();
      }
      if (this.searchText) {
          this.elements = this.mdbTable.searchLocalDataBy(this.searchText);
          this.mdbTable.setDataSource(prev);
      }
  }

  close(): void {
    this.dialogRef.close();
  }

  notifySelected(el): void {
    this.newItemEvent.emit(el.id);
    this.close();
  }
}
