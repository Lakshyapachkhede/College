// program to make a simple calculator in c
#include <stdio.h>

int main(){
    // lets take three integer variables to hold two numbers and their result

    int a, b, result;

    // and take a character variable to input opertion from user (+, -, *, /)

    char op;

    printf("Enter first number: ");
    scanf("%d", &a);

    printf("Enter second number: ");
    scanf("%d", &b);

    printf("Enter operation (+, -, *, /): ");
    scanf(" %c", &op); 


    
    if (op == '+') // checking if user enters the + sign
    {
        result = a + b;
    }
    else if (op == '-') // checking if users enters the - sign
    {
        result = a - b;
    }
    else if (op == '*') 
    { 
        result = a * b;
    }
    else 
    {
        result = a / b;
    }

    printf("The result is %d", result);
    
    return 0;
}
