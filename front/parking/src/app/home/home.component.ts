import { Ticket } from './../models/ticket.model';
import { TicketService } from './../services/ticket.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  tickets: Ticket[] = []
  constructor(private ticketService: TicketService) { }

  ngOnInit(): void {
    this.ticketService.getAll().subscribe(
      res => {
        res.forEach(element => {
          this.tickets.push(new Ticket(element));
        });
      },
      err => {
        alert(err.error.message);
      });

  }

}
