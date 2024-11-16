// what you can do with variables

#include <stdio.h>

int main(){
    // lets take two variables a and b

    int a = 3, b = 2;

    // output variables 

    printf("A is %d and b is %d\n", a, b);

    // %d is a format specifier used for placing variables in output
    // %f for float
    // %c for char
    // \n is a escape sequence to get into next line

    // lets take another variable and place sum of a and b in it.

    int sum = a + b;

    // basic operations on variables
    printf("%d\n", a + b); // addition
    printf("%d\n", a - b); // substraction
    printf("%d\n", a * b); // multiplication
    printf("%d\n", a / b);  // division
    printf("%d\n", a % b); // remainder 

    
    return 0;
}
