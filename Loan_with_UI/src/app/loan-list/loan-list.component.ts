import { Router } from '@angular/router';
import { HttpService } from './../http.service';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-loan-list',
  templateUrl: './loan-list.component.html',
  styleUrls: ['./loan-list.component.css']
})
export class LoanListComponent implements OnInit {
p:number=1;
  constructor(private service:HttpService,
    private router:Router,
    private title:Title) { }

  LoanLists:any[]=[];

  ngOnInit(): void {
    this.title.setTitle("Loan Details - Loan Application")
    this.GetAllLoans();
  }
  GetAllLoans(){
    this.service.getAllLoans()
    .subscribe((response:any)=>{
      this.LoanLists= response;
    })
  }
  onClick(){
    this.router.navigate(['/Home'])
  }

}
