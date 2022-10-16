import { LandingPageComponent } from './landing-page/landing-page.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoanListComponent } from './loan-list/loan-list.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoanFormComponent } from './loan-form/loan-form.component';
import { LoanScheduleComponent } from './loan-schedule/loan-schedule.component';

const routes: Routes = [
  {
    path:"",redirectTo:"/LandingPage",pathMatch:"full"
  },
  {
    path:"LandingPage",component:LandingPageComponent
  },
  {
    path:"Home",component:HomeComponent
  },
  {
    path:"LoanForm",component:LoanFormComponent
  },
  {
    path:"LoanList",component:LoanListComponent
  },
  {
    path:"LoanSchedule/:id",component:LoanScheduleComponent
  },
  
  {
    path:"**",component:PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
