import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Operand, Operator } from '../app.component';
import { Stack } from '../stacks/stacks.component';

@Injectable({
  providedIn: 'root'
})
export class FormCalculatorService {

  private readonly API_URL: String = environment.API_URL;

  constructor(private http: HttpClient) { }

  public addValue(operand : Operand): Observable<Stack> {
    return this.http.post<Stack>(`${this.API_URL}/api/rpn/stack/${operand.id}`, { value: operand.operand });
  }

  public applyOperator(operator: Operator): Observable<Stack> {
    return this.http.post<Stack>(`${this.API_URL}/api/rpn/op/stack/${operator.id}`, {operator: operator.operator});
  }

}
