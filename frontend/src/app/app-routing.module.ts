import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {
  path: 'login',
  component: LoginComponent,
  data: {breadcrumb: 'Login'}
},
{
  path: '',
  component: HomeComponent,
  data: {breadcrumb: 'Home'}
},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
