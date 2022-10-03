import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

export interface Stack {
  id: string,
  stack: Array<number>
}

@Component({
  selector: 'app-stacks',
  templateUrl: './stacks.component.html',
  styleUrls: ['./stacks.component.css']
})
export class StacksComponent implements OnInit {

  @Input()
  public stacks: ReadonlyArray<Stack> = [];

  @Output("createStackEvent")
  private createStackEvent: EventEmitter<any> = new EventEmitter<any>();

  @Output("removeStackEvent")
  private removeStackEvent: EventEmitter<string> = new EventEmitter<string>();


  constructor() { }

  ngOnInit(): void {
  }

  public remove(id: string): void {
    this.removeStackEvent.emit(id);
  }

  public createStack() {
    this.createStackEvent.emit(null);
  }
}
