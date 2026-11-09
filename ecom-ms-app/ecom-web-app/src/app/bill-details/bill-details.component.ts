import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-bill-details',
  templateUrl: './bill-details.component.html',
  styleUrls: ['./bill-details.component.css'],
  standalone: false
})
export class BillDetailsComponent implements OnInit {
  billDetails: any;
  billId!: number;

  constructor(private http: HttpClient, private route: ActivatedRoute) {
    this.billId = route.snapshot.params['billId'];
  }

  ngOnInit(): void {
    this.http.get('http://localhost:8888/fullBill/' + this.billId)
      .subscribe({
        next: (data) => { this.billDetails = data; },
        error: (err) => { console.error(err); }
      });
  }
}
