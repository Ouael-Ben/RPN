import { AbstractControl, ValidationErrors } from "@angular/forms";

const operators = ["+", "/", "-", "*"];

/* OperatorValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if(!operators.includes(control.value))
        return {operator: true};

     return null;   
    };

} */

export const OperatorValidator : ValidationErrors | null = (control: AbstractControl) => {
        if(!operators.includes(control.value))
        return {operator: true};

    return null;   
} 