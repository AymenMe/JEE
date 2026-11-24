import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css'],
  standalone: false
})
export class ProductsComponent implements OnInit {
  products: any;

  constructor(private cdr: ChangeDetectorRef, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get('http://localhost:8888/products')
      .subscribe({
        next: (data) => { this.products = data; this.cdr.markForCheck(); },
        error: (err) => { console.error(err); }
      });
  }
}
