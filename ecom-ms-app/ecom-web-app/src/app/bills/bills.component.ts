import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.css'],
  standalone: false
})
export class BillsComponent implements OnInit {
  bills: any;
  customerId!: number;

  constructor(private cdr: ChangeDetectorRef, private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.customerId = route.snapshot.params['customerId'];
  }

  ngOnInit(): void {
    this.http.get('http://localhost:8888/bills/search/byCustomerId?customerId=' + this.customerId)
      .subscribe({
        next: (data) => { this.bills = data; this.cdr.markForCheck(); },
        error: (err) => { console.error(err); }
      });
  }

  getBillDetails(b: any) {
    this.router.navigateByUrl('/bill-details/' + b.id);
  }
}
