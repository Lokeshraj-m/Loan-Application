import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';


@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {

  constructor(private router:Router,
    private title:Title) { }

  ngOnInit(): void {
    this.title.setTitle("Loan Application")
  }
  onClick(){
    this.router.navigate(['/Home']);
  }
}
