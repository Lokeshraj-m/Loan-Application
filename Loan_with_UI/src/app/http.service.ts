import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http:HttpClient) { }

  createTrade(obj:any){
    return this.http.post(`${environment.baseUrl}create`,obj,{
      responseType:"text"
    }) 
  }
  getAllLoans(){
    return this.http.get(`${environment.baseUrl}lists`) 
  }
  getAllScheduleById(id:number){
    return this.http.get(`${environment.baseUrl}list/${id}`)
  }
  paymentStatus(paymentId:number){
    return this.http.put(`${environment.baseUrl}PaymentStatus/${paymentId}`,null,{
      responseType:"text"
    })
  }
}
