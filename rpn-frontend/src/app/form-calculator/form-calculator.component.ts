import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Operand, Operator } from '../app.component';
import { OperatorValidator } from '../shared/operator.validator';


@Component({
  selector: 'app-form-calculator',
  templateUrl: './form-calculator.component.html',
  styleUrls: ['./form-calculator.component.css']
})
export class FormCalculatorComponent implements OnInit {

  @Output("operandEntred")
  private operandEntred: EventEmitter<Operand> = new EventEmitter<Operand>();

  @Output("operatorEntred")
  private operatorEntred: EventEmitter<Operator> = new EventEmitter<Operator>();

  
  formCalculator: FormGroup = this.fb.group({
    stackId: ['', Validators.required],
    operand: [''],
    operator: ['', OperatorValidator]
  });

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {

  }

  public addOperand(): void {
    if (this.formCalculator.get('stackId')?.valid) {
      this.operandEntred.emit({ id: this.formCalculator.get('stackId')?.value, operand: this.formCalculator.get('operand')?.value });
    }
  }

  public applyOperator(): void {
    if (this.formCalculator.valid) {
      this.operatorEntred.emit({ id: this.formCalculator.get('stackId')?.value, operator: this.formCalculator.get('operator')?.value });
    }
  }

}
