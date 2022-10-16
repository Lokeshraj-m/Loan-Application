import { HttpService } from './../http.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Title } from '@angular/platform-browser';


@Component({
  selector: 'app-loan-form',
  templateUrl: './loan-form.component.html',
  styleUrls: ['./loan-form.component.css']
})
export class LoanFormComponent implements OnInit {

  constructor(private service:HttpService,
    private router:Router,
    private toastr:ToastrService,
    private title:Title) { 
      this.maxDate.setMonth(this.maxDate.getMonth() + 12 );
    }

  startDate:number[]=[0,1,2,3,4,5,6,7,8,9,10];
  MaturityMonths:any[]=[12,24,36,48,60];
  PaymentFrequency:any[]=[1,3,6,12];
  PaymentTerm:any[]=['Interest Only','Even Principal'];
  Name_Pattern:any='^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,}$';
  minDate=new Date();
  maxDate=new Date();
  maturityDate=new Date();

  ngOnInit(): void {
    this.title.setTitle("Create Loan - Loan Application")
  }

  onSubmit(f:NgForm){
    // converting months into years.
    this.maturityDate.setMonth(f.value.loanStartDate.getMonth()+f.value.maturityMonths);
    // increasing the date plus 1.
    this.maturityDate.setDate(f.value.loanStartDate.getDate()+1);
    this.maturityDate.setHours(0,0,0,0);
    // increasing the date plus 1. 
    f.value.loanStartDate.setDate(f.value.loanStartDate.getDate()+1);

    // created a object as per the backend requirement
    let obj ={
      customerName:f.value.name,
      loanAmount:f.value.loanAmount,
      loanStartDate:f.value.loanStartDate,
      maturityDate:this.maturityDate,
      paymentFrequency:f.value.paymentFrequency,
      interestRate:f.value.interestRate,
      paymentTerm:f.value.paymentTerm
    }
    console.log(obj)
    this.service.createTrade(obj).subscribe((response:any)=>{
      if(confirm("Do You want to create a Loan ?")){
        this.toastr.success(response,"Successfull");
        this.router.navigate(['/Home']);
      }
    })
  }
  onBack(){
    this.router.navigate(['/Home'])
  }

}
