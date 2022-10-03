import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { FormCalculatorService } from './form-calculator/form-calculator.service';
import { Stack } from './stacks/stacks.component';
import { StacksService } from './stacks/stacks.service';

export interface StackIdIdentifier {
  readonly id: string
}

export interface Operand extends StackIdIdentifier{
  readonly operand: number
}

export interface Operator extends StackIdIdentifier {
  readonly operator: string
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  public stacks: Array<Stack> = [];
  private ngUnsubscribe = new Subject<void>();

  constructor(private formCalculatorService: FormCalculatorService, private stackService: StacksService) { }
  
  ngOnInit(): void {
    this.getListStack();
  }

  public processOperand(operand: Operand) {
    this.formCalculatorService.addValue(operand)
      .pipe(takeUntil(this.ngUnsubscribe)).subscribe(res => {
        this.updateStack(operand.id, res);
      });
  }

  public getListStack() {
    this.stackService.getStacks()
      .subscribe((res: Array<Stack>) => { this.stacks = res });
  }

  public processOperator(operator: Operator) {
    this.formCalculatorService.applyOperator(operator).pipe(takeUntil(this.ngUnsubscribe)).subscribe(res => {
      this.updateStack(operator.id, res);
    })
  }

  public processRemove(id: string) {
    this.stackService.remove(id)
      .pipe(takeUntil(this.ngUnsubscribe)).subscribe(res => {
        this.stacks = this.stacks.filter(element => element.id !== id);
      })
  }

  public processCreate() {
    this.stackService.create().pipe(takeUntil(this.ngUnsubscribe)).subscribe((res: Stack) => this.stacks.push(res));
  }

  private updateStack(id: String, res: Stack) {
    this.stacks
      .map(stack => {
        if(stack.id === id) {
          stack.stack = res.stack;
        }
        return stack;
      });
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }
}
