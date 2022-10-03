import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Stack } from './stacks.component';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StacksService {

  private readonly API_URL: String = environment.API_URL;

  constructor(private http: HttpClient) { }

  public getStacks() : Observable<Array<Stack>> { 
      return this.http.get<Array<Stack>>(`${this.API_URL}/api/rpn/stack`);
  }

  public remove(id: String) : Observable<any>{
    return this.http.delete(`${this.API_URL}/api/rpn/stack/${id}`);
  }

  public create() : Observable<Stack> {
      return this.http.post<Stack>(`${this.API_URL}/api/rpn/stack`, {});
  }

 
}