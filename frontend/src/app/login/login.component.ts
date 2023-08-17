import { Component } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  user: string = "";
  password: string = "";
  newuser: string = "";
  newpassword: string = "";
  repeatPassword: string = "";
  email: string = "";

  constructor(private router: Router, private http: HttpClient) {
  }
  login() {

    const params = new HttpParams()
      .set('email', this.user)
      .set('password', this.password);

    this.http.post("http://localhost:8080/signin", null, { params, responseType: 'text' }).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("Dang nhap thanh cong");
        this.router.navigateByUrl('/home');
      },
      (error: any) => {
        console.error(error);
        alert("Dang nhap that bai");
      }
    );
  }
  save() {
    if (this.newpassword !== this.repeatPassword) {
      alert("Passwords do not match. Please try again.");
      return;
    }
    
    const params = new HttpParams()
      .set('username', this.newuser)
      .set('email', this.email)
      .set('password', this.newpassword);
  

    this.http.post("http://localhost:8080/signup", null, { params,responseType: 'text' }).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("Registered Successfully");
      },
      (error: any) => {
        console.error(error);
        alert("Registration Failed");
      }
    );
  }


}


