import { ToastrService } from 'ngx-toastr';
import { HttpService } from './../http.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-loan-schedule',
  templateUrl: './loan-schedule.component.html',
  styleUrls: ['./loan-schedule.component.css']
})
export class LoanScheduleComponent implements OnInit {

  p:number=1;
  ScheduleList:any[]=[];
  name:String="";
  interestRate:number=0;

  constructor(private router:Router,
    private service:HttpService,
    private route:ActivatedRoute,
    private toastr:ToastrService,
    private title:Title) { }

  ngOnInit(): void {
    this.title.setTitle("Schedule List - Loan Application");
    this.GetScheduleList();
    
  }
  onClick(){
    this.router.navigate(['/LoanList']);
  }
  GetScheduleList(){
    this.route.paramMap
    .subscribe((param:any)=>{
      this.service.getAllScheduleById(param.get("id"))
      .subscribe((response:any)=>{
        this.name=response.customerName;
        this.interestRate=response.interestRate;
        this.ScheduleList=response.paymentSchedule;
        
      })
    })
  }
  onPay(paymentId:any){
    this.service.paymentStatus(paymentId)
    .subscribe((response)=>{
      if(confirm("Do You want to Pay?")){
        this.toastr.success(response, "Successfull");
        location.reload();
      }
      else{
        this.toastr.warning("Payment is Cancelled", "Cancelled")
      }
    })
  }
}
