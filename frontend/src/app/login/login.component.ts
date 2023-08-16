import { Component } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  user: string = "";
  password: string = "";
  constructor(private http: HttpClient) {
  }
  save() {
    
    const params = new HttpParams()
      .set('email', this.user)
      .set('password', this.password);
  
    this.http.post("http://localhost:8080/signin", null, { params, responseType: 'text' }).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("Dang nhap thanh cong");
      },
      (error: any) => {
        console.error(error);
        alert("Dang nhap that bai");
      }
    );
  }
}
