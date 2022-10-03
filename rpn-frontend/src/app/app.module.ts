import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FormCalculatorComponent } from './form-calculator/form-calculator.component';
import { StacksComponent } from './stacks/stacks.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StacksService } from './stacks/stacks.service';
import { HttpClientModule } from '@angular/common/http';
import { FormCalculatorService } from './form-calculator/form-calculator.service';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    FormCalculatorComponent,
    StacksComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule, HttpClientModule, CommonModule, FormsModule
  ],
  providers: [StacksService, FormCalculatorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
