import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css'],
  standalone: false
})
export class CustomersComponent implements OnInit {
  customers: any;

  constructor(private cdr: ChangeDetectorRef, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.http.get('http://localhost:8888/customers')
      .subscribe({
        next: (data) => { this.customers = data; this.cdr.markForCheck(); },
        error: (err) => { console.error(err); }
      });
  }

  getBills(c: any) {
    this.router.navigateByUrl('/bills/' + c.id);
  }
}
